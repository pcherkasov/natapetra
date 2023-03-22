package it.academy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDTO { //AppUserDto и, как по мне, можно просто UserDto (но это уже сама смотри)

    private String userName;

    private String email;

    private String role;

}
