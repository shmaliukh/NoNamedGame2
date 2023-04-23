package xyz.nonamed.gameclient.handlers;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import xyz.nonamed.dto.Borer;
import xyz.nonamed.gameclient.client_protocols.BorerAPI;

import java.util.List;

import static xyz.nonamed.Constants.BASE_URL;

public class BorerHandler implements BorerAPI {

    public BorerAPI borerAPI = Feign.builder()
            .decoder(new GsonDecoder())
            .encoder(new GsonEncoder())
            .target(BorerAPI.class, BASE_URL);

    @Override
    public List<Borer> getBorerList(String userName, String sessionCode) {
        return borerAPI.getBorerList(userName, sessionCode);
    }
    
}
