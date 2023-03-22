package it.academy.rest.controller;

import it.academy.dto.AppUserDTO;
import it.academy.dto.NewAppUserDTO;
import it.academy.service.AppUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AppUserController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/app_users")
    List<AppUserDTO> all(
            @RequestParam(name = "page", defaultValue = "0")  Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return appUserService.allUsersPagination(page, size);
    }

    @PostMapping("/app_users")
    NewAppUserDTO newUser(@Valid @RequestBody NewAppUserDTO newUser, Errors errors) {
        logger.info("Validation Errors: " + errors.hasErrors());
        logger.info("Errors count: " + errors.getErrorCount());
        logger.info("Errors message: " + errors.getAllErrors());
        return errors.hasErrors() ? null:appUserService.addNewUser(newUser);
    }

    @GetMapping("/app_users/{id}")
    AppUserDTO getOne(@PathVariable Long id) {
        return appUserService.getUserByID(id);
    }
}
