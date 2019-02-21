// Jakub Grobelny
// Pracownia PO, czwartek, s. 108
// L4, z4, Gramatyki bezkontekstowe
// Implementacja gramatyk bezkontekstowych
// z mo�liwo�ci� wyprowadzania losowych s��w.
// grammars.cs
// 2018-03-22

// Przestrze�nazw Grammars.
namespace Grammars
{   
    // Klasa ContextFreeGrammar reprezentuj�ca gramatyk� bezkontekstow�, 
    // czyli zbi�r terminali, nieterminali, zasad wyprowadzania oraz
    // symbolu startowego.
    public class ContextFreeGrammar
    {
        // Tablica zasad wyprowadzania.
        private ProductionRule[] productionRules;
        // Symbol startowy.
        private char startingSymbol;
        // Obiekt generuj�cy liczby pseudolosowe.
        System.Random randomizer;

        // Konstruktor.
        public ContextFreeGrammar(char startingSymbol, ProductionRule[] rules)
        {
            randomizer = new System.Random();

            this.startingSymbol = startingSymbol;
            this.productionRules = rules;
        }

        // Predykat sprawdzaj�cy, czy s�owo zawiera nieterminale.
        private static bool ContainsNonTerminals(string word)
        {
            foreach (var symbol in word)
                if (IsNonTerminal(symbol))
                    return true;
            return false;
        }

        // Predykat sprawdzaj�cy, czy dany symbol jest nieterminalem.
        private static bool IsNonTerminal(char symbol)
        {
            return (symbol >= 'A' && symbol <= 'Z');
        }

        // Procedura losuj�ca zasad�wyprowadzania, kt�ra zostanie zastosowana
        // przy generowaniu nowego losowego wyrazu.
        private ProductionRule RandomProductionRule(char symbol)
        {
            ProductionRule[] rulesTempArray = new ProductionRule[productionRules.Length];
            
            int i = 0;

            // Tworzenie tymczasowej tablicy, kt�ra zawiera zasady
            // wyprowadzania dla zadanego symbolu.
            foreach (var productionRule in productionRules)
                if (productionRule.NonTerminal == symbol)
                    rulesTempArray[i++] = productionRule;

            // Je�eli nie znaleziono zasady wyprowadzania dla danego
            // nieterminalu, to zwracany jest b��d.
            if (i == 0)
                throw new System.Exception("Error! Grammar is not correct!");

            // Zwracanie losowej zasady z podanych.
            return rulesTempArray[randomizer.Next(0, i)];
        }

        // Metoda generuj�ca losowe s�owo zgodne z zasadami
        // danej gramatyki bezkontekstowej.
        public string GenerateWord()
        {
            // Ka�de wyprowadzane s�owo zaczyna si�od symbolu startowego.
            string word = this.startingSymbol.ToString();

            // Zast�powanie wszystkich nieterminali przy
            // uzyciu zasad wyprowadzania.
            while (ContainsNonTerminals(word))
            {
                for (int i = 0; i < word.Length; i++)
                {
                    if (IsNonTerminal(word[i]))
                    {
                        int beginningLength = i;
                        int endLength = word.Length - (i + 1);

                        string beginning = word.Substring(0, beginningLength);
                        string end = word.Substring((i + 1), endLength);
                        string substitution = RandomProductionRule(word[i]).Production;
          
                        word = beginning + substitution + end;

                        break;
                    }
                }
            }

            return word;
        }
    }

    // Klasa reprezentuj�ca zasad�wyprowadzania.
    public class ProductionRule
    {
        // Symbol, z kt�rego wyprowadzamy.
        private char nonTerminal;
        // Wyprowadzane s�owo.
        private string production;

        // W�a�ciwo�� pozwalaj�ca na dost�p do symbolu �nonTerminal�.
        public char NonTerminal
        {
            get {return nonTerminal;}
        }

        // W�a�ciwo�� pozwalaj�ca na dost�p do wyprowadzanego s�owa.
        public string Production
        {
            get {return production;}
        }

        // Konstruktor tworz�cy zasad�wyprowadzania z symbolu oraz s�owa.
        public ProductionRule(char nonTerminal, string production)
        {
            this.nonTerminal = nonTerminal;
            this.production = production;
        }
    }
}