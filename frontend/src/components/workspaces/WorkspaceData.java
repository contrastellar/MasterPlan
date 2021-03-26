package components.workspaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collection;

import components.util.observable.Observable;
import components.util.observable.ObservableList;
import components.util.observable.ObservableMap;
import components.util.observable.IReadOnlyList;
import components.util.observable.IReadOnlyMap;

import components.Task.Task;


/**
 * The workspace data class is the underlying structure holding the information
 * contained in a workspace.
 */
public class WorkspaceData {
    public static class Category {
        public final Observable<String> name = new Observable<>("");
        public final ObservableList<Task> tasks = new ObservableList<>(new ArrayList<>());
    }

    // should we just use a graph data type? or a modified graph
    private final ObservableMap<Category, ObservableList<Category>> _data = new ObservableMap<>(new HashMap<>());
    public final IReadOnlyMap<Category, ? extends IReadOnlyList<Category>> data =  _data;

    public WorkspaceData() { }

    public void addCategory(Category c) {
        if (_data.containsKey(c))
            return;

        _data.put(c, new ObservableList<>( new ArrayList<>() ));
    }

    public void addCategoryRelationship(Category c, Collection<Category> relatedCategories) {
        // TODO: this isn't enough to check for circular relationships (this only checks for self circularity)
        if(relatedCategories.contains(c))
            throw new IllegalArgumentException("Circular relationships are not allowed");

        for(Category relatedC : relatedCategories)
            if(!_data.containsKey(relatedC))
                throw new IllegalArgumentException();

        addCategory(c);

        _data.get(c).addAll(relatedCategories);
    }
}
