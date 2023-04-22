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
public class World {

    public static final String WORLD_1 = "world_1";
    public static final String WORLD_2 = "world_2";
    public static final String WORLD_3 = "world_3";
    public static final String DEFAULT_WORLD_TYPE = WORLD_1;

    public static final List<String> worldTypeList = Arrays.asList(
            WORLD_1,
            WORLD_2,
            WORLD_3
    );
    public static final double DEFAULT_HEIGHT = 5111;
    public static final double DEFAULT_WIDTH = 10222;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long id;

    public String sessionCode;

    public double height = DEFAULT_HEIGHT;
    public double width = DEFAULT_WIDTH;
    public String type = DEFAULT_WORLD_TYPE;

}
