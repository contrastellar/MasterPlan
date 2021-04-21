package components.task;

import components.Completable;
import components.TodoElement;
import components.observable.IReadOnlyObservable;
import components.observable.Observable;

import java.util.Calendar;


public final class Task extends TodoElement implements Completable{

    private final Observable<Boolean> _isCompleted = new Observable<>(false);
    public final IReadOnlyObservable<Boolean> isCompleted = _isCompleted;

    private final Observable<Calendar> _dueDate = new Observable<>(null);
    public final IReadOnlyObservable<Calendar> dueDate = _dueDate;


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

    public void setDueDate(Calendar dueDate) {
        this._dueDate.setValue(dueDate);
    }

    public Calendar getDueDate() {
        return _dueDate.getValue();
    }

}
