// Jakub Grobelny
// Pracownia PO, czwartek, s. 108
// L3, z1, Listy dwukierunkowe
// Modu� z implementacj� list dwukierunkowych
// list.cs
// 2018-03-14

namespace Lists
{
    // Klasa List<T> reprezentuj�ca list� dwukierunkow�
    // zawieraj�c� obiekty dowolnego typu 'T'.
    public class List<T>
    {
        // Podklasa Element<T1> b�d�ca pojedynczym
        // elementem listy. Zawiera warto�� oraz
        // wska�niki na nast�pny i poprzedni element.
        private class Element<T1>
        {
            public T1 val;
            public Element<T1> next;
            public Element<T1> previous;

            // Konstruktor.
            public Element(T1 val)
            {
                this.val = val;
                this.previous = null;
                this.next = null;
            }
        }

        // Prywatne pola klasy element b�d�ce
        // wska�nikami na pierwszy i ostatni
        // element z listy.
        private Element<T> beginning;
        private Element<T> end;
        private int size;

        // Predykat sprawdzaj�cy, czy lista jest pusta.
        public bool IsEmpty()
        {
            return (beginning == null && end == null);
        }

        // Metoda zwracaj�ca ilo�� element�w w li�cie
        public int Size()
        {
            return size;
        }

        // Metoda dodaj�ca element z warto�ci� 'val'
        // na pocz�tek listy.
        public void PushFront(T val)
        {
            size++;

            Element<T> newFront = new Element<T>(val);
            newFront.next = beginning;

            if (newFront.next == null)
                end = newFront;
            else
                beginning.previous = newFront;

            beginning = newFront;
        }

        // Metoda dodaj�ca element z warto�ci� 'val'
        // na koniec listy.
        public void PushBack(T val)
        {
            size++;

            Element<T> newEnd = new Element<T>(val);
            newEnd.previous = end;

            if (newEnd.previous == null)
                beginning = newEnd;
            else
                end.next = newEnd;

            end = newEnd;
        }

        // Metoda usuwaj�ca i zwracaj�ca pierwszy
        // element z listy. Je�eli lista jest pusta
        // rzucony zostanie wyj�tek.
        public T PopFront()
        {
            size--;

            if (beginning != null)
            {
                Element<T> newFront = beginning.next;

                if (newFront != null)
                    newFront.previous = null;
                else
                    end = null;

                T result = beginning.val;

                beginning = newFront;

                return result;
            }
            else
                throw new System.InvalidOperationException();
        }

        // Metoda usuwajaca i zwracaj�ca ostatni
        // element z listy. Je�eli lista jest pusta
        // rzucony zostanie wyj�tek.
        public T PopBack()
        {
            size--;

            if (end != null)
            {
                Element<T> newEnd = end.previous;

                if (newEnd != null)
                    newEnd.next = null;    
                else
                    beginning = null;            

                T result = end.val;

                end = newEnd;

                return result;
            }
            else
                throw new System.InvalidOperationException();
        }

        // Operator indeksu s�u��cy do uzyskania 
        // i-tego elementu lub zmiany jego warto�ci.
        public T this[int i]
        {
            get
            {
                return GetValue(i);
            }
            set
            {
                SetValue(i, value);
            }
        }   

        // Prywatne procedury wykorzystywane przed operator indeksu.
        private T GetValue(int i)
        {
            if (i < 0)
                throw new System.IndexOutOfRangeException();

            var pointer = beginning;

            for (int iter = 1; iter <= i; iter++)
            {
                if (pointer == null)
                    throw new System.IndexOutOfRangeException();
                
                pointer = pointer.next;
            }

            return pointer.val;
        }

        private void SetValue(int i, T value)
        {
            if (i < 0)
                throw new System.IndexOutOfRangeException();

            var pointer = beginning;

            for (int iter = 1; iter <= i; iter++)
            {
                if (pointer == null)
                    throw new System.IndexOutOfRangeException();
                
                pointer = pointer.next;
            }

            pointer.val = value;
        }

        // Konstruktor (lista domy�lnie jest pusta).
        public List()
        {
            this.beginning = null;
            this.end = null;
            this.size = 0;
        }
    }
}