package ru.stellarburgers.utils;

import java.util.Random;

public class TestDataGenerator {

    private static final Random random = new Random();

    public static String generateEmail() {
        long timestamp = System.currentTimeMillis();
        return "test_user_" + timestamp + "@yandex.ru";
    }

    public static String generateName() {
        String[] names = {"Иван", "Петр", "Сергей", "Алексей", "Дмитрий"};
        return names[random.nextInt(names.length)];
    }

    public static String generateValidPassword() {
        return "password" + random.nextInt(10000);
    }

    public static String generateInvalidPassword() {
        return "12345";
    }
}
