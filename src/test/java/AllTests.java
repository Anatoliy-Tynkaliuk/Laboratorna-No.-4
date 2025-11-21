import org.junit.jupiter.api.Test;
import ua.model.*;
import static org.junit.jupiter.api.Assertions.*;

public class AllTests {

    @Test
    void testValidUserCreated() {
        User user = User.create("petro", "petro@mail.com");

        assertEquals("petro", user.username());
        assertEquals("petro@mail.com", user.email());
        assertNotNull(user.registrationDate());
    }

    @Test
    void testEmptyUsernameThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> User.create("", "email@gmail.com"));
    }

    @Test
    void testInvalidEmailThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> User.create("ivan", "wrong_email"));
    }

    @Test
    void testValidCommentCreated() {
        User user = User.create("petro", "petro@mail.com");
        Comment c = Comment.create(user, "Nice!");

        assertEquals("Nice!", c.content());
        assertNotNull(c.commentDate());
    }

    @Test
    void testEmptyCommentThrowsException() {
        User user = User.create("petro", "petro@mail.com");

        assertThrows(IllegalArgumentException.class,
                () -> Comment.create(user, " "));
    }

    @Test
    void testValidPostCreated() {
        User user = User.create("petro", "petro@mail.com");
        Post post = Post.create(user, "Hello!", PostType.TEXT);

        assertEquals("Hello!", post.content());
        assertEquals(PostType.TEXT, post.type());
        assertNotNull(post.postDate());
    }

    @Test
    void testEmptyContentThrowsException() {
        User user = User.create("ivan", "ivan@mail.com");

        assertThrows(IllegalArgumentException.class,
                () -> Post.create(user, "   ", PostType.TEXT));
    }

    @Test
    void testValidMessageCreated() {
        User s = User.create("petro", "petro@mail.com");
        User r = User.create("ivan", "ivan@mail.com");

        Message m = Message.create(s, r, "Hello!");

        assertEquals("Hello!", m.content());
        assertNotNull(m.sentDate());
    }

    @Test
    void testEmptyMessageThrowsException() {
        User s = User.create("petro", "petro@mail.com");
        User r = User.create("ivan", "ivan@mail.com");

        assertThrows(IllegalArgumentException.class,
                () -> Message.create(s, r, ""));
    }

    @Test
    void testValidFriendRequestCreated() {
        User s = User.create("petro", "petro@mail.com");
        User r = User.create("ivan", "ivan@mail.com");

        FriendRequest req = FriendRequest.create(s, r);

        assertEquals(FriendRequest.Status.PENDING, req.status());
        assertNotNull(req.requestDate());
    }

}
