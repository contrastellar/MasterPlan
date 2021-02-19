package components.workspaces.listspace;

import MVVM.ObservableCollection;
import components.workspaces.Workspace;

import java.util.ArrayList;

public class Listspace extends Workspace {

    public final ObservableCollection<List> lists = new ObservableCollection<>(new ArrayList<>());

    public Listspace()
    {
        super();
    }




}
