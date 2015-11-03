package com.deepakvadgama.javajokes;

import org.junit.Test;

public class JokerTest {
    @Test
    public void test() {
        JokeTeller jokeTeller = new JokeTeller();
        for (int i = 0; i < 5; i++) {
            assert jokeTeller.getJoke().length() != 0;
        }
    }
}