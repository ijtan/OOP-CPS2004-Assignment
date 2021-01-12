#include <iostream>
#include <limits>
#include <stdexcept>
#include <vector>
#include "math.h"
#include <string>
#include <limits.h>

// #ifndef ll
// #define ll unsigned long long
// #endif
using namespace std;

template <int size>
class myuint
{

private:
    static_assert(((size != 0) && ((size & (size - 1)) == 0)), "Size can only be power of two"); //compile time error if not power of two

    vector<bool> values = vector<bool>(size);

public:
    template <int otherSize>
    myuint(myuint<otherSize> other)
    {
        cout << "init with other myuint\n";
        if (other.getSize() > getSize())
        {
            cerr << "other size(" << other.getSize() << ") is too big to assign to this(" << getSize() << ") one!\n";
        }
        int diff = other.getSize() - size;
        this->values = other.getValueContainer();
        values.insert(values.begin(), diff, false);
    }

    template <class T>
    myuint(T value)
    {
        // cout << "init with size: " << size << " and value: " << value << '\n';
        // cout<<"value is :"<<type(T)<<endl;
        static_assert(is_integral<T>::value, "Can only initalize by integral types!");
        if (value >= pow(2, size))
        {
            cerr << "could not initalize by " << value << " as size is too small; max size is: " << pow(2, size) - 1 << "\n";
            return;
        }

        setValueByOtherType(value);
    }

    template <int otherSize>
    myuint<size> operator+(myuint<otherSize> &other)
    {
        cout << "+ operator called!\n";
        return addMyuints<size>(other);
    };

    template <class T>
    myuint<size> operator+(T other)
    {
        static_assert(is_integral<T>::value, "Can only add by integral types!");

        string bin = toBinaryString<T>(other);
        myuint<size> tmp(0);

        tmp.setValueByBinaryString(addBinaryStrings(this->toBinaryString(), bin));
        return tmp;
    };

    template <int otherSize>
    myuint<size> operator+=(myuint<otherSize> &other)
    {

        string bin = other.toBinaryString();
        setValueByBinaryString(addBinaryStrings(this->toBinaryString(), bin));
        return this[0];
    };

    template <class T>
    void operator+=(T other)
    {
        static_assert(is_integral<T>::value, "Can only add by integral types!");
        string bin = toBinaryString<T>(other);
        this->setValueByBinaryString(addBinaryStrings(toBinaryString(), bin));
    };

    template <int otherSize>
    myuint<size> operator-(myuint<otherSize> other)
    {
        cout << "- operator called!\n";
        return subtract<size>(other);
    };

    template <class T>
    myuint<size> operator-(T &other)
    {
        static_assert(is_integral<T>::value, "Can only subtract by integral types!");
        cout << "- operator called!\n";
        return subtract<T>(other);
    }

    template <int otherSize>
    myuint<size> operator-=(myuint<otherSize> other)
    {
        return *this = subtract<otherSize>(other);
    };

    template <class T>
    myuint<size> operator-=(const T other)
    {
        static_assert(is_integral<T>::value, "Can only subtract by integral types!");
        return this - other;
    }

    myuint<size> operator--()
    {
        setValueByBinaryString(subtract(1).toBinaryString());
        return this[0];
    }

    myuint<size> operator++()
    {
        // string bin = toBinaryString();
        setValueByBinaryString(addBinaryStrings(this->toBinaryString(), "01"));
        return this[0];
    }

    template <int otherSize>
    myuint<size> operator=(myuint<otherSize> other)
    {
        if (other.getSize() > getSize())
        {
            cerr << "other size(" << other.getSize() << ") is too big to assign to this(" << getSize() << ") one!\n";
            return this[0];
        }
        int diff = size - other.getSize();
        this->values = other.getValueContainer();
        values.insert(values.begin(), diff, false);
        return this[0];
    }

    template <int otherSize>
    bool operator==(myuint<otherSize> other)
    {
        return whichIsLarger(values, other.getValueContainer()) == 0;
    }

    template <int otherSize>
    bool operator>(myuint<otherSize> other)
    {
        return whichIsLarger(values, other.getValueContainer()) == 1;
    }

    template <int otherSize>
    bool operator<(myuint<otherSize> other)
    {
        return whichIsLarger(values, other.getValueContainer()) == 2;
    }

    template <int otherSize>
    bool operator>=(myuint<otherSize> other)
    {
        int val = whichIsLarger(values, other.getValueContainer());
        return val == 1 || val == 0;
    }

    template <int otherSize>
    bool operator<=(myuint<otherSize> other)
    {
        int val = whichIsLarger(values, other.getValueContainer());
        return val == 2 || val == 0;
    }

    template <class T>
    bool operator==(T other)
    {
        static_assert(is_integral<T>::value, "Can only compare to integral types!");
        string otherStr = toBinaryString(other);
        vector<bool> otherVec = stringToBoolVec(otherStr);
        return whichIsLarger(values, otherVec) == 0;
    }

    template <class T>
    bool operator>(T other)
    {
        static_assert(is_integral<T>::value, "Can only compare to integral types!");
        string otherStr = toBinaryString(other);
        vector<bool> otherVec = stringToBoolVec(otherStr);
        return whichIsLarger(values, otherVec) == 1;
    }

    template <class T>
    bool operator<(T other)
    {
        static_assert(is_integral<T>::value, "Can only compare to integral types!");
        string otherStr = toBinaryString(other);
        vector<bool> otherVec = stringToBoolVec(otherStr);
        return whichIsLarger(values, otherVec) == 2;
    }

    template <class T>
    bool operator>=(T other)
    {
        static_assert(is_integral<T>::value, "Can only compare to integral types!");
        string otherStr = toBinaryString(other);
        vector<bool> otherVec = stringToBoolVec(otherStr);
        int val = whichIsLarger(values, otherVec);
        return val == 1 || val == 1;
    }

    template <class T>
    bool operator<=(T other)
    {
        static_assert(is_integral<T>::value, "Can only compare to integral types!");
        string otherStr = toBinaryString(other);
        vector<bool> otherVec = stringToBoolVec(otherStr);
        int val = whichIsLarger(values, otherVec) == 2;
        return val == 1 || val == 1;
    }

    template <int otherSize>
    myuint<size> operator*(myuint<otherSize> other)
    {
        return checkOverflows(multiplyMyuints(other));
    }

    template <int otherSize>
    myuint<size> checkOverflows(myuint<otherSize> tmp)
    {

        auto tmpCont = tmp.getValueContainer();
        for (int i = 0; i < tmp.getSize() - size; i++)
        {
            if (tmpCont[i])
            {
                cerr << "overflow detected!" << endl;
                // cerr << "overflow detected! Aborting!" << endl;
                // throw "overflow detected!";
                break;
            }
        }
        tmpCont.erase(tmpCont.begin(), tmpCont.begin() + tmp.getSize() - size);
        myuint<size> ret(0);

        ret.setValueByVec(tmpCont);
        return ret;
    }
    // explicit operator int *() const { return nullptr; }

    template <class T>
    myuint<size> operator*(T other)
    {
        static_assert(is_integral<T>::value, "Can only multiply by integral types!");
        myuint<size> tmp(other);
        return multiplyMyuints(tmp);
    }

    template <int otherSize>
    myuint<size> operator*=(myuint<otherSize> other)
    {
        // static_assert(is_integral<T>::value, "Can only divide by integral types!");
        return *this = checkOverflows(multiplyMyuints(other));
    }

    template <class T>
    myuint<size> operator*=(T other)
    {
        static_assert(is_integral<T>::value, "Can only multiply by integral types!");
        myuint<size> tmp(other);
        return *this = checkOverflows(multiplyMyuints(tmp));
    }

    template <int otherSize>
    myuint<size> operator/(myuint<otherSize> other)
    {
        // static_assert(is_integral<T>::value, "Can only divide by integral types!");
        return longDivide(other)[0];
    }

    template <class T>
    myuint<size> operator/(T other)
    {
        static_assert(is_integral<T>::value, "Can only divide by integral types!");
        return longDivide(other)[0];
    }

    template <class T>
    myuint<size> operator%(T other)
    {
        static_assert(is_integral<T>::value, "Can only divide by integral types!");
        return longDivide(other)[1];
    }

    template <int otherSize>
    myuint<size> operator%(myuint<otherSize> other)
    {
        // static_assert(is_integral<T>::value, "Can only divide by integral types!");
        return longDivide(other)[1];
    }

    template <class T>
    myuint<size> operator/=(T other)
    {
        static_assert(is_integral<T>::value, "Can only divide by integral types!");
        return *this = longDivide(other)[0];
    }

    template <class T>
    myuint<size> operator%=(T other)
    {
        static_assert(is_integral<T>::value, "Can only divide by integral types!");
        return *this = longDivide(other)[1];
    }

    template <int otherSize>
    myuint<size> operator/=(myuint<otherSize> other)
    {
        // static_assert(is_integral<T>::value, "Can only divide by integral types!");
        return *this = longDivide(other)[0];
    }

    template <int otherSize>
    myuint<size> operator%=(myuint<otherSize> other)
    {
        // static_assert(is_integral<T>::value, "Can only divide by integral types!");
        return *this = longDivide(other)[1];
    }

    vector<bool> getValueContainer()
    {
        return values;
    }

    int getSize()
    {
        // return sizeof(values)/sizeof(values[0]);
        return size;
    }
    int getContainerSize()
    {
        // return sizeof(values)/sizeof(values[0]);
        return values.size();
    }

    template <class T>
    myuint<size> setValueByOtherType(T num)
    {
        static_assert(is_integral<T>::value, "can only assign sing integral types!");
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
        return this[0];
    }

    template <class T>
    string toBinaryString(T num)
    {
        static_assert(is_integral<T>::value, "Can only convert integral types to strings!");
        int a[size] = {0};
        int tmp = num;
        string ret = "";
        for (int i = 0; tmp > 0; i++)
        {
            a[i] = tmp % 2;
            tmp = tmp / 2;
        }

        for (int i = size - 1; i >= 0; i--)
            ret += to_string(a[i]);
        // cout << "num was converted to: " << ret << endl;
        return ret;
    }

    int whichIsLarger(vector<bool> boolVec, unsigned long long other)
    {
        string otherString = toBinaryString(other);
        vector<bool> otherVec = stringToBoolVec(otherString);
        return whichIsLarger(boolVec, otherVec);
    }

    int whichIsLarger(vector<bool> aV, vector<bool> bV)
    {
        size_t aSize = aV.size();
        size_t bSize = bV.size();

        if (aSize > bSize)
        {
            int diff = aSize - bSize;
            bV.insert(bV.begin(), abs(diff), false);

            bSize = bV.size();
        }
        else if (aSize < bSize)
        {
            int diff = bSize - aSize;
            aV.insert(aV.begin(), abs(diff), false);
            aSize = aV.size();
        }

        if (aSize != bSize)
            cerr << "comparison sizes could not be matched!\n";

        for (int i = 0; i < aSize; i++)
        {
            bool aC = aV[i];
            bool bC = bV[i];

            if (aC == 0 && bC == 1)
                return 2;
            if (aC == 1 && bC == 0)
                return 1;
        }
        return 0;
    }

    template <int otherSize>
    myuint<size> divideByMyuint(myuint<otherSize> m)
    {
        myuint<size> tmp(0);
        string newStr = this->toBinaryString();

        tmp.setValueByBinaryString(newStr);

        for (; m > 0; --m)
        {
            tmp.shiftRightThis(1);
            newStr = subtractBinaryStrings(newStr, tmp.toBinaryString());
        }
        tmp.setValueByBinaryString(newStr);

        return tmp;
    }

    template <int otherSize>
    myuint<size + otherSize> multiplyMyuints(myuint<otherSize> other)
    {

        myuint<size + otherSize> ret(0);
        string aStr = toBinaryString();
        string bStr = other.toBinaryString();

        string retStr = multiplyBinaryStrings(aStr, bStr);

        ret.setValueByBinaryString(retStr);
        return ret;
    }

    string multiplyBinaryStrings(string aStr, string bStr)
    {
        vector<string> tmp;
        int aSize = aStr.size();
        int bSize = bStr.size();
        if (aSize > bSize)
        {
            int diff = aSize - bSize;
            bStr.insert(0, abs(diff), '0');

            bSize = bStr.size();
        }
        else if (aSize < bSize)
        {
            int diff = bSize - aSize;
            aStr.insert(0, abs(diff), '0');
            aSize = aStr.size();
        }
        int i = aStr.size() - 1;
        int j = 0;

        string current;
        int aC, bC;
        aC = bC = 0;
        char abC;
        for (; i >= 0; i--)
        {
            current = "";
            for (j = bStr.size() - 1; j >= 0; j--)
            {
                aC = aStr[j] - '0';
                bC = bStr[i] - '0';
                abC = (aC * bC) + '0';
                current.insert(0, 1, abC);
            }
            tmp.push_back(current);
        }
        string retStr = "";
        int increase = 0;
        for (string s : tmp)
        {
            // cout<<increase<<") "<<s<<endl;
            s.insert(s.size(), increase, '0');
            s.insert(0, 1, '0');
            retStr = addBinaryStrings(retStr, s);
            increase++;
        }
        return retStr;
    }

    template <class T>
    bool isEqual(T num)
    {
        string other = toBinaryString(num);
        string thisS = toBinaryString();
        return (this == other);
    }

    template <int otherSize>
    bool isEqual(myuint<otherSize> other)
    {
        string otherS = other.toBinaryString();
        string thisS = toBinaryString();
        return (thisS == otherS);
    }

    string subtractBinaryStrings(string aStr, string bStr)
    {
        while (aStr.length() > bStr.length())
            bStr.insert(0, "0");
        while (bStr.length() > aStr.length())
            aStr.insert(0, "0");

        string ans(aStr.length(), '0');
        bool borrow = false;
        int aInt, bInt;
        for (int i = aStr.length() - 1; i >= 0; --i)
        {
            aInt = aStr[i];
            bInt = bStr[i];
            ans[i] += (aInt - bInt);

            if (borrow)
            {
                if (ans[i] != '0' - 1)
                {
                    ans[i] -= 1;
                    borrow = false;
                }
            }

            if (ans[i] == '0' - 1)
            {
                if (borrow)
                    ans[i] = '0';
                else
                    ans[i] = '0' + 1;
                borrow = true;
            }
            // if (borrow)
            //     ans += "error";TODO
        }
        return ans;
    }

    string addBinaryStrings(string aStr, string bStr)
    {
        string added = "";
        int x = 0;

        int aSize = aStr.size();
        int bSize = bStr.size();
        if (aSize > bSize)
        {
            int diff = aSize - bSize;
            bStr.insert(0, abs(diff), '0');

            bSize = bStr.size();
        }
        else if (aSize < bSize)
        {
            int diff = bSize - aSize;
            aStr.insert(0, abs(diff), '0');
            aSize = aStr.size();
        }
        int i = aStr.size() - 1;
        int j = bStr.size() - 1;

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

    template <int addResultSize, int otherSize>
    myuint<addResultSize> addMyuints(myuint<otherSize> &b)
    {
        cout << "in add method\n";
        vector<bool> aValues = getValueContainer();
        vector<bool> bValues = b.getValueContainer();

        string aStr = toBinaryString();
        string bStr = b.toBinaryString();

        myuint<addResultSize> ret(0);
        ret.setValueByBinaryString(addBinaryStrings(aStr, bStr));
        return ret;
    }

    vector<bool> stringToBoolVec(string s, int min = 0)
    {
        vector<bool> ret;

        for (char c : s)
        {
            if (c == '0')
                ret.push_back(false);

            else if (c == '1')
                ret.push_back(true);
            else
            {
                // cerr << "unkown character found! defaulting to 0\n";
                // this->values.push_back(false);
                cerr << "unkown character found! [" << c << "] aborting...\n";
                ret.clear();
                return ret;
            }
        }
        if (ret.size() < min)
        {
            int diff = min - ret.size();
            ret.insert(ret.begin(), diff, false);
        }
        return ret;
    }

    void setValueByBinaryString(string s)
    {
        values.clear();
        values = stringToBoolVec(s, size);
    }

    void setValueByVec(vector<bool> vals)
    {
        this->values = vals;
    }

    myuint<size> shiftRightThis(int shiftAmount)
    {

        // this->setSize((getSize()/8)+shiftAmount);

        values.erase(values.end() - shiftAmount, values.end());
        values.insert(values.begin(), shiftAmount, false);

        return this[0];
    }

    myuint<size> shiftLeftThis(int shiftAmount)
    {
        values.erase(values.begin(), values.begin() + shiftAmount);
        values.insert(values.end(), shiftAmount, false);

        return this[0];
    }

    myuint<size> shiftRight(int shiftAmount)
    {

        myuint<size> tmp(this);
        return tmp.shiftRightThis(shiftAmount);
    }

    myuint<size> shiftLeft(int shiftAmount)
    {
        myuint<size> tmp(this);
        return tmp.shiftLeftThis(shiftAmount);
    }

    string toBinaryString()
    {
        string bin;
        // cout << "value size: " << values.size();
        // cout << "starting conversion to binary String\n";

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
        // cout << "done2\n";
        return bin;
    }

    template <int otherSize>
    myuint<size> subtract(myuint<otherSize> &b)
    {
        string thisStr = toBinaryString();
        string bStr = b.toBinaryString();
        string result = subtractBinaryStrings(thisStr, bStr);
        myuint<size> tmp(0);
        tmp.setValueByBinaryString(result);
        return tmp;
    }

    template <class T>
    myuint<size> subtract(T b)
    {
        string thisStr = toBinaryString();
        string bStr = toBinaryString(b);
        string result = subtractBinaryStrings(thisStr, bStr);
        myuint<size> tmp(0);
        tmp.setValueByBinaryString(result);
        return tmp;
    }

    template <class T>
    vector<myuint<size>> longDivide(T other)
    {
        myuint<size> nomin(0);
        nomin.setValueByVec(getValueContainer());

        myuint<size> denom(0);
        denom.setValueByVec(getValueContainer());

        myuint<size> remain(0);
        remain.setValueByVec(getValueContainer());

        myuint<size> m0(1);
        myuint<size> n0(0);
        n0 = other;

        myuint<size> quotient(0);

        if (other > *this)
        {
            return {quotient, remain};
        }

        myuint<size> tmp(0);
        tmp = n0;
        while (((tmp.shiftLeftThis(1)) <= *this))
        {
            n0.shiftLeftThis(1);
            m0.shiftLeftThis(1);
            tmp = n0;
        }
        remain -= n0;
        quotient += m0;

        while (remain >= other)
        {
            m0.shiftRightThis(1);
            n0.shiftRightThis(1);
            if (n0 <= remain)
            {
                remain -= n0;
                quotient += m0;
            }
        }
        return {quotient, remain};
    }

    template <class T>
    T convert_to()
    {
        static_assert(is_integral<T>::value, "Integral type required.");
        T other = 0;
        bool canConvert = false;
        int cSize = size;
        if (getSize() <= sizeof(T) * 8)
        {
            canConvert = true;
        }

        int realSize = getValueContainer().size();
        for (bool x : getValueContainer())
        {
            if (x)
                break;
            realSize--;
        }
        if (realSize <= sizeof(T) * 8)
        {
            canConvert = true;
            cSize = realSize;
        }
        // cout<<"real size is: "<<realSize<<"\n";
        // cout<<"declared size is: "<<getSize()<<"\n";
        // cout<<"real container size is: "<<getContainerSize()<<"\n";
        // cout<<"dest size is: "<<sizeof(T)*8<<"\n";
        if (!canConvert)
        {
            cerr << "size of number cannot be converted to size of type specified\n";
            return 0;
        }

        T currentValue = 1;
        // for(bool c: getValueContainer()){
        vector<bool> valCont = getValueContainer();
        for (int i = valCont.size() - 1; i >= 0; i--)
        {
            if (valCont[i])
                other += currentValue;
            currentValue *= 2;
        }
        return other;
    }

    //copy const
    template <int otherSize>
    myuint(const myuint<otherSize> &other)
    {
        if (otherSize == size)
            this->values = other.values;
        else
            cerr << "Could not copy by different size!\n";
    }

    template <int otherSize>
    myuint(myuint<otherSize> &&other)
    {

        if (otherSize == size)
            this->values = move(other.values);
        else
            cerr << "Could not move by different size!\n";
    }
};
