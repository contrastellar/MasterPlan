package components.Task;

import java.util.Calendar;
import java.util.HashSet;

import MVVM.Observable;
import MVVM.ObservableCollection;

import components.Archival;
import components.Completable;
import components.Tag;

/**
 *
 */
public final class Task implements Completable, Archival {

    /**
     * User identifier for the task.
     */
    public final Observable<String> name = new Observable<>("");

    /**
     * User description of the task.
     */
    public final Observable<String> description = new Observable<>("");

    /**
     * The corresponding tags for the task.
     */
    public final ObservableCollection<Tag> tags = new ObservableCollection<>(new HashSet<>());

    /**
     * The due date of the task.
     * Optional.
     */
    public final Observable<IDateGenerator> dueDate = new Observable<>(null);

    /**
     * The archived state of the task.
     */
    public final Observable<Boolean> isArchived = new Observable<>(false);

    /**
     * The completion state of the task.
     */
    public final Observable<Boolean> isCompleted = new Observable<>(false);

    /**
     * The date and time when the task was first created.
     */
    public final Calendar creationDate;


    public Task()
    {
        creationDate = Calendar.getInstance();
    }


    @Override
    public boolean isArchived() {
        return isArchived.getValue();
    }

    @Override
    public void setArchive(boolean isArchived) {
        this.isArchived.setValue(isArchived);
    }


    @Override
    public boolean isCompleted() {
        return isCompleted.getValue();
    }

    @Override
    public void setComplete(boolean isComplete) {
        this.isCompleted.setValue(isComplete);
    }
}
