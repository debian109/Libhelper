package UIHelper;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nam Tran on 31-Dec-15.
 */
public class TextAndEditText {

    /**
     * Is English
     *
     * @param str The string to be verified
     * @return Is English
     */
    public static boolean isEnglish(String str) {
        byte[] bytes = str.getBytes();
        int i = bytes.length;
        int j = str.length();
        if (i == j) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Determine whether to repeat a string
     *
     * @param str The string to be verified
     * @return Whether duplicate strings
     */
    public static boolean isRepeatedString(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        int len = str.length();
        if (len <= 1) {
            return false;
        } else {
            char firstChar = str.charAt(0);// The first character
            for (int i = 1; i < len; i++) {
                char nextChar = str.charAt(i);// The i-th character
                if (firstChar != nextChar) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Determine whether the purely digital
     *
     * @param str The string to be verified
     * @return If it is pure digital
     */
    public static boolean isNumbericString(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }

        Pattern p = Pattern.compile("^[0-9]+$");// From the beginning to the end must all be digital
        Matcher m = p.matcher(str);

        return m.find();
    }

    /**
     * Determine whether a string is a continuous and other digital 45678901
     *
     * @param str The string to be verified
     * @return Whether the consecutive number
     */
    public static boolean isContinuousNum(String str) {
        if (TextUtils.isEmpty(str))
            return false;
        if (!isNumbericString(str))
            return true;
        int len = str.length();
        for (int i = 0; i < len - 1; i++) {
            char curChar = str.charAt(i);
            char verifyChar = (char) (curChar + 1);
            if (curChar == '9')
                verifyChar = '0';
            char nextChar = str.charAt(i + 1);
            if (nextChar != verifyChar) {
                return false;
            }
        }
        return true;
    }

    /**
     * Is the phone number
     *
     * @param num The string to be verified
     * @return Is the phone number
     */
    public static boolean isPhoneNum(String num) {
        // Ensure that every member is digital
        return !TextUtils.isEmpty(num) && num.matches("1[0-9]{10}")
                && !isRepeatedString(num) && !isContinuousNum(num);
    }

    /**
     * Is a valid phone number
     *
     * @param str The string to be verified
     * @return Is a valid phone number
     */
    public static boolean isValidTelephoneNumber(String str) {
        Pattern pattern = Pattern.compile("^(\\(\\d{0,4}\\)|\\d{0,4}-)?(\\(\\d{0,4}\\)|\\d{0,4}-)?\\d{7,}$");
        Matcher isValid = pattern.matcher(str);
        if (!isValid.matches()) {
            return false;
        }
        return true;
    }

    /**
     * Is a valid phone number
     *
     * @param str The string to be verified
     * @return Is a valid phone number
     */
    public static boolean isValidPhoneNumber(String str) {
        Pattern pattern = Pattern.compile("(^0?(13[0-9]|15[0-9]|18[0-9])[0-9]{8}$)|(^\\d{3,4}-\\d{7,8}(-\\d{1,4})?$)");
        Matcher isValid = pattern.matcher(str);
        if (!isValid.matches()) {
            return false;
        }
        return true;
    }

    /**
     * It is a valid mailbox
     *
     * @param str The string to be verified
     * @return It is a valid mailbox
     */
    public static boolean isValidEmail(String str) {
        Pattern pattern = Pattern.compile("[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?");
        Matcher isValid = pattern.matcher(str);
        if (!isValid.matches()) {
            return false;
        }
        return true;

    }

    /**
     * Whether it is pure letters
     *
     * @param str The string to be verified
     * @return Whether it is pure letters
     */
    public static boolean isAlphaBetaString(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }

        Pattern p = Pattern.compile("^[a-zA-Z]+$");// From the beginning to the end must be all letters or numbers
        Matcher m = p.matcher(str);

        return m.find();
    }

    /**
     * To determine whether the string is a continuous letter xyZ aBcd etc.
     *
     * @param str The string to be verified
     * @return Whether consecutive letters
     */
    public static boolean isContinuousWord(String str) {
        if (TextUtils.isEmpty(str))
            return false;
        if (!isAlphaBetaString(str))
            return true;
        int len = str.length();
        String local = str.toLowerCase();
        for (int i = 0; i < len - 1; i++) {
            char curChar = local.charAt(i);
            char verifyChar = (char) (curChar + 1);
            if (curChar == 'z')
                verifyChar = 'a';
            char nextChar = local.charAt(i + 1);
            if (nextChar != verifyChar) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determine whether the pure letters or numbers
     *
     * @param str The string to be verified
     * @return If it is pure letters or numbers
     */
    public static boolean isAlphaBetaOrNumbericString(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }

        Pattern p = Pattern.compile("^[a-zA-Z0-9]+$");// From the beginning to the end must be all letters or numbers
        Matcher m = p.matcher(str);

        return m.find();
    }

    /**
     * Contains Chinese
     *
     * @param str The string to be verified
     * @return Contains Chinese
     */
    public static boolean isContainsChinese(String str) {
        String regEx = "[\u4e00-\u9fa5]";
        Pattern pat = Pattern.compile(regEx);
        Matcher matcher = pat.matcher(str);
        boolean flg = false;
        if (matcher.find()) {
            flg = true;
        }
        return flg;
    }

    /**
     * Is Date
     * 20,120,506 total eight, the top four - in the middle of two - month, the last two - day
     *
     * @param date    The string to be verified
     * @param yearlen yearlength
     * @return It is true date
     */
    public static boolean isRealDate(String date, int yearlen) {
        int len = 4 + yearlen;
        if (date == null || date.length() != len)
            return false;

        if (!date.matches("[0-9]+"))
            return false;

        int year = Integer.parseInt(date.substring(0, yearlen));
        int month = Integer.parseInt(date.substring(yearlen, yearlen + 2));
        int day = Integer.parseInt(date.substring(yearlen + 2, yearlen + 4));

        if (year <= 0)
            return false;
        if (month <= 0 || month > 12)
            return false;
        if (day <= 0 || day > 31)
            return false;

        switch (month) {
            case 4:
            case 6:
            case 9:
            case 11:
                return day > 30 ? false : true;
            case 2:
                if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
                    return day > 29 ? false : true;
                return day > 28 ? false : true;
            default:
                return true;
        }
    }

    /**
     * The color values ​​into the form #RRGGBB
     *
     * @param color
     * @return
     */
    public static String getRGBColor(int color) {
        return "#" + Integer.toHexString(color | 0xff000000).substring(2);
    }

    /**
     * The color values ​​into #AARRGGBB form
     *
     * @param color
     * @return
     */
    public static String getARGBColor(int color) {
        return "#" + Long.toHexString(color | 0xffffffff00000000L).substring(8);
    }

    /**
     *	 Get colored text
     *
     * @param text
     * @param color
     * @return
     */
    public static CharSequence getColoredText(String text, int color) {
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        builder.setSpan(new ForegroundColorSpan(color), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }

    /**
     * @see #getColoredText(String, int)
     */
    public static CharSequence getColoredText(Context context, String text, int colorId) {
        return getColoredText(text, context.getResources().getColor(colorId));
    }

    /**
     * @see #getColoredText(String, int)
     */
    public static CharSequence getColoredText(Context context, int textId, int colorId) {
        return getColoredText(context.getString(textId), context.getResources().getColor(colorId));
    }

    /**
     * @param str The string to be verified
     * @return true if empty
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.trim().length() <= 0)
            return true;
        else
            return false;
    }
}
