package xyz.nonamed.gameserver.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import xyz.nonamed.gameserver.services.SessionService;
import xyz.nonamed.dto.Session;

import java.util.List;

@Slf4j
@RestController
@RequestMapping
@AllArgsConstructor
public class SessionController {

    final SessionService sessionService;

    @PostMapping(value = "/{userName}/{sessionCode}/postConnectUserToSession")
    public Session postConnectUserToSession(@PathVariable("userName") String userName,
                                            @PathVariable("sessionCode") String sessionCode) {
        Session session = sessionService.registerSession(userName, sessionCode);
        log.info("registered user '{}' to session '{}'", userName, session);
        return session;
    }

    @GetMapping(value = "/getAllSessions")
    public List<Session> getAllSessions() {
        return sessionService.readAllSessions();
    }

    @PostMapping(value = "/{userName}/{sessionCode}/postRunSession")
    public boolean postRunSession(@PathVariable("userName") String userName,
                                  @PathVariable("sessionCode") String sessionCode) {
        return sessionService.runSession(userName, sessionCode);
    }

    @GetMapping(value = "/getAllVisibleSessions")
    public List<Session> getAllVisibleSessions() {
        return sessionService.readAllVisibleSessions();
    }

}
