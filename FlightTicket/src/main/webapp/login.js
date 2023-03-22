/**
 * 
 */
 
 window.onload=function(){
	var xhr =new XMLHttpRequest();
	console.log(this.readyState)

	xhr.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
						var respObj=JSON.parse(xhr.responseText);
			var Role=respObj.Role;
			if(Role=="Admin"){
					window.location.href="./AddFlight.html";
			}else{
				window.location.href="./FlightBooking.html";
			}
		}
	}
	 xhr.open("POST","./User/CheckLogin");
	 xhr.send();
}
 
 
 function check(){
	var username=document.getElementById("userName").value;
	
	var password=document.getElementById("password").value;
	
	 var namePattern = /^[a-zA-Z ]{2,30}$/;
	 var regularExpression  = /^[a-zA-Z0-9!@#$%^&*]{6,16}$/;
 	if(!namePattern.test(username)){
	console.log("invalid input in username");
	return false;
}
	else if(!regularExpression.test(password)){
		console.log("invalid input in password");
		return false;
	}
 return true;	
}




 function Login(){
	if(check()==true){
		if(document.getElementById("userName").value!="" && document.getElementById("password").value!=""){
		var xhr=new XMLHttpRequest();
	xhr.onreadystatechange=function(){
		if(this.status==200 && this.readyState==4){
			var responseObj=JSON.parse(xhr.responseText);
			console.log(responseObj);
			if(responseObj.Role=="User"){
				window.location.href="./FlightBooking.html";

			}
			else if(responseObj.Role=="Admin"){
								window.location.href="./AddFlight.html";

			}
			else{
				alert("No suc user Found");
				document.getElementById("userName").value="";
		 document.getElementById("password").value="";
			}
		}
		
	}
	var tempobj={};
	tempobj.name=document.getElementById("userName").value;
	tempobj.password=document.getElementById("password").value;
	console.log(tempobj);
	xhr.open("POST","./login");
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	xhr.send("name="+tempobj.name+"&password="+tempobj.password);
	}
	}
	else{
			document.getElementById("userName").value="";
		 document.getElementById("password").value="";
	}
	
	
}