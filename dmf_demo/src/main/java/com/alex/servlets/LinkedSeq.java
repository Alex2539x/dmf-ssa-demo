package com.alex.servlets;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Assignment metadata
 * Name(s) and NetID(s): Alex Ho (ath58)
 * Hours spent on assignment: 20
 */

/**
 * A list of elements of type `T` implemented as a singly linked list.  Null elements are not
 * allowed.
 */
public class LinkedSeq<T> implements Seq<T> {

    /**
     * Number of elements in the list.  Equal to the number of linked nodes reachable from `head`.
     */
    private int size;

    /**
     * First node of the linked list (null if list is empty).
     */
    private Node<T> head;

    /**
     * Last node of the linked list starting at `head` (null if list is empty).  Next node must be
     * null.
     */
    private Node<T> tail;

    /**
     * Assert that this object satisfies its class invariants.
     */
    private void assertInv() {
        assert size >= 0;
        if (size == 0) {
            assert head == null;
            assert tail == null;
        } else {
            assert head != null;
            assert tail != null;

            int nodeNum = 0;
            for (int i = 0; i < size; i++) {
                nodeNum++;
            }
            assert nodeNum == size;

            Node<T> currNode = head;
            for (int i = 0; i < size - 1; i++) {
                currNode = currNode.next();
            }
            assert currNode == tail;
        }
    }

    /**
     * Create an empty list.
     */
    public LinkedSeq() {
        size = 0;
        head = null;
        tail = null;

        assertInv();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void prepend(T elem) {
        assertInv();
        assert elem != null;

        head = new Node<>(elem, head);
        // If list was empty, assign tail as well
        if (tail == null) {
            tail = head;
        }
        size += 1;

        assertInv();
    }

    /**
     * Return a text representation of this list with the following format: the string starts with
     * '[' and ends with ']'.  In between are the string representations of each element, in
     * sequence order, separated by ", ".
     * <p>
     * Example: a list containing 4 7 8 in that order would be represented by "[4, 7, 8]".
     * <p>
     * Example: a list containing two empty strings would be represented by "[, ]".
     * <p>
     * The string representations of elements may contain the characters '[', ',', and ']'; these
     * are not treated specially.
     */
    @Override
    public String toString() {
        String str = "[";

        assertInv();

        Node<T> currNode = head;

        if (head != null) {
            while (currNode != tail) {
                str += currNode.data() + ", ";
                currNode = currNode.next();
            }
            if (currNode == tail) {
                str += currNode.data();
            }
        }

        str += "]";
        assertInv();
        return str;
    }

    /**
     * Return a Boolean describing whether the list contains a certain element (true if the list
     * does contain the element and false if the list does not contain the element).
     * <p>
     * Example: a list containing "A" and "B" does not contain "C"
     * <p>
     * Example: a list containing "A", "B", and "C" does contain "C"
     * <p>
     * Example: a list containing "A", "C", and "C" does contain "C"
     */
    @Override
    public boolean contains(T elem) {
        assertInv();

        boolean doesContain = false;

        Node<T> currNode = head;

        for (int i = 0; i < size; i++) {
            if (currNode.data() == elem) {
                doesContain = true;
            }
            currNode = currNode.next();
        }
        assertInv();
        return doesContain;
    }

    /**
     * Return an element of a list at the given index. The first index of the list is 0. Will not
     * return null.
     * <p>
     * Example: a list containing ["A", "B", "C", "D"] contains "A" at index 0
     * <p>
     * Example: a list containing ["A", "B", "C", "D"] contains "B" at index 1
     * <p>
     * Example: a list containing ["A", "B", "C", "D"] contains "C" at index 2
     * <p>
     * Example: a list containing ["A", "B", "C", "D"] contains "D" at index 3
     * <p>
     *
     * @param index @code 0 <= index < size()
     */
    @Override
    public T get(int index) {
        assertInv();

        Node<T> currNode = head;
        T result = null;

        for (int i = 0; i <= index; i++) {
            result = currNode.data();
            currNode = currNode.next();
        }

        assertInv();
        return result;
    }

    /**
     * Add element `elem` to the end of this list.
     * <p>
     * Example: if the list is ["A", "B", "C"], append("D") would change the list to ["A", "B", "C",
     * "D"]. Requires `elem` is not null.
     * <p>
     *
     * @param elem is not null.
     */
    @Override
    public void append(T elem) {
        assertInv();

        Node<T> currNode = new Node<>(elem, null);

        if (size == 0) {
            head = currNode;
            tail = currNode;
            size++;
        } else {
            tail.setNext(currNode);
            tail = currNode;
            size++;
        }

        assertInv();
    }

    /**
     * Insert element `elem` before successor element 'successor'. Requires `elem` is not null and
     * that 'successor' is already in the list.
     * <p>
     * Example: if the list is ["A", "B", "C"], insertBefore("D","B") would change the list to ["A",
     * "D", "B", "C"].
     * <p>
     *
     * @param elem is not null.
     */
    @Override
    public void insertBefore(T elem, T successor) {
        assertInv();

        Node<T> currNode = new Node<>(elem, null);
        Node<T> testNode = head;
        Node<T> successorNode = head;
        Node<T> beforeNode = head;

        if (size == 1) {
            currNode.setNext(head);
            head = currNode;
            size++;
        } else {
            int indx = 0;
            for (int i = 0; i < size; i++) {
                if (testNode.data() == successor) {
                    indx = i;
                }
                testNode = testNode.next();
            }
            if (indx == 0) {
                currNode.setNext(head);
                head = currNode;
            } else {
                for (int i = 0; i < indx; i++) {
                    successorNode = successorNode.next();
                }
                for (int i = 0; i < indx - 1; i++) {
                    beforeNode = beforeNode.next();
                }
                currNode.setNext(successorNode);
                beforeNode.setNext(currNode);
            }
            size++;
        }
        assertInv();
    }

    /**
     * Remove the first occurrence of element `elem` (if any) from this list.  Return whether the
     * list changed.  Requires `elem` is not null.
     * <p>
     * Example: if the list is ["A", "B", "C"], remove("B") would change the list to ["A", "C"].
     * <p>
     *
     * @param elem is not null.
     */
    @Override
    public boolean remove(T elem) {
        assertInv();

        boolean wasRemoved = false;
        Node<T> testNode = head;
        Node<T> currentNode = head;
        Node<T> successorNode;
        Node<T> beforeNode = head;

        if (size == 0) {
            wasRemoved = false;
        } else if (size == 1) {
            if (head.data() == elem) {
                head = null;
                tail = null;
                size = 0;
                wasRemoved = true;
            }
        } else {
            int indx = -1;
            for (int i = 0; i < size; i++) {
                if (indx == -1) {
                    if (testNode.data().equals(elem)) {
                        indx = i;
                    }
                }
                testNode = testNode.next();
            }
            if (indx != -1) {
                if (indx == 0) {
                    head = head.next();
                } else if (indx == size - 1) {
                    for (int i = 0; i < indx - 1; i++) {
                        beforeNode = beforeNode.next();
                    }
                    tail = beforeNode;
                } else {
                    for (int i = 0; i < indx; i++) {
                        currentNode = currentNode.next();
                    }

                    for (int i = 0; i < indx - 1; i++) {
                        beforeNode = beforeNode.next();
                    }
                    successorNode = currentNode.next();
                    beforeNode.setNext(successorNode);
                }
                wasRemoved = true;
                size--;
            }
        }
        assertInv();
        return wasRemoved;
    }

    /**
     * Return whether this and `other` are `LinkedSeq`s containing the same elements in the same
     * order.  Two elements `e1` and `e2` are "the same" if `e1.equals(e2)`.  Note that `LinkedSeq`
     * is mutable, so equivalence between two objects may change over time.  See `Object.equals()`
     * for additional guarantees.
     */
    @Override
    public boolean equals(Object other) {
        // Note: In the `instanceof` check, we write `LinkedSeq` instead of `LinkedSeq<T>` because
        // of a limitation inherent in Java generics: it is not possible to check at run-time
        // what the specific type `T` is.  So instead we check a weaker property, namely,
        // that `other` is some (unknown) instantiation of `LinkedSeq`.  As a result, the static
        // type returned by `currNodeOther.data()` is `Object`.
        assertInv();
        if (!(other instanceof LinkedSeq)) {
            return false;
        }
        LinkedSeq otherSeq = (LinkedSeq) other;
        Node<T> currNodeThis = head;
        Node currNodeOther = otherSeq.head;
        Node currNodeOtherSize = otherSeq.head;

        boolean isEqual = true;
        int otherSize = 0;
        if (otherSeq.head != null) {
            while (currNodeOtherSize.next() != null) {
                currNodeOtherSize = currNodeOtherSize.next();
                otherSize++;
            }
            if (currNodeOtherSize == otherSeq.tail) {
                otherSize++;
            }
        } else {
            otherSize = 0;
        }

        if (size == otherSize) {
            for (int i = 0; i < size; i++) {
                if (!currNodeThis.data().toString().equals(currNodeOther.data().toString())) {
                    isEqual = false;
                }
                currNodeThis = currNodeThis.next();
                currNodeOther = currNodeOther.next();
            }
        } else {
            isEqual = false;
        }
        assertInv();
        return isEqual;
    }

    /*
     * There is no need to read the remainder of this file for the purpose of completing the
     * assignment.  We have not yet covered the implementation of these concepts in class.
     */

    /**
     * Returns a hash code value for the object.  See `Object.hashCode()` for additional
     * guarantees.
     */
    @Override
    public int hashCode() {
        // Whenever overriding `equals()`, must also override `hashCode()` to be consistent.
        // This hash recipe is recommended in _Effective Java_ (Joshua Bloch, 2008).
        int hash = 1;
        for (T e : this) {
            hash = 31 * hash + e.hashCode();
        }
        return hash;
    }

    /**
     * Return an iterator over the elements of this list (in sequence order).  By implementing
     * `Iterable`, clients can use Java's "enhanced for-loops" to iterate over the elements of the
     * list.  Requires that the list not be mutated while the iterator is in use.
     */
    @Override
    public Iterator<T> iterator() {
        assertInv();

        // Return an instance of an anonymous inner class implementing the Iterator interface.
        // For convenience, this uses Java features that have not eyt been introduced in the course.
        return new Iterator<>() {
            private Node<T> next = head;

            public T next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T result = next.data();
                next = next.next();
                return result;
            }

            public boolean hasNext() {
                return next != null;
            }
        };
    }
}
