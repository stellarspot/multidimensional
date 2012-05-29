/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.datatype;

import java.util.Iterator;

/**
 *
 * @author stellarspot
 */
public class MDList<Type> implements IMDList<Type> {

    protected int size;
    protected MDListElement<Type> first;
    protected MDListElement<Type> last;

    public MDList(Type... items) {
        for (Type item : items) {
            addLast(item);
        }
    }
    
    public MDList(Iterable<Type> items) {
        for (Type item : items) {
            addLast(item);
        }
    }
    
    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int getSize() {
        return size;
    }

    protected void addFirst(Type item) {
        if (first == null) {
            first = (last = new MDListElement<Type>(item, null));
        } else {
            first = new MDListElement<Type>(item, first.next);
        }
        size++;
    }

    protected void addLast(Type item) {
        if (last == null) {
            first = (last = new MDListElement<Type>(item, null));
        } else {
            last.next = new MDListElement<Type>(item, null);
            last = last.next;
        }
        size++;
    }

    public void iterate(IMDIterator<Type> iterator) {
        MDListElement<Type> elem = first;
        while (elem != null) {
            iterator.iterate(elem.value);
            elem = elem.next;
        }
    }

    public void show() {

        MDListElement<Type> elem = first;
        while (elem != null) {
            System.out.println("Type: " + elem.value);
            elem = elem.next;
        }
    }

    public Iterator<Type> iterator() {
        return new Iterator<Type>() {

            MDListElement<Type> item = first;

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
