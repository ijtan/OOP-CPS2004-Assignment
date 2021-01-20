#include <iostream>
#include <vector>
// #include "myuint.h"
#include "testing.h"
#define ll unsigned long long
// long long holds 8 bytes
using namespace std;

int main()
{
    int max8 = pow(2, 8) - 1;

    myuint<8> A(max8);
    myuint<16> B(max8);

    uint16_t i16 = max8;
    uint8_t i8 = max8;
    cout << (A * B) << endl;
    cout << B * A << endl;
    cout << i8 * i16 << endl;
    cout << i16 * i8 << endl;

    // printf("the size of i8 is %u\n", (unsigned int)sizeof i8 * 8);
    // printf("the size of i16 is %u\n", (unsigned int)sizeof i16 * 8);
    // printf("the size of i16*i8 is %u\n", (unsigned int) (sizeof ((uint16_t)i16 * i8)) * 8);

    runTest();
    return 0;
}
