package components.Task;

import java.util.Calendar;
import java.util.HashSet;

import MVVM.IReadOnly;
import MVVM.Observable;
import MVVM.ObservableCollection;

import components.Archival;
import components.Completable;
import components.Tag;


/**
 *c
 */
public final class Task implements Completable, Archival {
    /**
     * DATA FIELDS
     * name: User identifier for the task.
     * decription:  User description of the task.
     * tags: The corresponding tags for the task.
     * isArchived: The archived state of the task.
     * isCompleted: The status of a task
     * creationDate: The date and time when the task was first created.
     * dueDate: OPTIONAL The due date of the task.
     */
    private final Observable<String> _name = new Observable<>("");
    public  final IReadOnly<String> name = _name;

    private final Observable<String> _description = new Observable<>("");
    public  final IReadOnly<String> description = _description;

    private final Observable<Boolean> _isArchived = new Observable<>(false);
    public  final IReadOnly<Boolean> isArchived = _isArchived;

    public final ObservableCollection<Tag> tags = new ObservableCollection<>(new HashSet<>());
    public final Observable<Boolean> isCompleted = new Observable<>(false);
    public final Observable<IDateGenerator> dueDate = new Observable<>();

    public final Calendar creationDate;

    public Task() {
        creationDate = Calendar.getInstance();
    }

    public Task(String name) {
        if(name == null)
            throw new IllegalArgumentException("name cannot be null");

        creationDate = Calendar.getInstance();
    }

    @Override
    public void setArchive(Boolean isArchived) {
        if(isArchived == null)
            throw new IllegalArgumentException("isArchived cannot be null");
        this._isArchived.setValue(isArchived);
    }

    @Override
    public Boolean isArchived() {
        return this.isArchived.getValue();
    }

    @Override
    public boolean isCompleted() {
        return isCompleted.getValue();
    }

    @Override
    public void setComplete(boolean isComplete) {
        this.isCompleted.setValue(isComplete);
    }

    public void setName(String name) {
        if(name == null)
            throw new IllegalArgumentException("name cannot be null");

        this._name.setValue(name);
    }

    public void setDescription(String description) {
        if(description == null)
            throw new IllegalArgumentException("description cannot be null");

        this._description.setValue(description);
    }

}
