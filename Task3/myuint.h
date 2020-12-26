#include <iostream>
#include <limits>
#include <stdexcept>
#include <vector>
#include <string>
#define ll unsigned long long
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
        vector<bool> aValues = getValueContainer();
        vector<bool> bValues = b.getValueContainer();

        
        // if (sizeof(aValues + bValues) > addResultSize)
        // {
        //     cout << "size of value > size of return!\n";
        // }
        // return aValues + bValues;

        
        string aStr = toBinaryString();
        string bStr = b.toBinaryString();

        string added = ""; 
        int x = 0;
        int i = aStr.size() - 1;
        int j = bStr.size() - 1;
        while (x==1 or i >= 0 or j >= 0)
        {
            if(i>=0)
                x += aStr[i] - '0';
            else
                x += 0;

            if (j >= 0)
                x += bStr[i] - '0';
            else
                x+= 0;

            added = char(x % 2 + '0') + added;
            
            x /= 2;
            i--;
            j--;
        }
        myuint<addResultSize> ret;
        ret.setValueByBinaryString(added);
        return ret;
    }

    void setValueByBinaryString(string s){
        values.clear();
        for(char c: s){
            if(c == '0'){
                this->values.push_back(false);
            }
            else if (c == '1'){
                this->values.push_back(true);
            }else{
                cerr<<"unkown character found! aborting...\n";
                return;
            }
        }
    }

    string toBinaryString(){
        string bin;
        for (bool b : values)
        {
            if (b)
                bin+= '1';
            else
                bin+= '0';
        }
        return bin;
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