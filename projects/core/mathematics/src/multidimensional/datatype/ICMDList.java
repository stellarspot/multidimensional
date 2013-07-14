/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensional.datatype;

/**
 *
 * @author stallarspot
 */
public interface ICMDList<Type> extends IMDBaseList<Type> {

    void addHead(Type... items);
    void addTail(Type... items);

    void addHead(Iterable<Type> items);
    void addTail(Iterable<Type> items);
    
    IMDList<Type> getIMDList();
    
    void clear();
}
