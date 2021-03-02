package components;

import java.util.Calendar;
import java.util.HashSet;

import MVVM.IObservable;
import MVVM.IReadOnly;
import MVVM.Observable;
import MVVM.ObservableCollection;

import components.Archival;
import components.Completable;
import components.Tag;

/**
 *
 */
public abstract class TodoElement implements Archival{

    private final Observable<String> _name = new Observable<>("");
    public  final IReadOnly<String> name = _name;

    private final Observable<String> _description = new Observable<>("");
    public  final IReadOnly<String> description = _description;

    public  final ObservableCollection<Tag> tags = new ObservableCollection<>(new HashSet<>());

    private final Observable<Boolean> _isArchived = new Observable<>(false);
    public  final IReadOnly<Boolean> isArchived = _isArchived;

    public  final Calendar creationDate;

    public TodoElement() {
        creationDate = Calendar.getInstance();
    }

    /* Getters and Setters */
    @Override
    public final void setArchive(boolean isArchived) { this._isArchived.setValue(isArchived); }
    @Override
    public final boolean isArchived() { return _isArchived.getValue(); }

    public final void setName(String name) { this._name.setValue(name); }
    public final String getName() { return _name.getValue(); }

    public final void setDescription(String name) { this._description.setValue(name); }
    public final String getDescription() { return this._description.getValue(); }

}
