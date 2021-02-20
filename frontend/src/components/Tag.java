package components;

import MVVM.Observable;
import MVVM.ObservableCollection;

import java.util.HashSet;

public class Tag {

    public static final ObservableCollection<Tag> ALL_TAGS = new ObservableCollection<>(new HashSet<>());

}
