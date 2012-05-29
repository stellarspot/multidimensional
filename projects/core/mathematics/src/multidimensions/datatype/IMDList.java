/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.datatype;

/**
 *
 * @author stellarspot
 */
public interface IMDList<Type> extends Iterable<Type>{

    boolean isEmpty();
    int getSize();

    void iterate(IMDIterator<Type> iterator);


}
