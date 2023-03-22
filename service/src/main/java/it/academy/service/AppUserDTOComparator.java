package it.academy.service;

import it.academy.dto.AppUserDTO;

import java.util.Comparator;

public class AppUserDTOComparator implements Comparator<AppUserDTO> {

    @Override
    public int compare(AppUserDTO u1, AppUserDTO u2) {
        return u1.getEmail().toUpperCase().compareTo(u2.getEmail().toUpperCase());
    }

}
