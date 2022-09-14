// dates be declared using the normal "let x = ...", but it may be preferable to make them constants where appropriate to avoid overwriting
let date_normal = new Date(2018, 11, 24, 10, 33, 30, 0);
console.log(date_normal);
const date_const = new Date(2018, 11, 24, 10, 33, 30, 0);
console.log(date_const);

// the number of parameters passed to the date object on initialization determines the level of precision that the Date object has
const date_up_to_day = new Date(2022, 7, 4);
console.log(date_up_to_day);  // the hours/minutes/seconds etc are set to 0 in this Date object, because they were not passed as parameters
const date_up_to_hour = new Date(2022, 7, 4, 9);
console.log(date_up_to_hour); // whereas for this Date object, the hour is set to 9 o'clock but the minutes/seconds etc remain at 0

// note that some fields start at 0 (such as month), whereas others basically start at 1 (such as day)
const christmas = new Date(2022, 11, 25);
console.log(christmas);

// two digit years are parsed as 19xx
const nothing_happened = new Date(89, 5, 4);
console.log(nothing_happened);

// we can pass in a date string when creating the Date object
const date_from_string = new Date("October 13, 2014 11:13:00");
console.log(date_from_string);

// we can also convert in the other direction, from a Date to a string
let christmas_as_string = christmas.toUTCString();
console.log(christmas_as_string);
console.log(christmas);

// avoid using non-ISO string parameters for a date, as this isn't in-spec and the results can therefore be inconsistent between browsers
const dont_do_this = new Date(2022/08/06);
console.log(dont_do_this);

// there are a variety of methods available relating to dates
console.log(christmas.getFullYear());
console.log(christmas.getMonth());    // note that this returns month as 0-11, not 1-12
console.log(christmas.getDay());      // whereas this one returns day as 1-31!

const next_christmas_bad = christmas.setFullYear(2023);       // using this method doesn't convert as-expected, and you only end up with a number (the unix timestamp) instead of a Date object
console.log(next_christmas_bad);
const next_christmas = new Date(christmas.setFullYear(2023)); // this changes the year and keeps the rest
console.log(next_christmas);
console.log(next_christmas.toUTCString());

console.log(christmas)  // notice how we've actually also changed the underlying variable with the above changes!!

const christmas_dinner = new Date(christmas.setHours(19));
console.log(christmas_dinner);

const boxing_day = new Date(christmas.setDate(26)); // this is "set day" - confusing coming from C# stuff!
console.log(boxing_day);

// we can do comparisons with dates
const christmas_2022 = new Date(2022, 11, 25);
const boxing_day_2022 = new Date(2022, 11, 26);
if (boxing_day_2022 > christmas_2022) { console.log("boxing day is after christmas"); } else { console.log("boxing day is not after christmas"); }