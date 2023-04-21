package xyz.nonamed.gameserver.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.nonamed.gameserver.ConvertToDto;
import xyz.nonamed.gameserver.entities.SessionEntity;
import xyz.nonamed.gameserver.repositories.SessionRepository;
import xyz.nonamed.dto.Session;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class SessionService implements ConvertToDto<SessionEntity, Session> {

    final SessionRepository sessionRepository;
    final UserService userService;

    public SessionEntity save(SessionEntity entity) {
        SessionEntity session = sessionRepository.saveAndFlush(entity);
        log.info("saved session: '{}'", session);
        return session;
    }

    public SessionEntity readBySessionCode(String sessionCode) {
        return sessionRepository.readFirstBySessionCodeEqualsIgnoreCase(sessionCode);
    }

    public List<SessionEntity> readAllSessions() {
        // TODO implement pagination
        // TODO filter only visible sessions
        return sessionRepository.findAll();
    }

    public SessionEntity registerSession(String userName, String sessionCode) {
        SessionEntity sessionEntity = readBySessionCode(sessionCode);
        if (sessionEntity == null) {
            sessionEntity = new SessionEntity();
            sessionEntity.setOwnerName(userName);
            sessionEntity.setSessionCode(sessionCode);
            sessionEntity = save(sessionEntity); // need to set id
            log.info("created new session: '{}'", sessionEntity);
        }
        return sessionEntity;
    }

    public boolean runSession(String userName, String sessionCode) {
        SessionEntity sessionEntity = readBySessionCode(sessionCode);
        if (userName != null
                && sessionEntity != null
                && sessionEntity.getOwnerName() != null
                && userName.equalsIgnoreCase(sessionEntity.getOwnerName())) {
            boolean newState = true;
            sessionEntity.setRun(newState);
            sessionEntity = save(sessionEntity);
            log.info("user '{}' set session run state '{}' // session: '{}'", userName, newState, sessionEntity);
            return true;
        } else {
            log.error("nor allowed user '{}' to run session: '{}'", userName, sessionCode);
            return false;
        }
    }

    @Override
    public Session toDto(SessionEntity entity) {
        // TODO try cast SessionEntity to Session
        Session session = new Session();
        session.setSessionCode(entity.getSessionCode());
        session.setOwnerName(entity.getOwnerName());
        session.setMaxUser(entity.getMaxUser());
        session.setVisible(entity.isVisible());
        session.setBotCounter(entity.getBotCounter());
        session.setBotMaxCounter(entity.getBotMaxCounter());
        return session;
    }

}
