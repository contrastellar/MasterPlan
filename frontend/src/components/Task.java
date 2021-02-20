package components;

import java.util.Calendar;
import java.util.HashSet;

import MVVM.Observable;
import MVVM.ObservableCollection;

public class Task implements Archivable{

    public final Observable<String> name = new Observable<>("");
    public final Observable<String> description = new Observable<>("");
    public final ObservableCollection<Tag> tags = new ObservableCollection<>(new HashSet<>());
    public final Observable<Boolean> isArchived = new Observable<>(false);
    public final Observable<Calendar> dueDate = new Observable<>();


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
}
