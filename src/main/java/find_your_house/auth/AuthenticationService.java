package find_your_house.auth;

import find_your_house.email.EmailService;
import find_your_house.email.EmailTemplateName;
import find_your_house.role.RoleRepository;
import find_your_house.security.JwtService;
import find_your_house.user.Token;
import find_your_house.user.TokenRepository;
import find_your_house.user.User;
import find_your_house.user.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final AuthenticationManager authenticateManager;
    private final JwtService jwtService;
    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;
    public void register(RegistrationRequest request) throws MessagingException {
        var userRole=roleRepository.findByName("User")
                .orElseThrow(()->new IllegalStateException("Role USER was not initialized"));
        var user= User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enable(false)
                .createdDate(LocalDate.now())
                .roles(List.of(userRole))
                .build();
        userRepository.save(user);
        sendValidationEmail(user);
    }

    private void sendValidationEmail(User user) throws MessagingException {
        var newToken=generateAndSaveActivationToken(user);

        emailService.sendEmail(
                user.getEmail(),
                user.fullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken,
                "Account activation"
        );

    }

    private String generateAndSaveActivationToken(User user) {
        String generatedToken=generateActivationCode(6);
        var token= Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);

        return generatedToken;
    }

    private String generateActivationCode(int length) {
        String characters="0123456789";
        StringBuilder codeBuilder=new StringBuilder();
        SecureRandom secureRandom=new SecureRandom();
        for (int i=0;i<length;i++){
            int randomIndex= secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var auth=authenticateManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var claims=new HashMap<String,Object>();
        var user=((User)auth.getPrincipal());
        claims.put("fullName",user.fullName());
        var jwtToken=jwtService.generateToken(claims,user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


    @Transactional
    public void activateAccount(String token) throws MessagingException {
        Token savedToken =tokenRepository.findByToken(token)
                .orElseThrow(()->new RuntimeException("Invalid Token"));
        if (LocalDateTime.now().isAfter(savedToken.getExpiresAt())){
            sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("Activation token has expired. A new token has been sent to the same email address");
        }
        var user=userRepository.findById(savedToken.getUser().getId())
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
        user.setEnable(true);
        userRepository.save(user);
        savedToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(savedToken);

    }
}
