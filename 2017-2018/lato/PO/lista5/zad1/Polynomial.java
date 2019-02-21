// Jakub Grobelny
// Pracownia PO, czwartek, s. 108
// L5, z1, Kolekcje por�wnywalnych element�w.
// Hierarchia klas implementuj�cych interfejs �Comparable<T>�.
// Polynomial.java
// 2018-03-30

// Klasa reprezentuj�ca dowolny wielomian.
public class Polynomial extends Function
{
    // Wsp�czynniki wielomianu zapisane w takiej kolejno�ci,
    // �e wsp�czynnik przy x^0 jest pierwszym elementem tablicy.
    float[] coefficients;

    // Metoda zamieniaj�ca funkcj� na napis.
    public String toString()
    {   
        String result = "";

        for (int i = coefficients.length - 1; i > 0; i--)
        {
            if (coefficients[i] != 0)
            {
                result = result + Float.toString(coefficients[i]);
                result = result  + "x^" + Integer.toString(i) + " + ";
            }
        }
        if (coefficients[0] != 0 || coefficients.length == 1)
            result += Float.toString(coefficients[0]);
    
        return result;
    }

    // Metoda obliczaj�ca warto�� wielomianu w punkcie x.
    public float valueAt(float x)
    {
        float sum = 0;
        float xToPower = 1;

        for (float coefficient : coefficients)
        {
            sum += coefficient * xToPower;
            xToPower *= x;
        }

        return sum;
    }

    // Konstruktor.
    public Polynomial(float[] coefficients)
    {
        this.coefficients = coefficients;
    }
}