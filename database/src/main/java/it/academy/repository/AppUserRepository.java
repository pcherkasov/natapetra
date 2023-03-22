package it.academy.repository;

import it.academy.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    @Query(value = "SELECT * FROM app_user ORDER BY email", nativeQuery = true) //ты ищешь по емейлу, но почему метод никакого параметра не принимает?
    List<AppUser> allUsersOrderByEmail();
}
