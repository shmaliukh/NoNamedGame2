package xyz.nonamed.dto;

import jakarta.persistence.*;
import lombok.*;
import xyz.nonamed.utils.DataUtils;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Borer {

    // FIXME extract 'alive' class or interface (bot and hero are similar)

    public static final double DEFAULT_BORER_POS_X = 200;
    public static final double DEFAULT_BORER_POS_Y = 200;

    public static final double DEFAULT_BORER_HEIGHT = 256;
    public static final double DEFAULT_BORER_WIDTH = 256;

    public static final long DEFAULT_BORER_START_ENERGY = 1;
    public static final long DEFAULT_BORER_MAX_ENERGY = 1000;

    public static final double DEFAULT_BORER_DISTANCE_TO_ACTIVATE = 120;
    public static final String RED = "RED";

    public static final String BORER_1 = "borer_1";
    public static final String BORER_2 = "borer_2";



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long id;

    public String name = DataUtils.getRandomName();
    public String sessionCode;
    public String userName = null; // who delegate actions

    public double posX = DEFAULT_BORER_POS_X;
    public double posY = DEFAULT_BORER_POS_Y;

    public double height = DEFAULT_BORER_HEIGHT;
    public double width = DEFAULT_BORER_WIDTH;

    public double minDistanceToActivate = DEFAULT_BORER_DISTANCE_TO_ACTIVATE;

    public long startEnergy = DEFAULT_BORER_START_ENERGY;
    public long maxEnergy = DEFAULT_BORER_MAX_ENERGY;
    public boolean isActive = false;

    public String color = RED;
    public String animationType;

}
