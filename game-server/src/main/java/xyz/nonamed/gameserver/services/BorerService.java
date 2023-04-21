package xyz.nonamed.gameserver.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.nonamed.dto.Borer;
import xyz.nonamed.dto.Bot;
import xyz.nonamed.gameserver.repositories.BorerRepository;
import xyz.nonamed.gameserver.repositories.BotRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BorerService {

    final BorerRepository borerRepository;

    public List<Borer> readBorerListBySessionCode(String sessionCode) {
        return borerRepository.readAllBySessionCodeEqualsIgnoreCase(sessionCode);
    }

    public Borer save(Borer borer) {
        return borerRepository.saveAndFlush(borer);
    }

    public List<Borer> save(List<Borer> borerList) {
        return borerRepository.saveAllAndFlush(borerList);
    }

    public Borer addNewBorerToSession(Borer borer, String sessionCode) {
        borer.setSessionCode(sessionCode);
        borer = save(borer);
        log.info("created new borer: '{}' // session code: '{}'", borer, sessionCode);
        return borer;
    }

    public List<Borer> getBorerListBySessionCode(String sessionCode) {
        return new ArrayList<>(readBorerListBySessionCode(sessionCode));
    }

}
