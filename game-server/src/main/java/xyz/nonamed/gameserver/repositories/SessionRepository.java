package xyz.nonamed.gameserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.nonamed.dto.Session;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    Session readFirstBySessionCodeEqualsIgnoreCase(String sessionCode);

    List<Session> readAllByVisible(boolean isVisible);

}
