#include <iostream>
#include <vector>
#include "myuint.h"
#define ll long long
// long long holds 8 bytes
using namespace std;




int main()
{
    cout << "size of long in bytes is: " << sizeof(ll) << endl;
    cout << "size of long in bits is: " << sizeof(ll) * 8 /*(<-- size of bit)*/ << endl;
    myuint<32> i;
    cout << "i size is: " << i.getSize() << endl;

    myuint<2048> j;
    cout << "j size is: " << j.getSize() << endl;

    myuint x = i + j;
    cout << "x size is: " << x.getSize() << endl;

    myuint y = i - j;    
    cout << "y size is: " << y.getSize() << endl;


    myuint<32> k(5);
    vector<bool> vals = k.getValues(); 
    for(bool b : vals){
        if(b)
            cout<<'1';
        else
            cout<<'0';
    }
    cout<<'\n';
    cout<<"value converted to int is: "<<k.getIntFromValue()<<'\n';
    return 0;
}
