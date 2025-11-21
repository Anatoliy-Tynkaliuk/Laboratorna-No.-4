package ua.repository;

import org.junit.jupiter.api.Test;
import ua.model.User;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTests {

    @Test
    void testAddAndFind() {
        GenericRepository<User> repo = new GenericRepository<>(User::username);

        User u = User.create("alex", "alex@mail.com");
        repo.add(u);

        assertEquals(u, repo.findByIdentity("alex"));
    }

    @Test
    void testDuplicateIgnored() {
        GenericRepository<User> repo = new GenericRepository<>(User::username);

        User u1 = User.create("alex", "a@mail.com");
        User u2 = User.create("alex", "b@mail.com");

        repo.add(u1);
        repo.add(u2);

        assertEquals(1, repo.getAll().size());
    }

    @Test
    void testRemove() {
        GenericRepository<User> repo = new GenericRepository<>(User::username);

        User u = User.create("alex", "a@mail.com");

        repo.add(u);
        repo.remove(u);

        assertNull(repo.findByIdentity("alex"));
    }

    @Test
    void testGetAll() {
        GenericRepository<User> repo = new GenericRepository<>(User::username);

        repo.add(User.create("alex", "a@mail.com"));
        repo.add(User.create("bob", "b@mail.com"));

        assertEquals(2, repo.getAll().size());
        assertTrue(repo.getAll().stream().anyMatch(u -> u.username().equals("alex")));
    }

}
