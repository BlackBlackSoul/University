// Jakub Grobelny
// Pracownia PO, czwartek, s. 108
// L6, z5, Wielow�tkowe sortowanie przez scalanie.
// Test sortowania przez scalanie wykorzystuj�cego wielow�tkowo��.
// Main.java
// 2018-04-04

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        // Wypisywanie instrukcji, je�eli nie podano �cie�ki do �adnego pliku.
        if (args.length < 1)
        {
            System.out.print("To use the program you need to put the filename");
            System.out.println(" as command line argument!");
            System.out.println("Example:\njava Main file.txt");
            return;
        }

        // Zmienna przechowuj�ca informacj� o tym, czy nale�y wypisywa� elementy tablicy.
        boolean printElements = args[0].equals("-p") ? true : false;
        // Zmienna przechowuj�ca informacj� o tym, od kt�rego argumentu nale�y
        // zacz��odczytywanie.
        int firstArg = printElements ? 1 : 0;

        ArrayList<ArrayList<Integer>> arrays = new ArrayList<ArrayList<Integer>>();
        
        for (int i = firstArg; i < args.length; i++)
            arrays.add(new ArrayList<Integer>());

        try
        {
            // Odczytywanie zawarto�ci wszystkich plik�w.
            for (int i = firstArg; i < args.length; i++)
            {
                BufferedReader reader = new BufferedReader(new FileReader(new File(args[i])));
                
                String line = null;

                while ((line = reader.readLine()) != null)
                    arrays.get(i - firstArg).add(Integer.parseInt(line));

                reader.close();
            }
        }
        catch (Exception exc)
        {
            System.out.println(exc);
            return;
        }

        // Sortowanie kolejnych plik�w i wypisywanie informacji.
        for (int i = 0; i < args.length - firstArg; i++)
        {
            int[] array = new int[arrays.get(i).size()];

            for (int e = 0; e < arrays.get(i).size(); e++)
                array[e] = arrays.get(i).get(e);

            int[] sorted = MergeSort.sort(array);

            System.out.println(isSorted(sorted) ?
                                        "\nSorting was successful!" :
                                        "\nSorting failed!");

            if (printElements)
            {
                System.out.println("result:");

                for (int element : sorted)
                    System.out.println(element);    
            }
        }
    }

    // Metoda sprawdzaj�ca, czy sortowanie sie powiod�o.
    private static boolean isSorted(int[] array)
    {
        for (int i = 0; i < array.length - 1; i++)
            if (array[i] > array[i + 1])
                return false;

        return true;
    }
}