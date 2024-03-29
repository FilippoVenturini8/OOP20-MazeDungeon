package model.common;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is an infinite Iterator that provides id for all the GameObjects.
 *
 */
public class IdIterator implements Iterator<Integer> {

    private int value;

    /**
     * Has always a next value.
     * @return true
     */
    @Override
    public boolean hasNext() {
        return true;
    }

    /**
     * @return the next value
     */
    @Override
    public Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        return this.value++; 
    }
}
