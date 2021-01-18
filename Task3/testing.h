#include "myuint.h"
#include <cassert>

static void runTest(){
    myuint<32>A (20);
    myuint<32> A1(20);

    myuint<32>B (10);    

    int x = A.convert_to<int>();
    int y = B.convert_to<int>();

    assert(A==x && "Equality check with integer failed");
    assert(A1==A && "Equality check with other Big Int failed");

    assert(A + B  == x+y && "Addition with other Big int failed");
    assert(A + 10 == x + 10 && "Addition with int failed");

    assert(A - B == x-y&& "Subtraction with other Big int failed");    
    assert(A - 10 == x-10 && "Subtraction With int failed");

    assert(A * B == x * y && "multiplication with other Big int failed");
    assert(A * 10 == x * 10 && "multiplication with int failed");

    assert(A / B == x / y && "division with other Big int failed");
    assert(A / 10 == x / 10 && "division With int failed");

    assert(A % B == x % y && "modulus with other Big int failed");
    assert(A % 10 == x % 10 && "modulus with int failed");
}