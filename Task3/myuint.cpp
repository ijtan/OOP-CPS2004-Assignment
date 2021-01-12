// #include "myuint.h"

// // template <ll size>

// // template <ll otherSize>
// // myuint<size+otherSize> myuint<size>::operator+(myuint<otherSize> &other)
// // {
// //     cout << "+ operator called!\n";
// //     return add<size + otherSize>(other);
// // };

// // template <ll otherSize>
// // myuint<(myuint.getSize() > otherSize ? size : otherSize)> myuint<size>::operator-(myuint<otherSize> &other)
// // {

// //     cout << "- operator called!\n";
// //     return subtract<(size > otherSize ? size : otherSize)>(other);
// // }

// template <int size>
// template <int otherSize>
// myuint<size>::myuint<size + otherSize> multiplyMyuints(myuint<otherSize> other)
// {

//     myuint<size + otherSize> ret(0);
//     string aStr = toBinaryString();
//     string bStr = other.toBinaryString();

//     string retStr = multiplyBinaryStrings(aStr, bStr);

//     ret.setValueByBinaryString(retStr);
//     return ret;
// }