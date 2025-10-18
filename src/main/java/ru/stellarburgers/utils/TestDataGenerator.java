package ru.stellarburgers.utils;

import java.util.Random;

public class TestDataGenerator {

    private static final Random random = new Random();

    // Генерация случайного email
    public static String generateEmail() {
        long timestamp = System.currentTimeMillis();
        return "test_user_" + timestamp + "@yandex.ru";
    }



    // Генерация случайного имени
    public static String generateName() {
        String[] names = {"Иван", "Петр", "Сергей", "Алексей", "Дмитрий"};
        return names[random.nextInt(names.length)];


    }

    // Генерация валидного пароля (6+ символов)
    public static String generateValidPassword() {
        return "password" + random.nextInt(10000);
    }

    // Генерация невалидного пароля (меньше 6 символов)
    public static String generateInvalidPassword() {
        return "12345"; // 5 символов - невалидный пароль
    }
}