/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multidimensions.datatype;

/**
 *
 * @author stellarspot
 */
public interface ICMDObservableList<Type> extends ICMDList<Type>{

    void addListeners(IMDListListener<Type>... listeners);

}
