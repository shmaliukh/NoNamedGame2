package xyz.nonamed.gameserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.nonamed.dto.Bot;

import java.util.List;

@Repository
public interface BotRepository extends JpaRepository<Bot, Long> {

    List<Bot> readAllBySessionCodeEqualsIgnoreCase(String sessionCode);

}
