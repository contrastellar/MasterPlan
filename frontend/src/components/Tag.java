package components;

import components.observable.IReadOnlyObservable;
import components.observable.Observable;
import components.observable.ObservableCollection;
import javafx.scene.paint.Color;

import java.util.HashSet;

public final class Tag {

    private final Observable<String> _name = new Observable<>("");
    public final IReadOnlyObservable<String> name = _name;

    private final Observable<Color> _color = new Observable<>(Color.TRANSPARENT);
    public final IReadOnlyObservable<Color> color = _color;


    public Tag(String name, Color color) {
        if(name == null)
            throw new IllegalArgumentException("name cannot be null");

    }


    public void setName(String name) {
        if(name == null)
            throw new IllegalArgumentException("Tag.setName() - name can not be null");

        this._name.setValue(name);
    }

    public String getName() {
        return _name.getValue();
    }

    public void setColor(Color color) {
        if(color == null)
            throw new IllegalArgumentException("Tag.setColor() - name can not be null");

        this._color.setValue(color);
    }

    public Color getColor() {
        return _color.getValue();
    }

}
