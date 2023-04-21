package xyz.nonamed.gameserver.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import xyz.nonamed.dto.User;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserEntity extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public String name;
    public String sessionCode;

    public int score = DEFAULT_USER_SCORE;
    public boolean isActive = DEFAULT_USER_IS_ACTIVE;

}
