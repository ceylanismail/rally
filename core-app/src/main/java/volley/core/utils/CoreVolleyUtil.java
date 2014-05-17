/*
* Copyright (C) 2014 Zeplin
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
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
