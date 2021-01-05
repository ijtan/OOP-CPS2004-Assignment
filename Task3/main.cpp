#include <iostream>
#include <vector>
#include "myuint.h"
#define ll unsigned long long
// long long holds 8 bytes
using namespace std;

int main()
{
    // cout << "size of long in bytes is: " << sizeof(ll) << endl;
    // cout << "size of long in bits is: " << sizeof(ll) * 8 /*(<-- size of bit)*/ << endl;

    // cout << "size of int in bytes is: " << sizeof(int) << endl;
    // cout << "size of int in bits is: " << sizeof(int) * 8 /*(<-- size of bit)*/ << endl;
    // myuint<32> i;
    // cout << "i size is: " << i.getSize() << endl;

    // myuint<2048> j;
    // cout << "j size is: " << j.getSize() << endl;

    // myuint x = i + j;
    // cout << "x size is: " << x.getSize() << endl;

    // myuint y = i - j;
    // cout << "y size is: " << y.getSize() << endl;

    // myuint<32> k(5);
    // vector<bool> vals = k.getValueContainer();
    // for (bool b : vals)
    // {
    //     if (b)
    //         cout << '1';
    //     else
    //         cout << '0';
    // }
    // cout << '\n';
    // cout << "value converted to int is: " << k.getIntFromValue() << '\n';

    // myuint<32> L(15);

    // myuint<32> f = L + k;

    // myuint<32> f(10);
    // f += 6;
    // cout<<f.toBinaryString()<<"\n";

    // cout<< "\n\nshifting tests\n";
    // myuint<16> t (8);
    // cout << "not shifted: " << t.toBinaryString() << "\n";
    // cout << "not shifted int: " << t.getIntFromValue() << "\n";
    // // myuint<8> r1(1); //TODO not adding well with different sizes !! maybe add 0s ?
    // // t += r1;
    // t.shiftLeft<8>();

    //TODO add different sizes!!
    myuint<32> f(10);
    myuint<32> t(2);

    cout<<f.subtractBinaryStrings("1000", "0100")<<"\n";

    cout<<"\n\n"<<f.multiplyBinaryStringByScalar(f.toBinaryString(),100)<<endl;

    cout << "\nunary\n";
    cout << "b4\n"
         << f.toBinaryString() << endl;
    // f = ++f;
    
    cout << f.toBinaryString() << endl;

    cout<<"\ndiv\n";
    cout<<"b4\n"<<f.toBinaryString()<<endl;
    // f = f/t;
    cout<<f.toBinaryString()<<endl;

        // cout << "binary shifted left: " << t.toBinaryString() << "\n";
        // cout << "shifted int: " << t.getIntFromValue() << "\n";

        // cout << "\n\ncomparison\n";

        // myuint<32> one(1);
        // myuint<16> two(-100);
        // cout << t.isLarger(one.getValueContainer(), two.getValueContainer()) << "\n";

        // t.shiftRight<9>();
        // cout << "binary shifted right: " << t.toBinaryString() << "\n";
        // cout << "shifted int: " << t.getIntFromValue() << "\n";

        // myuint<1024> i(5);               // creates a 1024-bit unsigned int ’5’
        // i.shiftLeft<1000>();
        // i+=23;
        // myuint<2048> j = i; // shifts it by 1000 bits
        // cout << "newSize2: " << j.getSize() << "\n";
        // cout << "newSize3: " << j.getValueContainer().size() << "\n";
        // cout<<"converted: "<<j.toBinaryString()<<"\n";
        // cout<<"done3\n";
        // and adds 23
        // return j.template convert_to<int>() // returns 23

        // myuint<924> Q(20);
        // cout << "Q value as string: \n\n " << Q.getValueAsString() << endl;
        // Q.setValueByBinaryString("100000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000000000011100000000000000110000000000100111000010000");
        // cout<<"Q value as string: \n\n "<<Q.getValueAsString()<<endl;

        return 0;
}
