// Jakub Grobelny
// Pracownia PO, czwartek, s. 108
// L5, z1, Kolekcje por�wnywalnych element�w.
// Hierarchia klas implementuj�cych interfejs �Comparable<T>�.
// Rational.java
// 2018-03-30

// Klasa reprezentuj�ca funkcj� wymiern�.
public class Rational extends Function
{
    // Wielomiany b�d�ce licznikiem i mianownikiem funkcji wymiernej.
    Polynomial numerator;
    Polynomial denominator;

    // Metoda zamieniaj�ca funkcj� na napis.
    public String toString()
    {
        return "( " + numerator.toString() + " ) / (" + denominator.toString() + " )";
    }

    // Metoda obliczaj�ca warto�� funkcji w punkcie x.
    public float valueAt(float x)
    {
        // Je�eli mianownik jest r�wny zero to zwr�cona zostanie
        // warto��Float.POSITIVE_INFINITY.
        return this.numerator.valueAt(x) / this.denominator.valueAt(x);
    }

    // Konstruktory.
    public Rational(float[] coeffsNum, float[] coeffsDen)
    {
        this.numerator = new Polynomial(coeffsNum);
        this.denominator = new Polynomial(coeffsDen);
    }

    public Rational(Polynomial numerator, Polynomial denominator)
    {
        this.numerator = numerator;
        this.denominator = denominator;
    }
}