package components.workspaces.boardspace;

import java.util.ArrayList;

import components.util.observable.ObservableCollection;
import components.workspaces.WorkspaceData;

public final class Boardspace {

    public final ObservableCollection<Board> boards = new ObservableCollection<>(new ArrayList<>());

    public Boardspace(WorkspaceData data) {


        // TODO: ?
    }

}
