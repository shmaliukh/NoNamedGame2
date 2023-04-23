package xyz.nonamed.gameclient.handlers;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import xyz.nonamed.dto.Session;
import xyz.nonamed.gameclient.client_protocols.SessionAPI;

import java.util.List;

import static xyz.nonamed.Constants.BASE_URL;

public class SessionHandler implements SessionAPI {

    public SessionAPI sessionAPI = Feign.builder()
            .encoder(new GsonEncoder())
            .decoder(new GsonDecoder())
            .target(SessionAPI.class, BASE_URL);

    @Override
    public Session postConnectUserToSession(Session session, String userName, String sessionCode) {
        return sessionAPI.postConnectUserToSession(session, userName, sessionCode);
    }

    @Override
    public List<Session> getAllSessions() {
        return sessionAPI.getAllSessions();
    }

    @Override
    public List<Session> getAllVisibleSessions() {
        return sessionAPI.getAllVisibleSessions();
    }

    @Override
    public boolean postRunSession(String userName, String sessionCode) {
        return sessionAPI.postRunSession(userName, sessionCode);
    }

}
