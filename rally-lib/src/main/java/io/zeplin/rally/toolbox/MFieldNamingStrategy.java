/*
 * Copyright (C) 2014 Zeplin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * you may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.zeplin.rally.toolbox;

import com.google.gson.FieldNamingStrategy;

import java.lang.reflect.Field;

public class MFieldNamingStrategy implements FieldNamingStrategy {

    /**
     * Field member prefix, if any exists
     */
    private final String FIELD_NAME_PREFIX = "m";

    @Override
    public String translateName(Field field) {
        String fieldName = field.getName();

        if (fieldName.startsWith(FIELD_NAME_PREFIX) && Character.isUpperCase(fieldName.charAt(1))) {
            //skip first letter
            // convert first letter of remaining field name to lowercase
            fieldName = Character.toLowerCase(fieldName.charAt(1))
                    + fieldName.substring(2);
        }

        return fieldName;
    }
}
