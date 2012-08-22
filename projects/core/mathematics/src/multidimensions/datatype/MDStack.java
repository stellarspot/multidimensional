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
public class MDStack<Type> implements IMDStack<Type>{

    private MDElement<Type> head;

    public boolean isEmpty() {
        return head == null;
    }

    public Type pop() {
        
        MDElement<Type> elem = head;
        if(head != null){
            head = head.next;
        }
        Type value = elem.value;
        
        return value;
    }

    public void push(Type item) {
        head = new MDElement<Type>(item, head);
    }
    
    public Iterator<Type> iterator() {
        return new Iterator<Type>() {

            MDElement<Type> item = head;

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
    
    
    protected static class MDElement<Type> {

        Type value;
        MDElement<Type> next;

        public MDElement(Type value, MDElement<Type> next) {
            this.value = value;
            this.next = next;
        }
    }

    

}
