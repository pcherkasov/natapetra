package it.academy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewAppUserDTO implements Serializable {

    @Pattern(regexp = "^[a-zA-Z]{1,40}$", message = "Error lastName")
    @NotEmpty
    private String lastName;

    @Pattern(regexp = "^[a-zA-Z]{1,20}$", message = "Error firstName")
    @NotEmpty
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]{1,40}$", message = "Error surname")
    @NotEmpty
    private String surname;

    @Email(message = "Error email")
    @Pattern(regexp = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$")
    @NotEmpty
    private String email;

    @NotEmpty
    @Pattern(regexp = "\\b(Administrator|Sale User|Customer User|Secure API User)\\b", message = "Error role")
    private String roleName;
}
