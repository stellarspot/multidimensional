/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multidimensions.datatype;

/**
 *
 * @author stellarspot
 */
public class CMDObservableList<Type> extends CMDList<Type> implements ICMDObservableList<Type> {

    ICMDList<IMDListListener<Type>> listeners = new CMDList<IMDListListener<Type>>();

    public void addListeners(IMDListListener<Type>... listeners) {
        this.listeners.addLast(listeners);
    }

    @Override
    protected void addFirst(Type item) {
        super.addFirst(item);
        for (IMDListListener<Type> listener : listeners) {
            listener.add(item);
        }
    }

    @Override
    protected void addLast(Type item) {
        super.addLast(item);
        for (IMDListListener<Type> listener : listeners) {
            listener.add(item);
        }
    }
}
