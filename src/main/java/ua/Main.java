package ua;

import ua.model.*;
import ua.repository.GenericRepository;
import ua.repository.IdentityExtractor;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== ТЕСТ РЕПОЗИТОРІЇВ ===\n");

        // ▓▓▓ 1. Створюємо репозиторії
        GenericRepository<User> userRepo = new GenericRepository<>(User::username);
        GenericRepository<Post> postRepo = new GenericRepository<>(p -> p.user().username() + "#" + p.postDate());
        GenericRepository<Comment> commentRepo = new GenericRepository<>(c -> c.user().username() + "#" + c.commentDate());
        GenericRepository<Message> messageRepo = new GenericRepository<>(m -> m.sender().username() + "->" + m.receiver().username() + "#" + m.sentDate());
        GenericRepository<FriendRequest> requestRepo = new GenericRepository<>(r -> r.sender().username() + "->" + r.receiver().username());

        // ▓▓▓ 2. Створюємо об'єкти
        User u1 = User.create("alex", "alex@mail.com");
        User u2 = User.create("bob", "bob@mail.com");
        User u3 = User.create("alex", "duplicate@mail.com"); // Дублікат!!! username однаковий

        Post p1 = Post.create(u1, "Hello world!", PostType.TEXT);
        Post p2 = Post.create(u2, "Image post", PostType.IMAGE);

        Comment c1 = Comment.create(u1, "Nice post!");
        Comment c2 = Comment.create(u2, "Cool!");

        Message m1 = Message.create(u1, u2, "Hi Bob!");
        Message m2 = Message.create(u2, u1, "Hi Alex!");

        FriendRequest fr1 = FriendRequest.create(u1, u2);
        FriendRequest fr2 = FriendRequest.create(u2, u1);

        // ▓▓▓ 3. Додавання в репозиторії (з логуванням)
        userRepo.add(u1);
        userRepo.add(u2);
        userRepo.add(u3); // дубль → лог Warning

        postRepo.add(p1);
        postRepo.add(p2);

        commentRepo.add(c1);
        commentRepo.add(c2);

        messageRepo.add(m1);
        messageRepo.add(m2);

        requestRepo.add(fr1);
        requestRepo.add(fr2);

        // ▓▓▓ 4. Пошук за identity
        System.out.println("\n=== Пошук користувача за identity 'alex' ===");
        System.out.println(userRepo.findByIdentity("alex"));

        // ▓▓▓ 5. Виведення всіх даних
        System.out.println("\n=== USERS ===");
        userRepo.getAll().forEach(System.out::println);

        System.out.println("\n=== POSTS ===");
        postRepo.getAll().forEach(System.out::println);

        System.out.println("\n=== COMMENTS ===");
        commentRepo.getAll().forEach(System.out::println);

        System.out.println("\n=== MESSAGES ===");
        messageRepo.getAll().forEach(System.out::println);

        System.out.println("\n=== FRIEND REQUESTS ===");
        requestRepo.getAll().forEach(System.out::println);
    }
}
