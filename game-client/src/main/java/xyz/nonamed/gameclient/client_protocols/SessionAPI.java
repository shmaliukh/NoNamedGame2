package xyz.nonamed.gameclient.client_protocols;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import xyz.nonamed.dto.Session;

import java.util.List;

@Headers("Accept: application/json")
public interface SessionAPI {

    @RequestLine("POST /{userName}/{sessionCode}/postConnectUserToSession")
    Session postConnectUserToSession(@Param("userName") String userName,
                                     @Param("sessionCode") String sessionCode);

    @RequestLine("POST /{userName}/{sessionCode}/postRunSession")
    boolean postRunSession(@Param("userName") String userName,
                           @Param("sessionCode") String sessionCode);

    @RequestLine("GET /getAllSessions")
    List<Session> getAllSessions();

}