package components;

import java.net.URI;

import components.util.observable.IReadOnly;
import components.util.observable.Observable;
import javafx.scene.paint.Color;

public final class Category extends TodoElement {

    private final Observable<URI> _backgroundImage = new Observable<>( null );
    public final IReadOnly<URI> backgroundImage = _backgroundImage;

    private final Observable<Color> _backgroundColor = new Observable<>( null );
    public final IReadOnly<Color> backgroundColor = _backgroundColor;

    public Category() {
        super();
    }


    /* Getters and Setters */

    public URI getBackgroundImageURI() { return this._backgroundImage.getValue(); }
    public void setBackgroundImageURI(URI uri) { this._backgroundImage.setValue(uri); }

    public Color getBackgroundColor() { return this._backgroundColor.getValue(); }
    public void setBackgroundColor(Color color) { this._backgroundColor.setValue(color); }

}
