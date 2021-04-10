package ui.navigation;

import javafx.scene.Parent;

public interface INavigable<T> {

    Parent navigate(T t);

}
