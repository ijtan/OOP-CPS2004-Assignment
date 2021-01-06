#include <iostream>
#include <vector>
#include "myuint.h"
#define ll unsigned long long
// long long holds 8 bytes
using namespace std;

int main(){



    myuint<32> f(20);
    

    cout << "\nmult test\n";

    myuint<2048> q(1);
    // myuint<2048> r(1000000);
    cout<<q.toBinaryString()<<endl;

    // cout << q.multiplyBinaryStringByScalar(q.toBinaryString(), 1000000) << endl; TODO
    // myuint<64> k(4);

    myuint<16> j(20);
    myuint<64> k(4);
    cout << "\ndiiff size add test\n";
    cout<< (j + k).toBinaryString()<<"\n";

        cout
         << "\nunary\n";
    cout << "b4\n"
         << f.toBinaryString() << endl;
    // f = ++f;
    // f +=t;
    

    cout << (--f).toBinaryString() << endl;
    cout << (--f).toBinaryString() << endl;


    cout<<"\ndiv\n";
    myuint<32> d(1986);
    
    myuint<32> t(9);
    // cout<<"b4 d\n"<<d.toBinaryString()<<endl;
    // cout<<"b4 t\n"<<d.toBinaryString()<<endl;
    // f = f/t;
    cout << d.getIntFromValue() / t.getIntFromValue() << " / ";
    cout << d.getIntFromValue() % t.getIntFromValue() << endl;

    vector<myuint<32>> dr = d.longDivide(t);

    myuint<32> r(0);
    d = dr[0];
    r = dr[1];
    // string ret = d.divideByScalar(3);
    // cout << f.toBinaryString() << endl;
    cout << d.toBinaryString() << " / ";
    cout << r.toBinaryString() << endl;

    cout << d.getIntFromValue() << " / ";
    cout << r.getIntFromValue() << endl;

    return 0;
}
