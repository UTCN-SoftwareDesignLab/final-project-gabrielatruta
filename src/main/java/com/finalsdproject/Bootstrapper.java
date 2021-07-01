package com.finalsdproject;

import com.finalsdproject.animal.SpeciesRepository;
import com.finalsdproject.animal.model.Animal;
import com.finalsdproject.animal.AnimalRepository;
import com.finalsdproject.animal.model.ESpecies;
import com.finalsdproject.animal.model.Species;
import com.finalsdproject.email.EmailRequestDTO;
import com.finalsdproject.email.EmailService;
import com.finalsdproject.owner.OwnerRepository;
import com.finalsdproject.owner.model.Owner;
import com.finalsdproject.security.AuthService;
import com.finalsdproject.security.dto.SignupRequest;
import com.finalsdproject.sms.SMS;
import com.finalsdproject.sms.SMSService;
import com.finalsdproject.user.RoleRepository;
import com.finalsdproject.user.UserRepository;
import com.finalsdproject.user.model.ERole;
import com.finalsdproject.user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.finalsdproject.animal.model.ESpecies.CAT;
import static com.finalsdproject.animal.model.ESpecies.DOG;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final AnimalRepository animalRepository;
    private final SpeciesRepository speciesRepository;
    private final OwnerRepository ownerRepository;
    private final EmailService emailService;
    private final SMSService smsService;

    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if(bootstrap) {
            animalRepository.deleteAll();
            userRepository.deleteAll();
            roleRepository.deleteAll();
            speciesRepository.deleteAll();
            ownerRepository.deleteAll();
            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }
            for (ESpecies value : ESpecies.values()) {
                speciesRepository.save(
                        Species.builder()
                            .name(value)
                            .build()
                );
            }
            authService.register(SignupRequest.builder()
                    .email("admin@email.com")
                    .username("admin")
                    .password("Administrator3!")
                    .roles(Set.of("ADMINISTRATOR"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("gabi1@email.com")
                    .username("gabi1")
                    .password("Gabriela3!")
                    .roles(Set.of("USER"))
                    .build());

            Animal Iris = Animal.builder()
                    .animal(DOG)
                    .name("Iris")
                    .age("6 years")
                    .gender("F")
                    .size("medium")
                    .description("German shepard mixture, very smart")
                    .build();
            animalRepository.save(Iris);
            animalRepository.save(Animal.builder()
                    .animal(CAT)
                    .name("Fonfi")
                    .age("12 years")
                    .gender("M")
                    .size("small")
                    .description("Friendly cat, chubby, gets along well with any cat that doesn't start a fight")
                    .build());
            animalRepository.save(Animal.builder()
                    .animal(DOG)
                    .name("Rex")
                    .age("8 months")
                    .gender("M")
                    .size("medium")
                    .description( "German shepard mixture, very smart, gets along well with cats" +
                            " but needs time with other dogs and people, doesn't get along well with other" +
                            " females and children, very active. Vaccinated, dewormed, microchiped")
                    .build());
            animalRepository.save(Animal.builder()
                    .animal(CAT)
                    .name("Oscar")
                    .age("4 years")
                    .gender("M")
                    .size("small")
                    .description("Orange cat, looks like Garfield, likes to eat")
                    .build());

            Owner owner = Owner.builder()
                    .name("Truta Gabriela")
                    .address("Zalau, Salaj, Romania, str. Kiss Karoly, nr. 2")
                    .CNP("2990318314892")
                    .numericalCode("SX567890")
                    .phone("0786782019")
                    .animals(Set.of(Iris))
                    .build();
            ownerRepository.save(owner);
            Iris.setOwner(owner);
            animalRepository.save(Iris);

            EmailRequestDTO emailRequestDTO = EmailRequestDTO.builder()
                    .subject("Sukuna")
                    .message("GAMBARE GAMBARE")
                    .build();
//            emailService.sendMail(emailRequestDTO);
//
//            smsService.send(SMS.builder()
//                    .receiver("+40784780820")
//                    .build());

        }
    }
}
