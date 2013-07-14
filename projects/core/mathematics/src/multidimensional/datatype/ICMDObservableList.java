/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multidimensional.datatype;

/**
 *
 * @author stellarspot
 */
public interface ICMDObservableList<Type> extends ICMDList<Type>{

    void addListeners(IMDListListener<Type>... listeners);

}
