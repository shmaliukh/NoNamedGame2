package xyz.nonamed.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    public static final boolean DEFAULT_SESSION_IS_VISIBLE = true;
    public static final int DEFAULT_SESSION_MAX_USERS = 4;
    public static final int DEFAULT_SESSION_USER_COUNTER = 1;
    public static final int DEFAULT_SESSION_MAX_BOTS = 50; // TODO

    public String ownerName;
    public String sessionCode;

    public boolean isVisible = DEFAULT_SESSION_IS_VISIBLE;
    public int maxUser = DEFAULT_SESSION_MAX_USERS;
    public int userCounter = DEFAULT_SESSION_USER_COUNTER;
    public int botCounter = 0;
    public int botMaxCounter = DEFAULT_SESSION_MAX_BOTS;

    public boolean isRun = false;

}
