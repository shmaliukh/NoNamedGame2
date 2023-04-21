package xyz.nonamed.gameserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.nonamed.dto.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    Session readFirstBySessionCodeEqualsIgnoreCase(String sessionCode);

}
