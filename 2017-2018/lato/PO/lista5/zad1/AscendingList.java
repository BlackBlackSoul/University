// Jakub Grobelny
// Pracownia PO, czwartek, s. 108
// L5, z1, Kolekcje por�wnywalnych element�w.
// Implementacja list uporz�dkowanych rosn�co, kt�re zawieraj� elementy
// implementuj�ce interfejst �Comparable<T>�.
// Element.java
// 2018-03-30

// Klasa reprezentuj�ca list� uporz�dkowan� rosn�co.
public class AscendingList<T extends Comparable<T>>
{
    // Referencja do pocz�tku listy. Lista sk�ada si�
    // z obiekt�w klasy Element<T>.
    private Element<T> listHead;

    // Metoda s�u��ca do wypisywania element�w listy.
    public void print()
    {
        Element<T> ptr = listHead;

        while (ptr != null)
        {
            System.out.println(ptr.getValue());
            ptr = ptr.getNext();
        }
    }

    // Metoda s�u��ca do sprawdzania, czy lista jest pusta.
    public boolean empty()
    {
        return (listHead == null);
    }

    // Metoda s�u��ca do pobierania i usuwania pierwszego elementu.
    public T pop() throws IllegalStateException
    {
        if (listHead != null)
        {
            T result = listHead.getValue();
            listHead = listHead.getNext();
            return result;
        }
        else
            throw new IllegalStateException("pop(): list is empty!");
    }

    // Metoda s�u��ca do dodawania nowego elementu. Ka�dy element jest 
    // dodawany w takim miejscu, �e lista jest posortowana od najmniejszego
    // do najwi�kszego elementu.
    public void push(T value)
    {
        if (listHead == null)
            listHead = new Element<T>(value, null);
        else if (listHead.getValue().compareTo(value) > 0)
        {
            Element<T> newHead = new Element<T>(value, listHead);
            this.listHead = newHead;
        }
        else
        {
            Element<T> ptr = listHead;
    
            while ((ptr.getNext() != null) && (ptr.getNext().getValue().compareTo(value) < 0))
                ptr = ptr.getNext();
    
            ptr.setNext(new Element<T>(value, ptr.getNext()));
        }
    }

    // Konstruktor.
    public AscendingList()
    {
        this.listHead = null;
    }
}