package xyz.nonamed.gameserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.nonamed.dto.UserEntity;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity readFirstByNameEqualsIgnoreCase(String name);

    List<UserEntity> readAllBySessionCodeEqualsIgnoreCase(String sessionCode);

}
