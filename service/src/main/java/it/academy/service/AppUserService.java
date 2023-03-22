package it.academy.service;

import it.academy.dto.AppUserDTO;
import it.academy.dto.NewAppUserDTO;
import it.academy.exception.AppUserNotFoundException;
import it.academy.model.AppUser;
import it.academy.model.AppUserRole;
import it.academy.repository.AppUserRepository;
import it.academy.repository.AppUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppUserService{

    @Autowired
    private AppUserRoleRepository appUserRoleRepository;
    @Autowired
    private AppUserRepository appUserRepository;

    public List<AppUserDTO> allUsers(){
        List<AppUserDTO> appUsers = new ArrayList<>();
        List<AppUser> users = appUserRepository.allUsersOrderByEmail();
        for (AppUser user: users) {
            AppUserDTO appUserDTO = new AppUserDTO();
            appUserDTO.setUserName(user.getFirstName() + " " + user.getLastName() + " " + user.getSurname());
            appUserDTO.setEmail(user.getEmail());
            appUserDTO.setRole(user.getAppUserRole().getRoleName());
            appUsers.add(appUserDTO);
        }
        return appUsers;
    }

    public List<AppUserDTO> allUsersPagination(Integer pageNumber, Integer pageSize){
        List<AppUserDTO> appUsers = new ArrayList<>();
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        Page<AppUser> users = appUserRepository.findAll(paging);
        //лучше вынести в отдельный класс, например UserConverter, тем более, что это в несокльких местах используется
        for (AppUser user: users) {
            AppUserDTO appUserDTO = new AppUserDTO();
            appUserDTO.setUserName(user.getFirstName() + " " + user.getLastName() + " " + user.getSurname());
            appUserDTO.setEmail(user.getEmail());
            appUserDTO.setRole(user.getAppUserRole().getRoleName());
            appUsers.add(appUserDTO);
        }
        return appUsers.stream()
                .sorted(new AppUserDTOComparator())
                .collect(Collectors.toList());
    }

    public NewAppUserDTO addNewUser(NewAppUserDTO newAppUserDTO){
        AppUser appUser = new AppUser();
        appUser.setFirstName(newAppUserDTO.getFirstName());
        appUser.setLastName(newAppUserDTO.getLastName());
        appUser.setSurname(newAppUserDTO.getSurname());
        appUser.setEmail(newAppUserDTO.getEmail());
        AppUserRole userRole = appUserRoleRepository.findAppUserRoleByRoleName(newAppUserDTO.getRoleName());
        if(userRole==null){
            userRole = appUserRoleRepository.save(new AppUserRole(null, newAppUserDTO.getRoleName()));
        }
        appUser.setAppUserRole(userRole);
        appUserRepository.save(appUser);
        return newAppUserDTO;
    }

    public AppUserDTO getUserByID(Long id){
        AppUser user = appUserRepository.findById(id).orElseThrow(() -> new AppUserNotFoundException(id));
        AppUserDTO appUserDTO = new AppUserDTO();
        appUserDTO.setUserName(user.getFirstName() + " " + user.getLastName() + " " + user.getSurname());
        appUserDTO.setEmail(user.getEmail());
        appUserDTO.setRole(user.getAppUserRole().getRoleName());
        return appUserDTO;
    }

}
