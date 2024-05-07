
function getSelected(){
let price = document.getElementById('price');
let selectedPrice = price.options[price.selectedIndex];
innerPrice = selectedPrice.innerHTML;





  document.getElementById('final_price').placeholder=innerPrice;

if(innerPrice=="Other Price"){
  document.getElementById("final_price").removeAttribute("disabled");


}

else{
  document.getElementById("final_price").disabled=true;
}


}


function getGiftMessage(){

  let price = document.getElementById('price');
  let selectedPrice = price.options[price.selectedIndex];
  innerPrice = selectedPrice.innerHTML;
  
  let campaign = document.getElementById('campaign');
  let  selectedCampign= campaign.options[campaign.selectedIndex];
  innerCampaign = selectedCampign.innerHTML;


if(innerPrice!="Select donation amount" && innerCampaign!="Select campaign name"){

document.getElementById('message').innerHTML=`Thank You For Your Donation to  ${innerCampaign}`;

document.getElementById('message').classList.remove('text-danger-emphasis');
document.getElementById('message').classList.add('text-success');





}

else{

  document.getElementById('message').innerHTML=`Please Complete Fields to complete your donations`;

document.getElementById('message').classList.remove('text-success');


document.getElementById('message').classList.add('text-danger-emphasis');


}
}
