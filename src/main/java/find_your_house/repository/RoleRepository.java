package find_your_house.repository;

import find_your_house.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    //Optional<Role> findByName(String role);
}
