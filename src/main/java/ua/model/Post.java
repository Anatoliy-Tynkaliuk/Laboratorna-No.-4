//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ua.model;

import java.time.LocalDateTime;
import ua.util.Utils;

public record Post(User user, String content, PostType type, LocalDateTime postDate) {
    public Post {
        Utils.validateString(content, "Post content");
    }

    public static Post create(User user, String content, PostType type) {
        return new Post(user, content, type, LocalDateTime.now());
    }
}
