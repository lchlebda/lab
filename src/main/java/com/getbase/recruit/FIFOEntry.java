package com.getbase.recruit;

import java.util.concurrent.atomic.AtomicLong;

/**
 * If you need to enforce an ordering in Trees, you can use this class that use a secondary key to break
 * ties in primary priority values. It applies first-in-first-out (FIFO) tie-breaking to
 * comparable elements. To use it, you would insert a new FIFOEntry(anEntry) instead of a plain entry object.
 *
 * @param <E> - type of object you originally want to store in tree collection
 */
public class FIFOEntry<E extends Comparable<? super E>> implements Comparable<FIFOEntry<E>> {

    private static final AtomicLong sequence = new AtomicLong(0);
    private final long sequenceNumber;
    private final E entry;

    public FIFOEntry(E entry) {
        sequenceNumber = sequence.getAndIncrement();
        this.entry = entry;
    }

    public E getEntry() {
        return entry;
    }

    public int compareTo(FIFOEntry<E> other) {
        int response = entry.compareTo(other.entry);
        if (response == 0 && other.entry != this.entry) {
            response = (sequenceNumber > other.sequenceNumber ? -1 : 1);
        }

        return response;
    }
}