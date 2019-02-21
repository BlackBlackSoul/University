// Jakub Grobelny
// Pracownia PO, czwartek, s. 108
// L6, z2, Kolekcja implementuj�ca interfejs �Collection�.
// Implementacja kolekcji (listy dwukierunkowej) implementuj�cej
// interfejs �Collection�.
// List.java
// 2018-04-05

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;
import java.util.Arrays;

// Lista dwukierunkowa implementuj�ca interfejs �Collection�.
public class List<E> implements Collection<E>
{
    // Klasa reprezentuj�ca pojedynczy element listy.
    private class Element<T>
    {
        // Warto�� elementu oraz referencje do 
        // nast�pnego i poprzedniego elementu.
        public T value;
        public Element<T> previous;
        public Element<T> next;

        // Konstruktor.
        public Element(T value)
        {
            this.value = value;
            this.previous = null;
            this.next = null;
        }
        
    }

    // Klasa reprezentuj�ca iterator kolekcji.
    private class ListIterator implements Iterator<E>
    {
        // Referencja do listy, kt�r� przebiega iterator.
        Element<E> current;

        // Metoda sprawdzaj�ca, czy istnieje element nast�pny.
        public boolean hasNext()
        {
            return (current != null);
        }

        // Metoda zwracaj�ca kolejny element kolekcji.
        public E next() throws NoSuchElementException
        {
            if (current == null)
                throw new NoSuchElementException();

            E value = current.value;
            current = current.next;
            return value;
        }

        // Metoda (nie jest wymagana przez interfejs)
        // usuwaj�ca aktualnie wskazywany element.
        public void remove() throws UnsupportedOperationException
        {
            throw new UnsupportedOperationException();
        }

        // Konstruktor
        ListIterator(List<E> list)
        {
            current = list.beginning;
        }
    }

    // Referencja do poczatku i ko�ca listy.
    private Element<E> beginning;
    private Element<E> end;
    // Ilo��element�w listy.
    private int size;

    // Metoda zwracaj�ca iterator listy.
    public Iterator<E> iterator()
    {
        return new ListIterator(this);
    }

    // Metoda sprawdzaj�ca, czy lista jest pusta.
    public boolean isEmpty()
    {
        return size == 0;
    }

    // Metoda dodaj�ca nowy element do listy. Zwraca prawd�, je�eli
    // lista zosta�a zmodyfikowana (w tym przyk�adzie zawsze dodawany
    // jest element).
    public boolean add(E element)
    {
        pushBack(element);
        return true;
    }

    // Metoda usuwaj�ca wszystkie elementy o warto�ci �element�.
    // Zwraca prawd�, je�eli lista zosta�a zmodyfikowana.
    public boolean remove(Object element)
    {
        boolean wasRemoved = false;

        List<E> newList = new List<E>();
        
        while(!isEmpty())
        {
            E value = popFront();
            
            if (value.equals(element))
                wasRemoved = true;
            else
                newList.pushBack(value);
        }

        this.beginning = newList.beginning;
        this.end = newList.end;
        this.size = newList.size;

        return wasRemoved;
    }

    // Metoda usuwaj�ca wszystkie elementy z listy.
    public void clear()
    {
        size = 0;
        beginning = null;
        end = null;
    }

    // Metoda zwracajaca rozmiar listy.
    public int size()
    {
        return size;
    }

    // Metoda sprawdzaj�ca, czy wszystkie elementy z listy �list�
    // znajduj� si� r�wnie� w li�cie, dla kt�rej wywo�ywana jest metoda.
    @SuppressWarnings("unchecked")
    public boolean containsAll(Collection<?> list)
    {        
        for (E element : (Collection<E>)list)
        {
            Element<E> ptrThis = beginning;

            while (ptrThis != null)
            {
                if (ptrThis.value.equals(element))
                    break;

                ptrThis = ptrThis.next;
            }
            
            if (ptrThis == null)
                return false;
        }

        return true;
    }

    // Metoda dodaj�ca wszystkie elementy z kolekcji �collection�, kt�re
    // nie znajdowa�y si� na dotychczasowej liscie.
    // Zwraca prawd�, je�eli lista zosta�a zmodyfikowana.
    public boolean addAll(Collection<? extends E> collection)
    {
        boolean changed = false;

        for (E element : collection)
        {
            boolean exists = false;

            Element<E> ptrThis = beginning;

            while (ptrThis != null)
                if (ptrThis.value.equals(element))
                    exists = true;

            if (!exists)
            {
                pushBack(element);
                changed = true;
            }
        }

        return changed;
    }

    // Metoda usuwaj�ca wszystkie elementy z kolekcji �list�, kt�re znajduj�
    // si� w kolekcji, dla kt�rej wywo�ana zosta�a metoda.
    // Zwraca prawd�, je�eli lista zosta�a zmodyfikowana.
    @SuppressWarnings("unchecked")
    public boolean removeAll(Collection<?> list)
    {
        boolean changed = false;
        
        for (E element : (Collection<E>)list)
        {
            int prevSize = size;

            remove(element);

            if (size < prevSize)
                changed = true;
        }

        return changed;
    }

    // Metoda usuwaj�ca z listy takie elementy, kt�re nie znajduj�
    // si� w li�cie �list�.
    // Zwraca prawd�, je�eli lista zosta�a zmodyfikowana.
    public boolean retainAll(Collection<?> list)
    {
        boolean changed = false;

        for (E element : this)
        {
            if (!list.contains(element))
            {
                remove(element);
                changed = true;
            }
        }

        return changed;
    }

    // Metoda zmieniaj�ca list� na tablic�.
    public Object[] toArray()
    {
        Object[] array = new Object[size];

        Element<E> ptr = beginning;
        int i = 0;

        while (ptr != null)
        {
            array[i++] = ptr.value;
            ptr = ptr.next;
        }

        return array;
    }

    // Metoda zmieniaj�ca list� na tablic� zadanego typu.
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] array)
    {
        Object[] original = toArray();
        T[] result = Arrays.copyOf(array, original.length);

        for (int i = 0; i < original.length; i++)
            result[i] = (T)original[i];

        return result;
    }

    // Metoda sprawdzaj�ca, czy zadany element �obj� znajduje si� w li�cie.
    public boolean contains(Object obj)
    {
        if (obj == null)
            return false;

        Element<E> ptr = beginning;
        
        while (ptr != null)
        {
            if (ptr.value.equals(obj))
                return true;

            ptr = ptr.next;
        }
    
        return false;
    }

    // Metoda dodaj�ca nowy element na pocz�tek listy.
    public void pushFront(E value)
    {
        size++;

        Element<E> newFront = new Element<E>(value);
        newFront.next = beginning;

        if (newFront.next == null)
            end = newFront;
        else
            beginning.previous = newFront;

        beginning = newFront;
    }

    // Metoda dodaj�ca nowy element na koniec listy.
    public void pushBack(E value)
    {
        size++;

        Element<E> newEnd = new Element<E>(value);
        newEnd.previous = end;

        if (newEnd.previous == null)
            beginning = newEnd;
        else
            end.next = newEnd;
            
        end = newEnd;
    }

    // Metoda usuwaj�ca i zwracaj�ca element z pocz�tku listy.
    public E popFront() throws IllegalStateException
    {
        size--;

        if (beginning != null)
        {
            Element<E> newFront = beginning.next;

            if (newFront != null)
                newFront.previous = null;
            else
                end = null;
        
            E result = beginning.value;
            
            beginning = newFront;

            return result;
        }
        else
            throw new IllegalStateException("popFront(): list is empty!");
    }

    // Metoda usuwaj�ca i zwracaj�ca ostatni element listy.
    public E popBack() throws IllegalStateException
    {
        size--;

        if (end != null)
        {
            Element<E> newEnd = end.previous;

            if (newEnd != null)
                newEnd.next = null;
            else
                beginning = null;

            E result = end.value;

            end = newEnd;

            return result;
        }
        else
            throw new IllegalStateException("popBack(): list is empty!");

    }

    // Metoda zwracaj�ca element o zadanym indeksie.
    public E get(int index) throws IndexOutOfBoundsException
    {
        if (index >= size)
            throw new IndexOutOfBoundsException("get(): index out of range!");
        else
        {
            int i = 0;

            Element<E> ptr = beginning;

            while (i != index)
            {
                i++;
                ptr = ptr.next;
            }

            return ptr.value;
        }
    }

    // Metoda ustawiaj�cy element o podanym indeksie na podan� warto��.
    public void set(int index, E value) throws IndexOutOfBoundsException
    {
        if (index >= size)
            throw new IndexOutOfBoundsException("set(): index out of range!");
        else
        {
            Element<E> ptr = beginning;
            int i = 0;

            while (i != index)
            {
                ptr = ptr.next;
                i++;
            }

            ptr.value = value;
        }
    }

    // Konstruktor.
    public List()
    {
        this.beginning = null;
        this.end = null;
        this.size = 0;
    }
}