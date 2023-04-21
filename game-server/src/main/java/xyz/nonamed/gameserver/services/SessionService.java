package xyz.nonamed.gameserver.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.nonamed.gameserver.repositories.SessionRepository;
import xyz.nonamed.dto.Session;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class SessionService {

    final SessionRepository sessionRepository;
    final UserService userService;

    public Session save(Session entity) {
        Session session = sessionRepository.saveAndFlush(entity);
        log.info("saved session: '{}'", session);
        return session;
    }

    public Session readBySessionCode(String sessionCode) {
        return sessionRepository.readFirstBySessionCodeEqualsIgnoreCase(sessionCode);
    }

    public List<Session> readAllSessions() {
        // TODO implement pagination
        // TODO filter only visible sessions
        return sessionRepository.findAll();
    }

    public Session registerSession(String userName, String sessionCode) {
        Session session = readBySessionCode(sessionCode);
        if (session == null) {
            session = new Session();
            session.setOwnerName(userName);
            session.setSessionCode(sessionCode);
            session = save(session); // need to set id
            log.info("created new session: '{}'", session);
        }
        return session;
    }

    public boolean runSession(String userName, String sessionCode) {
        Session session = readBySessionCode(sessionCode);
        if (userName != null
                && session != null
                && session.getOwnerName() != null
                && userName.equalsIgnoreCase(session.getOwnerName())) {
            boolean newState = true;
            session.setRun(newState);
            session = save(session);
            log.info("user '{}' set session run state '{}' // session: '{}'", userName, newState, session);
            return true;
        } else {
            log.error("nor allowed user '{}' to run session: '{}'", userName, sessionCode);
            return false;
        }
    }

}
