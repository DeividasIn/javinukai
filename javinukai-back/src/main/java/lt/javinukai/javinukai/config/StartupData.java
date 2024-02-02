package lt.javinukai.javinukai.config;

import lombok.RequiredArgsConstructor;
import lt.javinukai.javinukai.config.security.AuthenticationService;
import lt.javinukai.javinukai.dto.response.AuthenticationResponse;
import lt.javinukai.javinukai.dto.request.user.UserRegistrationRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class StartupData {
    private final AuthenticationService authenticationService;

    @Bean
    CommandLineRunner initialize() {
        return args -> {

            UserRegistrationRequest registration = new UserRegistrationRequest(
                    "John",
                    "Doe",
                    1984,
                    "+37047812482",
                    "jdoe@mail.com",
                    "password"
            );

           AuthenticationResponse res =  authenticationService.register(registration);
            System.out.println("CREATED JWT: " + res.getToken());
        };
    }
}