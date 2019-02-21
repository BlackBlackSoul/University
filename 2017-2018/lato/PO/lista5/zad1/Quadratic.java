// Jakub Grobelny
// Pracownia PO, czwartek, s. 108
// L5, z1, Kolekcje por�wnywalnych element�w.
// Hierarchia klas implementuj�cych interfejs �Comparable<T>�.
// Quadratic.java
// 2018-03-30

// Klasa reprezentuj�ca funkcj� kwadratow�.
public class Quadratic extends Function
{
    // f(x) = ax^2 + bx + c
    private float a;
    private float b;
    private float c;

    // Metoda zamieniaj�ca funkcj� na napis.
    public String toString()
    {
        String result = "";
        if (a != 0)
            result = Float.toString(a) + "x^2 + ";
        if (b != 0)
            result += Float.toString(b) + "x + ";
        if (c != 0)
            result += Float.toString(c);

        return result;
    }

    // Metoda zwracaj�ca warto�� funkcji w punkcie x.
    public float valueAt(float x)
    {
        return a*x*x + b*x + c;
    }

    // Konstruktor
    public Quadratic(float a, float b, float c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}