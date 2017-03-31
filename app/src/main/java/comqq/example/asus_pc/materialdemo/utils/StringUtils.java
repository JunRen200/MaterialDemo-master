package comqq.example.asus_pc.materialdemo.utils;

/**
 * Created by asus-pc on 2016/12/28.
 */

public class StringUtils {
    public static final String REGEX_USER_NAME="^[a-zA-Z]\\w{2,19}$";
    public static final String REGEX_PASWORD="^[0-9]{3,20}$";
    public static boolean isValidUserName(String userName){
        return userName.matches(REGEX_USER_NAME);
    }
    public static boolean isValidPassword(String password){
        return password.matches(REGEX_PASWORD);
    }
}
