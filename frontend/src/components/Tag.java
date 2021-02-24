package components;

import MVVM.Observable;
import MVVM.ObservableCollection;
import javafx.scene.paint.Color;

import java.util.HashSet;

public class Tag {

    public static final ObservableCollection<Tag> ALL_TAGS = new ObservableCollection<>(new HashSet<>());

    public final Observable<String> name = new Observable<>();
    public final Observable<Color> color = new Observable<>();

    private Tag(String name, Color color) {

        if(name == null)
            throw new IllegalArgumentException("name cannot be null");

        this.name.setValue(name);
        this.color.setValue(color);
    }

    // TODO: create register method

    @Override
    public boolean equals(Object o) {
        if(o == this)
            return true;

        if(!(o instanceof Tag))
            return false;

        final Tag t = (Tag) o;

        return this.name.getValue().equals( t.name.getValue() );
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 53 * hash + (this.name.getValue() != null ? this.name.getValue().hashCode() : 0);

        return hash;
    }

}
