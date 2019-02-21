// Jakub Grobelny
// Pracownia PO, czwartek, s. 108
// L4, z4, Gramatyki bezkontestowe
// Program testuj�cy implementacj�
// gramatyk bezkontekstowych poprzez
// losowanie s��w zgodnych z zadan�
// gramatyk�.
// main.cs
// 2018-03-22

using Grammars;
using System;
using System.IO;

class MainClass
{
    // Funkcja main
    public static void Main(string[] args)
    {
        ProductionRule[] productionRules;
        char startingSymbol;

        // Wczytywanie wej�cia z pliku.
        if (args.Length != 0)
        {
            try
            {                
                StreamReader file = File.OpenText(args[0]);
                string line;

                startingSymbol = file.ReadLine()[0];
                int numberOfRules = int.Parse(file.ReadLine());

                productionRules = new ProductionRule[numberOfRules];

                for (int i = 0; i < numberOfRules; i++)
                {
                    line = file.ReadLine();
                    productionRules[i] = new ProductionRule(line[0], line.Substring(3, line.Length - 3));
                }

            }
            catch
            {
                Console.WriteLine("Failed to open {0}", args[0]);
                return;
            }
        }
        // Wczytywanie danych wprowadzonych przez u�ytkownika.
        else
        {
            // Wczytywanie ilo�ci zasad wyprowadzania.
            int numberOfRules;

            Console.Write("Enter the number of production rules: ");

            if (!int.TryParse(Console.ReadLine(), out numberOfRules))
            {
                Console.WriteLine("Invalid input!");
                return;
            }
            productionRules = new ProductionRule[numberOfRules];

            // Wczytywanie symbolu startowego.
            Console.Write("Enter the starting symbol: ");
            
            startingSymbol = Console.ReadLine()[0];

            // Wczytywanie kolejnych zasad wyprowdzania.
            for (int i = 0; i < numberOfRules; i++)
            {
                char nonTerminal;
                string production;

                Console.Write("Enter the symbol: ");
                nonTerminal = Console.ReadLine()[0];

                Console.Write("Enter the production: ");  
                production = Console.ReadLine();

                productionRules[i] = new ProductionRule(nonTerminal, production);
            }

        }


        var grammar = new ContextFreeGrammar(startingSymbol, productionRules);

        // Pytanie u�ytkownika, czy chce aby generowano tak�� puste s�owa.
        Console.WriteLine("Do you want to generate empty words? (Y/N)");
        char option = 'X';

        while (Char.ToLower(option) != 'n' && Char.ToLower(option) != 'y')
        {
            option = Console.ReadLine()[0];
        }
        option = Char.ToLower(option);

        // Wczytywanie ilo�ci s��w do wygenerowania.
        Console.Write("How many words do you want to generate?: ");
        int numberOfWords;

        if (!int.TryParse(Console.ReadLine(), out numberOfWords))
        {
            Console.WriteLine("Invalid input!");
            return;
        }

        // Generowanie s��w.
        for (int i = 0; i < numberOfWords; i++)
        {
            try
            {
                string word = grammar.GenerateWord();

                if (option == 'n' && word.Length < 10)
                    i--;
                else
                    Console.WriteLine("{0}\n", word);
            }
            catch (Exception exc)
            {
                System.Console.WriteLine(exc);
                return;
            }
        } 
    }
}