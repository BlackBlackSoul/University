// Jakub Grobelny
// Pracownia PO, czwartek, s. 108
// L5, z2, Wyra�enia arytmetyczne.
// Implementacja reprezentacji wyra�e�
// arytmetycznych w postaci drzew.
// Variable.java
// 2018-03-29

import java.util.HashMap;

// Klasa reprezentuj�ca lis� drzewa zawieraj�cy zmienn�.
public class Variable extends Expression
{
    // Pole zawierajace symbol zmiennej.
    private String symbol;

    // Publiczna, statyczna mapa haszowania zawieraj�ca warto�ci
    // przypisane danym zmiennym.
    public static HashMap<String, Float> variables = new HashMap<String, Float>();
    
    // Publiczna, statyczna metoda s�u��ca do dodawania nowych
    // zmiennych do mapy.
    public static void addVariable(String symbol, float val)
    {
        variables.put(symbol, val);
    }

    // Metoda wyliczaj�ca warto�� zmiennej. Je�eli zmienna nie istnieje,
    // to wyrzucony zostaje wyj�tek IllegalArgumentException.
    public float evaluate() throws IllegalArgumentException
    {
        if (variables.get(symbol) != null)
            return variables.get(symbol);
        else
            throw new IllegalArgumentException();
    }

    // Metoda zwracaj�ca symbol jako �a�cuch znak�w.
    public String toString()
    {
        return symbol;
    }

    // Konstruktor.
    public Variable(String symbol)
    {
        this.symbol = symbol;
    }
}