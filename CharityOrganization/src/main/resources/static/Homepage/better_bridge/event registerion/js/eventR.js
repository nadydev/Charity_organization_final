let eventName= document.getElementById('eventName');
let e_name = localStorage.getItem("events")
eventName.setAttribute("placeholder",e_name)


if(eventName.getAttribute("placeholder")=="null"){
  eventName.setAttribute("placeholder","");
}

localStorage.clear();




