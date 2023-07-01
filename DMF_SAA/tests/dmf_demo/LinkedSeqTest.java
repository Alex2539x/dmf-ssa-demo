package dmf_demo;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedSeqTest {

    // Helper functions for creating lists used by multiple tests.  By constructing strings with
    // `new`, more likely to catch inadvertent use of `==` instead of `.equals()`.

    /**
     * Creates [].
     */
    static Seq<String> makeList0() {
        return new LinkedSeq<>();
    }

    /**
     * Creates ["A"].  Only uses prepend.
     */
    static Seq<String> makeList1() {
        Seq<String> ans = new LinkedSeq<>();
        ans.prepend(new String("A"));
        return ans;
    }

    /**
     * Creates ["A", "B"].  Only uses prepend.
     */
    static Seq<String> makeList2() {
        Seq<String> ans = new LinkedSeq<>();
        ans.prepend(new String("B"));
        ans.prepend(new String("A"));
        return ans;
    }

    /**
     * Creates ["A", "B", "C"].  Only uses prepend.
     */
    static Seq<String> makeList3() {
        Seq<String> ans = new LinkedSeq<>();
        ans.prepend(new String("C"));
        ans.prepend(new String("B"));
        ans.prepend(new String("A"));
        return ans;
    }

    /**
     * Creates ["A", "B", "B"].  Only uses prepend.
     */
    static Seq<String> makeList4() {
        Seq<String> ans = new LinkedSeq<>();
        ans.prepend(new String("B"));
        ans.prepend(new String("B"));
        ans.prepend(new String("A"));
        return ans;
    }

    /**
     * Creates a list containing the same elements (in the same order) as array `elements`.  Only
     * uses prepend.
     */
    static <T> Seq<T> makeList(T[] elements) {
        Seq<T> ans = new LinkedSeq<>();
        for (int i = elements.length; i > 0; i--) {
            ans.prepend(elements[i - 1]);
        }
        return ans;
    }

    @Test
    void testConstructorSize() {
        Seq<String> list = new LinkedSeq<>();
        assertEquals(0, list.size());
    }

    @Test
    void testPrependSize() {
        // List creation helper functions use prepend.
        Seq<String> list;

        list = makeList1();
        assertEquals(1, list.size());

        list = makeList2();
        assertEquals(2, list.size());

        list = makeList3();
        assertEquals(3, list.size());
    }

    @Test
    void testToString() {
        Seq<String> list;

        list = makeList0();
        assertEquals("[]", list.toString());

        list = makeList1();
        assertEquals("[A]", list.toString());

        list = makeList2();
        assertEquals("[A, B]", list.toString());

        list = makeList3();
        assertEquals("[A, B, C]", list.toString());
    }

    // TODO: Add new test cases here as you implement each method in `LinkedSeq`.  You may combine
    // multiple tests for the _same_ method in the same @Test procedure, but be sure that each test
    // case is visibly distinct (comments are good for this).  You are welcome to compare against an
    // expected `toString()` output in order to check multiple aspects of the state at once (in
    // general, later tests may make use of methods that have previously been tested).

    @Test
    void testContains() {
        Seq<String> list;

        String[] array = new String[]{"A", "A"};
        list = makeList(array);
        assertEquals(false, list.contains("B"));

        array = new String[]{"A", "B"};
        list = makeList(array);
        assertEquals(true, list.contains("B"));

        array = new String[]{"A", "B", "B"};
        list = makeList(array);
        assertEquals(true, list.contains("B"));
    }

    @Test
    void testGet() {
        Seq<String> list;

        String[] array = new String[]{"A", "B", "C", "D"};
        list = makeList(array);
        assertEquals("A", list.get(0));

        array = new String[]{"A", "B", "C", "D"};
        list = makeList(array);
        assertEquals("B", list.get(1));

        array = new String[]{"A", "B", "C", "D"};
        list = makeList(array);
        assertEquals("D", list.get(3));
    }

    @Test
    void testAppend() {
        Seq<String> list;
        Seq<String> correctList;

        // Append element to empty list
        String[] array = new String[]{};
        String[] correctArray = new String[]{"A"};
        list = makeList(array);
        correctList = makeList(correctArray);
        list.append("A");
        assertEquals(correctList.toString(), list.toString());

        // Append element to list with 1 element
        array = new String[]{"A"};
        correctArray = new String[]{"A", "B"};
        list = makeList(array);
        correctList = makeList(correctArray);
        list.append("B");
        assertEquals(correctList.toString(), list.toString());

        // Append element to list with 3 elements
        array = new String[]{"A", "B", "C"};
        correctArray = new String[]{"A", "B", "C", "D"};
        list = makeList(array);
        correctList = makeList(correctArray);
        list.append("D");
        assertEquals(correctList.toString(), list.toString());
    }

    @Test
    void testInsertBefore() {
        Seq<String> list;
        Seq<String> correctList;

        // Insert element in list with single element (before original element)
        String[] array = new String[]{"B"};
        String[] correctArray = new String[]{"A","B"};
        list = makeList(array);
        correctList = makeList(correctArray);
        list.insertBefore("A","B");
        assertEquals(correctList.toString(), list.toString());

        // Insert element in list with two elements (before last element)
        array = new String[]{"A", "C"};
        correctArray = new String[]{"A", "B", "C"};
        list = makeList(array);
        correctList = makeList(correctArray);
        list.insertBefore("B","C");
        assertEquals(correctList.toString(), list.toString());

        // Insert element in list with two elements (before first element)
        array = new String[]{"B", "C"};
        correctArray = new String[]{"A", "B", "C"};
        list = makeList(array);
        correctList = makeList(correctArray);
        list.insertBefore("A","B");
        assertEquals(correctList.toString(), list.toString());

        // Insert element in list with three elements (before second element)
        array = new String[]{"A", "B", "C"};
        correctArray = new String[]{"A", "D", "B", "C"};
        list = makeList(array);
        correctList = makeList(correctArray);
        list.insertBefore("D","B");
        assertEquals(correctList.toString(), list.toString());

        // Insert element in list with four elements (before third element)
        array = new String[]{"A", "B", "C", "D"};
        correctArray = new String[]{"A", "B", "O", "C", "D"};
        list = makeList(array);
        correctList = makeList(correctArray);
        list.insertBefore("O","C");
        assertEquals(correctList.toString(), list.toString());
    }

    @Test
    void testRemove() {
        Seq<String> list;
        Seq<String> correctList;

        // Remove element from list does not contain the element
        String[] array = new String[]{"A", "B", "C"};
        String[] correctArray = new String[]{"A", "B", "C"};
        list = makeList(array);
        correctList = makeList(correctArray);
        assertEquals(false, list.remove("D"));
        assertEquals(correctList.toString(), list.toString());

        // Remove element from a list that contains the element once
        array = new String[]{"A"};
        correctArray = new String[]{};
        list = makeList(array);
        correctList = makeList(correctArray);
        assertEquals(true, list.remove("A"));
        assertEquals(correctList.toString(), list.toString());

        // Remove element from a list that contains the element once
        array = new String[]{"A", "B", "C"};
        correctArray = new String[]{"A", "C"};
        list = makeList(array);
        correctList = makeList(correctArray);
        assertEquals(true, list.remove("B"));
        assertEquals(correctList.toString(), list.toString());

        // Remove element from a list that contains the element once
        array = new String[]{"A", "B", "C"};
        correctArray = new String[]{"A", "B"};
        list = makeList(array);
        correctList = makeList(correctArray);
        assertEquals(true, list.remove("C"));
        assertEquals(correctList.toString(), list.toString());

        // Remove element from a list that contains the element more than once
        array = new String[]{"A", "C", "B", "C",};
        correctArray = new String[]{"A", "B", "C"};
        list = makeList(array);
        correctList = makeList(correctArray);
        assertEquals(true, list.remove("C"));
        assertEquals(correctList.toString(), list.toString());
    }

    @Test
    void testEquals() {
        Seq<String> list;
        String[] array;
        Seq<String> otherList;
        String[] otherArray;

        // Determine if two linked sequences are the same (both have no elements)
        array = new String[]{};
        otherArray = new String[]{};
        list = makeList(array);
        otherList = makeList(otherArray);
        assertEquals(true, list.equals(otherList));

        // Determine if two linked sequences are the same (single element in each)
        array = new String[]{"A"};
        otherArray = new String[]{"A"};
        list = makeList(array);
        otherList = makeList(otherArray);
        assertEquals(true, list.equals(otherList));

        // Determine if two linked sequences are the same (same elements but different order)
        array = new String[]{"A", "B", "C"};
        otherArray = new String[]{"C", "B", "A"};
        list = makeList(array);
        otherList = makeList(otherArray);
        assertEquals(false, list.equals(otherList));

        // Determine if two linked sequences are the same (different elements initially, but
        // later have the same elements in the correct order when an element is added)
        array = new String[]{"A", "B", "C"};
        otherArray = new String[]{"A", "B"};
        list = makeList(array);
        otherList = makeList(otherArray);
        otherList.append("C");
        assertEquals(true, list.equals(otherList));

        // Determine if two linked sequences are the same (same elements initially, but then
        // altered so that they are no longer the same)
        array = new String[]{"A", "B", "C"};
        otherArray = new String[]{"A", "B", "C"};
        list = makeList(array);
        otherList = makeList(otherArray);
        otherList.remove("A");
        assertEquals(false, list.equals(otherList));
    }

    /*
     * There is no need to read the remainder of this file for the purpose of completing the
     * assignment.  We have not yet covered `hashCode()` or `assertThrows()` in class.
     */

    @Test
    void testHashCode() {
        assertEquals(makeList0().hashCode(), makeList0().hashCode());

        assertEquals(makeList1().hashCode(), makeList1().hashCode());

        assertEquals(makeList2().hashCode(), makeList2().hashCode());

        assertEquals(makeList3().hashCode(), makeList3().hashCode());
    }

    @Test
    void testIterator() {
        Seq<String> list;
        Iterator<String> it;

        list = makeList0();
        it = list.iterator();
        assertFalse(it.hasNext());
        Iterator<String> itAlias = it;
        assertThrows(NoSuchElementException.class, () -> itAlias.next());

        list = makeList1();
        it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals("A", it.next());
        assertFalse(it.hasNext());

        list = makeList2();
        it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals("A", it.next());
        assertTrue(it.hasNext());
        assertEquals("B", it.next());
        assertFalse(it.hasNext());
    }
}
