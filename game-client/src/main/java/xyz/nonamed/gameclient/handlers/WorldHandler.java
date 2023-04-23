package xyz.nonamed.gameclient.handlers;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import xyz.nonamed.dto.World;
import xyz.nonamed.gameclient.client_protocols.WorldAPI;

import java.util.List;

import static xyz.nonamed.Constants.BASE_URL;

public class WorldHandler implements WorldAPI {

    public WorldAPI worldAPI = Feign.builder()
            .decoder(new GsonDecoder())
            .encoder(new GsonEncoder())
            .target(WorldAPI.class, BASE_URL);


    @Override
    public World getWorldBySession(String userName, String sessionCode) {
        return worldAPI.getWorldBySession(userName, sessionCode);
    }

}
