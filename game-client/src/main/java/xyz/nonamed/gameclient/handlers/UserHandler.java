package xyz.nonamed.gameclient.handlers;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import xyz.nonamed.dto.UserEntity;
import xyz.nonamed.gameclient.client_protocols.UserAPI;

import java.util.List;

import static xyz.nonamed.Constants.BASE_URL;

public class UserHandler implements UserAPI {

    public UserAPI userAPI = Feign.builder()
            .decoder(new GsonDecoder())
            .encoder(new GsonEncoder())
            .target(UserAPI.class, BASE_URL);

    @Override
    public UserEntity postRegisterUser(String userName) {
        return userAPI.postRegisterUser(userName);
    }

    @Override
    public List<UserEntity> getUsersBySessionCode(String userName, String sessionCode) {
        return userAPI.getUsersBySessionCode(userName, sessionCode);
    }

}
