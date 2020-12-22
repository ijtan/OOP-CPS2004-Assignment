#include <iostream>
#include <vector>
#define ll long long
using namespace std;

template <ll size>
class myuint
{
private:
    // vector<ll> values = vector<ll>((size / sizeof(ll) >=1)? size / sizeof(ll) : 1); // check if size < 1 //TODO as we should hold minimum of 1 bit!;
    // if (!IsPowerOfTwo(num))
    // {
    //     cerr << "size is not powerOfTwo!\n";
    //     return;
    // }
    vector<bool> values = vector<bool>(size); //TODO compile time error if not power of two
public:
    template <ll otherSize>
    myuint<size> operator+(myuint<otherSize> &other)
    {
        cout << "+ operator called!\n";
        return add<size>(other);
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
    // vector<bool> convertToValues(ll num)
    // {
    // }

    int getIntFromValue()
    {
        if (size > 32)
        {
            cerr << "cannot convert number larger than 32 bits to int!!\n";
            return -1;
        }
        int j = 1;
        int x = 0;
        for (int i = values.size() - 1; i >= 0; i--)
        {
            if (values[i])
                x += j;
            j *= 2;
        }
        return x;
    }

    vector<bool> getValues()
    {
        return values;
    }

    int getSize()
    {
        // return sizeof(values)/sizeof(values[0]);
        return values.size() * sizeof(values[0]);
    }

    void setValueByLong(ll num)
    {
        values.clear();
        int a[size] = {0};
        for (int i = 0; num > 0; i++)
        {
            a[i] = num % 2;
            num = num / 2;
        }

        for (int i = size-1; i >= 0; i--)
        {
            values.push_back((bool)a[i]);
        }
    }

    bool IsPowerOfTwo(ll x)
    {
        return (x != 0) && ((x & (x - 1)) == 0);
    }

    myuint(ll value)
    {
        cout << "init with size: " << size << " and value: " << value << '\n';
        setValueByLong(value);
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
        if (sizeof(aValue + bValue) > addResultSize)
        {
            cout << "size of value > size of return!\n";
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