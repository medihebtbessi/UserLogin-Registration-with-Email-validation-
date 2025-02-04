package find_your_house;

import find_your_house.role.Role;
import find_your_house.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.LocalDate;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class FindYourHouseApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FindYourHouseApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(RoleRepository roleRepository){
		return args -> {
			if (roleRepository.findByName("USER").isEmpty()){
				roleRepository.save(
						Role.builder().name("USER")
								.createdDate(LocalDate.now())
								.build()
				);
			}
		};
	}

}
