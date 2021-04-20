package components.task;

import components.Archival;
import components.Completable;
import components.TodoElement;
import components.observable.IReadOnlyObservable;
import components.observable.Observable;

import java.util.Calendar;


public final class Task extends TodoElement implements Completable, Archival{

    private final Observable<Boolean> _isCompleted = new Observable<>(false);
    public final IReadOnlyObservable<Boolean> isCompleted = _isCompleted;

    private final Observable<Boolean> _isArchived = new Observable<>(false);
    public final IReadOnlyObservable<Boolean> isArchived = _isArchived;

    private final Observable<Calendar> _dueDate = new Observable<>(null);
    public final IReadOnlyObservable<Calendar> dueDate = _dueDate;

    private final Observable<String> _notes = new Observable<>("");
    public final IReadOnlyObservable<String> notes = _notes;

    private final Observable<String> _author = new Observable<>("");
    private final IReadOnlyObservable<String> author = new Observable<>("");

    public Task() {
        super();
    }
    public Task(String name) {
        super(name);
    }

    @Override
    public boolean isCompleted() {
        return _isCompleted.getValue();
    }

    @Override
    public void setCompleted(boolean isComplete) {
        this._isCompleted.setValue(isComplete);
    }

    public void setArchived(boolean isArchived) {
        this._isArchived.setValue(isArchived);
    }

    public void setDueDate(Calendar dueDate) {
        this._dueDate.setValue(dueDate);
    }

    public Calendar getDueDate() {
        return _dueDate.getValue();
    }

    public void setNotes(String notes) {
        if(notes == null)
            throw new IllegalArgumentException("Task.setNotes() - notes can not be null");

        this._notes.setValue(notes);
    }

    public String getNotes() {
        return _notes.getValue();
    }

    public void setAuthor(String author) {
        if(author == null)
            throw new IllegalArgumentException("Task.setAuthor() - author can not be null");

        this._author.setValue(author);
    }

    public String getAuthor() {
        return this._author.getValue();
    }

}
