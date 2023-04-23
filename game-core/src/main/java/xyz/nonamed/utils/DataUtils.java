package xyz.nonamed.utils;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@UtilityClass
public class DataUtils {

    public static final Random random = new Random();

    public static String getRandomName() {
        return DEFAULT_NAME_LIST.get(random.nextInt(DEFAULT_NAME_LIST.size()));
    }

    public static final List<String> DEFAULT_NAME_LIST = Arrays.asList(
            "Vlad is love",
            // developers))
            // ***
            "Vlad",
            "Artem",
            "Yana",
            "Vanya",
            "Dima",
            "Kate",
            // ***
            "Joey",
            "Aurelio",
            "Evan",
            "Donny",
            "Foster",
            "Dwayne",
            "Grady",
            "Quinton",
            "Darin",
            "Mickey",
            "Hank",
            "Kim",
            "Peter",
            "Jeremy",
            "Jess",
            "Jimmie",
            "Vern",
            "Pasquale",
            "Romeo",
            "Chris",
            "Dale",
            "Beau",
            "Cliff",
            "Timothy",
            "Raphael",
            "Brain",
            "Mauro",
            "Luke",
            "Myron",
            "Omar",
            "Reynaldo",
            "Major",
            "Clinton",
            "Nolan",
            "Raymond",
            "Lucien",
            "Carey",
            "Winfred",
            "Dan",
            "Abel",
            "Elliott",
            "Brent",
            "Chuck",
            "Dirk",
            "Tod",
            "Emerson",
            "Dewey",
            "Scot",
            "Enrique",
            "Al",
            "Beatrice",
            "Brandy",
            "Kathy",
            "Jane",
            "Marcy",
            "Shelly",
            "Lucy",
            "Cathy",
            "Joanna",
            "Doris",
            "Lindsay",
            "Staci",
            "Shelia",
            "Rosanne",
            "Rebecca",
            "Luz",
            "Flora",
            "Rosalie",
            "Karla",
            "Phoebe",
            "Meagan",
            "Virginia",
            "Amanda",
            "Katy",
            "Karla",
            "Deanne",
            "Pearl",
            "Christi",
            "Victoria",
            "Ola",
            "Alexandra",
            "Marina",
            "Lorraine",
            "Sybil",
            "Adeline",
            "Taylor",
            "Anita",
            "Aurora",
            "Neva",
            "Alisha",
            "Maria",
            "Erna",
            "Gwendolyn",
            "Brenda",
            "Bethany",
            "Sybil",
            "Earline",
            "June",
            "Brandy",
            "Sue"
    );

}
