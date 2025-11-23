package ua;
import ua.io.DataFileReader;
import ua.exceptions.InvalidDataException;

import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        logger.info("Старт програми.");

        String path = "src/main/resources/data.txt";

        try {
            logger.info("Спроба зчитати файл: " + path);

            var result = DataFileReader.readData(path);

            logger.info("Файл успішно зчитано.");

            System.out.println("\n=== USERS ===");
            result.users().forEach(System.out::println);

            System.out.println("\n=== POSTS ===");
            result.posts().forEach(System.out::println);

            System.out.println("\n=== COMMENTS ===");
            result.comments().forEach(System.out::println);

            System.out.println("\n=== MESSAGES ===");
            result.messages().forEach(System.out::println);

            System.out.println("\n=== REQUESTS ===");
            result.requests().forEach(System.out::println);

        } catch (InvalidDataException e) {

            logger.severe("Помилка при зчитуванні даних: " + e.getMessage());
            System.out.println("Помилка: " + e.getMessage());

        } finally {

            logger.info("Програма завершилася.");

        }
    }
}
