package xyz.nonamed.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    public static final int DEFAULT_USER_SCORE = 0;
    public static final boolean DEFAULT_USER_IS_ACTIVE = true;

    public Long id;
    public String name;
    public String sessionCode;

    public int score = DEFAULT_USER_SCORE;
    public boolean isActive = DEFAULT_USER_IS_ACTIVE;

}
