// Jakub Grobelny
// Pracownia PO, czwartek, s. 108
// L5, z1, Kolekcje por�wnywalnych element�w.
// Hierarchia klas implementuj�cych interfejs �Comparable<T>�.
// Function.java
// 2018-03-30

// Abstracyjna klasa, z kt�rej dziedzicz� inne klasy
// reprezentuj�ce r�ne typy funkcji.
public abstract class Function implements Comparable<Function>
{
    // Por�wnywanie funkcji w tym przyk�adzie polega
    // na sprawdzaniu, kt�ra funkcja ma wi�ksz�
    // ilo�� wi�kszych warto�ci na przedziale
    // od zera do tysi�ca.    
    public int compareTo(Function f)
    {
        int accumulator = 0;
        
        for (float x = 0.0f; x <= 1000.0f; x += 10.0f)
        {
            float y1 = this.valueAt(x);
            float y2 = f.valueAt(x);
            
            if (y1 > y2)
                accumulator++;
            else if (y1 < y2)
                accumulator--;
        }
        
        return accumulator;
    }

    // 
    public abstract String toString();
    
    // Metoda zwracaj�ca warto��funkcji w punkcie x.
    public abstract float valueAt(float x);
}