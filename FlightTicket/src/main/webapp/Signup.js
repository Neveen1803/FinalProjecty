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
	var PhoneNumber=document.getElementById("PhoneNumber").value;
	var confirmPassword=document.getElementById("ConfirmPassword").value;
	 var namePattern = /^[a-zA-Z ]{2,30}$/;
	 var regularExpression  = /^[a-zA-Z0-9!@#$%^&*]{6,16}$/;
	 var numberExpression=/^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/;
	 
 	if(!namePattern.test(username)){
	alert("invalid input in username");
	return false;
}
	else if(!numberExpression.test(PhoneNumber)){
		alert("invalid mobile number");
	return false;
	}
	else if(!regularExpression.test(password)){
		alert("invalid input in password");
		return false;
	}
	else if(!regularExpression.test(confirmPassword)){
		alert("invalid input in confirm password");
		return false;
	}
	else if(!password==confirmPassword){
		alert("password and confirm password does not match")
		return false;
	}
 return true;	
}

 function Signup(){
	if(check()==true){
	var xhr=new XMLHttpRequest();
	xhr.onreadystatechange=function(){
		
		if(this.readyState==4 && this.status==200){
			alert("Successfully logged")
	
			var responseObj=JSON.parse(xhr.responseText);
			console.log(xhr.responseText);
			if(responseObj.Role=="User"){
			console.log("success");
			console.log(signupObj);
				window.location.href="./FlightBooking.html";
			}
			else{
					alert("User already exist");
				document.getElementById("userName").value="";
	document.getElementById("password").value="";
	document.getElementById("PhoneNumber").value="";
	document.getElementById("ConfirmPassword").value="";
			}
			
		}
	}
	var Username=document.getElementById("userName").value;
	var Password=document.getElementById("password").value;
	var confirmPassword=document.getElementById("ConfirmPassword").value;
	var PhoneNumber=document.getElementById("PhoneNumber").value;
	
	var signupObj={};
	signupObj.name=Username;
	signupObj.phoneNumber=PhoneNumber;
	signupObj.password=Password;
	signupObj.confirmPassword=confirmPassword;
		console.log(signupObj);
	xhr.open("POST","./signup");
	xhr.setRequestHeader("Content-Type","application/json");
	xhr.send(JSON.stringify(signupObj));
	}
	else{
	document.getElementById("userName").value="";
	document.getElementById("password").value="";
	document.getElementById("PhoneNumber").value="";
	document.getElementById("ConfirmPassword").value="";
	}
	
}