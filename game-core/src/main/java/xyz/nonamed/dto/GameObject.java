package xyz.nonamed.dto;

import jakarta.persistence.*;
import lombok.*;

import java.util.Arrays;
import java.util.List;

import static xyz.nonamed.dto.Hero.DEFAULT_HERO_TYPE;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GameObject {

    public static final String GAME_OBJECT_1 = "bot_1";
    public static final String GAME_OBJECT_2 = "bot_2";
    public static final String GAME_OBJECT_3 = "bot_3";
    public static final String GAME_OBJECT_4 = "bot_4";
    public static final String GAME_OBJECT_5 = "bot_5";
    public static final String GAME_OBJECT_6 = "bot_6";
    public static final String DEFAULT_GAME_OBJECT_6_TYPE = GAME_OBJECT_1;

    public static List<String> gameObjectTypeList = Arrays.asList(
            GAME_OBJECT_1,
            GAME_OBJECT_2,
            GAME_OBJECT_3,
            GAME_OBJECT_4,
            GAME_OBJECT_5,
            GAME_OBJECT_6
    );

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public String name;
    public String sessionCode;

    public double posX = 0;
    public double posY = 0;

    public double height = 0;
    public double width = 0;

    public double collisionPosX = 0;
    public double collisionPosY = 0;

    public double collisionHeight = 0;
    public double collisionWidth = 0;

    public String type = DEFAULT_GAME_OBJECT_6_TYPE;
    public String color;

}
