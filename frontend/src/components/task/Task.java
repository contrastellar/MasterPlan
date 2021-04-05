package components.task;

import components.Completable;
import components.TodoElement;
import components.observable.Observable;


public final class Task extends TodoElement implements Completable {

    public final Observable<Boolean> isCompleted = new Observable<>(false);
    public final Observable<IDateGenerator> dueDate = new Observable<>();

    public Task() {
        super();
    }
    public Task(String name) {
        super(name);
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