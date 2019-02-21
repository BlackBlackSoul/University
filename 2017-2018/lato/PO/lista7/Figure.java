// Jakub Grobelny
// Pracownia PO, czwartek, s. 108
// L7, Edytor obiekt�w.
// Abstrakcyjna klasa �Figure�.
// Figure.java
// 2018-04-14

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// Abstrakcyjna klasa �Figure� implementuj�ca interfejs �Serializable�
// pozwalaj�cy  na zapisywanie i odczytywanie obiekt�w z pliku.
public abstract class Figure implements Serializable
{
    // Nazwa oraz wsp�rz�dne �rodka figury.
    protected String name;
    protected double posX;
    protected double posY;

    // Unikalny identyfikator s�u��cy do serializacji.
    private static final long serialVersionUID = 7521472295622776147L;;

    // Abstrakcyjne metody zapisu/odczytu obiektu z pliku.
    public abstract boolean readFromFile(String path);    
    public abstract boolean writeToFile(String path);

    // Metoda zamieniaj�ca figur� na napis.
    public String toString()
    {
        return name + " " + 
               "x: " + Double.toString(posX) + ", " + 
               "y: " + Double.toString(posY);
    }

    // Settery/gettery p�l obiektu.

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPosX(double x)
    {
        this.posX = x;
    }

    public void setPosY(double y)
    {
        this.posY = y;
    }

    public String getName()
    {
        return this.name;
    }

    public double getPosX()
    {
        return this.posX;
    }

    public double getPosY()
    {
        return this.posY;
    }

    // Konstruktor.
    public Figure(double x, double y, String name)
    {
        this.posX = x;
        this.posY = y;
        this.name = name;
    }

    // Konstruktor bezargumentowy.
    public Figure()
    {
        this(0, 0, "");
    }
}