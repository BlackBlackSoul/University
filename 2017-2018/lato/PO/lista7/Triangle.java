// Jakub Grobelny
// Pracownia PO, czwartek, s. 108
// L7, Edytor obiekt�w.
// Implementacja klasy �Triangle�.
// Triangle.java
// 2018-04-14

import java.io.*;

// Klasa reprezentuj�ca Tr�jk�t.
public class Triangle extends Figure
{
    // Tablice wsp�rz�dnych x i y trzech wierzcho�k�w tr�jk�ta.
    private double[] x;
    private double[] y;

    // Unikalny identyfikator s�u��cy do serializacji.
    private static final long serialVersionUID = 7524472295622776147L;;

    // Metoda wczytuj�ca obiekt z pliku o podanej nazwie.
    // Zwraca fa�sz, je�eli nie uda�o si� odczyta�danych z podanego pliku.
    public boolean readFromFile(String path)
    {
        try
        {
            FileInputStream file = new FileInputStream(path);
            ObjectInputStream input = new ObjectInputStream(file);

            // Tworzenie tymczasowego obiektu i kopiowanie jego warto�ci.
            Triangle temp = (Triangle)input.readObject();
            this.x = temp.x;
            this.y = temp.y;
            this.posX = temp.posX;
            this.posY = temp.posY;
            this.name = temp.name;

            // Zamykanie pliku.
            input.close();
            file.close();

            return true;
        }
        catch (Exception exc)
        {
            return false;
        }
    }

    // Metoda zapisuj�ca obiekt do pliku.
    // Zwraca fa�sz, je�eli nie uda�o si� zapisa� obiektu.
    public boolean writeToFile(String path)
    {
        try
        {
            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream output = new ObjectOutputStream(file);
            
            output.writeObject(this);
            output.close();
            file.close();

            return true;
        }
        catch (Exception exc)
        {
            return false;
        }
    }

    // Metoda zamieni�ca obiekt na napis, kt�ry zawiera informacje
    // o polach obiektu.
    public String toString()
    {
        String vertexPositions = new String();

        // Przepisywanie wsp�rz�dnych wierzcho�k�w.
        for (int i = 0; i < 3; i++)
        {
            vertexPositions = vertexPositions + " " +
                              "x" + Integer.toString(i + 1) + ": " +
                              Double.toString(x[i]) + " " +
                              "y" + Integer.toString(i + 1) + ": " +
                              Double.toString(y[i]);
        }

        return name + vertexPositions;
    }

    // Metoda prywatna zwracaj�ca wsp�rz�dn� x lub y wierzcholka o indeksie
    // �which�. Je�eli jako �x� podana zostanie prawda, to zwr�cona zostanie
    // wsp�rz�dna x. W przeciwnym razie zwr�cona zostanie wsp�lrz�dna y.
    private double getVertex(int which, boolean x)
        throws IllegalArgumentException
    {
        if (which > 2 || which < 0)
            throw new IllegalArgumentException("Illegal vertex index!"); 

        return x ? this.x[which] : this.y[which];
    }

    // Metoda zwracaj�ca wsp�rz�dn� x wierzcho�ka o indeksie �which�.
    public double getVertexPosX(int which)
        throws IllegalArgumentException
    {
        return this.getVertex(which, true);
    }

    // Metoda zwracaj�ca wsp�rz�dn� y wierzcho�ka o indeksie �which�.
    public double getVertexPosY(int which)
        throws IllegalArgumentException
    {
        return this.getVertex(which, false);
    }

    // Metoda ustawiaj�ca wsp�rz�dne wierzcho�ka o indeksie �which� na
    // podane warto�ci.
    public void setVertex(int which, double x, double y)
        throws IllegalArgumentException
    {
        if (which > 2 || which < 0)
            throw new IllegalArgumentException("Illegal vertex index!");

        this.x[which] = x;
        this.y[which] = y;

        // Obliczanie nowego �rodka tr�jk�ta.
        double sumX = 0;
        for (double posX : this.x)
            sumX += posX;
        
        double sumY = 0;
        for (double posY : this.y)
            sumY += posY;

        this.posX = sumX / 3;
        this.posY = sumY / 3;
    }

    // Konstruktor.
    public Triangle(double x1, double x2, double x3,
                    double y1, double y2, double y3, String name)
    {
        super((x1 + x2 + x3) / 3,
              (y1 + y2 + y3) / 3,
              name);

        this.x = new double[3];
        this.y = new double[3];

        this.x[0] = x1;
        this.x[1] = x2;
        this.x[2] = x3;

        this.y[0] = y1;
        this.y[1] = y2;
        this.y[2] = y3;
    }

    // Konstruktor bezargumentowy.
    public Triangle()
    {
        this(0, 1, 0, 1, 0, 0, "");
    }    
}