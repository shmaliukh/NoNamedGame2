package xyz.nonamed.gameserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.nonamed.gameserver.entities.SessionEntity;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Long> {

    SessionEntity readFirstBySessionCodeEqualsIgnoreCase(String sessionCode);

}
