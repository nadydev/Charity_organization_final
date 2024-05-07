

var productName=document.getElementById("pn");
var productPrice=document.getElementById("pp"); 
var productCategory=document.getElementById("pc");
var productDesc=document.getElementById("desc");
var productID=document.getElementById("ID-VOLON");


var Volounter=[];
 if (JSON.parse(localStorage.getItem("Volounter")) !=null) {
    Volounter=JSON.parse(localStorage.getItem("Volounter"))
     displayProducts();
 }

var ind;
 function GetElement(){
    var product={
        name:productName.value,
        price:productPrice.value,
        category:productCategory.value,
        ID:productID.value,
        
    }
  if ( document.getElementById("btn").innerHTML==" Add") {

    Volounter.push(product)
   
    
   
    clear()
    displayProducts()
   }
 else {

    Volounter.splice(ind,1,product)
    displayProducts()
    clear()
    document.getElementById("btn").innerHTML="Add";

  }
  localStorage.setItem("Volounter",JSON.stringify(Volounter))
 }
 function clear(){
    productName.value="";
    productPrice.value="";
    productCategory.value="";
    productID.value="";
   
 }

 function displayProducts(){
    var cartoona="";
    var test="coca"
    for (var i = 0; i<Volounter.length; i++) {
        
        cartoona+=`<tr>
        <td>${ Volounter[i].name}</td>
        <td>${Volounter[i].price}</td>
        <td>${Volounter[i].category}</td>
        <td>${Volounter[i].ID}</td>
       
        <td><button class="btn btn-danger" onclick="deleteProduct(${i})">Delete</button></td>
        <td><button class="btn btn-warning" onclick="UpdateProducts(${i})">Update</button></td>
    </tr>`
    
    }
    document.getElementById("tbody").innerHTML=cartoona;
 }
 function deleteProduct(index){
    Volounter.splice(index,1,)
    displayProducts()
    localStorage.setItem("Volounter",JSON.stringify(Volounter))

 }
 function UpdateProducts(index){
    ind=index;
    productName.value=Volounter[index].name;
    productPrice.value=Volounter[index].price;
    productCategory.value=Volounter[index].category;
    productID.value=Volounter[index].ID;
   
    document.getElementById("btn").innerHTML="Update";
   
  } 



 