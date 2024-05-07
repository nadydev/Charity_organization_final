let v= document.getElementById('event1').innerHTML;

let RegisterBtn = document.getElementById('r1');

RegisterBtn.addEventListener('click',function(e){
  localStorage.setItem("events", v);

})


let x= document.getElementById('event2').innerHTML;
console.log(x);

let RegisterBtn_2 = document.getElementById('r2');

RegisterBtn_2.addEventListener('click',function(e){
  localStorage.setItem("events", x);

})



let y= document.getElementById('event3').innerHTML;
console.log(x);

let RegisterBtn_3 = document.getElementById('r3');

RegisterBtn_3.addEventListener('click',function(e){
  localStorage.setItem("events", y);

})



let h= document.getElementById('event4').innerHTML;
console.log(x);

let RegisterBtn_4 = document.getElementById('r4');

RegisterBtn_4.addEventListener('click',function(e){
  localStorage.setItem("events", h);

})










