package xyz.nonamed.gameclient.client_protocols;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import xyz.nonamed.dto.Session;

import java.util.List;

@Headers("Accept: application/json")
public interface IsAliveServerAPI {

    @RequestLine("GET /getIsServerAlive")
    boolean getIsServerAlive();

}