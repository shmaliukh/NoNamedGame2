package xyz.nonamed.gameclient.handlers;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import xyz.nonamed.gameclient.client_protocols.IsAliveServerAPI;

import static xyz.nonamed.Constants.*;

public class IsAliveServerHandler {

    public IsAliveServerAPI callLocal = Feign.builder()
            .encoder(new GsonEncoder())
            .decoder(new GsonDecoder())
            .target(IsAliveServerAPI.class, LOCALHOST_URL);

    public IsAliveServerAPI callGlobal = Feign.builder()
            .encoder(new GsonEncoder())
            .decoder(new GsonDecoder())
            .target(IsAliveServerAPI.class, AWS_URL);

    public boolean isLocalAlive() {
        try {
            return callLocal.getIsServerAlive();
        } catch (Throwable throwable) {
            System.out.println("local is NOT alive");
        }
        return false;
    }

    public boolean isGlobalAlive() {
        try {
            return callGlobal.getIsServerAlive();
        } catch (Throwable throwable) {
            System.out.println("global is NOT alive");
        }
        return false;
    }

}
