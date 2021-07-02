package us.peaky.counterpack.util;

public class EmojiUtils {
    private static final String[] emojiDigits = {
            "0️⃣", "1️⃣", "2️⃣", "3️⃣", "4️⃣", "5️⃣", "6️⃣", "7️⃣", "8️⃣", "9️⃣"
    };

    public static String constructFromEmoji(int number) {
        StringBuilder builder = new StringBuilder();
        while (number != 0) {
            builder.append(emojiDigits[number % 10]);
            number /= 10;
        }

        return builder.toString();
    }

    public static String getPlaceSymbol(int place) {
        switch (place) {
            case 1:
                return "\uD83E\uDD47";    // 🥇
            case 2:
                return "\uD83E\uDD48";    // 🥈
            case 3:
                return "\uD83E\uDD49";    // 🥉
            default:
                return constructFromEmoji(place);
        }
    }
}
