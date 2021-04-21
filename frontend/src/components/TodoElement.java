package components;

import components.observable.IReadOnlyObservable;
import components.observable.Observable;
import components.observable.ObservableCollection;

import java.util.Calendar;
import java.util.HashSet;

/**
 * Abstract class used in Task.java
 * @Author (not Gabby)
 */
public abstract class TodoElement implements Archival{

    private final Observable<String> _name = new Observable<>("");
    public  final IReadOnlyObservable<String> name = _name;

    private final Observable<String> _description = new Observable<>("");
    public  final IReadOnlyObservable<String> description = _description;

    public  final ObservableCollection<Tag> tags = new ObservableCollection<>(new HashSet<>());

    private final Observable<Boolean> _isArchived = new Observable<>(false);
    public  final IReadOnlyObservable<Boolean> isArchived = _isArchived;

    public  final Calendar creationDate;

    /* constructors */
    public TodoElement() {
        creationDate = Calendar.getInstance();
    }

    public TodoElement(String name) {
        creationDate = Calendar.getInstance();
        setName(name);
    }

    /* Getters and Setters */
    @Override
    public final void setArchive(boolean isArchived) { this._isArchived.setValue(isArchived); }
    @Override
    public final boolean isArchived() { return _isArchived.getValue(); }

    public final void setName(String name) {
        if(name == null)
            throw new IllegalArgumentException("name cannot be null");

        this._name.setValue(name);
    }
    public final String getName() { return _name.getValue(); }

    public final void setDescription(String name) {
        if(name == null)
            throw new IllegalArgumentException("name cannot be null");

        this._description.setValue(name);
    }
    public final String getDescription() { return this._description.getValue(); }

}
