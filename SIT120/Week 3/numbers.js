// starter variables
let x1 = 100;
let pi = 3.14;

// basic addition
console.log(x1 + pi);

// example of "addition" with strings resulting in concat
let xs = "100";
let pis = "3.14";
console.log(xs + pis);

// mixing strings and numbers in addition does concat as well, with the number converted to a string
console.log(x1 + xs);

// but some other operations will automatically invoke a conversion from a string
console.log(xs / pi);

// note that the same wouldn't work if the conversion fails
console.log(xs / "pi");

// double equals for equality comparisons
let y1 = 100;
console.log(x1 == y1);

// triple equals for "strict" equality comparisons (e.g. no automatic conversion from a string)
console.log(x1 === y1);
console.log(x1 == xs);
console.log(x1 === xs);

// number.toString(x) can be used to change what base the number is converted with
let address = 235789;
console.log(address.toString(16));

// the default conversion is base 10
let address2 = 0x3990d;
console.log(address2.toString());

// numbers can be put into arrays
let array1 = [x1, y1];
console.log(array1);

// creating an array will try to convert strings to numbers
let array2 = [xs, pis]
console.log(array2);

// elements in the array can be accessed as expected
console.log(array1[0] + array1[1]);
console.log(array1[0] / array1[1]);
console.log(array1[0] / array2[0]);
console.log(array1[0] * array2[0]);