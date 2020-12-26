#include <iostream>
#include <limits>
#include <stdexcept>
#include <vector>
#include <string>
#include <limits.h>

#ifndef ll
#define ll unsigned long long
#endif
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

    // bool isPowerOfTwo(ll x)
    //     return ;

    static_assert(((size != 0) && ((size & (size - 1)) == 0)), "Size can only be power of two");
    vector<bool> values = vector<bool>(size); //TODO compile time error if not power of two
public:
    template <ll otherSize>
    myuint<size> operator+(myuint<otherSize> &other)
    {
        cout << "+ operator called!\n";
        return add<size>(other);
    };

    myuint<size> operator+(int other)
    {
        cout << "+ operator called woth long!\n";

        string bin = longToBinaryString(other);
        myuint<size> tmp;

        tmp.setValueByBinaryString(addBinaryStrings(this->toBinaryString(), bin));
        return tmp;
    };

    template <ll otherSize>
    void operator+=(myuint<otherSize> &other)
    {
        this->values = add<size>(other).getValueContainer();
    };

    void operator+=(int other)
    {
        string bin = longToBinaryString(other);
        cout<<"int converted to bin: "<<bin<<endl;
        this->setValueByBinaryString(addBinaryStrings(toBinaryString(), bin));
    };

    template <ll otherSize>
    myuint<(size > otherSize ? size : otherSize)> operator-(myuint<otherSize> &other)
    {
        cout << "- operator called!\n";
        return subtract<(size > otherSize ? size : otherSize)>(other);
    };

    template <ll otherSize>
    myuint<otherSize> operator=(const myuint<otherSize> &other)
    {
        this->values = other.getValueContainer();
    }

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

    u_int getIntFromValue()
    {
        cout << "getting int, myuint size is: " << getSize() / 8 << " and max size is: " << sizeof(u_int) * 8 << "\n";
        if (getSize() / 8 > sizeof(u_int) * 8)
        {
            cerr << "cannot convert number larger than 32 bits to int!!\n";
            return 0;
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
    vector<bool> getValueContainer()
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

        for (int i = size - 1; i >= 0; i--)
        {
            values.push_back((bool)a[i]);
        }
    }

    string longToBinaryString(ll num)
    {
        int a[size] = {0};
        string ret = "";
        for (int i = 0; num > 0; i++)
        {
            a[i] = num % 2;
            num = num / 2;
        }

        for (int i = size - 1; i >= 0; i--)
            ret += to_string(a[i]);
        cout << "num was converted to: " << ret << endl;
        return ret;
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

    template <ll otherSize>
    operator myuint<otherSize>()
    {
        cout << "old size: " << values.size() << "\n";
            // myuint<otherSize> tmp;
            // vector<bool> vec = this->getValueContainer();
            if (otherSize > size)
        {
            cout << "inserting values: " << otherSize - size<<"\n";
            values.insert(values.begin(), otherSize - size, false);
            cout << "newSize: " << values.size()<<"\n";
        }
        else
        {
            cout << "removing values: " << size - otherSize << "\n";
            values.erase(values.end() - (size - otherSize), values.end());
        }
        // tmp.setValueByVec(vec);
        // return this;
    }

    // string getValueAsString()
    // {

    //     myuint<size> j(1);
    //     ll x = 0;
    //     ll xCounter = 0;
    //     for (int i = values.size() - 1; i >= 0; i--)
    //     {
    //         if (sizeof(x) >= INT_MAX)
    //         {
    //             xCounter++;
    //             x -= INT_MAX;
    //         }
    //         if (values[i])
    //             x += j;
    //         j.setValueByBinaryString(addBinaryStrings(j.toBinaryString(), j.toBinaryString()));
    //     }
    //     string returnVal = "0";
    //     for (; xCounter > 0; xCounter--)
    //     {
    //         addBinaryStrings(returnVal, to_string(INT_MAX));
    //     }
    //     addBinaryStrings(returnVal, to_string(x));
    //     return returnVal;
    // }

    string addBinaryStrings(string aStr, string bStr)
    {
        string added = "";
        int x = 0;
        int i = aStr.size() - 1;
        int j = bStr.size() - 1;

        cout<<"adding binary string with sizes: "<<i<<" and "<<j<<"\n";
        cout<<"a: "<<aStr<<endl;
        cout<<"b: "<<bStr<<endl;

        while (x == 1 or i >= 0 or j >= 0)
        {
            if (i >= 0)
                x += aStr[i] - '0';
            else
                x += 0;

            if (j >= 0)
                x += bStr[i] - '0';
            else
                x += 0;

            added = char(x % 2 + '0') + added;

            x /= 2;
            i--;
            j--;
        }
        return added;
    }

    template <ll addResultSize, ll otherSize>
    myuint<addResultSize> add(myuint<otherSize> &b)
    {
        cout << "in add method\n";
        vector<bool> aValues = getValueContainer();
        vector<bool> bValues = b.getValueContainer();

        string aStr = toBinaryString();
        string bStr = b.toBinaryString();

        myuint<addResultSize> ret;
        ret.setValueByBinaryString(addBinaryStrings(aStr, bStr));
        return ret;
    }

    void setValueByBinaryString(string s)
    {
        values.clear();

        cout<<"assigning value using string: "<<s<<endl;
        for (char c : s)
        {
            if (c == '0')
            {
                this->values.push_back(false);
            }
            else if (c == '1')
            {
                this->values.push_back(true);
            }
            else
            {
                // cerr << "unkown character found! defaulting to 0\n";
                // this->values.push_back(false);
                cerr << "unkown character found! ["<<c<<"] aborting...\n";
                return;
            }
        }
        cout<<"done\n";
    }

    void setValueByVec(vector<bool> vals)
    {
        this->values = vals;
    }

    template <ll shiftAmount>
    void shiftRight()
    { //TODO change to myuint return

        // this->setSize((getSize()/8)+shiftAmount);

        values.insert(values.begin(), shiftAmount, false);

        values.erase(values.end() - shiftAmount, values.end());

        // return this;
    }

    template <ll shiftAmount>
    void shiftLeft()
    { //TODO change to myuint return
        values.insert(values.end(), shiftAmount, false);

        values.erase(values.begin(), values.begin() + shiftAmount);

        // return this;
    }

    string toBinaryString()
    {
        string bin;
        cout << "value size: " << values.size();
        cout << "starting conversion to binary String\n";
        
        int i = 0;
        for (bool b : values)
        {
            i++;
            // cout<<i<<" ";

            if (b)
                bin += '1';
            else
                bin += '0';
        }
        cout << "done2\n";
        return bin;
    }

    template <ll subResultSize, ll otherSize>
    myuint<subResultSize> subtract(myuint<otherSize> &b)
    {
        cout << "in sub method\n";
        ll aValue = getValueContainer().back();
        ll bValue = b.getValueContainer().back();
        return aValue - bValue;
    }
};