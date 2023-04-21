package xyz.nonamed.dto;

import jakarta.persistence.*;
import lombok.*;

@Entity
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long id;

    public String ownerName;
    public String sessionCode;
    // TODO implement level ???
    // public String level;

    public boolean isVisible = DEFAULT_SESSION_IS_VISIBLE;
    public int maxUser = DEFAULT_SESSION_MAX_USERS;
    public int userCounter = DEFAULT_SESSION_USER_COUNTER;
    public int botCounter = 0;
    public int botMaxCounter = DEFAULT_SESSION_MAX_BOTS;

    public boolean isRun = false;

}
