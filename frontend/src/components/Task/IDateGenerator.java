package components.Task;

import java.util.Calendar;

public interface IDateGenerator {

    /**
     *
     * @return
     */
    int getIndex();

    /**
     *
     * @param index
     */
    void setIndex(int index);

    /**
     *
     * @return
     */
    Calendar getDate();

    boolean hasNext();

    /**
     *
     * @return
     */
    Calendar getNext();

    /**
     *
     * @return
     */
    Calendar getPrev();

}
