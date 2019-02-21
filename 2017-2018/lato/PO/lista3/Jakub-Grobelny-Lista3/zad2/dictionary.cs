// Jakub Grobelny
// Pracownia PO, czwartek, s. 108
// L3, z2, S�ownik
// Modu� z implementacj� typu s�ownikowego
// dictionary.cs
// 2018-03-14

namespace Dictionaries
{
    // Klasa Dictionary<K, V> reprezentuj�ca s�ownik, czyli
    // tablic�, kt�ra mo�e by� indeksowana warto�ciami
    // dowolnego typu.
    public class Dictionary<K, V> where K : class
    {
        // Prywatne pola klasy, oznaczajace kolejno
        // pojemno�� tablic, ilo�� element�w oraz tablice
        // kluczy i warto�ci.
        private int capacity;
        private int size;
        private K[] keys;
        private V[] values;

        // Operator indeksu
        public V this[K key]
        {
            get
            {
                return GetValue(key);
            }
            set
            {
                SetValue(key, value);
            }
        }   
        
        // Metoda szukaj�ca warto�ci znajduj�cej si�
        // pod indeksem 'key'.
        private V GetValue(K key)
        {
            for (int i = 0; i < size; i++)
            {
                if (keys[i].Equals(key))
                    return values[i];
            }

            throw new System.IndexOutOfRangeException();
        }

        // Metoda ustawiaj�ca warto�� pod indeksem 'key' 
        // na 'value'.
        private void SetValue(K key, V value)
        {
            for (int i = 0; i < size; i++)
            {
                if (keys[i].Equals(key))
                {
                    values[i] = value;
                    return;
                }
            }

            AddElement(key, value);
        }

        // Metoda dodaj�ca nowy element do s�ownika.
        private void AddElement(K key, V value)
        {
            // Je�eli ilo�� element�w przekroczy�aby pojemno��
            // tablic, to nale�y je powi�kszy� przed dodaniem
            // nowego elementu.
            if (size >= capacity)
            {
                capacity *= 2;
                K[] new_keys = new K[capacity];
                V[] new_values = new V[capacity];

                for (int i = 0; i < size; i++)
                {
                    new_keys[i] = keys[i];
                    new_values[i] = values[i];
                }

                values = new_values;
                keys = new_keys;
            }

            // Dodawanie elementu.
            keys[size] = key;
            values[size] = value;

            size++;
        }

        // Metoda zwracaj�ca i-ty klucz s�ownika
        public K GetKey(int i)
        {
            return keys[i];
        }

        // Metoda usuwaj�ca element o kluczu 'key'
        public void Remove(K key)
        {
            for (int index = 0; index < size; index++)
            {
                if (keys[index].Equals(key))
                {
                    for (int i = index; i < size - 1; i++)
                    {

                        keys[i] = keys[i + 1];
                        values[i] = values[i + 1];
                    }

                    size--;
                    return;
                }
            }
        }

        // Konstruktor tworzacy s�ownik o zadanej pojemno�ci
        // (domy�lnie 1)
        public Dictionary(int initialCapacity = 1)
        {
            size = 0;
            capacity = initialCapacity;
            keys = new K[capacity];
            values = new V[capacity];
        }

        // Metoda zwracaj�ca ilo�� element�w w s�owniku
        public int GetSize()
        {
            return size;
        }
    }
}