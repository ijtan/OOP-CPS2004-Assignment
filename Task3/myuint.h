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

    vector<ll> getValues()
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

    template <ll addResultSize, ll otherSize>
    myuint<addResultSize> add(myuint<otherSize> &b)
    {
        cout << "in add method\n";
        ll aValue = getValues().back();
        ll bValue = b.getValues().back();
        if(sizeof(aValue + bValue) > addResultSize){
            cout<<"size of value > size of return!\n";
        }
        return aValue + bValue;
    }

    template <ll subResultSize, ll otherSize>
    myuint<subResultSize> sub(myuint<otherSize> &b)
    {
        cout << "in sub method\n";
        ll aValue = getValues().back();
        ll bValue = b.getValues().back();
        return aValue - bValue;
    }
};