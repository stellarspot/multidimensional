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
public class CMDList<Type> extends MDList<Type> implements ICMDList<Type> {

    public CMDList(Type... items) {
        super(items);
    }

    public CMDList(Iterable<Type> items) {
        super(items);
    }

    public void addFirst(Type... items) {
        for(Type item: items){
            super.addFirst(item);
        }
    }

    public void addLast(Type... items) {
        for(Type item: items){
            super.addLast(item);
        }
    }

    public void addFirst(Iterable<Type> items) {
        for(Type item: items){
            super.addFirst(item);
        }
    }

    public void addLast(Iterable<Type> items) {
        for(Type item: items){
            super.addLast(item);
        }
    }
    
    @Override
    public void clear() {
        first = last = null;
    }
    
}
