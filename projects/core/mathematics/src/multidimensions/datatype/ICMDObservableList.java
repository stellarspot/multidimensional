/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multidimensions.datatype;

/**
 *
 * @author stallarspot
 */
public interface ICMDObservableList<Type> extends ICMDList<Type>{

    void addListener(IMDListListener<Type> listener);

}
