package components;

import java.net.URI;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ITodoElement extends Archival {

    public void setName(String name);
    public String getName();

    public void setDescription(String name);
    public String getDescription();

    public List<Tag> getTags();

    public List<URI> getAttachments();

    public Calendar getCreationDate();

}
