/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multidimensional.datatype;

/**
 *
 * @author stellarspot
 */
public interface IMDListListener<Type> {

    void add(Type item);
    void remove(Type item);
}
