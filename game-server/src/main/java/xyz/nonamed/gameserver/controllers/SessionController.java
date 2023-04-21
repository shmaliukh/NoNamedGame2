package xyz.nonamed.gameserver.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import xyz.nonamed.gameserver.entities.SessionEntity;
import xyz.nonamed.gameserver.services.SessionService;
import xyz.nonamed.dto.Session;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping
@AllArgsConstructor
public class SessionController {

    final SessionService sessionService;

    @PostMapping(value = "/{userName}/{sessionCode}/postConnectUserToSession")
    public Session postConnectUserToSession(@PathVariable("userName") String userName,
                                            @PathVariable("sessionCode") String sessionCode) {
        SessionEntity sessionEntity = sessionService.registerSession(userName, sessionCode);
        log.info("registered user '{}' to session '{}'", userName, sessionEntity);
        return sessionService.toDto(sessionEntity);
    }

    @GetMapping(value = "/getAllSessions")
    public List<Session> getAllSessions() {
        List<SessionEntity> sessionEntityList = sessionService.readAllSessions();
        return sessionEntityList.stream()
                .map(sessionService::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/{userName}/{sessionCode}/postRunSession")
    public boolean postRunSession(@PathVariable("userName") String userName,
                                  @PathVariable("sessionCode") String sessionCode) {
        return sessionService.runSession(userName, sessionCode);
    }

}
