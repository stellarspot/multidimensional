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

    public CMDObservableList(Type... items) {
        super(items);
    }

    public void addListeners(IMDListListener<Type>... listeners) {
        this.listeners.addTail(listeners);
    }

    @Override
    protected void addHead(Type item) {
        super.addHead(item);
        for (IMDListListener<Type> listener : listeners) {
            listener.add(item);
        }
    }

    @Override
    protected void addTail(Type item) {
        super.addTail(item);
        for (IMDListListener<Type> listener : listeners) {
            listener.add(item);
        }
    }
}
