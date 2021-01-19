#include "myuint.h"
#include <cassert>

static void runTest(){
    cout<<"Starting Testing\n";
    myuint<32>A (20);
    myuint<32> A1(20);

    myuint<32>B (10);    

    int x = A.convert_to<int>();
    int y = B.convert_to<int>();

    assert(A==x && "Equality check with integer failed");
    assert(A1==A && "Equality check with other Big Int failed");
    assert(B!=x && "Not equal check with integer failed");
    assert(A!=B && "Not equal check with other Big Int failed");

    assert(A + B  == x+y && "Addition with other Big int failed");
    assert(A + 10 == x + 10 && "Addition with int failed");

    assert(A - B == x-y && "Subtraction with other Big int failed");    
    assert(A - 10 == x-10 && "Subtraction With int failed");

    assert(A * B == x * y && "multiplication with other Big int failed");
    assert(A * 10 == x * 10 && "multiplication with int failed");

    assert(A / B == x / y && "division with other Big int failed");
    assert(A / 10 == x / 10 && "division With int failed");

    assert(A % B == x % y && "modulus with other Big int failed");
    assert(A % 10 == x % 10 && "modulus with int failed");

    assert(A<<1 == x<<1 && "left Shift failed");
    assert(A>>1 == x>>1 && "right Shift failed");

    assert(A<<20 == x<<20 && "big left Shift failed");
    assert(A>>20 == x>>20 && "big right Shift failed");

    assert(A>x==false && "Comparison check with integer failed");
    assert(A>=x==true && "Comparison check with integer failed");
    assert(A<x==false && "Comparison check with integer failed");
    assert(A<=x==true && "Comparison check with integer failed");

    assert(A>A1==false && "Comparison check with other big int failed");
    assert(A>=A1==true && "Comparison check with other big int failed");
    assert(A<A1==false && "Comparison check with other big int failed");
    assert(A<=A1==true && "Comparison check with other big int failed");

    cout<<"\nTesting Passed!\n\n";
}