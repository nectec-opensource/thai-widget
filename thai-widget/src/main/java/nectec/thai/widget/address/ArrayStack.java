/*
 * Copyright (c) 2016 NECTEC
 *   National Electronics and Computer Technology Center, Thailand
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package nectec.thai.widget.address;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ArrayStack<T> {

    private List<T> list;

    public ArrayStack() {
        this(new ArrayList<T>());
    }

    public ArrayStack(List<T> list) {
        this.list = list;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void push(T t) {
        list.add(t);
    }

    public T pop() {
        int peekIndex = list.size() - 1;
        return list.remove(peekIndex);
    }

    public T peek() {
        int peekIndex = list.size() - 1;
        return list.get(peekIndex);
    }

    public Iterator<T> getIterator() {
        return list.iterator();
    }
}
