// Jakub Grobelny
// Pracownia PO, czwartek, s. 108
// L5, z2, Wyra�enia arytmetyczne.
// Implementacja reprezentacji wyra�e�
// arytmetycznych w postaci drzew.
// Constant.java
// 2018-03-29

// Klasa reprezentuj�ca li��drzewa zawieraj�cy sta��.
public class Constant extends Expression
{
    // Pole zawieraj�ce warto��sta�ej.
    private float value;

    // Metoda zwracaj�ca warto��sta�ej.
    public float evaluate()
    {
        return value;
    }

    // Metoda zamieniaj��a sta�� na �a�cuch znak�w.
    public String toString()
    {
        return Float.toString(value);
    }

    // Konstruktor
    public Constant(float value)
    {
        this.value = value;
    }
}