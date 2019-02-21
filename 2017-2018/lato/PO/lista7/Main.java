// Jakub Grobelny
// Pracownia PO, czwartek, s. 108
// L7, Edytor obiekt�w.
// Test edytora obiekt�w klasy �Triangle� i �Circle�.
// Main.java
// 2018-04-14

import javax.swing.*;

public class Main
{
    // Wy��czanie ostrze�e� przy kompilacji zwi�zanych z tym, �e
    // obiekt edytora jest nieu�ywany (co jest w praktyce nieprawd�).
    @SuppressWarnings("unused")
    public static void main(String[] args)
    {
        // Na wej�ciu nale�y poda� nazw� pliku i rodzaj klasy.
        if (args.length != 2)
        {
            System.out.println("To use the program you need to specify " +
                               "file name and class name!");
            return;
        }

        // Sprawdzanie, czy podano poprawn� klas�.
        if (!args[1].equals("Triangle") && !args[1].equals("Circle"))
        {
            System.out.println("Invalid class!\n" + 
                               "Avaliable classes are Triangle and Circle!");
            return;
        }

        // Tworzenie okna.
        JFrame frame = new JFrame("Figure editor");
        
        String path = args[0];
        // Tworzenie edytora okr�gu b�d��tr�jk�ta.
        Editor editor = args[1].equals("Triangle") ?
                        new TriangleEditor(frame, path) :
                        new CircleEditor(frame, path);

        // Ustawienie odpowiednich opcji okna.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }
}