//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ua.model;

import java.time.LocalDate;
import ua.util.Utils;

public record User(String username, String email, LocalDate registrationDate) {
    public User {
        Utils.validateString(username, "Username");
        Utils.validateEmail(email);
    }

    public static User create(String username, String email) {
        return new User(username, email, LocalDate.now());
    }
}
