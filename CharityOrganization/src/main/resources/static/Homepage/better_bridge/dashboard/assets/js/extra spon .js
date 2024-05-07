

var productName=document.getElementById("pn");
var productPrice=document.getElementById("pp"); 
var productCategory=document.getElementById("pc");
var productDesc=document.getElementById("desc");
var productBes=document.getElementById("Bes");
var productCam=document.getElementById("Cam");
var productID=document.getElementById("ID-SPON");


var Sponser=[];
 if (JSON.parse(localStorage.getItem("Sponser")) !=null) {
    Sponser=JSON.parse(localStorage.getItem("Sponser"))
     displayProducts();
 }

var ind;
 function GetElement(){
    var product={
        name:productName.value,
        price:productPrice.value,
        category:productCategory.value,
        description:productDesc.value,
        bussiness:productBes.value,
        campaign:productCam.value,
        ID:productID.value
    }
  if ( document.getElementById("btn").innerHTML==" Add") {

    Sponser.push(product)
   
    
   
    clear()
    displayProducts()
   }
 else {

    Sponser.splice(ind,1,product)
    displayProducts()
    clear()
    document.getElementById("btn").innerHTML="Add";

  }
  localStorage.setItem("Sponser",JSON.stringify(Sponser))
 }
 function clear(){
    productName.value="";
    productPrice.value="";
    productCategory.value="";
    productDesc.value="";
    productBesvalue="" ; 
    productCam.value="";
    productID.value="";
 }

 function displayProducts(){
    var cartoona="";
    var test="coca"
    for (var i = 0; i<Sponser.length; i++) {
        
        cartoona+=`<tr>
        <td>${ Sponser[i].name}</td>
        <td>${Sponser[i].price}</td>
        <td>${Sponser[i].category}</td>
        <td>${Sponser[i].description}</td>
        <td>${Sponser[i].bussiness}</td>
        <td>${Sponser[i].campaign}</td>
        <td>${Sponser[i].ID}</td>
        <td><button class="btn btn-danger" onclick="deleteProduct(${i})">Delete</button></td>
        <td><button class="btn btn-warning" onclick="UpdateProducts(${i})">Update</button></td>
    </tr>`
    
    }
    document.getElementById("tbody").innerHTML=cartoona;
 }
 function deleteProduct(index){
    Sponser.splice(index,1,)
    displayProducts()
    localStorage.setItem("Sponser",JSON.stringify(Sponser))

 }
 function UpdateProducts(index){
    ind=index;
    productName.value=Sponser[index].name;
    productPrice.value=Sponser[index].price;
    productCategory.value=Sponser[index].category;
    productDesc.value=Sponser[index].description;
    productBes.value=Sponser[index].bussiness;
    productCam.value=Sponser[index].campaign;
    productID.value=Sponser[index].ID;
    document.getElementById("btn").innerHTML="Update";


 }