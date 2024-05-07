

var productName=document.getElementById("pn");
var productPrice=document.getElementById("pp"); 
var productCategory=document.getElementById("pc");
var productDesc=document.getElementById("desc");
var productID=document.getElementById("ID-DON");

var Donor=[];
 if (JSON.parse(localStorage.getItem("Donor")) !=null) {
    Donor=JSON.parse(localStorage.getItem("Donor"))
     displayProducts();
 }

var ind;
 function GetElement(){
    var product={
        name:productName.value,
        price:productPrice.value,
        category:productCategory.value,
        description:productDesc.value,
        ID: productID.value
    }
  if ( document.getElementById("btn").innerHTML==" Add") {

    Donor.push(product)
   
    
   
    clear()
    displayProducts()
   }
 else {

    Donor.splice(ind,1,product)
    displayProducts()
    clear()
    document.getElementById("btn").innerHTML="Add";

  }
  localStorage.setItem("Donor",JSON.stringify(Donor))
 }
 function clear(){
    productName.value="";
    productPrice.value="";
    productCategory.value="";
    productDesc.value="";
    productID.value="";
 }

 function displayProducts(){
    var cartoona="";
    var test="coca"
    for (var i = 0; i<Donor.length; i++) {
        
        cartoona+=`<tr>
        <td>${ Donor[i].name}</td>
        <td>${Donor[i].price}</td>
        <td>${Donor[i].category}</td>
        <td>${Donor[i].ID}</td>
        <td>${Donor[i].description}</td>

        <td><button class="btn btn-danger" onclick="deleteProduct(${i})">Delete</button></td>
        <td><button class="btn btn-warning" onclick="UpdateProducts(${i})">Update</button></td>
    </tr>`
    
    }
    document.getElementById("tbody").innerHTML=cartoona;
 }
 function deleteProduct(index){
    Donor.splice(index,1,)
    displayProducts()
    localStorage.setItem("Donor",JSON.stringify(Donor))

 }
 function UpdateProducts(index){
    ind=index;
    productName.value=Donor[index].name;
    productPrice.value=Donor[index].price;
    productCategory.value=Donor[index].category;
    productDesc.value=Donor[index].description;
    productID.value=Donor[index].ID;
    document.getElementById("btn").innerHTML="Update";


 }