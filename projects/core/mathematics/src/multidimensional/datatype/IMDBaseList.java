/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensional.datatype;

/**
 *
 * @author stellarspot
 */
public interface IMDBaseList<Type> extends Iterable<Type> {

    boolean isEmpty();
    int getSize();

    void iterate(IMDIterator<Type> iterator);
}
