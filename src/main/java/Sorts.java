import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Sorts {
    public Sorts() { }

    /**
     * Algoritmo de sort Gnome
     * Referencia: https://panthema.net/2013/sound-of-sorting/
     * Algoritmo de notación O(n), siendo en un caso desfavorable hasta O(n2)
     * @param numbers
     * @return ArrayList<Numbers>
     */
    public ArrayList<Numbers> gnomeSort(ArrayList<Numbers> numbers) {
        int i = 1;
        Numbers temp = new Numbers(0);

        while (i < numbers.size()) {
            if (numbers.get(i).compareTo(numbers.get(i - 1)) >= 0) {
                i = i + 1;
            } else {
                temp = numbers.get(i);
                numbers.set(i, numbers.get(i - 1));
                numbers.set(i - 1, temp);

                if (i > 1) {
                    i--;
                }
            }
        }

        return numbers;
    }

    /**
     * Algoritmo de sort Merge.
     * Algoritmo de notación O(n logn)
     * Referencia: https://www.youtube.com/watch?v=yv6svAfoYik&t=444s
     * @param numbers
     * @return ArrayList<Numbers>
     */
    public ArrayList<Numbers> mergeSort(ArrayList<Numbers> numbers) {
        int size = 0;
        if (numbers.size() <= 1) {
            return numbers;
        }

        int midpoint = numbers.size() / 2;

        if(numbers.size() % 2 == 0) {
            size = midpoint;
        }else {
            size = midpoint + 1;
        }

        ArrayList<Numbers> left = new ArrayList<>();
        ArrayList<Numbers> right = new ArrayList<>();

        for(int i = 0; i < midpoint; i++) {
            left.add(numbers.get(i));
        }

        for(int j = 0; j < size; j++) {
            right.add(numbers.get(midpoint + j));
        }

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }


    /**
     * Merge parte del algoritmo MergeSort. En esta parse se une cada mitad.
     * Referencia: https://www.youtube.com/watch?v=yv6svAfoYik&t=444s
     * @param left
     * @param right
     * @return ArrayList<Numbers>
     */
    public ArrayList<Numbers> merge(ArrayList<Numbers> left, ArrayList<Numbers> right) {
        ArrayList<Numbers> result = new ArrayList<>();
        int[] res = new int[left.size() + right.size()];

        int leftPointer, rightPointer, resultPointer;
        leftPointer = rightPointer = resultPointer = 0;

        while (leftPointer < left.size() || rightPointer < right.size()) {
            if(leftPointer < left.size() && rightPointer < right.size()) {
                if(left.get(leftPointer).compareTo(right.get(rightPointer)) < 0) {
                    res[resultPointer++] = left.get(leftPointer++).getNumber();
                }else {
                    res[resultPointer++] = right.get(rightPointer++).getNumber();
                }
            }else if(leftPointer < left.size()) {
                res[resultPointer++] = left.get(leftPointer++).getNumber();
            }else if(rightPointer < right.size()) {
                res[resultPointer++] = right.get(rightPointer++).getNumber();
            }
        }

        for (int n: res) {
            result.add(new Numbers(n));
        }

        return result;
    }

    /**
     * Llamada para quickSort y tener un valor de retorno.
     * @param numbers
     * @return Arrays
     */
    public ArrayList<Numbers> quickSortMethod(ArrayList<Numbers> numbers) {
        int left = 0;
        int right = numbers.size() - 1;

        return quickSort(numbers, left, right);
    }

    /**
     * Algoritmo de sort Quick
     * Referencia: https://www.youtube.com/watch?v=Fiot5yuwPAg&t=477s
     * Quick sort puede llegar a ser O(n2) o hasta el peor de los casos O(n logn)
     * @param numbers
     * @param left
     * @param right
     * @return ArrayList
     */
    public ArrayList<Numbers> quickSort(ArrayList<Numbers> numbers, int left, int right) {
        Numbers pivot = numbers.get(left);
        int lefty = left;
        int righty = right;
        int temp = 0;

        while (lefty < righty) {
            while (numbers.get(lefty).compareTo(pivot) <= 0 && lefty < righty) {
                lefty++;
            }

            while (numbers.get(righty).compareTo(pivot) > 0) {
                righty--;
            }

            if (lefty < righty) {
                temp = numbers.get(lefty).getNumber();
                numbers.set(lefty, numbers.get(righty));
                numbers.set(righty, new Numbers(temp));
            }
        }

        numbers.set(left, numbers.get(righty));
        numbers.set(righty, pivot);

        if (left < righty - 1) {
            quickSort(numbers, left, righty - 1);
        }

        if (righty + 1 < right) {
            quickSort(numbers, righty + 1, right);
        }

        return numbers;
    }

    /**
     * Llamada al algoritmo para tener un valor de retorno.
     * @param numbers
     * @return String
     */
    public ArrayList<Numbers> radixMethod(ArrayList<Numbers> numbers) {
        return radixSort(numbers, numbers.size());
    }

    /**
     * Algoritmo parte de RadixSort,
     * Referencia: //https://www.geeksforgeeks.org/radix-sort/
     * @param numbers
     * @param n
     * @param exp
     * @return ArrayList
     */
    public ArrayList<Numbers> countSort(ArrayList<Numbers> numbers, int n, int exp)
    {
        int output[] = new int[n];
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);
        for (i = 0; i < n; i++)
            count[(numbers.get(i).getNumber() / exp) % 10]++;
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];
        for (i = n - 1; i >= 0; i--) {
            output[count[(numbers.get(i).getNumber() / exp) % 10] - 1] = numbers.get(i).getNumber();
            count[(numbers.get(i).getNumber() / exp) % 10]--;
        }
        for (i = 0; i < n; i++)
            numbers.set(i, new Numbers(output[i]));

        return numbers;
    }


    /**
     * Algoritmo para sort Radix
     * Referencia: //https://www.geeksforgeeks.org/radix-sort/
     * Tiene una notación de O(n) en el mejor comportamiento. Si no se aprovecha la estructura
     * de claves, este tiene una estructura de O(n logn).
     * @param numbers
     * @param n
     * @return ArrayList
     */
    public ArrayList<Numbers> radixSort(ArrayList<Numbers> numbers, int n)
    {
        int max = numbers.stream().max(Comparator.comparing(Numbers::getNumber)).get().getNumber();

        for (int exp = 1; max / exp > 0; exp *= 10)
            numbers =  countSort(numbers, n, exp);

        return numbers;
    }

    /**
     * Algoritmo para sort Bubble
     * Elegido. Referencia por: https://www.geeksforgeeks.org/bubble-sort/
     * Tiene una notación O(n2)
     * @param numbers
     * @return ArrayList
     */
    public ArrayList<Numbers> bubbleSort(ArrayList<Numbers> numbers) {
        int n = numbers.size();
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (numbers.get(j).compareTo(numbers.get(j + 1)) > 0) {
                    Numbers temp = numbers.get(j);
                    numbers.set(j, numbers.get(j + 1));
                    numbers.set(j + 1, temp);
                }

        return numbers;
    }
}
