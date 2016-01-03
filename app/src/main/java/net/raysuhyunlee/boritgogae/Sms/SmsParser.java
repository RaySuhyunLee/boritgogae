package net.raysuhyunlee.boritgogae.Sms;

import net.raysuhyunlee.boritgogae.DB.Money;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by SuhyunLee on 2015. 12. 8..
 */
public class SmsParser {
    public static final Pattern payPattern = Pattern.compile("[(출금)(승인)(체크)]");
    public static final Pattern earnPattern = Pattern.compile("(입금)");
    public static final Pattern wonPattern = Pattern.compile("([\\d,.]+원)");

    /**
     * @param msg sms message body
     * @return A new money instance. Null if it's not a money-concerned message
     */
    public static Money parse(String msg) {
        Matcher wonMatcher = wonPattern.matcher(msg);
        if (wonMatcher.find()) {
            String wonString = msg.substring(wonMatcher.start(), wonMatcher.end())
                    .replace("원", "").replace(",", "");
            int won = Integer.valueOf(wonString);

            String[] words = msg.split("[\n\r]+");
            String name = words[words.length - 1];

            Matcher payMatcher = payPattern.matcher(msg);
            Matcher earnMatcher = earnPattern.matcher(msg);
            if (payMatcher.find()) {
                return new Money(name, won);
            } else if (earnMatcher.find()) {
                return new Money(name, -won);
            }
        }

        return null;
    }
}
