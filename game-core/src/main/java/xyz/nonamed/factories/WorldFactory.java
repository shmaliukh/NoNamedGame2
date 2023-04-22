package xyz.nonamed.factories;

import xyz.nonamed.dto.World;

import java.util.ArrayList;
import java.util.List;

public class WorldFactory implements MyFactory<World> {

    public World create(String type) {
        return switch (type) {
            case World.WORLD_1 -> createWorld1();
            case World.WORLD_2 -> createWorld2();
            case World.WORLD_3 -> createWorld3();
            default -> createWorld1();
        };
    }

    public World createWorld1() {
        World world = new World();
        world.setType(World.WORLD_1);
        return world;
    }

    public World createWorld2() {
        World world = new World();
        world.setType(World.WORLD_2);
        return world;
    }

    public World createWorld3() {
        World world = new World();
        world.setType(World.WORLD_3);
        return world;
    }

    public List<String> getTypeList() {
        return new ArrayList<>(World.worldTypeList);
    }

}
