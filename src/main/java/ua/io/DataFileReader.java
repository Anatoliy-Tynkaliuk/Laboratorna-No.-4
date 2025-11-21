package ua.io;

import ua.model.*;
import ua.util.Utils;
import ua.exceptions.InvalidDataException;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DataFileReader {

    private static final Logger logger = Logger.getLogger(DataFileReader.class.getName());

    public record DataResult(
            List<User> users,
            List<Post> posts,
            List<Comment> comments,
            List<Message> messages,
            List<FriendRequest> requests
    ) {}

    public static DataResult readData(String fileName) throws InvalidDataException {

        logger.info("Початок читання файлу: " + fileName);

        List<User> users = new ArrayList<>();
        List<Post> posts = new ArrayList<>();
        List<Comment> comments = new ArrayList<>();
        List<Message> messages = new ArrayList<>();
        List<FriendRequest> requests = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            int num = 0;

            while ((line = br.readLine()) != null) {
                num++;

                if (line.isBlank()) continue;

                String[] parts = line.split(";");
                String type = parts[0].trim().toUpperCase();

                try {
                    switch (type) {

                        case "USER" -> {
                            if (parts.length != 3)
                                throw new InvalidDataException("USER рядок некоректний: " + line);
                            String username = parts[1].trim();
                            String email = parts[2].trim();
                            Utils.validateString(username, "Username");
                            Utils.validateEmail(email);
                            users.add(User.create(username, email));
                        }

                        case "POST" -> {
                            if (parts.length != 4)
                                throw new InvalidDataException("POST рядок некоректний: " + line);
                            String username = parts[1].trim();
                            String content = parts[2].trim();
                            PostType pt = PostType.valueOf(parts[3].trim());
                            posts.add(Post.create(new User(username, "none@mail.com", null), content, pt));
                        }

                        case "COMMENT" -> {
                            if (parts.length != 3)
                                throw new InvalidDataException("COMMENT рядок некоректний: " + line);
                            String username = parts[1].trim();
                            String content = parts[2].trim();
                            comments.add(Comment.create(new User(username, "none@mail.com", null), content));
                        }

                        case "MESSAGE" -> {
                            if (parts.length != 4)
                                throw new InvalidDataException("MESSAGE рядок некоректний: " + line);
                            String sender = parts[1].trim();
                            String receiver = parts[2].trim();
                            String content = parts[3].trim();
                            messages.add(Message.create(
                                    new User(sender, "none@mail.com", null),
                                    new User(receiver, "none@mail.com", null),
                                    content));
                        }

                        case "REQUEST" -> {
                            if (parts.length != 4)
                                throw new InvalidDataException("REQUEST рядок некоректний: " + line);
                            String sender = parts[1].trim();
                            String receiver = parts[2].trim();
                            FriendRequest.Status st =
                                    FriendRequest.Status.valueOf(parts[3].trim());
                            requests.add(new FriendRequest(
                                    new User(sender, "none@mail.com", null),
                                    new User(receiver, "none@mail.com", null),
                                    st,
                                    LocalDateTime.now()));
                        }

                        default ->
                                throw new InvalidDataException("Невідомий тип запису у рядку: " + line);
                    }

                    logger.info("Оброблено рядок: " + line);

                } catch (Exception e) {
                    logger.warning("Помилка у рядку " + ": " + e.getMessage());
                    System.out.println("Помилка у рядку " + ": " + line);
                }
            }

        } catch (IOException e) {
            throw new InvalidDataException("Помилка читання файлу", e);
        }

        logger.info("Файл успішно оброблено.");

        return new DataResult(users, posts, comments, messages, requests);
    }
}
