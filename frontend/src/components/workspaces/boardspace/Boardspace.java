package components.workspaces.boardspace;

import java.util.ArrayList;

import MVVM.ObservableCollection;
import components.workspaces.Workspace;
import components.workspaces.WorkspaceData;

public final class Boardspace {

    public final ObservableCollection<Board> boards = new ObservableCollection<>(new ArrayList<>());

    public Boardspace(WorkspaceData data) {


        // TODO: ?
    }

}
