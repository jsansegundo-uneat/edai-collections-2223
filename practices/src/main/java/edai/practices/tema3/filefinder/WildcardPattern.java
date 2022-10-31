package edai.practices.tema3.filefinder;

import java.util.regex.Pattern;

public abstract class WildcardPattern {
    public static Pattern compile(String pattern) {
        StringBuilder builder = new StringBuilder();

        builder.append("^");
        for (char c : pattern.toCharArray()) {
            builder.append(wildcardCharToRegex(c));
        }
        builder.append("$");

        String regexPattern = builder.toString();
        return Pattern.compile(regexPattern);
    }

    public static boolean isMatch(String pattern, String text) {
        return compile(pattern).matcher(text).matches();
    }

    private static String wildcardCharToRegex(char c) {
        switch (c) {
            case '?':
                return ".";
            case '*':
                return ".+";
            case '.':
                return "\\.";
            case '\\':
                return "\\\\";
            default:
                return Character.toString(c);
        }
    }
}
