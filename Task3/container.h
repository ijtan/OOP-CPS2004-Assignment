#include <iostream>
#include <limits>
#include <stdexcept>
#include <vector>
#include <string>
#include <limits.h>
using namespace std;
template<int size>
class container{
    static_assert(size <= 2048, "Size is too big, no container available");
    static_assert(((size != 0) && ((size & (size - 1)) == 0)), "Size can only be power of two");
    class static initialize()
    {
        int cSize = size/4;
        if (cSize <= sizeof(char))
            return char;
        if (cSize <= sizeof(int))
            return int;
        if (cSize <= sizeof(unsigned int))
            return unsigned int;
        if (cSize <= sizeof(unsigned long))
            return unsigned long;
        if (cSize <= sizeof(unsigned long long))
            return unsigned long long;
        if (cSize <= sizeof(2048))
            return myuint<size>(0);    
    }
};