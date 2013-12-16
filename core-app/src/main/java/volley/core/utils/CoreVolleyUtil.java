package volley.core.utils;

import com.android.volley.Request;

import java.lang.reflect.Field;

public class CoreVolleyUtil {

    /**
     * @param methodCode integer value of HTTP Methods
     * @return String value of HTTP Methods defined in {@link com.android.volley.Request.Method}
     */
    public static String getMethodName(int methodCode) throws IllegalAccessException {
        Field[] fields = Request.Method.class.getDeclaredFields();

        for (Field field : fields) {
            if (methodCode == field.getInt(null)) {
                return field.getName();
            }
        }

        return null;
    }
}
