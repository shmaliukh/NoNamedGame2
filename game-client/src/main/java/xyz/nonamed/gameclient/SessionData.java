package xyz.nonamed.gameclient;

public class SessionData {

    private int code;
    private String name;

    private int maxUsers;

    private int activeUsers;

    public SessionData(int code, String name, int maxUsers, int activeUsers) {
        this.code = code;
        this.name = name;
        this.maxUsers = maxUsers;
        this.activeUsers = activeUsers;
    }

    public int getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(int maxUsers) {
        this.maxUsers = maxUsers;
    }

    public int getActiveUsers() {
        return activeUsers;
    }

    public void setActiveUsers(int activeUsers) {
        this.activeUsers = activeUsers;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String nme) {
        this.name = nme;
    }

    @Override
    public String toString() {
        return "code: " + code + " - " + "name: " + name;
    }

}