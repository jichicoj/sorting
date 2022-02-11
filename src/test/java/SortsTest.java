import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SortsTest {

    ArrayList<Numbers> numbers;
    ArrayList<Numbers> sorted;
    Sorts sort;

    @BeforeEach
    void setUp() {
        sort = new Sorts();
        numbers = new ArrayList<>();
        sorted = new ArrayList<>();

        numbers.add(new Numbers(9554));
        numbers.add(new Numbers(5507));
        numbers.add(new Numbers(4601));
        numbers.add(new Numbers(6196));
        numbers.add(new Numbers(6123));
        numbers.add(new Numbers(2137));
        numbers.add(new Numbers(2628));
        numbers.add(new Numbers(1585));
        numbers.add(new Numbers(1220));
        numbers.add(new Numbers(3920));

        sorted.add(new Numbers(1220));
        sorted.add(new Numbers(1585));
        sorted.add(new Numbers(2137));
        sorted.add(new Numbers(2628));
        sorted.add(new Numbers(3920));
        sorted.add(new Numbers(4601));
        sorted.add(new Numbers(5507));
        sorted.add(new Numbers(6123));
        sorted.add(new Numbers(6196));
        sorted.add(new Numbers(9554));
    }

    @Test
    void gnomeSort() {
        assertEquals(sorted, sort.gnomeSort(numbers));
    }

    @Test
    void mergeSort() {

    }

    @Test
    void merge() {
    }

    @Test
    void quickSortMethod() {
    }

    @Test
    void quickSort() {
    }

    @Test
    void radixMethod() {
    }

    @Test
    void countSort() {
    }

    @Test
    void radixSort() {
    }

    @Test
    void bubbleSort() {
    }
}