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

    void addFirst(Iterable<Type> items);
    void addLast(Iterable<Type> items);

    //void clear();
    void clear();
}
