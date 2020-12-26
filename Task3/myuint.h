#include <iostream>
#include <limits>
#include <stdexcept>
#include <vector>
#define ll unsigned long long
using namespace std;

template <ll size>
class myuint
{
private:
    vector<ll> values = vector<ll>((size / sizeof(ll) >=1)? size / sizeof(ll) : 1); // check if size < 1 //TODO as we should hold minimum of 1 bit!;
public:
    template <ll otherSize>
    myuint<size + otherSize> operator+(myuint<otherSize> &other)
    {
        cout << "+ operator called!\n";
        return add<size + otherSize>(other);
    };



    template <ll otherSize>
    myuint<(size > otherSize ? size : otherSize)> operator-(myuint<otherSize> &other)
    {
        cout << "- operator called!\n";
        return sub<(size > otherSize ? size : otherSize)>(other);
    };

    template <ll otherSize>
    myuint operator=(const myuint &p) const;

    // myuint operator&(const myuint &p) const;
    // myuint operator|(const myuint &p) const;
    // myuint operator^(const myuint &p) const;
    // myuint operator<<(const myuint &p) const;
    // myuint operator>>(const myuint &p) const;

    // bool operator~(const myuint &p) const;
    // bool operator/(const myuint &p) const;
    // bool operator%(const myuint &p) const;
    // bool operator*(const myuint &p) const;

    // bool operator<(const myuint &p) const;
    // bool operator>(const myuint &p) const;

    vector<ll> getValueContainer()
    {
        return values;
    }

    int getSize()
    {
        // return sizeof(values)/sizeof(values[0]);
        return values.size()*sizeof(values[0]);
    }

    myuint(ll value)
    {
        cout << "init with size: " << size << " and value: " << value <<'\n';
        // this.assign(value);
    }

    myuint()
    {
        cout << "init with size: " << size << " and no value\n";
        int currentSize = size;
    }

    string getValueAsString(){
        
    }

    myuint<size> setValue(ll num)
    {
        cout << "in assign method\n";
        

        if(num%2==0)
            value[size-1] == 0;
        else{
            value[size-1] == 1;
            num-=1;
        }
        
        int current = 2;
        for(int i = size-2;i>=0;i--){
            if(num-current >= 0){
                num-=current;
                value[i] = true;
            }else{
                value[i] = false;
            }

            if(num==0){
                cout<<"number represented!\n";
                break;
            }            
            current*=2;
        }
        if(num!=0){
            cerr<<"Num is still not zero -> not fully represented!!\n";
        }
        return this;
    }

    template <ll addResultSize, ll otherSize>
    myuint<addResultSize> add(myuint<otherSize> &b)
    {
        cout << "in add method\n";
        ll aValue = getValueContainer().back();
        ll bValue = b.getValueContainer().back();
        if(sizeof(aValue + bValue) > addResultSize){
            cout<<"size of value > size of return!\n";
        }
        return aValue + bValue;
    }

    template <ll subResultSize, ll otherSize>
    myuint<subResultSize> sub(myuint<otherSize> &b)
    {
        cout << "in sub method\n";
        ll aValue = getValueContainer().back();
        ll bValue = b.getValueContainer().back();
        return aValue - bValue;
    }
};