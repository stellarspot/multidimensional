/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multidimensions.datatype;

/**
 *
 * @author stellarspot
 */
public interface IMDListListener<Type> {

    void add(Type item);
    void remove(Type item);
}
