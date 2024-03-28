package dev.movies.repositories;

import dev.movies.entities.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleModel, Long> {
    RoleModel findByRolename(String rolename);
}
