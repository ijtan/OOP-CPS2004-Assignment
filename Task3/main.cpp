#include <iostream>
#include <vector>
#include "myuint.h"
#define ll unsigned long long
// long long holds 8 bytes
using namespace std;

int main(){

    cout<<"\nmult\n";
    myuint<16> x(65535);
    myuint<16> y(65535);
    myuint<32> xy(0);
    cout << x.toBinaryString() << endl;
    xy = x*y;
    cout<<xy.toBinaryString()<<endl;
    cout<<xy.convert_to<u_int32_t>()<<endl;

    cout << "\ndiv\n";
    myuint<16> xx(1234);
    myuint<16> yy(2);
    myuint<32> xxyy(0);
    xxyy = xx/yy;
    cout << xxyy.toBinaryString() << endl;
    cout << xxyy.convert_to<u_int32_t>() << endl;

    cout << "\nconv\n";
    myuint<64>z0(0);
    myuint<1024>z1(0);
    z0=4800;
    z0.shiftLeftThis(31);
    unsigned long long i1 = z0.convert_to<unsigned long long>();
    cout<<"converted to other: "<<i1<<endl;
    

    cout<<"\n\n";
    myuint<32>c0(68);
    myuint<32>c1 = myuint<32>(c0);

    

    int cc = c1.convert_to<int>();
    cout<<"copied "<<cc<<"\n";

    cc = c0.convert_to<int>();
    cout << "prev " << c0.convert_to<int>() << "\n";

    myuint<32> c2 = move(c0);
    cc = c2.convert_to<int>();
    cout << "moved " << cc << "\n";

    cc = c0.convert_to<int>();
    cout << "prev " << c0.convert_to<int>() << "\n";

    return 0;
}


