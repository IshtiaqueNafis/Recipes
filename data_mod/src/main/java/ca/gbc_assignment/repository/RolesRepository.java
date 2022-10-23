package ca.gbc_assignment.repository;

import ca.gbc_assignment.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
