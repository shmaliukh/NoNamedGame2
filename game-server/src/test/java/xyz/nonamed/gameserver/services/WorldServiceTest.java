package xyz.nonamed.gameserver.services;

import org.junit.jupiter.api.Test;
import xyz.nonamed.factories.WorldFactory;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static xyz.nonamed.dto.World.worldTypeList;

class WorldServiceTest {

    @Test
    void test(){
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            WorldFactory worldFactory = new WorldFactory();
            worldFactory.create(worldTypeList.get(random.nextInt(worldTypeList.size())));
            System.out.println();
        }

    }

}