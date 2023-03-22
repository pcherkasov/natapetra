package it.academy.repository;

import it.academy.model.AppUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRoleRepository extends JpaRepository<AppUserRole, Long> {

    AppUserRole findAppUserRoleByRoleName(String roleName);
}
