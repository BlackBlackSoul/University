// Jakub Grobelny
// Pracownia PO, czwartek, s. 108
// L5, z1, Kolekcje por�wnywalnych element�w.
// Implementacja list uporz�dkowanych rosn�co, kt�re zawieraj� elementy
// implementuj�ce interfejst �Comparable<T>�.
// Element.java
// 2018-03-30

// Klasa reprezentuj�ca pojedynczy element listy.
public class Element<T extends Comparable<T>>
{
    // Pola zawieraj�ce warto�� oraz referencj� do kolejnego elementu.
    private T value;
    private Element<T> next;

    // Metoda zwracaj�ca warto�� prywatnego pola �value�.
    public T getValue()
    {
        return this.value;
    }

    // Metoda zmieniaj�ca warto�� prywatnego pola �value�.
    public void setValue(T value)
    {
        this.value = value;
    }

    // Metoda zwracaj�ca warto�� prywatnego pola �next�.
    public Element<T> getNext()
    {
        return this.next;
    }

    // Metoda zmieniaj�ca warto�� prywatnego pola �next�.
    public void setNext(Element<T> next)
    {
        this.next = next;
    }

    // Konstruktor
    public Element(T value, Element<T> next)
    {
        this.value = value;
        this.next = next;
    }
}
