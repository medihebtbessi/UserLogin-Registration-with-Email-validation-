package find_your_house;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class FindYourHouseApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FindYourHouseApiApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner runner(RoleRepository roleRepository){
//		return args -> {
//			if (roleRepository.findByName("USER").isEmpty()){
//				roleRepository.save(
//						Role.builder().name("USER")
//								.createdDate(LocalDate.now())
//								.build()
//				);
//			}
//		};
//	}

}
//46min