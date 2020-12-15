import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Defines Utils, which is a utility class for formatting dates.
 */
class Utils {

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    static Date convertDate(String input) throws ParseException {
        return formatter.parse(input);
    }

    static String formattedDate(Date date) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }
}
