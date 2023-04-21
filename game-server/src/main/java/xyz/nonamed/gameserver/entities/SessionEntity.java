package xyz.nonamed.gameserver.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import xyz.nonamed.dto.Session;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class SessionEntity extends Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public String ownerName;
    public String sessionCode;

    public boolean isVisible = DEFAULT_SESSION_IS_VISIBLE;
    public int maxUser = DEFAULT_SESSION_MAX_USERS;
    public int userCounter = DEFAULT_SESSION_USER_COUNTER;
    public int botCounter = 0;
    public int botMaxCounter = DEFAULT_SESSION_MAX_BOTS;

    public boolean isRun = false;

}
