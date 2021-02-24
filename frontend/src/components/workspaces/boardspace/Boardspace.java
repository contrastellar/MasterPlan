package components.workspaces.boardspace;

import java.util.ArrayList;

import MVVM.ObservableCollection;
import components.workspaces.Workspace;
import components.workspaces.WorkspaceData;

public final class Boardspace extends Workspace {

    public final ObservableCollection<Board> boards = new ObservableCollection<>(new ArrayList<>());

    public Boardspace() {
        super();
    }

    @Override
    public WorkspaceData convertToWorkspaceData() {
        // TODO
        return null;
    }

}
