//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ua.model;

import java.time.LocalDateTime;
import ua.util.Utils;

public record Message(User sender, User receiver, String content, LocalDateTime sentDate) {
    public Message {
        Utils.validateString(content, "Message content");
    }

    public static Message create(User sender, User receiver, String content) {
        return new Message(sender, receiver, content, LocalDateTime.now());
    }
}
