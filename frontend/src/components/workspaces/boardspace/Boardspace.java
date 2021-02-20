package components.workspaces.boardspace;

import java.util.ArrayList;

import MVVM.ObservableCollection;
import components.workspaces.Workspace;

public class Boardspace extends Workspace {

    public final ObservableCollection<Board> boards = new ObservableCollection<>(new ArrayList<>());

    public Boardspace() {
        super();
    }

}
