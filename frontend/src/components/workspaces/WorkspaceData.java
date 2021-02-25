package components.workspaces;

import java.util.*;

import MVVM.*;
import MVVM.Observable;
import components.Task.Task;


/**
 * The workspace data class is the underlying structure holding the information
 * contained in a workspace.
 */
public class WorkspaceData {

    public class Category {
        public final Observable<String> name = new Observable<>("");
        public final ObservableList<Task> tasks = new ObservableList<>(new ArrayList<>());
    }


    // TODO: create readonly observable map, include all methods that don't have the updateListeners
    private final ObservableMap<Category, ObservableList<Category>> _data = new ObservableMap<>(new HashMap<>());

    // OOPS
    // public final IReadOnlyMap<Category, IReadOnlyList<Category>> data =  _data;

    public WorkspaceData() { }

    public void addCategory(Category c) {
        if (_data.containsKey(c))
            return;

        _data.put(c, new ObservableList<Category>( new ArrayList<>() ));
    }

    public void addCategoryRelationship(Category c, Collection<Category> relatedCategories) {
        if(relatedCategories.contains(c))
            throw new IllegalArgumentException("Circular relationships are not allowed");

        for(Category relatedC : relatedCategories)
            if(!_data.containsKey(relatedC))
                throw new IllegalArgumentException();

        addCategory(c);

        if (_data.containsKey(c)) {

        }

    }
}
