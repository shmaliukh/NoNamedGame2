package xyz.nonamed.gameclient.handlers;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import xyz.nonamed.dto.GameObject;
import xyz.nonamed.dto.World;
import xyz.nonamed.gameclient.client_protocols.GameObjectAPI;

import java.util.List;

import static xyz.nonamed.Constants.BASE_URL;

public class GameObjectHandler implements GameObjectAPI {

    public GameObjectAPI gameObjectAPI = Feign.builder()
            .decoder(new GsonDecoder())
            .encoder(new GsonEncoder())
            .target(GameObjectAPI.class, BASE_URL);

    @Override
    public List<GameObject> getGameObjectList(String userName, String sessionCode) {
        return gameObjectAPI.getGameObjectList(userName, sessionCode);
    }

    @Override
    public List<GameObject> postGenerateGameObjects(World world, String userName, String sessionCode) {
        return gameObjectAPI.postGenerateGameObjects(world, userName, sessionCode);
    }

}
