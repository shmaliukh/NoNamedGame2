package xyz.nonamed.gameserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.nonamed.dto.Borer;
import xyz.nonamed.dto.World;

import java.util.List;

@Repository
public interface WorldRepository extends JpaRepository<World, Long> {

    World readFirstBySessionCodeEqualsIgnoreCase(String sessionCode);

}
