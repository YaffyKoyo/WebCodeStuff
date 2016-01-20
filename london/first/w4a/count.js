// this is a variable, we start it at 0
var counter = 0;
// display the variable in the
// element "number"
$("#number").text("count: "+counter);
// when this function is called it runs
// everything inside the curly brackets
function count(SetToZero) {
  // firstly one is added to the variable
  // ‘counter'
  if (SetToZero) {
    counter = 0;
  }else {
    counter = counter+1;
  }
  // set the h1 element (with id “number”)
  // with the value of ‘counter'
  $("#number").text("count: "+counter);
}
