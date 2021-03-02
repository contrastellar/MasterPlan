package components.Task;

import MVVM.IReadOnly;
import MVVM.Observable;

import MVVM.ObservableCollection;
import components.Completable;
import components.Tag;
import components.TodoElement;

import java.util.Calendar;
import java.util.HashSet;


public final class Task extends TodoElement implements Completable {

    public final Observable<Boolean> isCompleted = new Observable<>(false);
    public final Observable<IDateGenerator> dueDate = new Observable<>();

    public Task() {
        super();
    }

    @Override
    public boolean isCompleted() {
        return isCompleted.getValue();
    }

    @Override
    public void setCompleted(boolean isComplete) {
        this.isCompleted.setValue(isComplete);
    }

}
