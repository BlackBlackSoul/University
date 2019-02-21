// Jakub Grobelny
// Pracownia PO, czwartek, s. 108
// L6, z5, Wielow�tkowe sortowanie przez scalanie.
// Implementacja sortowania przez scalanie wykorzystuj�cego wielow�tkowo��.
// MergeSort.java
// 2018-04-04

// Klasa, w kt�rej znajduje si� metoda sort().
public final class MergeSort
{
    // Podklasa rozszerzaj�ca klas� �Thread�, umo�liwiaj�ca jednoczesne
    // sortowanie dw�ch cz�ci tablicy.
    static class SortingThread extends Thread
    {
        // Referencja do sortowanej tablicy:
        private int[] array;

        // Pola, w kt�rych przechowywane s� informacje o indeksie
        // od kt�rego zaczyna i na kt�rym ko�czy si� dana cz�� ca�ej tablicy.
        private int start;
        private int end;

        // Przeci��ona, odziedziczona metoda, kt�ra 
        // wykonywana jest po uruchomieniu w�tku.
        // Metoda ta wykonuje sortowanie przez scalanie.
        public void run()
        {
            if (start == end)
                return;
            else
            {
                int middle = (start + end) / 2;
                Thread[] threads = new Thread[2];

                // Tworzenie oddzielnych w�tk�w, kt�re b�d� oddzielnie
                // sortowa�obie cz�ci tablicy.
                threads[0] = new SortingThread(array, start, middle);
                threads[1] = new SortingThread(array, middle + 1, end);
                
                for (Thread thread : threads)
                    thread.start();
                
                try
                {
                    // Wymuszanie oczekiwania na zako�czenie pracy obu
                    // w�tk�w przed scalaniem.
                    for (Thread thread : threads)
                        thread.join();
                }
                catch (Exception exc)
                {
                    System.out.println(exc);
                    System.exit(1);
                }

                merge(array, start, middle + 1, middle, end);
            }
        }

        // Konstruktor
        public SortingThread(int[] array, int start, int end)
        {
            this.array = array;
            this.start = start;
            this.end = end;
        }
    }

    // Publiczna statyczna metoda, kt�ra inicjuje sortowanie.
    // Metoda ta zwraca now� tablic� zamiast modyfikowa� t�, kt�ra
    // zosta�a podana jako parametr.
    public static int[] sort(int[] array)
    {
        // Tworzenie kopii tablicy.
        int[] copy = new int[array.length];
        System.arraycopy(array, 0, copy, 0, array.length);

        // Rozpoczecie sortowania.
        Thread sorting = new SortingThread(copy, 0, copy.length - 1);
        sorting.start();

        try
        {
            // Wymuszenie oczekiwania na zako�czenie pracy w�tku.
            sorting.join();
        }
        catch (Exception exc)
        {
            System.out.println(exc);
            System.exit(1);
        }

        return copy;
    }
 
    // Statyczna metoda scalaj�ca dwie cz�ci tablicy.
    private static void merge(int[] array, int s1, int s2, int e1, int e2)
    {
        // Tymczasowa tablica, w kt�rej b�dzie przechowywany
        // scalony fragment tablicy.
        int[] temp = new int[e2 - s1 + 1];
        
        int ptr1 = s1;
        int ptr2 = s2;
        int i = 0;
                
        // Przebieganie dw�ch cz�ci tablicy przy u�yciu dw�ch
        // indeks�w i dodawanie elemeent�w do tablicy �temp�
        // w kolejno�ci rosn�cej.
        while (ptr1 <= e1 && ptr2 <= e2)
        {
            if (array[ptr1] >= array[ptr2])
            {
                temp[i] = array[ptr2];
                ptr2++;
            }
            else
            {
                temp[i] = array[ptr1];
                ptr1++;
            }
            
            i++;
        }
        
        int ptr = (ptr1 <= e1) ? ptr1 : ptr2;
        int end = (ptr1 <= e1) ? e1 : e2;

        // Przepisywanie reszty element�w, je�eli tablice
        // nie by�y tego samego rozmiaru.
        while (ptr <= end)
        {
            temp[i] = array[ptr];
            ptr++;
            i++;
        }

        // Przepisywanie wyniku z �temp� do oryginalnej tablicy.
        ptr = s1;
        for (int element : temp)
        {
            array[ptr] = element;
            ptr++;
        }
    }
}