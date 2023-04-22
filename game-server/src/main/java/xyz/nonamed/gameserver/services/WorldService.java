package xyz.nonamed.gameserver.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.nonamed.dto.World;
import xyz.nonamed.factories.WorldFactory;
import xyz.nonamed.gameserver.repositories.WorldRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static xyz.nonamed.dto.World.*;

@Slf4j
@Service
@AllArgsConstructor
public class WorldService {

    static Random random = new Random();

    final WorldRepository worldRepository;

    public World readWorldBySessionCode(String sessionCode) {
        return worldRepository.readFirstBySessionCodeEqualsIgnoreCase(sessionCode);
    }

    public World save(World world) {
        return worldRepository.saveAndFlush(world);
    }

    public World addWorldToSession(World world, String sessionCode) {
        WorldFactory worldFactory = new WorldFactory();
        world = worldFactory.create(worldTypeList.get(random.nextInt(worldTypeList.size())));
        world.setSessionCode(sessionCode);
        world = save(world);
        log.info("created new world: '{}' // session code: '{}'", world, sessionCode);
        return world;
    }

}
