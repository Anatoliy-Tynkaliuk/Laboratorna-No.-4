//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ua.model;

import java.time.LocalDateTime;
import ua.util.Utils;

public record Comment(User user, String content, LocalDateTime commentDate) {
    public Comment {
        Utils.validateString(content, "Comment content");
    }

    public static Comment create(User user, String content) {
        return new Comment(user, content, LocalDateTime.now());
    }
}
