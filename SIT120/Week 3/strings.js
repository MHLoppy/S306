// starting string
let colours = "Red, Green, Blue";

// string length
let colours_length = colours.length;
console.log(colours_length);

// string slice()
let green = colours.slice(5, 10);
console.log(green);
let blue = colours.slice(-4);
console.log(blue);

// string substring() - similar to slice but start/end values less than 0 are treated as 0
let green_and_blue = colours.substring(5);
console.log(green_and_blue);

// string substr() - similar to slice but second number specifies length of extracted part
let red = colours.substr(0, 3);
console.log(red)

// upper and lower case conversions
let upper = colours.toUpperCase();
console.log(upper)
let lower = colours.toLowerCase();
console.log(lower)

// string split()
let colours_split = colours.split(","); // this is an array, but note that we didn't include the spaces in the split
console.log(colours_split);

// string concat()
let throwaway_string = " ";
let colours_concat = throwaway_string.concat(colours_split[0], colours_split[1], colours_split[2]); // because our earlier split didn't include spaces, we get Red Green Blue, not RedGreenBlue
console.log(colours_concat);