/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multidimensions.datatype;

/**
 *
 * @author stellarspot
 */
public interface IMDStack<Type> extends Iterable<Type> {

    boolean isEmpty();

    Type pop();
    void push(Type item);

}
