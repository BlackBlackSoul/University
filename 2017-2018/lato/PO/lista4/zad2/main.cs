// Jakub Grobelny
// Pracownia PO, czwartek, s. 108
// L4, z2, Kolekcja liczb pierwszych
// Program testuj�cy implementacj� kolekcji 
// generuj�cej kolejne liczby pierwsze.
// main.cs
// 2018-03-21

using Primes;
using System;

class MainClass
{
    // Funkcja Main
    public static void Main(string[] args)
    {
        PrimeCollection primes = new PrimeCollection();

        // Je�eli u�ytkownik nie poda limitu liczb, to wygenerowane
        // zostan� wszystkie liczby pierwsze mieszcz�ce si� w zmiennej
        // typu int (32 bitowej).
        int limit = Int32.MaxValue;

        // Sprawdzanie, czy u�ytkownik poda� limit, do kt�rego b�d�
        // wyznaczane kolejne liczby pierwsze.
        if (args.Length > 0)
        {
            // Sprawdzanie, czy podany argument jest poprawn� liczb� ca�kowit�.
            if (!Int32.TryParse(args[0], out limit))
            {
                Console.WriteLine("Given command line argument is invalid!");
                return;
            }
            // Wypisywanie komunikatu z b��dem, je�eli nie istniej� liczby
            // pierwsze mniejsze ni��zadany limit.
            else if (limit <= 2)
            {
                Console.WriteLine("There are no prime numbers smaller than 2!");
                return;
            }
        }

        // Przebieganie przez kolekcj�i wyznaczanie kolejnych liczb.
        // Je�eli nowo wyznaczona liczba przekracza podany limit, to
        // p�tla zostaje przerwana.
        foreach (var prime in primes)
        {
            if (prime >= limit)
                break;
            Console.WriteLine("{0}", prime);
        }
    }
}