package ru.ticketeen.research;


import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ItemTest {
    @Test
    public void toLowerCase() throws Exception {
        assertTrue("КОРЗИНА ДЛЯ БЕЛЬЯ".toLowerCase()
                .contains("корзина".toLowerCase()));
    }
}
