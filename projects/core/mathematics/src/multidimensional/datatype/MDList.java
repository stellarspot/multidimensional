/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensional.datatype;

import java.util.Iterator;

/**
 *
 * @author stellarspot
 */
public class MDList<Type> implements IMDList<Type> {

    protected final int size;
    protected MDListElement<Type> head;

    public MDList(Type... items) {
        int size = 0;
        MDListElement<Type> elem = null;
        for (Type item : items) {
            if (head == null) {
                head = (elem = new MDListElement<Type>(item, null));
            } else {
                elem.next = new MDListElement<Type>(item, null);
                elem = elem.next;
            }
            size++;
        }
        this.size = size;
    }

    public MDList(Iterable<Type> items) {
        int size = 0;
        MDListElement<Type> elem = null;
        for (Type item : items) {
            if (head == null) {
                head = (elem = new MDListElement<Type>(item, null));
            } else {
                elem.next = new MDListElement<Type>(item, null);
                elem = elem.next;
            }
            size++;
        }
        this.size = size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int getSize() {
        return size;
    }

    public void iterate(IMDIterator<Type> iterator) {
        MDListElement<Type> elem = head;
        while (elem != null) {
            iterator.iterate(elem.value);
            elem = elem.next;
        }
    }

    public void show() {

        MDListElement<Type> elem = head;
        while (elem != null) {
            System.out.println("Type: " + elem.value);
            elem = elem.next;
        }
    }

    public Iterator<Type> iterator() {
        return new Iterator<Type>() {
            MDListElement<Type> item = head;

            public boolean hasNext() {
                return item != null;
            }

            public Type next() {
                Type value = item.value;
                item = item.next;
                return value;
            }

            public void remove() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
    }

    protected static class MDListElement<Type> {

        Type value;
        MDListElement<Type> next;

        public MDListElement(Type value, MDListElement<Type> next) {
            this.value = value;
            this.next = next;
        }
    }
}
