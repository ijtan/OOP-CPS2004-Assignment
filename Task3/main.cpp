#include <iostream>
#include <vector>
#define ll long long
// long long holds 8 bytes
using namespace std;

template <ll size>
class myuint
{
private:
    vector<ll> values = vector<ll>((size / 8) / sizeof(ll)); // elements
public:
    template <ll otherSize>
    myuint<size + otherSize> operator+(myuint<otherSize> &other)
    {
        cout << "+ operator called!" << endl;
        return add<size + otherSize>(other);
    };



    template <ll otherSize>
    myuint<(size > otherSize ? size : otherSize)> operator-(myuint<otherSize> &other)
    {
        cout << "- operator called!" << endl;
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
        return size;
    }

    myuint(ll value)
    {
        cout << "init with size: " << size << " and value: " << value << endl;
        // this.assign(value);
    }

    myuint()
    {
        cout << "init with size: " << size << " and no value" << endl;
        int currentSize = size;
    }

    template <ll addResultSize, ll otherSize>
    myuint<addResultSize> add(myuint<otherSize> &b)
    {
        cout << "in add method" << endl;
        ll aValue = getValues().back();
        ll bValue = b.getValues().back();
        return aValue + bValue;
    }

    template <ll subResultSize, ll otherSize>
    myuint<subResultSize> sub(myuint<otherSize> &b)
    {
        cout << "in sub method" << endl;
        ll aValue = getValues().back();
        ll bValue = b.getValues().back();
        return aValue - bValue;
    }
};


int main()
{
    // myuint(4)::myuint operator+(const myuint &other) const { return add(this, other); };
    cout << "size of long in bytes is: " << sizeof(ll) << endl;
    cout << "size of long in bits is: " << sizeof(ll) * 8 << endl;
    myuint<64> i;
    myuint<2048> j;
    myuint x = i + j;
    myuint y = i - j;
    cout << "x size is: " << x.getSize() << endl;
    cout << "y size is: " << y.getSize() << endl;

    return 0;
}
