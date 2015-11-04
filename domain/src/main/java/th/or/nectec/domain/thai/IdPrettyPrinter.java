/*
 * Copyright © 2015 NECTEC
 * National Electronics and Computer Technology Center, Thailand
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

package th.or.nectec.domain.thai;

public abstract class IdPrettyPrinter implements PrettyPrinter {
    public static final String DEFAULT_SEPARATOR = "-";

    @Override
    public String print(String id) {
        id = id.trim();
        StringBuilder idFormatted = new StringBuilder();
        for (int i = 0; i < id.length(); i++) {
            if (positionToInsertSeparatorBefore(i))
                idFormatted.append(separator());
            idFormatted.append(id.charAt(i));
        }
        return idFormatted.toString();
    }

    abstract boolean positionToInsertSeparatorBefore(int position);

    @Override
    public String separator() {
        return DEFAULT_SEPARATOR;
    }


}
