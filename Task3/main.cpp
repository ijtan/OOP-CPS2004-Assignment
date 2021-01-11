#include <iostream>
#include <vector>
#include "myuint.h"
#define ll unsigned long long
// long long holds 8 bytes
using namespace std;

int main(){

    cout<<"\nmult\n";
    myuint<64> x(0);
    myuint<64> y(2);
    x.setValueByBinaryString("11010010");
    cout << x.toBinaryString() << endl;
    x = x.realMultiplyByOther(y);
    cout<<x.toBinaryString()<<endl;



    myuint<64>z0(0);
    myuint<1024>z1(0);
    z0=4800;
    z0.shiftLeft(31);
    unsigned long long i1 = z0.convert_to<unsigned long long>();
    // string s1 = z0.convert_to<string>();
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


