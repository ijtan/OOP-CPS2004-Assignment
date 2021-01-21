#include <iostream>
#include <limits>
#include <stdexcept>
#include <vector>
#include "math.h"
#include <string>
#include <limits.h>

using namespace std;

template <int size>
class myuint
{

private:
    static_assert(((size != 0) && ((size & (size - 1)) == 0)), "Size can only be power of two"); //compile time error if not power of two

    vector<bool> values = vector<bool>(size);

public:
    template <class T>
    myuint(T value)
    {
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
        return addMyuints<size>(other);
    };

    template <class T>
    myuint<size> operator+(T other)
    {
        static_assert(is_integral<T>::value, "Can only add by integral types!");

        myuint<size> tmp(0);
        tmp.setValueByVec(addVecs(values, toBinVec(other)));
        return tmp;
    };

    template <int otherSize>
    myuint<size> operator+=(myuint<otherSize> &other)
    {
        setValueByVec(addVecs(values, other.getValueContainer()));
        return this[0];
    };

    template <class T>
    myuint<size> operator+=(T other)
    {
        static_assert(is_integral<T>::value, "Can only add by integral types!");
        setValueByVec(addVecs(values, toBinVec(other)));
        return this[0];
    };

    template <int otherSize>
    myuint<size> operator-(myuint<otherSize> other)
    {
        return subtract<size>(other);
    };

    template <class T>
    myuint<size> operator-(T other)
    {
        static_assert(is_integral<T>::value, "Can only subtract by integral types!");
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
        setValueByVec(subtractVecs(values, toBinVec(other)));

        return *this;
    }

    myuint<size> operator--()
    {
        return *this -= 1;
    }

    myuint<size> operator++()
    {
        return *this += 1;
    }

    template <int otherSize>
    bool operator==(myuint<otherSize> other)
    {
        return isEqual(other);
    }

    template <class T>
    bool operator==(T other)
    {
        static_assert(is_integral<T>::value, "Can only compare to integral types!");
        return isEqual(other);
    }

    template <int otherSize>
    bool operator!=(myuint<otherSize> other)
    {
        return !isEqual(other);
    }

    template <class T>
    bool operator!=(T other)
    {
        static_assert(is_integral<T>::value, "Can only compare to integral types!");
        return !isEqual(other);
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
    bool operator>(T other)
    {
        static_assert(is_integral<T>::value, "Can only compare to integral types!");
        return whichIsLarger(values, toBinVec(other)) == 1;
    }

    template <class T>
    bool operator<(T other)
    {
        static_assert(is_integral<T>::value, "Can only compare to integral types!");
        return whichIsLarger(values, toBinVec(other)) == 2;
    }

    template <class T>
    bool operator>=(T other)
    {
        static_assert(is_integral<T>::value, "Can only compare to integral types!");
        int val = whichIsLarger(values, toBinVec(other));
        return val == 1 || val == 0;
    }

    template <class T>
    bool operator<=(T other)
    {
        static_assert(is_integral<T>::value, "Can only compare to integral types!");
        int val = whichIsLarger(values, toBinVec(other));
        return val == 2 || val == 0;
    }

    myuint<size> operator<<(int amount)
    {
        return shiftLeft(amount);
    }
    myuint<size> operator>>(int amount)
    {
        return shiftRight(amount);
    }

    myuint<size> operator<<=(int amount)
    {
        return shiftLeftThis(amount);
    }
    myuint<size> operator>>=(int amount)
    {
        return shiftRightThis(amount);
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
                break;
            }
        }
        tmpCont.erase(tmpCont.begin(), tmpCont.begin() + tmp.getSize() - size);
        myuint<size> ret(0);

        ret.setValueByVec(tmpCont);
        return ret;
    }

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
        return *this = longDivide(other)[0];
    }

    template <int otherSize>
    myuint<size> operator%=(myuint<otherSize> other)
    {
        return *this = longDivide(other)[1];
    }

    vector<bool> getValueContainer()
    {
        return values;
    }

    int getSize()
    {
        return size;
    }
    int getContainerSize()
    {
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
        if (values.size() != size)
        {
            cerr << "Error while trying to initialize myuint with other type\n";
        }
        return this[0];
    }

    template <class T>
    string toBinaryString(T num)
    {
        static_assert(is_integral<T>::value, "Can only convert integral types to strings!");
        int a[size] = {0};
        T tmp = num;
        string ret = "";
        for (int i = 0; tmp > 0; i++)
        {
            a[i] = tmp % 2;
            tmp = tmp / 2;
        }

        for (int i = size - 1; i >= 0; i--)
            ret += to_string(a[i]);
        return ret;
    }

    template <class T>
    vector<bool> toBinVec(T num)
    {

        static_assert(is_integral<T>::value, "Can only convert integral types to strings!");
        vector<bool> ret;
        T tmp = num;
        if (num == 0)
        {
            ret.push_back(false);
            return ret;
        }

        while (tmp > 0)
        {
            ret.push_back((bool)(tmp % 2));
            tmp >>= 1;
        }

        reverse(ret.begin(), ret.end());
        return ret;
    }

    int whichIsLarger(vector<bool> A, vector<bool> B)
    {
        while (A.size() > B.size())
            B.insert(B.begin(), false);
        while (B.size() > A.size())
            A.insert(A.begin(), false);

        if (A.size() != B.size())
            cerr << "comparison sizes could not be matched!\n";

        for (int i = 0; i < A.size(); i++)
        {
            if (!A[i] && B[i])
                return 2;
            if (A[i] && !B[i])
                return 1;
        }
        return 0;
    }

    template <int otherSize>
    myuint<size> multiplyMyuints(myuint<otherSize> other)
    {

        myuint<size> ret(0);
        ret.setValueByVec(multiplyVecs(values, other.getValueContainer()));
        return ret;
    }
    vector<bool> multiplyVecs(vector<bool> A, vector<bool> B)
    {
        vector<vector<bool>> tmp;

        while (A.size() > B.size())
            B.insert(B.begin(), false);
        while (B.size() > A.size())
            A.insert(A.begin(), false);
        int i, j;

        vector<bool> currentVec;
        for (int i = A.size() - 1; i >= 0; i--)
        {
            currentVec.clear();
            for (int j = B.size() - 1; j >= 0; j--)
                currentVec.insert(currentVec.begin(), A[j] && B[i]);

            tmp.push_back(currentVec);
        }
        vector<bool> retVec;
        int increase = 0;
        for (vector<bool> num : tmp)
        {
            num.insert(num.end(), increase, false);
            num.insert(num.begin(), false);
            retVec = addVecs(retVec, num);
            increase++;
        }
        return retVec;
    }

    template <class T>
    bool isEqual(T num)
    {
        vector<bool> other = toBinVec(num);
        vector<bool> current = values;

        if (current.empty() || other.empty())
            return false;

        while (!other[0] && other.size() > 1)
            other.erase(other.begin());
        while (!current[0] && current.size() > 1)
            current.erase(current.begin());

        return (current == other);
    }

    template <int otherSize>
    bool isEqual(myuint<otherSize> other)
    {

        vector<bool> otherVec = other.getValueContainer();
        vector<bool> currentVec = values;

        if (currentVec.empty() || otherVec.empty())
            return false;

        while (!currentVec[0] && currentVec.size() > 1)
            currentVec.erase(currentVec.begin());
        while (!otherVec[0] && otherVec.size() > 1)
            otherVec.erase(otherVec.begin());

        return (currentVec == otherVec);
    }

    vector<bool> subtractVecs(vector<bool> A, vector<bool> B)
    {
        while (A.size() > B.size())
            B.insert(B.begin(), false);
        while (B.size() > A.size())
            A.insert(A.begin(), false);
        int i, j;

        // string ans(aStr.length(), '0');
        vector<bool> ans;
        bool borrowed = false;
        bool currBit = false;
        bool needBorrow = false;
        for (int i = A.size() - 1; i >= 0; --i)
        {
            //emulate subtracting bitA - bitB
            if (A[i] && B[i])
                currBit = false;
            if (A[i] && !B[i])
                currBit = true;
            if (!A[i] && B[i])
                needBorrow = true;
            //check if previous bit needed a borrow
            //if we also need a borrow skip
            if (borrowed && !needBorrow)
            {
                if (currBit) //if we are 1, we can turn into a 0, we can sacrifice current bit to resolve borrow
                    currBit = false;
                else //if we are 0, we need to borrow to satisfy the previous borrow
                    needBorrow = true;
                borrowed = false; //either way, the current borrow is satisfied (either by ourselves or future borrow)
            }

            if (needBorrow) //if we need to borrow
            {
                currBit = !borrowed; //if we have already borrowed, current bit=0, else curr becomes 1, since we will have borrowed
                borrowed = true;     //flag for next numbers
                needBorrow = false;  //borrow need has been satisfied
            }
            ans.insert(ans.begin(), currBit); //insert  current bit to ans
            currBit = false;                  //reset current bit
        }
        return ans;
    }

    vector<bool> addVecs(vector<bool> A, vector<bool> B)
    {
        vector<bool> added;
        int x = 0;
        while (A.size() > B.size())
            B.insert(B.begin(), false);
        while (B.size() > A.size())
            A.insert(A.begin(), false);
        int i, j;
        i = j = A.size() - 1;

        while (x == 1 or i >= 0 or j >= 0)
        {
            if (i >= 0 && A[i])
                x++;

            if (j >= 0 && B[i])
                x++;

            added.insert(added.begin(), (bool)(x % 2));

            x /= 2;
            i--;
            j--;
        }
        return added;
    }

    template <int addResultSize, int otherSize>
    myuint<addResultSize> addMyuints(myuint<otherSize> &b)
    {

        myuint<addResultSize> ret(0);
        ret.setValueByVec(addVecs(values, b.getValueContainer()));
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
        vector<bool> tmp = stringToBoolVec(s, size);
        if (tmp.size() > size)
        {
            cerr << "string too large to assign " << tmp.size() << ">" << size << " Removing extra bits!\n";
            int diff = (tmp.size() - size);
            tmp.erase(tmp.begin(), tmp.begin() + diff);
        }
        values = tmp;
    }

    void setValueByVec(vector<bool> vals)
    {
        if (vals.size() == size)
        {
            this->values = vals;
            return;
        }
        if (vals.size() < size)
        {
            int diff = size - vals.size();
            vals.insert(vals.begin(), diff, false);
            this->values = vals;
            return;
        }
        cerr << "Vector to assign is larger than size; data may be lost\n";
        while (size < vals.size())
        {
            vals.erase(vals.begin());
        }
        this->values = vals;
        return;
    }

    myuint<size> shiftRightThis(int shiftAmount)
    {
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

        myuint<size> tmp(*this);
        return tmp.shiftRightThis(shiftAmount);
    }

    myuint<size> shiftLeft(int shiftAmount)
    {
        myuint<size> tmp(*this);
        return tmp.shiftLeftThis(shiftAmount);
    }

    string toBinaryString()
    {
        string bin;
        int i = 0;
        for (bool b : values)
        {
            i++;

            if (b)
                bin += '1';
            else
                bin += '0';
        }
        return bin;
    }

    template <int otherSize>
    myuint<size> subtract(myuint<otherSize> &b)
    {
        myuint<size> tmp(0);
        tmp.setValueByVec(subtractVecs(values, b.getValueContainer()));
        return tmp;
    }

    template <class T>
    myuint<size> subtract(T b)
    {
        myuint<size> tmp(0);
        tmp.setValueByVec(subtractVecs(values, toBinVec(b)));
        return tmp;
    }

    template <class T>
    vector<myuint<size>> longDivide(T other)
    {
        if (other == 0)
            throw "Division by zero!";

        myuint<size> nomin(*this);

        myuint<size> denom(*this);

        myuint<size> remain(*this);

        myuint<size> m0(1);
        myuint<size> n0(0);
        n0 = other;

        myuint<size> quotient(0);

        if (*this < other)
        {
            return {quotient, remain};
        }

        while (n0 <= shiftRight(1))
        {
            n0.shiftLeftThis(1);
            m0.shiftLeftThis(1);
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
    myuint(myuint<otherSize> &other)
    {
        setValueByVec(other.getValueContainer());
    }

    template <int otherSize>
    myuint(myuint<otherSize> &&other)
    {
        cout << "move constructor\n";

        if (other.getSize() > getSize())
        {
            cerr << "other size(" << other.getSize() << ") is too big to move to this(" << getSize() << ") one!\n";
        }
        int diff = size - other.getSize();
        this->values = move(other.getValueContainer());
        values.insert(values.begin(), diff, false);
    }

    string toDecimalString()
    {
        string ret = "";
        myuint<size> current(*this);
        myuint<size>remTmp(0);
        
        int rem = 0;

        if (current == 0)
            return "0";
        do
        {
            remTmp = current % 10;
            rem = remTmp.convert_to<int>();
            current /= 10;
            ret.insert(0, 1, rem + '0');
        } while (current > 0);

        return ret;
    }
};

template <int size>
ostream &operator<<(ostream &os, myuint<size> mi)
{
    os << mi.toDecimalString();
    return os;
}
