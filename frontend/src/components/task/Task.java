package components.task;

import components.observable.Observable;

import components.Completable;
import components.TodoElement;


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
