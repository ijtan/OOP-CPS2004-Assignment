#include <iostream>
#include <vector>
// #include "myuint.h"
#include "testing.h"
#define ll unsigned long long
// long long holds 8 bytes
using namespace std;

int main()
{
    runTest();

    myuint<32> A1(2000);
    myuint<32> B1(6);
    cout << A1*B1 << endl;
    return 0;
}
