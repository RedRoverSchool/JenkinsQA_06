package school.redrover.runner;

import org.testng.annotations.DataProvider;

import java.util.Arrays;
import java.util.Iterator;

public class DataTest {

    @DataProvider(name = "unsafeCharacter")
    public static Object[][] unsafeCharacterArray() {
        return new Object[][]{{'!', "!"}, {'@', "@"}, {'#', "#"}, {'$', "$"}, {'%', "%"}, {'^', "^"}, {'&', "&amp;"}, {'*', "*"}, {'[', "["}, {']', "]"}, {'\\', "\\"}, {'|', "|"}
                , {';', ";"}, {':', ":"}, {'<', "&lt;"}, {'>', "&gt;"}, {'/', "/"}, {'?', "?"}};
    }
}
