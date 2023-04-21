package xyz.nonamed.gameclient.client_protocols;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import xyz.nonamed.dto.UserEntity;

import java.util.List;

@Headers("Accept: application/json")
public interface UserAPI {

    @RequestLine("POST /{userName}/postRegisterUser")
    UserEntity postRegisterUser(@Param("userName") String userName);

    @RequestLine("GET /{userName}/{sessionCode}/postRegisterUser")
    List<UserEntity> getUsersBySessionCode(@Param("userName") String userName,
                                           @Param("sessionCode") String sessionCode);

}