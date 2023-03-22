/**
 * 
 */
function check(){
	

	var depart=document.getElementById("departure").value;
	var arrive=document.getElementById("arrival").value;
	var DepDate=document.getElementById("DepDate").value;
	var ArrDate=document.getElementById("ArrDate").value;
	console.log(DepDate)
	return depart!=null && arrive!=null  && DepDate!="" ;

}



function valueChecker(){
	var depart=document.getElementById("departure").value;
	var arrive=document.getElementById("arrival").value;
	if(depart==arrive){
		alert("Departure and Arrival Can't be same");
	}
}
let Trip;

 window.onload=function(){
	$("#middle-shrink").slideDown();
	console.log("hia"+ document.cookie)
	if(document.cookie!="SessionId="){
		console.log("hia if")
			var day=new Date();
var date=day.getDate();
var Month=day.getMonth()+1;
var Year=day.getFullYear();
if(date<10){
	date="0"+date;
}
if(Month<10){
	Month="0"+Month;
}
day=Year+"-"+Month+"-"+date;
document.getElementById("DepDate").setAttribute("min",day);
document.getElementById("DepDate").setAttribute("onchange","changeDate(0)")
document.getElementById("ArrDate").setAttribute("min",day);
		document.getElementById("ArrDate").style.display="none";
		document.getElementById("addflight").innerHTML="";
		document.getElementById("count").style.display="none";
		document.getElementById("departure").value="";
document.getElementById("arrival").value="";
	document.getElementById("DepDate").value="";
	document.getElementById("ArrDate").value="";

Trip="OneWay";
	}else{
		console.log("hia else")
		window.location.href="./login.html";
	}

}




let multi=0;
 function tripMode(trip){
	
			document.getElementById("bottom-shrink").innerHTMl="";

	if(trip=="oneway"){
		console.log("first")
		Trip="OneWay";
		multi=0;
		const parentDiv = document.getElementById("total");
const childDivs = parentDiv.children;

for (let i = childDivs.length - 1; i > 0; i--) {
	console.log(childDivs);
  parentDiv.removeChild(childDivs[i]);
}
	document.getElementById("departure").style.width="33%";
	document.getElementById("arrival").style.width="33%";
	document.getElementById("DepDate").style.width="33%";
	document.getElementById("ArrDate").style.display="none";
		document.getElementById("addflight").innerHTML="";

	}
	else if(trip=="RoundTrip"){
		console.log("first")
			Trip="RoundTrip";
		multi=0;
		const parentDiv = document.getElementById("total");
const childDivs = parentDiv.children;

for (let i = childDivs.length - 1; i > 0; i--) {
	console.log(childDivs);
  parentDiv.removeChild(childDivs[i]);
}
		document.getElementById("departure").style.width="24%";
	document.getElementById("arrival").style.width="24%";
	document.getElementById("DepDate").style.width="24%";
	document.getElementById("ArrDate").style.display="inline-flex";
			document.getElementById("addflight").innerHTML="";


	}
	else if(trip=="MultiCity"){
		N=0;
		console.log("first")
		Trip="MultiCity";

		multi=5;
		document.getElementById("departure").style.width="33%";
	document.getElementById("arrival").style.width="33%";
	document.getElementById("DepDate").style.width="33%";
	document.getElementById("ArrDate").style.display="none";
		document.getElementById("addflight").innerHTML=" Add Flight";
		

	}
}


let arr=["Chennai","Thoothukudi","Madurai","Kayathar","Coimbatore","Salem","Vellore","Puducherry"];

let N=0;
function addFlight(){
	multi-=1;
if(multi>0){
	var BookingBox=document.createElement("div");
	BookingBox.setAttribute("class","Booking-Box");
	
	document.getElementById("total").appendChild(BookingBox);
	console.log("get")
	
		var selectFrom=document.createElement("select");
		selectFrom.setAttribute("class","departure");
		BookingBox.appendChild(selectFrom);
		for(let i=0;i<arr.length;i++){
			var Option=document.createElement("option");
			Option.setAttribute("value",""+arr[i]);
			Option.innerText=""+arr[i];
			selectFrom.appendChild(Option);
		}
	
	
	
	
		
	
		var selectTo=document.createElement("select");
		selectTo.setAttribute("class","arrival");
		BookingBox.appendChild(selectTo);
		for(let i=0;i<arr.length;i++){
			var Option=document.createElement("option");
			Option.setAttribute("value",""+arr[i]);
			Option.innerText=""+arr[i];
			selectTo.appendChild(Option);
		}
var day=new Date();
var date=day.getDate()+N;
var Month=day.getMonth()+1;
var Year=day.getFullYear()+N;
if(date<10){
	date="0"+date;
}
if(Month<10){
	Month="0"+Month;
}
day=Year+"-"+Month+"-"+date;
		var depDate=document.createElement("input");
		depDate.setAttribute("class","DepDate");
		depDate.setAttribute("type","date");
		depDate.setAttribute("min",day);
		N++;
		depDate.setAttribute("onchange","changeDate("+N+")");
		BookingBox.appendChild(depDate);
	
	var close=document.createElement("div");
	close.setAttribute("class","x");
	close.setAttribute("onclick","exit(this.parentNode)");
	BookingBox.appendChild(close);
	close.innerHTML="X";
	
}
if(multi==1){
				document.getElementById("addflight").innerHTML="";

}
}

function changeDate(e){
	var prevdate=document.getElementsByClassName("DepDate")[e].value;
	var test=document.getElementsByClassName("DepDate")[e+1]? console.log("True"):console.log("false");
	
}
function exit(e){
	e.remove();
	multi+=1;
			document.getElementById("addflight").innerHTML=" Add Flight";

}

var x=0;
function count(){
	if(x==1){
		document.getElementById("count").style.display="flex";
		x=0;
	}
	else{
		document.getElementById("count").style.display="none";
		x=1;
	}
	
}


var FlightName,DepartureCity,ArrivalCity,DepartureTime,ArrivalTime,NumberOfSeats,Price;
	var PriceForSeat,FlightId;

var ObjectArray=[];
var FlightArray=[];
var Index=0;


function search(){
		 PriceForSeat=0;
Index=0;
	if(check()){
			var bottomshrink=document.getElementById("bottom-shrink");
    bottomshrink.innerHTML="";
	var xhr=new XMLHttpRequest();
	var depart=document.getElementById("departure").value;
	var arrive=document.getElementById("arrival").value;
	var DepDate1=document.getElementById("DepDate").value;
	console.log(DepDate1)
	var ArrDate1;
	if(Trip=="RoundTrip"){
		ArrDate1=document.getElementById("ArrDate").value;
	}
	else{
		ArrDate1=DepDate1;
	}
	
	console.log(depart,arrive,DepDate,ArrDate,Trip);
	xhr.onreadystatechange=function(){
		console.log(this.readyState)
		if(this.readyState==4 && this.status==200){
		var ResObj=JSON.parse(xhr.responseText);
		console.log(ResObj);
			for(let i=0;i<ResObj.length;i++){
			FlightName=ResObj[i].Flight;
			DepartureCity=ResObj[i].Departure;
			ArrivalCity=ResObj[i].Arrival;
			DepartureTime=ResObj[i].DepartureTime;
			ArrivalTime=ResObj[i].ArrivalTime;
			NumberOfSeats=ResObj[i].NoOfSeats;
			Price=ResObj[i].Price;
			FlightId=ResObj[i].FlightNumber;
			var flightobj={};
			flightobj.FlightName=ResObj[i].Flight;
			flightobj.DepartureCity=ResObj[i].Departure;
			flightobj.ArrivalCity=ResObj[i].Arrival;
			flightobj.DepartureTime=ResObj[i].DepartureTime;
			flightobj.ArrivalTime=ResObj[i].ArrivalTime;
			flightobj.NumberOfSeats=ResObj[i].NoOfSeats;
			flightobj.Price=ResObj[i].Price;
			flightobj.Trip=Trip;
			flightobj.DepartureDate=DepDate;
			flightobj.ArrivalDate=ArrDate;
			PriceForSeat+=Number(Price);
			number=adultcount+childcount;
			flightobj.Travalers=number;
			FlightArray[i]=flightobj;
			
			if(Trip=="OneWay"){
				console.log(Trip)
				generateFlight();
			}
			else{
					console.log(Trip)
				generateFlightfor();
					
			}
	}
	if(Trip=="RoundTrip" || Trip=="MultiCity" ){
		generateFlightForRateInfo();
	}
	}
	}
	ObjectArray=[];
	let Length=document.getElementsByClassName("departure").length
	for(let i=0;i<Length;i++){
	var departclass=document.getElementsByClassName("departure")[i].value;
	var arriveclass=document.getElementsByClassName("arrival")[i].value;
	var DepDate=document.getElementsByClassName("DepDate")[i].value;
	var ArrDate=DepDate;
	
	var TempObject={};
	TempObject.departure=departclass;
	TempObject.arrival=arriveclass;
	TempObject.Trip=Trip;
	number=adultcount+childcount;
	TempObject.Travaler=number;
	TempObject.DepartureDate=DepDate;
	TempObject.ArrivalDate=ArrDate;
	ObjectArray[i]=TempObject;
	}
	if(Trip=="RoundTrip"){
	var arriveclass=document.getElementsByClassName("departure")[0].value;
	var departclass=document.getElementsByClassName("arrival")[0].value;
	var DepDate=document.getElementsByClassName("DepDate")[0].value;
	var ArrDate=document.getElementsByClassName("ArrDate")[0].value;
	
	var TempObject={};
	TempObject.departure=departclass;
	TempObject.arrival=arriveclass;
	TempObject.Trip=Trip;
	number=adultcount+childcount;
	TempObject.Travaler=number;
	TempObject.DepartureDate=DepDate;
	TempObject.ArrivalDate=ArrDate;
	ObjectArray[1]=TempObject;
	
		var bottomshrink=document.getElementById("bottom-shrink");
		
    
	var bottomInner=document.createElement("div");
	bottomInner.setAttribute("id","bottom-inner");
	bottomshrink.appendChild(bottomInner);

	var flightinfo=document.createElement("div");
	flightinfo.setAttribute("class","flightInfo");
	bottomInner.appendChild(flightinfo);
	}
	else if(Trip=="MultiCity"){
		var bottomshrink=document.getElementById("bottom-shrink");
		
    
	var bottomInner=document.createElement("div");
	bottomInner.setAttribute("id","bottom-inner");
	bottomshrink.appendChild(bottomInner);

	var flightinfo=document.createElement("div");
	flightinfo.setAttribute("class","flightInfo");
	bottomInner.appendChild(flightinfo);
	}
	console.log(ObjectArray);
	
	
xhr.open("POST","./User/FlightDetails");
xhr.setRequestHeader("Content-Type","application/json");
xhr.send(JSON.stringify(ObjectArray));
	}
	else{
		document.getElementById("departure").value="";
document.getElementById("arrival").value="";
	document.getElementById("DepDate").value="";
	document.getElementById("ArrDate").value="";
	alert("input fields can't be empty");
	}






	
}


function generateFlight(){
	
	var bottomshrink=document.getElementById("bottom-shrink");
    
	var bottomInner=document.createElement("div");
	bottomInner.setAttribute("id","bottom-inner");
	bottomshrink.appendChild(bottomInner);

	var flightinfo=document.createElement("div");
	flightinfo.setAttribute("class","flightInfo");
	bottomInner.appendChild(flightinfo);
//	flightinfo.style.flexDirection="";

	var TimeInfo=document.createElement("div");
	TimeInfo.setAttribute("class","TimeInfo");
	flightinfo.appendChild(TimeInfo);
	

	var TimeTop=document.createElement("div");
	TimeTop.setAttribute("class","TimeTop");
	TimeInfo.appendChild(TimeTop);


	var flightSymbol=document.createElement("div");
	flightSymbol.setAttribute("class","flightSymbol");
	TimeTop.appendChild(flightSymbol);
	flightSymbol.innerHTML="<i class='fa-solid fa-jet-fighter-up'></i>";
	var FlightNameBox=document.createElement("div");
	FlightNameBox.setAttribute("class","FlightNameBox");
	flightSymbol.appendChild(FlightNameBox);
	FlightNameBox.innerHTML=FlightName;
	var FlightIdBox=document.createElement("div");
	FlightIdBox.setAttribute("class","FlightNameBox");
	FlightIdBox.innerHTML=FlightId;
	flightSymbol.appendChild(FlightIdBox);
	

	var FromTime=document.createElement("div");
	FromTime.setAttribute("class","FromTime");
	TimeTop.appendChild(FromTime);
	FromTime.innerHTML+=DepartureTime;
	var FromTimeBox=document.createElement("div");
	FromTimeBox.setAttribute("class","FromTimeBox");
	FromTime.appendChild(FromTimeBox);
	FromTimeBox.innerHTML=DepartureCity;

	var bar=document.createElement("div");
	bar.setAttribute("class","bar");
	TimeTop.appendChild(bar);
	var timeDiff=document.createElement("div");
	timeDiff.setAttribute("class","timediff");
	bar.appendChild(timeDiff);
	timeDiff.innerHTML+=""+TimeDifference();
	var hr=document.createElement("hr");
	bar.appendChild(hr);
	

	var ToTime=document.createElement("div");
	ToTime.setAttribute("class","ToTime");
	TimeTop.appendChild(ToTime);
	ToTime.innerHTML=ArrivalTime;
	var ToTimeBox=document.createElement("div");
	ToTimeBox.setAttribute("class","ToTimeBox");
	ToTime.appendChild(ToTimeBox);
	ToTimeBox.innerHTML=ArrivalCity;


	var rateInfo=document.createElement("div");
	rateInfo.setAttribute("class","rateInfo");
	flightinfo.appendChild(rateInfo);

	var suitcase=document.createElement("div");
	suitcase.setAttribute("class","suitcase");
	rateInfo.appendChild(suitcase);
	suitcase.innerHTML+="<i class='fa-solid fa-suitcase-rolling'></i>"+
	"<p class='p'>Included: carry-on bag, checked bag</p>";


	var inr=document.createElement("div");
	inr.setAttribute("class","inr");
	rateInfo.appendChild(inr);
	var head=document.createElement("h3");
	
	inr.appendChild(head);
	head.innerHTML+="INR"+Price;

	var seeFlight=document.createElement("div");
	seeFlight.setAttribute("class","seeFlight");
	
	seeFlight.setAttribute("onclick","seeFlight("+Index+")");
	Index+=1;
	rateInfo.appendChild(seeFlight);
	seeFlight.innerHTML="See the flight";
}
function logout(){
	var currentUser = document.cookie.split("=");
	console.log(currentUser[1]);
	var xhr=new XMLHttpRequest();
	xhr.onreadystatechange=function(){
		console.log(this.readyState)
		if(this.readyState==4 && this.status==200){
			var responseObj = JSON.parse(xhr.responseText);
		  if(responseObj.Message == "User Logged Out")
		  {
			document.cookie = "SessionId="
			window.location.href="./login.html";
		  }

		}
	}
	xhr.open("POST","./User/Logout");
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xhr.send("SessionId="+currentUser[1]);
	
}

function TimeDifference(){
	var arr1=DepartureTime.split(":");
	var arr2=ArrivalTime.split(":");
	var diff="";
	var hr=Math.abs(Number(arr2[0])-Number(arr1[0]));
	var mn=Math.abs(Number(arr2[1])-Number(arr1[1]));
	var sc=Math.abs(Number(arr2[2])-Number(arr1[2]));
	diff+= hr+":"+mn+":"+sc;
	console.log(Math.abs(3-5))
	return diff;
}
	
var number=0;
var adultcount=1;
var childcount=0;

function adultplus(){
  if(number<9){
     adultcount++;
    number=adultcount+childcount;
  console.log(number)
  document.getElementById("number").innerHTML=adultcount;
     document.getElementById("totalcount").innerHTML=number+" Travalers";
      document.getElementById("counter").innerHTML=number+" Travalers";

  }
 
}
function adultminus(){
  if(number>0 && adultcount>1){
    adultcount--;
    number=adultcount+childcount;
    console.log(number)
    document.getElementById("number").innerHTML=adultcount;
    document.getElementById("totalcount").innerHTML=number+" Travalers";
      document.getElementById("counter").innerHTML=number+" Travalers";

  }
}
  function childplus(){
  if(number<9){
      childcount++;
    number=adultcount+childcount;
  console.log(number)
  document.getElementById("childNumber").innerHTML=childcount;
     document.getElementById("totalcount").innerHTML=number+" Travalers";
      document.getElementById("counter").innerHTML=number+" Travalers";
  }
 
}
function childminus(){
  if(number>0 && childcount>0){
    childcount--;
    number=adultcount+childcount;
    console.log(number)
    document.getElementById("childNumber").innerHTML=childcount;
     document.getElementById("totalcount").innerHTML=number+" Travalers";
      document.getElementById("counter").innerHTML=number+" Travalers";

  }

}

function done(){
  document.getElementById("count").style.display="none";
  alert(childcount+","+adultcount);
  x=1;
}












function generateFlightfor(){
	
	
    
	var flightinfo=document.getElementsByClassName("flightInfo")[0];
	flightinfo.style.flexDirection="column";
	var TimeInfo=document.createElement("div");
	TimeInfo.setAttribute("class","TimeInfo");
	flightinfo.appendChild(TimeInfo);
	

	var TimeTop=document.createElement("div");
	TimeTop.setAttribute("class","TimeTop");
	TimeInfo.appendChild(TimeTop);


	var flightSymbol=document.createElement("div");
	flightSymbol.setAttribute("class","flightSymbol");
	TimeTop.appendChild(flightSymbol);
	flightSymbol.innerHTML="<i class='fa-solid fa-jet-fighter-up'></i>";
	var FlightNameBox=document.createElement("div");
	FlightNameBox.setAttribute("class","FlightNameBox");
	flightSymbol.appendChild(FlightNameBox);
	FlightNameBox.innerHTML=FlightName;
	var FlightIdBox=document.createElement("div");
	FlightIdBox.setAttribute("class","FlightNameBox");
	FlightIdBox.innerHTML=FlightId;
	flightSymbol.appendChild(FlightIdBox);
	

	var FromTime=document.createElement("div");
	FromTime.setAttribute("class","FromTime");
	TimeTop.appendChild(FromTime);
	FromTime.innerHTML+=DepartureTime;
	var FromTimeBox=document.createElement("div");
	FromTimeBox.setAttribute("class","FromTimeBox");
	FromTime.appendChild(FromTimeBox);
	FromTimeBox.innerHTML=DepartureCity;

	var bar=document.createElement("div");
	bar.setAttribute("class","bar");
	TimeTop.appendChild(bar);
	var timeDiff=document.createElement("div");
	timeDiff.setAttribute("class","timediff");
	bar.appendChild(timeDiff);
	timeDiff.innerHTML+=""+TimeDifference();
	var hr=document.createElement("hr");
	bar.appendChild(hr);
	

	var ToTime=document.createElement("div");
	ToTime.setAttribute("class","ToTime");
	TimeTop.appendChild(ToTime);
	ToTime.innerHTML=ArrivalTime;
	var ToTimeBox=document.createElement("div");
	ToTimeBox.setAttribute("class","ToTimeBox");
	ToTime.appendChild(ToTimeBox);
	ToTimeBox.innerHTML=ArrivalCity;


	
}

function generateFlightForRateInfo(){
	var flightinfo=document.getElementsByClassName("flightInfo")[0];
	var rateInfo=document.createElement("div");
	rateInfo.setAttribute("class","rateInfo");
	rateInfo.style.alignSelf="flex-end";
	rateInfo.style.marginRight="26%";
	
	flightinfo.appendChild(rateInfo);

	var suitcase=document.createElement("div");
	suitcase.setAttribute("class","suitcase");
	rateInfo.appendChild(suitcase);
	suitcase.innerHTML+="<i class='fa-solid fa-suitcase-rolling'></i>"+
	"<p class='p'>Included: carry-on bag, checked bag</p>";


	var inr=document.createElement("div");
	inr.setAttribute("class","inr");
	rateInfo.appendChild(inr);
	var head=document.createElement("h3");
	
	inr.appendChild(head);
	head.innerHTML+="INR"+PriceForSeat;

	var seeFlight=document.createElement("div");
	seeFlight.setAttribute("class","seeFlight");
	seeFlight.setAttribute("onclick","seeFlight("+Index+")");
	rateInfo.appendChild(seeFlight);
	seeFlight.innerHTML="See the flight";
}


function seeFlight(i){
	var xhr=new XMLHttpRequest();
	xhr.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			window.location.href="./Booking.html"
		}
	}
	var temparr=[];
	if(Trip=="OneWay"){
	console.log(FlightArray[i]);
	temparr[0]=FlightArray[i];
}
	else{
		console.log(FlightArray);
		temparr=FlightArray;
	}
	xhr.open("POST","./User/SelectTheFlight");
	xhr.setRequestHeader("Content-Type","application/json");
	xhr.send(JSON.stringify(temparr));
	


}








