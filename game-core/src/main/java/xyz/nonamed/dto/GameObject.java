package xyz.nonamed.dto;

import jakarta.persistence.*;
import lombok.*;

import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GameObject {

    public static final String GAME_OBJECT_1 = "home1";
    public static final String GAME_OBJECT_2 = "home2";
    public static final String GAME_OBJECT_3 = "tank1";
    public static final String GAME_OBJECT_4 = "tank2";
    public static final String GAME_OBJECT_5 = "tank3";
    public static final String GAME_OBJECT_6 = "tank4";
    public static final String GAME_OBJECT_7 = "home3";
    public static final String GAME_OBJECT_8 = "home4";

    public static final String GAME_OBJECT_9 = "bord1";
    public static final String GAME_OBJECT_10 = "bord2";
    public static final String GAME_OBJECT_11 = "bord3";
    public static final String GAME_OBJECT_12 = "car1";
    public static final String GAME_OBJECT_13 = "car2";
    public static final String GAME_OBJECT_14 = "server1";
    public static final String GAME_OBJECT_15 = "server2";
    public static final String GAME_OBJECT_16 = "barrel";
    public static final String GAME_OBJECT_17 = "box";
    public static final String GAME_OBJECT_18 = "luckybox";
    public static final String GAME_OBJECT_19 = "nlo";
    public static final String GAME_OBJECT_20 = "solar";

    public static final String DEFAULT_GAME_OBJECT_TYPE = GAME_OBJECT_1;

    public static List<String> gameObjectTypeList = Arrays.asList(
            GAME_OBJECT_1,
            GAME_OBJECT_2,
            GAME_OBJECT_3,
            GAME_OBJECT_4,
            GAME_OBJECT_5,
            GAME_OBJECT_6,
            GAME_OBJECT_7,
            GAME_OBJECT_8,
            GAME_OBJECT_9,
            GAME_OBJECT_10,
            GAME_OBJECT_11,
            GAME_OBJECT_12,
            GAME_OBJECT_13,
            GAME_OBJECT_14,
            GAME_OBJECT_15,
            GAME_OBJECT_16,
            GAME_OBJECT_17,
            GAME_OBJECT_18
    );

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long id;

    public String name;
    public String sessionCode;

    public double posX = 0;
    public double posY = 0;

    public double height = 0;
    public double width = 0;

    public boolean isCollision = false;
    public double collisionPosX = 0;
    public double collisionPosY = 0;

    public double collisionHeight = 0;
    public double collisionWidth = 0;

    public String type = DEFAULT_GAME_OBJECT_TYPE;
    public String color;

}
