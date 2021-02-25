package components.workspaces;

import MVVM.IReadOnly;
import MVVM.Observable;
import components.Archival;

import java.util.Calendar;

public abstract class Workspace implements Archival {
    /**
     * DATA FIELDS
     * name: User identifier for the workspace.
     * decription:  User description of the workspace.
     * isArchived: The archived state of the workspace.
     * creationDate: The date and time when the workspace was first created.
     */
    private final Observable<String> _name = new Observable<>("");
    public  final IReadOnly<String> name = _name;

    private final Observable<String> _description = new Observable<>("");
    public  final IReadOnly<String> description = _description;

    private final Observable<Boolean> _isArchived = new Observable<>(false);
    public  final IReadOnly<Boolean> isArchived = _isArchived;

    public final Calendar creationDate;

    public final WorkspaceData data;

    public Workspace(WorkspaceData data) {
        this.creationDate = Calendar.getInstance();
        this.data = data;
    }

    @Override
    public void setArchive(Boolean isArchived) {
        if(isArchived == null)
            throw new IllegalArgumentException("isArchived cannot be null");
        this._isArchived.setValue(isArchived);
    }

    @Override
    public Boolean isArchived() {
        return isArchived.getValue();
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
