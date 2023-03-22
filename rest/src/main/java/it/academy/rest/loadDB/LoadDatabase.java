package it.academy.rest.loadDB;

import it.academy.model.AppUser;
import it.academy.model.AppUserRole;
import it.academy.repository.AppUserRepository;
import it.academy.repository.AppUserRoleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    //я бы сделал инициализацию немного иначе, пожалуй.
    //в ликвибейзе есть возможность выполнять sql скрипты. тоесть создаёшь файлик с запросами, которые наполняют базу,
    //кладёшь к миграционным скриптам и на этапе разворачивания приложения они накатываются
    //вот, глянь, как у меня нечно подобное сделано
    //https://github.com/pcherkasov/sensorsmonitor/tree/development/src/main/resources/migrations

    @Autowired
    private AppUserRoleRepository appUserRoleRepository;

    private static final Logger logger = LogManager.getLogger();

    @Bean
    CommandLineRunner initDatabase(AppUserRepository repository) {

        AppUserRole appUserRole = appUserRoleRepository.save(new AppUserRole(1L, "Administrator"));
        AppUserRole appUserRole2 = appUserRoleRepository.save(new AppUserRole(2L, "Sale User"));

        return args -> {
            logger.info("Preloading " + repository.save(new AppUser(1L, "Ivanov", "Ivan", "Ivanovich", "ivanov@mail.ru", appUserRole)));
            logger.info("Preloading " + repository.save(new AppUser(2L, "Petrov", "Petr", "Petrovich", "petrov@mail.ru", appUserRole2)));
        };
    }
}
