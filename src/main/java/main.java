import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String args[]) {
        Scanner keyboard = new Scanner(System.in); // Variable que obtiene le input
        boolean exit = false; // Variable de control para salir del menú
        int option = 0;

        Sorts sorts = new Sorts();

        while (!exit) {
            System.out.println("Ingrese el número de la opción que desea realizar. \n1. Generar un nuevo archivo" +
                    "\n2. Gnome Sort \n3. Merge Sort\n4. Quick Sort\n5. Radix Sort\n6. Bubble Sort\n7. Salir");
            option = Integer.parseInt(getNumber(keyboard));

            // Menú
            switch (option) {
                case 1:
                    boolean quantityOK = false;
                    int quantity = 0;

                    while (!quantityOK) {
                        System.out.println("¿Cuántos números desea ordenar?");
                        quantity = Integer.parseInt(getNumber(keyboard));

                        if (quantity >= 10 && quantity <= 3000) {
                            quantityOK = true;
                        }
                    }

                    generateNumbers(quantity);
                    break;
                case 2:
                    int n = 10;
                    while (n <= 3000) {
                        generateNumbers(n);

                        ArrayList<Numbers> numbers = readFile();

                        long nano_startTime = System.nanoTime();
                        ArrayList<Numbers> test = sorts.gnomeSort(numbers);
                        long nano_endTime = System.nanoTime();
                        System.out.println("Tiempo en ordenar con datos desordenados: " + (nano_endTime - nano_startTime));

                        nano_startTime = System.nanoTime();
                        test = sorts.gnomeSort(test);
                        nano_endTime = System.nanoTime();
                        System.out.println("Tiempo en ordenar con datos ordenados: " + (nano_endTime - nano_startTime));

                        n += 55;
                    }
                    break;
                case 3:
                    int n1 = 10;
                    while (n1 <= 3000) {
                        generateNumbers(n1);

                        ArrayList<Numbers> numbers1 = readFile();

                        long nano_startTime1 = System.nanoTime();
                        ArrayList<Numbers> test1 = sorts.mergeSort(numbers1);
                        long nano_endTime1 = System.nanoTime();
                        System.out.println("Tiempo en ordenar con datos desordenados: " + (nano_endTime1 - nano_startTime1));

                        nano_startTime1 = System.nanoTime();
                        test1 = sorts.mergeSort(test1);
                        nano_endTime1 = System.nanoTime();
                        System.out.println("Tiempo en ordenar con datos ordenados: " + (nano_endTime1 - nano_startTime1));

                        n1 += 55;
                    }
                    break;
                case 4:
                    int n2 = 10;
                    while (n2 <= 3000) {
                        generateNumbers(n2);

                        ArrayList<Numbers> numbers2 = readFile();

                        long nano_startTime2 = System.nanoTime();
                        ArrayList<Numbers> test2 = sorts.quickSortMethod(numbers2);
                        long nano_endTime2 = System.nanoTime();
                        System.out.println("Tiempo en ordenar con datos desordenados: " + (nano_endTime2 - nano_startTime2));

                        nano_startTime2 = System.nanoTime();
                        test2 = sorts.quickSortMethod(test2);
                        nano_endTime2 = System.nanoTime();
                        System.out.println("Tiempo en ordenar con datos ordenados: " + (nano_endTime2 - nano_startTime2));

                        n2 += 55;
                    }
                    break;
                case 5:
                    int n3 = 10;
                    while (n3 <= 3000) {
                        generateNumbers(n3);

                        ArrayList<Numbers> numbers3 = readFile();

                        long nano_startTime3 = System.nanoTime();
                        ArrayList<Numbers> test3 = sorts.radixMethod(numbers3);
                        long nano_endTime3 = System.nanoTime();
                        System.out.println("Tiempo en ordenar con datos desordenados: " + (nano_endTime3 - nano_startTime3));

                        nano_startTime3 = System.nanoTime();
                        test3 = sorts.radixMethod(test3);
                        nano_endTime3 = System.nanoTime();
                        System.out.println("Tiempo en ordenar con datos ordenados: " + (nano_endTime3 - nano_startTime3));

                        n3 += 55;
                    }
                    break;
                case 6:
                    int n4 = 10;
                    while (n4 <= 3000) {
                        generateNumbers(n4);

                        ArrayList<Numbers> numbers4 = readFile();

                        long nano_startTime4 = System.nanoTime();
                        ArrayList<Numbers> test4 = sorts.bubbleSort(numbers4);
                        long nano_endTime4 = System.nanoTime();
                        System.out.println("Tiempo en ordenar con datos desordenados: " + (nano_endTime4 - nano_startTime4));

                        nano_startTime4 = System.nanoTime();
                        test4 = sorts.bubbleSort(test4);
                        nano_endTime4 = System.nanoTime();
                        System.out.println("Tiempo en ordenar con datos ordenados: " + (nano_endTime4 - nano_startTime4));

                        n4 += 55;
                    }
                    break;
                case 7:
                    exit = true;
                default:
                    System.out.println("La opción ingresadad no está definida.");
            }
        }
    }

    /**
     * @param keyboard
     * @return
     */
    public static String getNumber(Scanner keyboard) {
        String number = keyboard.nextLine(); // Obtiene el input
        boolean isNumber = false;

        while (!isNumber) { // Vuelve a pedir input hasta que este sea un número
            try {
                int nm = Integer.parseInt(number); // Verifica que el input sea un número
                isNumber = true;
            } catch (NumberFormatException nft) {
                System.out.println("ERROR. Verifique que el valor ingresado sea numérico e intente de nuevo.");
                number = keyboard.nextLine();
            }
        }

        return number;
    }

    public static void generateNumbers(int quantity) {
        int number;
        number = (int) (Math.random() * 10000 + 1);
        String line = "" + number;
        for (int i = 0; i <= (quantity - 1); i++) {
            number = (int) (Math.random() * 10000 + 1);
            line = line + ", " + number;
        }
        try {
            FileWriter fw = new FileWriter("numbers.txt"+ "");
            fw.write(line);
            fw.close();
        }catch(Exception e) {
            System.out.println("Error en creacion de archivo");
        }
    }

    /**
     * Lee el archivo para cada vez que se vaya a ejecutar cada sort, sea el mismo
     * @return int[]
     */
    public static ArrayList<Numbers> readFile() {
        String text = new String();

        try {
            FileReader fr = new FileReader("numbers.txt");
            BufferedReader entrada = new BufferedReader(fr);
            String s;
            ArrayList<Numbers> numbers = new ArrayList<Numbers>();

            while((s = entrada.readLine()) != null) {
                for (String number: s.split(", ")) {
                    numbers.add(new Numbers(Integer.parseInt(number)));
                }
            }
            return numbers;
        }
        catch(java.io.FileNotFoundException fnfex) {
            System.out.println("Archivo no encontrado: " + fnfex);return null;}
        catch(java.io.IOException ioex) {return null;}
    }
}
