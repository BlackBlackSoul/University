// Programowanie Obiektowe
// Jakub Grobelny 300481
// Zadanie 2 lista 1

// Program wykonujący podstawowe działania na
// podanych liczbach wymiernych.

// Język: C
// Kompilator: gcc

#include <stdio.h>

#include "fractions.h"

void draw(char* operation, Fraction* first, Fraction* second, Fraction* result)
{
    print_fraction(first);
    printf("%s", operation);
    print_fraction(second);
    printf(" = ");
    print_fraction(result);
    printf(" = ");
    print_fraction_newline_as_double(result);
}

int main()
{

    double first_double;
    printf("First fraction (double): ");
    scanf("%lf", &first_double);
    Fraction* first = create_fraction_from_double(first_double);
    
    int nominator, denominator;
    printf("Second fraction (nominator and denominator): ");
    scanf("%d %d", &nominator, &denominator);
    Fraction* second = create_fraction(nominator, denominator);

    if (denominator == 0)
    {
	    printf("You can't divide by zero!\n");
	    exit(-1);
    }

    Fraction* addition = add_fractions(first, second);
    Fraction* substraction = substract_fractions(first, second);
    Fraction* multiplication = multiply_fractions(first, second);
    Fraction* division = divide_fractions(first, second);

    draw(" + ", first, second, addition);
    draw(" - ", first, second, substraction);
    draw(" * ", first, second, multiplication);
    draw(" / ", first, second, division);

    // Destroying allocated objects
    destroy_fraction(first);
    destroy_fraction(second);
    destroy_fraction(addition);
    destroy_fraction(multiplication);
    destroy_fraction(division);
    destroy_fraction(substraction);

    return 0;
}
