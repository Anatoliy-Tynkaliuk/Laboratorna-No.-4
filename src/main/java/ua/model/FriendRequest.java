//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ua.model;

import java.time.LocalDateTime;

public record FriendRequest(User sender, User receiver, Status status, LocalDateTime requestDate) {
    public static FriendRequest create(User sender, User receiver) {
        return new FriendRequest(sender, receiver, FriendRequest.Status.PENDING, LocalDateTime.now());
    }

    public static enum Status {
        PENDING,
        ACCEPTED,
        DECLINED;
    }
}
