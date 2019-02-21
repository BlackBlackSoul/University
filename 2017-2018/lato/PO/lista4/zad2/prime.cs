// Jakub Grobelny
// Pracownia PO, czwartek, s. 108
// L4, z2, Kolekcja liczb pierwszych
// Implementacja kolekcji liczb pierwszych, kt�r�
// mo�na przegl�da� przy u�yciu p�tli �foreach�.
// prime.cs
// 2018-03-21

using System.Collections;

// Przestrze� nazw Primes
namespace Primes
{

    // Klasa reprezentuj�ca kolekcj� liczb pierwszych.
    // (Implementuje interfejs �IEnumerable�.)
    public class PrimeCollection : IEnumerable
    {
        // Prywatna podklasa implementuj�ca iterator kolekcji.
        public class PrimeEnumerator : IEnumerator
        {
            // Ostatnio wyznaczony element kolekcji. Kolejne liczby
            // pierwsze s� wyznaczane na bierz�co i nie s� nigdzie
            // przechowywane.
            int current;

            // Predykat sprawdzaj�cy pierwszo�� zmiennej �current�.
            private bool IsPrime()
            {            
                if (this.current % 2 == 0 && this.current != 2)
                    return false;

                for (int i = 3; i <= (int)System.Math.Sqrt(this.current); i += 2)
                    if (this.current % i == 0)
                        return false;

                return true;
            }

            // Konstruktor (ustawia pole �current� na 1). 
            // (iterator zaczyna przebiega� kolekcj� od
            // 'minus pierwszego' elementu.)
            public PrimeEnumerator()
            {
                this.Reset();
            }

            // Metoda wymagana przez interfejs �IEnumerator�. Znajduje kolejny
            // element w kolekcji. Je�eli nie ma kolejnego elementu, to
            // zwraca fa�sz. W przeciwnym razie zwraca prawd�.
            public bool MoveNext()
            {
                this.current++;

                while (!IsPrime())
                {
                    if (this.current == System.Int32.MaxValue)
                        return false;
                    this.current++;
                }

                return true;
            }

            // Ustawienie iteratora z powrotem na pocz�tkowy element.
            public void Reset()
            {
                this.current = 1;
            }

            // W�a�ciwo�� �Current� wymagana przez interfejs.
            object IEnumerator.Current
            {
                get { return Current; }
            }

            // W�a�ciwo�� pozwalaj�ca na dost�p do aktualnego elementu.
            public int Current
            {
                get { return this.current; }
            }
        }

        // Konstruktor kolekcji.
        public PrimeCollection() {}

        // Metoda wymagana przez interfejs, zwracaj�ca iterator.
        IEnumerator IEnumerable.GetEnumerator()
        {
            return (IEnumerator)GetEnumerator();
        }

        // Implementacja metody tworz�cej iterator.
        public PrimeEnumerator GetEnumerator()
        {
            return new PrimeEnumerator();
        }
    }

}