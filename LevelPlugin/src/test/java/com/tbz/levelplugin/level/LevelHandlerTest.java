package com.tbz.levelplugin.level;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LevelHandlerTest {

    @Test
    void getLevel() {
            int xp = 100;
            int result = (int) (((Math.sqrt(5 * (4 * xp + 5))) + 5) / 10);

            assertEquals(5, result);
    }

}