#include "myuint.h"
#include <cassert>

static void runTest()
{
    cout << "Starting Testing\n";
    myuint<32> A(20);
    myuint<32> A1(20);

    myuint<32> B(10);

    int x = A.convert_to<int>();
    int y = B.convert_to<int>();

    assert(A.toDecimalString() == "20" && "to decimal string failed");
    // assert((A * B).toDecimalString() == "200" && "to decimal string failed");
    assert(B.toDecimalString() == "10" && "to decimal string failed");

    assert(A == x && "Equality check with integer failed");        //tests both convert_to() and ==
    assert(A1 == A && "Equality check with other Big Int failed"); //tests both convert_to() and ==
    assert(B != x && "Not equal check with integer failed");       //tests both convert_to() and ==
    assert(A != B && "Not equal check with other Big Int failed"); //tests both convert_to() and ==

    assert(A + B == x + y && "Addition with other Big int failed");
    assert(A + 10 == x + 10 && "Addition with int failed");

    assert(A - B == x - y && "Subtraction with other Big int failed");
    assert(A - 10 == x - 10 && "Subtraction With int failed");

    assert(A * B == x * y && "multiplication with other Big int failed");
    assert(A * 10 == x * 10 && "multiplication with int failed");    

    assert(A / B == x / y && "division with other Big int failed");
    assert(A / 10 == x / 10 && "division With int failed");

    assert((A *= B) == (x *= y) && "multiplication= failed with other Big int");
    assert((A *= 10) == (x *= 10) && "multiplication= with int failed");
    assert((A /= B) == (x /= y) && "division= failed with other Big int");
    assert((A /= 10) == (x /= 10) && "division= with int failed");

    myuint<32>modTester(A);
    int mt = x;
    assert((modTester %= B) == (mt %= y) && "division= failed with other Big int");
    assert((modTester %= 10) == (mt %= 10) && "division= with int failed");

    assert(A % B == x % y && "modulus with other Big int failed");
    assert(A % 10 == x % 10 && "modulus with int failed");

    assert(A << 1 == x << 1 && "left Shift failed");
    assert(A >> 1 == x >> 1 && "right Shift failed");

    assert(A << 20 == x << 20 && "big left Shift failed");
    assert(A >> 20 == x >> 20 && "big right Shift failed");

    assert((A <<= 1) == (x <<= 1) && "left Shift failed");
    assert((A >>= 1) == (x >>= 1) && "right Shift failed");

    assert((A <<= 20) == (x <<= 20) && "big left Shift failed");
    assert((A >>= 20) == (x >>= 20) && "big right Shift failed");

    assert(A > x == false && "Comparison check with integer failed");
    assert(A >= x == true && "Comparison check with integer failed");
    assert(A < x == false && "Comparison check with integer failed");
    assert(A <= x == true && "Comparison check with integer failed");

    assert(A > A1 == false && "Comparison check with other big int failed");
    assert(A >= A1 == true && "Comparison check with other big int failed");
    assert(A < A1 == false && "Comparison check with other big int failed");
    assert(A <= A1 == true && "Comparison check with other big int failed");

    assert(++A == ++x && "increment check failed");
    assert(--A == --x && "decrement check failed");
    assert((A += 10) == (x += 10) && "increment (+=) check failed");
    assert((A -= 10) == (x -= 10) && "decrement (-=) check failed");

    assert((A += B) == (x += y) && "increment (+=) check failed");
    assert((A -= B) == (x -= y) && "decrement (-=) check failed");

    myuint<32> C(B);
    assert(C == B && "copy constructor failed");

    myuint<32> D = move(C);
    assert(C != B && "move constructor failed");
    assert(D == B && "move constructor failed");
}