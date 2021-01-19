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
    // vector
    myuint<32>x(1);
    myuint<32>A(100000);
    myuint<32>B(590000);
    vector<bool>test = x.addVecs(A.getValueContainer(),B.getValueContainer());
    x.setValueByVec(test);
    cout<<x<<endl;

    myuint<32> A0(100);
    myuint<32> B0(25);
    test = x.subtractVecs(A0.getValueContainer(), B0.getValueContainer());
    x.setValueByVec(test);
    cout << x << endl;
    
        // x<<=2047;
        // cout<<x.toBinaryString()<<endl;
        // cout<<x.toDecimalString()<<endl;
        // myuint<128> testString0(1234567890123);

        // cout << testString0.convert_to<int>() << endl;

        // cout << testString0 << endl;
        // cout << testString0.shiftLeft(1) << endl;
        // cout << testString0.shiftLeft(2) << endl;

        // cout<< "\nmult\n";
        // unsigned long t = pow(2, 32) - 1;
        // cout << "int to mult: " << t << endl;
        // myuint<32> y(t);
        // myuint<64> y0(t);
        // // myuint<16> y(4);
        // myuint<32> x(0);
        // myuint<64> x0(0);
        // x = y * y;

        // // cout<<xy.toBinaryString()<<endl;
        // cout << "mult ints " << x.convert_to<u_int64_t>() << endl;

        // x0 = y0 * y0;
        // cout << "mult longs " << x0.convert_to<u_int64_t>() << endl;

        // // cout << x.toBinaryString() << endl;
        // // cout << x.convert_to<u_int32_t>() << endl;

        // cout << "\ndiv\n";
        // myuint<16> xx(1234);
        // myuint<16> yy(2);
        // myuint<32> xxyy(0);
        // xxyy = xx / yy;
        // cout << xxyy.toBinaryString() << endl;
        // cout << xxyy.convert_to<u_int32_t>() << endl;

        // cout << "\nconv\n";
        // myuint<64> z0(0);
        // myuint<1024> z1(0);
        // z0 = 4800;
        // z0.shiftLeftThis(31);
        // unsigned long long i1 = z0.convert_to<unsigned long long>();
        // cout << "converted to other: " << i1 << endl;

        // cout << "\n\n";
        // myuint<32> c0(68);
        // myuint<32> c1 = myuint<32>(c0);

        // int cc = c1.convert_to<int>();
        // cout << "copied " << cc << "\n";

        // cc = c0.convert_to<int>();
        // cout << "prev " << c0.convert_to<int>() << "\n";

        // myuint<32> c2 = move(c0);
        // cc = c2.convert_to<int>();
        // cout << "moved " << cc << "\n";

        // cc = c0.convert_to<int>();
        // cout << "prev " << c0.convert_to<int>() << "\n";

        return 0;
}
