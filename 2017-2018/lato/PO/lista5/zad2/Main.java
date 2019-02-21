// Jakub Grobelny
// Pracownia PO, czwartek, s. 108
// L5, z2, Wyra�enia arytmetyczne.
// Program testuj�cy implementacj� reprezentacji
// wyra�e�arytmetycznych.
// Main.java
// 2018-03-29

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

// Klasa z metod� main.
public class Main
{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        // Sprawdzanie, czy u�ytkownik poda� nazw� pliku do wczytania.
        if (args.length < 1)
        {
            System.out.print("To use the program you need to put the filename");
            System.out.println(" as command line argument!");
            System.out.println("Example:\njava Main file.txt");
            return;
        }
        else
        {
            // Lista napis�w, kt�re s� podanymi S-wyra�eniami.
            ArrayList<String> expressions = new ArrayList<String>();
            
            try 
            {
                BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])));
                String line = null;
                
                boolean definitions = true;

                // Wczytywanie kolejnych definicji oraz wyra�e� z podanego pliku.
                while ((line = reader.readLine()) != null)
                {
                    // Sprawdzanie, czy aktualna linia jest etykiet���examples�
                    if (line.equals("examples:"))
                        definitions = false;
                    // Je�eli sko�czono wczytywa� definicje, to odczytana linia
                    // zostanie dodana do listy �expressions�.
                    else if (!definitions)
                    {
                        if (line.charAt(0) != '#')
                            expressions.add(line);
                    }
                    // W przeciwnym razie dodawany jest nowy element do mapy zmiennych.
                    else if (!line.equals("definitions:") && (line.charAt(0) != '#'))
                    {

                        String[] definition = line.split("\\s+");
                        Variable.addVariable(definition[0], Float.parseFloat(definition[1]));
                    }
                }

                reader.close();
            }
            catch (FileNotFoundException exc)
            {
                System.out.println("Failed to open the file!");
                return;
            }

            try
            {        
                // Ewaluacja kolejnych wyra�e� z listy �expressions�.
                for (String expression : expressions)
                {
                    Expression expr = Parser.parseExpression(expression);
                    System.out.print(expr + "=");
                    System.out.println(expr.evaluate() + "\n");
                }
            }
            // Zwracanie wyj�tku w przypadku napotkania na b��d przy ewaluacji.
            catch(Exception exc)
            {
                System.out.println("exception: " + exc);
            }
        }
    }
}   