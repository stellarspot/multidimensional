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
public class CMDList<Type> implements ICMDList<Type> {

    protected int size;
    protected MDListElement<Type> head;
    protected MDListElement<Type> tail;

    public CMDList(Type... items) {
        for (Type item : items) {
            addTail(item);
        }
    }

    public CMDList(Iterable<Type> items) {
        for (Type item : items) {
            addTail(item);
        }
    }

    public void addHead(Type... items) {
        for (Type item : items) {
            addHead(item);
        }
    }

    public void addTail(Type... items) {
        for (Type item : items) {
            addTail(item);
        }
    }

    public void addHead(Iterable<Type> items) {
        for (Type item : items) {
            addHead(item);
        }
    }

    public void addTail(Iterable<Type> items) {
        for (Type item : items) {
            addTail(item);
        }
    }
    
    protected void addHead(Type item) {
        if (head == null) {
            head = (tail = new MDListElement<Type>(item, null));
        } else {
            head = new MDListElement<Type>(item, head.next);
        }
        size++;
    }

    protected void addTail(Type item) {
        if (tail == null) {
            head = (tail = new MDListElement<Type>(item, null));
        } else {
            tail.next = new MDListElement<Type>(item, null);
            tail = tail.next;
        }
        size++;
    }
    

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int getSize() {
        return size;
    }
    
    
    @Override
    public void clear() {
        head = (tail = null);
    }

    @Override
    public IMDList<Type> getIMDList() {
        return new MDList<Type>(this);
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
