//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ua.util;

import ua.model.PostType;

public class Utils {
    public static void validateEmail(String email) {
        if (!ValidationHelper.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email: " + email);
        }
    }

    public static void validateString(String str, String fieldName) {
        if (!ValidationHelper.isNotEmpty(str)) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
    }

    public static String describePostType(PostType type) {
        String var10000;
        switch (type) {
            case TEXT -> var10000 = "Text post — contains written content only.";
            case IMAGE -> var10000 = "Image post — includes pictures.";
            case VIDEO -> var10000 = "Video post — includes multimedia.";
            default -> throw new MatchException((String)null, (Throwable)null);
        }

        return var10000;
    }
}
