package xyz.nonamed.gameserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.nonamed.dto.Borer;
import xyz.nonamed.dto.GameObject;

import java.util.List;

@Repository
public interface GameObjectRepository extends JpaRepository<GameObject, Long> {

    List<GameObject> readAllBySessionCodeEqualsIgnoreCase(String sessionCode);

}
