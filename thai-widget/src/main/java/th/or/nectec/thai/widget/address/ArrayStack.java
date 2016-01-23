package th.or.nectec.thai.widget.address;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayStack<T> {

    private ArrayList<T> arrayList;

    public ArrayStack() {
        this(new ArrayList<T>());
    }

    public ArrayStack(ArrayList<T> arrayList) {
        this.arrayList = arrayList;
    }

    public boolean isEmpty() {
        return arrayList.isEmpty();
    }

    public void push(T t) {
        arrayList.add(t);
    }

    public T pop() {
        int peekIndex = arrayList.size() - 1;
        return arrayList.remove(peekIndex);
    }

    public T peek() {
        int peekIndex = arrayList.size() - 1;
        return arrayList.get(peekIndex);
    }

    public Iterator<T> getIterator() {
        return arrayList.iterator();
    }
}
