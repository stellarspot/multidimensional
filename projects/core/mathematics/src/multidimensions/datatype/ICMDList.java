/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.datatype;

/**
 *
 * @author stallarspot
 */
public interface ICMDList<Type> extends IMDList<Type> {

    void addFirst(Type... items);
    void addLast(Type... items);

    void addFirst(IMDList<Type> list);
    void addLast(IMDList<Type> list);
    
    
    //void add(Type... items);
    //void add(Iterable<Type> list);
    //void add(IMDList<Type> list);

}
