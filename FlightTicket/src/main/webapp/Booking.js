var Departure,Arrival,DepartTime,ArrivalTime,FlightName,Trip,Travalers,Capacity,DepartureDate,ArrivalDate,FlightId,NumberOfFlights;
var Price=0;
var SelectedSeat;
var DepArr=[];

var count=0;
let travalerCount=0;

window.onload=function(){
if(document.cookie!="SessionId="){
	//console.log( document.getElementById("WhoFlying"))
    document.getElementById("WhoFlying").style.display="none";
    document.getElementById("checkAndPay").style.display="none";
	var xhr=new XMLHttpRequest();
	xhr.onreadystatechange=function(){
			console.log(this.readyState,this.status)
		if(this.readyState==4 && this.status==200){
			TripDetails();
		console.log(xhr.responseText);
			var respObj=JSON.parse(xhr.responseText);
			for(let i=0;i<respObj.length;i++){
				FlightId=respObj[i].FlightId;
				FlightName=respObj[i].FlightName;
				Departure=respObj[i].Departure;
				Arrival=respObj[i].Arrival;
				DepartTime=respObj[i].DepartureTime;
				ArrivalTime=respObj[i].ArrivalTime;
				Trip=respObj[i].Trip;
				Price=Price+respObj[i].Price;
				Capacity=respObj[i].FlightCapacity;
				Travalers=Number(respObj[i].Travalers);
				travalerCount=Travalers;
				DepartureDate=respObj[i].DepartureDate;
				ArrivalDate=respObj[i].ArrivalDate;
				SelectedSeat=respObj[i].Bookedseats;
				var DepArrObj={};
				DepArrObj.FlightId=FlightId;
				DepArrObj.Departure=Departure;
				DepArrObj.Arrival=Arrival;
				DepArrObj.DepartureDate=DepartureDate;
				DepArrObj.ArrivalDate=ArrivalDate;
				DepArrObj.seats=respObj[i].seat;
				DepArrObj.Bookedseats=SelectedSeat;
				
				DepArr[i]=DepArrObj;
				getSeatInfo();
				count++;
			}
			TripDetailsInnerHtml()
            TotalCredentials("InnerTripInfo")
		}
	}
	xhr.open("POST","./User/ShowFlightToBookSeat");
	xhr.setRequestHeader("Content-Type","application/json");
	xhr.send();
	}else{
		window.location.href="./login.html";
	}
}

var locationsCity="";
function localCity(){
	for(let i=0;i<DepArr.length;i++){
		locationsCity+=DepArr[i].Departure+" -> "+DepArr[i].Arrival+". ";
	}
}
function TripDetails(){
 var InnerTripSelectYourSeet=document.getElementById("InnerTripSelectYourSeet");
	
	var tripDetails=document.createElement("div");
	tripDetails.setAttribute("class","tripDetails");
	InnerTripSelectYourSeet.appendChild(tripDetails);
	
	var TripName=document.createElement("div");
	TripName.setAttribute("class","TripName");
	tripDetails.appendChild(TripName);
	
	var TravalerCount=document.createElement("div");
	TravalerCount.setAttribute("class","TravalerCount");
	tripDetails.appendChild(TravalerCount);
	
	var DepartureDate=document.createElement("div");
	DepartureDate.setAttribute("class","DepartureDate");
	tripDetails.appendChild(DepartureDate);
	
	var ArraivalDate=document.createElement("div");
	ArraivalDate.setAttribute("class","ArraivalDate");
	tripDetails.appendChild(ArraivalDate);
	 
	 var CityName=document.createElement("div");
	CityName.setAttribute("class","CityName");
	InnerTripSelectYourSeet.appendChild(CityName);
	
	var para=document.createElement("div");
	para.setAttribute("class","para");
	InnerTripSelectYourSeet.appendChild(para);
	para.innerHTML="Select Yout Seat";
	 
	
}
function TripDetailsInnerHtml(){
	document.getElementsByClassName("TripName")[0].innerHTML=Trip;
	console.log(Travalers)
	document.getElementsByClassName("TravalerCount")[0].innerHTML=Travalers+" Travalers";
	document.getElementsByClassName("DepartureDate")[0].innerHTML=DepArr[0].DepartureDate;
	document.getElementsByClassName("ArraivalDate")[0].innerHTML=DepArr[DepArr.length-1].ArrivalDate;
	localCity();
	document.getElementsByClassName("CityName")[0].innerHTML=locationsCity;
	

}

function getSeatInfo(){
   var InnerTripSelectYourSeet=document.getElementById("InnerTripSelectYourSeet");



    var SelectYourSeat=document.createElement("div");
    SelectYourSeat.setAttribute("class","SelectYourSeat");
    SelectYourSeat.setAttribute("onclick","DropDownFlightSeatInfo("+count+",'"+Capacity+"')");
    InnerTripSelectYourSeet.appendChild(SelectYourSeat);
    
    var From_To=document.createElement("div");
    From_To.setAttribute("class","From-To");
    SelectYourSeat.appendChild(From_To);
    From_To.innerHTML=Departure+"-"+Arrival;
    
    var Time_Flight=document.createElement("div");
    Time_Flight.setAttribute("class","Time-Flight");
    SelectYourSeat.appendChild(Time_Flight);
    Time_Flight.innerHTML=DepartTime+"-"+FlightName;
    
    
    var SeatStatus_Price=document.createElement("div");
    SeatStatus_Price.setAttribute("class","SeatStatus-Price");
    SelectYourSeat.appendChild(SeatStatus_Price);
    SeatStatus_Price.innerHTML="0 seat selected";
    
    var SeeSeat=document.createElement("div");
    SeeSeat.setAttribute("class","SeeSeat");
    SelectYourSeat.appendChild(SeeSeat);
    SeeSeat.innerHTML="Change seat selection";
    
    var DropDown=document.createElement("div");
    DropDown.setAttribute("class","DropDown");
    InnerTripSelectYourSeet.appendChild(DropDown);

    var seatInfo=document.createElement("div");
    seatInfo.setAttribute("class","seatInfo");
    DropDown.appendChild(seatInfo);
    seatInfo.style.display="none";


var TravalerandSeatSelect=document.createElement("div");
TravalerandSeatSelect.setAttribute("class","TravalerandSeatSelect");
seatInfo.appendChild(TravalerandSeatSelect);
var TravalerandSeatSelectchild1=document.createElement("div");
TravalerandSeatSelectchild1.setAttribute("class","TravalerandSeatSelectchild1");
TravalerandSeatSelect.appendChild(TravalerandSeatSelectchild1);
TravalerandSeatSelectchild1.innerHTML=Travalers+" Travalers";
var TravalerandSeatSelectchild2=document.createElement("div");
TravalerandSeatSelectchild2.setAttribute("class","TravalerandSeatSelectchild2");
TravalerandSeatSelect.appendChild(TravalerandSeatSelectchild2);
TravalerandSeatSelectchild2.innerHTML="No seat selected";

var availableSeat=document.createElement("div");
availableSeat.setAttribute("class","availableSeat");
seatInfo.appendChild(availableSeat);
var availableSeatchild1=document.createElement("div");
availableSeatchild1.setAttribute("class","availableSeatchild1");
availableSeat.appendChild(availableSeatchild1);
var availableSeatchild2=document.createElement("div");
availableSeatchild2.setAttribute("class","availableSeatchild2");
availableSeat.appendChild(availableSeatchild2);
availableSeatchild2.innerHTML="Available Seats";


var unavailableSeat=document.createElement("div");
unavailableSeat.setAttribute("class","unavailableSeat");
seatInfo.appendChild(unavailableSeat);
var unavailableSeatchild1=document.createElement("div");
unavailableSeatchild1.setAttribute("class","unavailableSeatchild1");
unavailableSeat.appendChild(unavailableSeatchild1);
unavailableSeatchild1.innerHTML='<i class="fa-solid fa-xmark"></i>';
var unavailableSeatchild2=document.createElement("div");
unavailableSeatchild2.setAttribute("class","unavailableSeatchild2");
unavailableSeat.appendChild(unavailableSeatchild2);
unavailableSeatchild2.innerHTML="Unavailable seat";


var selectedSeat=document.createElement("div");
selectedSeat.setAttribute("class","selectedSeat");
seatInfo.appendChild(selectedSeat);
var selectedSeatchild1=document.createElement("div");
selectedSeatchild1.setAttribute("class","selectedSeatchild1");
selectedSeat.appendChild(selectedSeatchild1);
selectedSeatchild1.innerHTML='<i class="fa-solid fa-check"></i>';
var selectedSeatchild2=document.createElement("div");
selectedSeatchild2.setAttribute("class","selectedSeatchild2");
selectedSeat.appendChild(selectedSeatchild2);
selectedSeatchild2.innerHTML="Selected seats";





















    var DropFlightSeat=document.createElement("div");
    DropFlightSeat.setAttribute("class","DropFlightSeat");
    DropFlightSeat.setAttribute("id","DropFlightSeat");
    DropDown.appendChild(DropFlightSeat);
	
	
}
var FlightArr1=[];
var FlightArr2=[];
var FlightArr3=[];
var FlightArr4=[];
var FlightArr5=[];
var listOfSelectedSeats=[FlightArr1,FlightArr2,FlightArr3,FlightArr4,FlightArr5];
var x=1;
function DropDownFlightSeatInfo(e,size){
	  // tempFunction();
	var seatArray=DepArr[e].seats.split(",");
	var FlightID=DepArr[e].FlightId;
	var SelectedSeatArray=DepArr[e].Bookedseats.split(",");
	//console.log(SelectedSeatArray)
	console.log(SelectedSeat);
	var seatInfo=document.getElementsByClassName("seatInfo")[e];
if(x==1){
    x-=1;
   var  DropFlightSeat=document.getElementsByClassName("DropFlightSeat")[e];
   seatInfo.style.display="block";
seatInfo.style.width="400px";
seatInfo.style.height="400px";
    if(size=="small"){
        DropFlightSeat.style.width="263px";
        DropFlightSeat.style.height="1000px"

var column=["A","B","@","D","F"];
var row=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20];
var temp=0;
if( DropFlightSeat.innerHTML==""){
	for(let i=0;i<row.length;i++){
    for(let j=0;j<column.length;j++){
        var Box=document.createElement("div");
               Box.setAttribute("class","Box "+column[j]+row[i]);

        if(SelectedSeatArray.includes(column[j]+row[i])){
            Box.innerHTML='<i class="fa-solid fa-xmark"></i>';
             Box.style.color="white";
            Box.style.backgroundColor="red";
        }else{
            if(j==Math.floor(column.length/2) && i!=0){
                Box.innerHTML=row[i];
                Box.style.borderColor="white";
            }
            if(i==0){
                Box.innerHTML=column[j];
                Box.style.borderColor="white";
        }
         if(j!=Math.floor(column.length/2) && i!=0){
        Box.setAttribute("value",""+seatArray[temp]);
        Box.style.backgroundColor="#2e6de1";
        Box.setAttribute("onclick","Box('"+seatArray[temp]+"','"+FlightID+"',"+e+",this,"+Travalers+",'unbooked')");
        temp++;

    }
         }
       
       DropFlightSeat.appendChild(Box)
    }
   
}
		document.getElementsByClassName("SelectYourSeat")[e].setAttribute("onclick","summa("+e+")");


}
    }
    else if(size=="medium"){
        DropFlightSeat.style.width="351px";
        DropFlightSeat.style.height="1450px";
        var column=["A","B","C","@","D","E","F"];
var row=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30];


if( DropFlightSeat.innerHTML==""){
	var temp=0;
	for(let i=0;i<row.length;i++){
    for(let j=0;j<column.length;j++){
        var Box=document.createElement("div");
        Box.setAttribute("class","Box "+column[j]+row[i]);
       
        if(SelectedSeatArray.includes(column[j]+row[i])){
            Box.innerHTML='<i class="fa-solid fa-xmark"></i>';
             Box.style.color="white";
            Box.style.backgroundColor="red";
        }else{
            if(j==Math.floor(column.length/2) && i!=0){
                Box.innerHTML=row[i];
                Box.style.borderColor="white";
            }
            if(i==0){
                Box.innerHTML=column[j];
                Box.style.borderColor="white";
        }
         if(j!=Math.floor(column.length/2) && i!=0){
        
        Box.style.backgroundColor="#2e6de1";
        Box.setAttribute("onclick","Box('"+seatArray[temp]+"','"+FlightID+"',"+e+",this,"+Travalers+",'unbooked')");
         Box.setAttribute("value",""+seatArray[temp]);
        temp++;

    }
         }
       
       DropFlightSeat.appendChild(Box)
     
    }
   
}

}

    }else if(size=="large"){
	
        DropFlightSeat.style.width="351px";
        DropFlightSeat.style.height="2000px";
        var column=["A","B","C","@","D","E","F"];
var row=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40];


if( DropFlightSeat.innerHTML==""){
	var temp=0;
	for(let i=0;i<row.length;i++){
    for(let j=0;j<column.length;j++){
        var Box=document.createElement("div");
               Box.setAttribute("class","Box "+column[j]+row[i]);

         if(SelectedSeatArray.includes(column[j]+row[i])){
            Box.innerHTML='<i class="fa-solid fa-xmark"></i>';
            Box.style.color="white";
            Box.style.backgroundColor="red";
              temp++;
        }else{
            if(j==Math.floor(column.length/2) && i!=0){
                Box.innerHTML=row[i];
                Box.style.borderColor="white";
            }
            if(i==0){
                Box.innerHTML=column[j];
                Box.style.borderColor="white";
        }
         if(j!=Math.floor(column.length/2) && i!=0){
        
        Box.style.backgroundColor="#2e6de1";
        Box.setAttribute("onclick","Box('"+seatArray[temp]+"','"+FlightID+"',"+e+",this,"+Travalers+",'unbooked')");
         Box.setAttribute("value",""+seatArray[temp]);
        temp++;

    }
         }
       
       DropFlightSeat.appendChild(Box)
     
    }
    }
 		document.getElementsByClassName("SelectYourSeat")[e].setAttribute("onclick","summa("+e+")");

}

}
}
else{
    x+=1;
   var  DropFlightSeat=document.getElementsByClassName("DropFlightSeat")[e];
    DropFlightSeat.innerHTML="";
    DropFlightSeat.style.width="0px";
    DropFlightSeat.style.height="0px";
    seatInfo.style.width="0px";
seatInfo.style.height="0px";
seatInfo.style.display="none";
 
}
  
}


function TotalCredentials(e){
 var InnerTripSelectYourSeet;
if(e=="InnerTripInfo"){
	InnerTripSelectYourSeet=document.getElementById("InnerTripSelectYourSeet");
}else if(e=="WhoFlying"){
		InnerTripSelectYourSeet=document.getElementById("WhoFlying");
}
else if(e=="checkAndPay"){
		InnerTripSelectYourSeet=document.getElementById("checkAndPay");
}
    var TotalCredentials=document.createElement("div");
    TotalCredentials.setAttribute("class","TotalCredentials");
    InnerTripSelectYourSeet.appendChild(TotalCredentials);
    TotalCredentials.style.float="right";

    var Tickets=document.createElement("div");
    Tickets.setAttribute("class","Tickets");
    TotalCredentials.appendChild(Tickets);

    var TicketsChild1=document.createElement("div");
    TicketsChild1.setAttribute("class","TicketsChild1");
    Tickets.appendChild(TicketsChild1);
    TicketsChild1.innerHTML="Tickets ("+Travalers+" Travalers)";

    var TicketsChild2=document.createElement("div");
    TicketsChild2.setAttribute("class","TicketsChild2");
    Tickets.appendChild(TicketsChild2);
    TicketsChild2.innerHTML="INR "+Price*Travalers;

    var FlightFare=document.createElement("div");
    FlightFare.setAttribute("class","FlightFare");
    TotalCredentials.appendChild(FlightFare);

    var FlightFare1=document.createElement("div");
    FlightFare1.setAttribute("class","FlightFare1");
    FlightFare.appendChild(FlightFare1);
    FlightFare1.innerHTML="FlightFare ";

    var FlightFare2=document.createElement("div");
    FlightFare2.setAttribute("class","FlightFare2");
    FlightFare.appendChild(FlightFare2);
    FlightFare2.innerHTML="INR "+Price;

    var Total=document.createElement("div");
    Total.setAttribute("class","Total");
    TotalCredentials.appendChild(Total);

    var Total1=document.createElement("div");
    Total1.setAttribute("class","Total1");
    Total.appendChild(Total1);
    Total1.innerHTML="Total";

    var Total2=document.createElement("div");
    Total2.setAttribute("class","Total2");
    Total.appendChild(Total2);
    Total2.innerHTML="INR "+Price*Travalers;

    var next=document.createElement("div");
    next.setAttribute("class","next");
    TotalCredentials.appendChild(next);
if(e=="InnerTripInfo"){
var nextButton=document.createElement("div");
    nextButton.setAttribute("class","nextButton");
    nextButton.setAttribute("onclick","SubmitBooking()")
    next.appendChild(nextButton);
    nextButton.innerHTML="Submit";
}else{
var nextButton=document.createElement("div");
    nextButton.setAttribute("class","nextButton");
    nextButton.setAttribute("onclick","Ok()")
    next.appendChild(nextButton);
    nextButton.innerHTML="Ok";
}

    

//BOX fucntion
}

let temp=0;
function Box(e,Flight,cnt,element,travelSome,BookStatus){
		
	if(travelSome>0){
		if(BookStatus=='unbooked'){
			element.innerHTML='<i class="fa-solid fa-check"></i>';
	element.style.color="white";
	travelSome-=1;
	element.setAttribute("onclick","Box('"+e+"','"+Flight+"',"+cnt+",this,"+travelSome+",'booked')");
	element.style.backgroundColor="green";
	listOfSelectedSeats[cnt][temp]=e;
	listOfSelectedSeats[cnt]=listOfSelectedSeats[cnt].filter(elm => elm);
	console.log(listOfSelectedSeats)
	document.getElementsByClassName("TravalerandSeatSelectchild2")[cnt].innerHTML=""+listOfSelectedSeats[cnt];
	document.getElementsByClassName("SeatStatus-Price")[cnt].innerHTML=""+listOfSelectedSeats[cnt].length+" Seats Selected";
	temp+=1;
	
	tempFunction(cnt,travelSome);
		}
	
	}
		if(BookStatus=='booked'){
			element.innerHTML='';
	element.style.color="black";
	travelSome+=1;
	element.setAttribute("onclick","Box('"+e+"','"+Flight+"',"+cnt+",this,"+travelSome+",'unbooked')");
	element.style.backgroundColor="#2e6de1";
	listOfSelectedSeats[cnt].splice(listOfSelectedSeats[cnt].indexOf(e),1);
	listOfSelectedSeats[cnt]=listOfSelectedSeats[cnt].filter(elm => elm);
	console.log(listOfSelectedSeats)
	document.getElementsByClassName("TravalerandSeatSelectchild2")[cnt].innerHTML=""+listOfSelectedSeats[cnt];
	document.getElementsByClassName("SeatStatus-Price")[cnt].innerHTML=""+listOfSelectedSeats[cnt].length+" Seats Selected";
	temp+=1;
	
	tempFunction(cnt,travelSome);
		}
		
}
function tempFunction(cnt,travelSome){
	var seatArray=DepArr[cnt].seats.split(",");
	console.log(seatArray);
	var FlightID=DepArr[cnt].FlightId;
		for(let i=0;i<seatArray.length;i++){
			if(!listOfSelectedSeats[cnt].includes(seatArray[i])){
				//console.log(document.getElementsByClassName(seatArray[i]).length,cnt,seatArray[i]);
				
				document.getElementsByClassName(seatArray[i])[cnt].setAttribute("onclick","Box('"+seatArray[i]+"','"+FlightID+"',"+cnt+",this,"+travelSome+",'unbooked')");	
			}
				
	}
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
let T=1;
function whoIsFlyig(){
  
    var WhoFlying=document.getElementById("WhoFlying");
   // document.getElementById("WhoFlying").style.display="block";
    var WhoFlyingBox=document.createElement("div");
    WhoFlyingBox.setAttribute("class","WhoFlyingBox");
    WhoFlying.appendChild(WhoFlyingBox);

    var Travaler=document.createElement("div");
    Travaler.setAttribute("class","Travaler");
    WhoFlyingBox.appendChild(Travaler);

    var TrvalerNumber=document.createElement("h1");
    TrvalerNumber.setAttribute("class","TrvalerNumber");
    TrvalerNumber.innerHTML="Travaler "+T;
    T++;
    Travaler.appendChild(TrvalerNumber);

    var Names=document.createElement("div");
    Names.setAttribute("class","Names");
    WhoFlyingBox.appendChild(Names);

    var NameBox1=document.createElement("div");
    NameBox1.setAttribute("class","NameBox");
    Names.appendChild(NameBox1);

    var labelFirstName=document.createElement("label");
    NameBox1.appendChild(labelFirstName);
    labelFirstName.innerHTML="First Name"

    var FirstnameInput=document.createElement("input");
    FirstnameInput.setAttribute("type","text");
    FirstnameInput.setAttribute("placeholder","First Names");
    FirstnameInput.setAttribute("class","FirstName");
    NameBox1.appendChild(FirstnameInput);


    var NameBox2=document.createElement("div");
    NameBox2.setAttribute("class","NameBox");
    Names.appendChild(NameBox2);

    var labelLastName=document.createElement("label");
    NameBox2.appendChild(labelLastName);
   labelLastName.innerHTML="Last Name";

   var LastnameInput=document.createElement("input");
   LastnameInput.setAttribute("type","text");
   LastnameInput.setAttribute("placeholder","Last Names");
   LastnameInput.setAttribute("class","LastName");
   NameBox2.appendChild(LastnameInput);

    var GenderDOB=document.createElement("div");
    GenderDOB.setAttribute("class","GenderDOB");
    WhoFlyingBox.appendChild(GenderDOB);

    var DOBBox1=document.createElement("div");
    DOBBox1.setAttribute("class","DOBBox");
    GenderDOB.appendChild(DOBBox1);

    DOBBox1.innerHTML=' <label>Gender specified on your travel document</label>'+
   ' <select class="Gender">'+
   '<option>Select The Gender</option>'+
    '<option value="Male">Male</option>'+
    '<option value="Female">Female</option>'+
'</select>';

var DOBBox2=document.createElement("div");
DOBBox2.setAttribute("class","DOBBox");
GenderDOB.appendChild(DOBBox2);
DOBBox2.innerHTML='  <label>Date Of Birth</label>'+
'<input type="date" class="DateOfBirth"/>';

var TravelDocumentDetails=document.createElement("div");
TravelDocumentDetails.setAttribute("class","TravelDocumentDetails");
WhoFlyingBox.appendChild(TravelDocumentDetails);
TravelDocumentDetails.innerHTML=' <label>Mobile Number</label>'+
' <input type="text" maxlength="10" class="MobileNumber"/>';

}



var s=0;
function summa(e){
	if(s==0){
		s+=1;
		document.getElementsByClassName("DropDown")[e].style.display="none"
	}
	else{
		s-=1;
		document.getElementsByClassName("DropDown")[e].style.display="flex"
	}
}
function SubmitBooking(){
	document.getElementById("InnerTripInfo").style.display="none";
        document.getElementById("WhoFlying").style.display="block";
            document.getElementById("checkAndPay").style.display="none";

	var xhr=new XMLHttpRequest();
	xhr.open("POST","./User/BookingTable");
	xhr.setRequestHeader("Content-Type","application/json");
	xhr.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
   TotalCredentials("WhoFlying");
   for(let i=0;i<Number(Travalers);i++){
	   whoIsFlyig();

}
		}
	}
	
	var Arrays=[];
	for(let i=0;i<DepArr.length;i++){
		var RequestObj={};
		RequestObj.FlightId=DepArr[i].FlightId;
		if(Trip=="RoundTrip" && i==0){
			RequestObj.DeparturDate=DepArr[i].DepartureDate;
		RequestObj.ArrivalDate=DepArr[i].DepartureDate;
		}
		else if(Trip=="RoundTrip" && i==1){
			RequestObj.DeparturDate=DepArr[i].ArrivalDate;
		RequestObj.ArrivalDate=DepArr[i].ArrivalDate;
		}
		else{
			RequestObj.DeparturDate=DepArr[i].DepartureDate;
		RequestObj.ArrivalDate=DepArr[i].DepartureDate;
		}
		
		RequestObj.SelectedSeat=""+listOfSelectedSeats[i];
		Arrays[i]=RequestObj;
	}
	xhr.send(JSON.stringify(Arrays));
}



function Ok(){
	document.getElementById("InnerTripInfo").style.display="none";
        document.getElementById("WhoFlying").style.display="none";
            document.getElementById("checkAndPay").style.display="block";
	var xhr=new XMLHttpRequest();
	xhr.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			TotalCredentials("checkAndPay");
			checkAndPay();
		}
	}
	var RequestArray=[];
	var cnt=0;
	for(let i=0;i<DepArr.length;i++){
		for(let j=0;j<Number(Travalers);j++){
				var RequestObject={};
		RequestObject.FirstName=document.getElementsByClassName("FirstName")[j].value;
		RequestObject.LastName=document.getElementsByClassName("LastName")[j].value;
		RequestObject.Gender=document.getElementsByClassName("Gender")[j].value;
		RequestObject.DateOfBirth=document.getElementsByClassName("DateOfBirth")[j].value;
		RequestObject.MobileNumber=document.getElementsByClassName("MobileNumber")[j].value;
		RequestObject.SelectedSeat=listOfSelectedSeats[i][j];
		RequestObject.FlightId=DepArr[i].FlightId;
		if(Trip=="RoundTrip" && i==0){
			RequestObject.DeparturDate=DepArr[i].DepartureDate;
		RequestObject.ArrivalDate=DepArr[i].DepartureDate;
		}
		else if(Trip=="RoundTrip" && i==1){
			RequestObject.DeparturDate=DepArr[i].ArrivalDate;
		RequestObject.ArrivalDate=DepArr[i].ArrivalDate;
		}
		else{
			RequestObject.DeparturDate=DepArr[i].DepartureDate;
		RequestObject.ArrivalDate=DepArr[i].ArrivalDate;
		}
		
		RequestArray[cnt]=RequestObject;
		cnt++;
		}
		
	

	}
				console.log(RequestArray);

	xhr.open("POST","./User/PassengerDetails");
	xhr.setRequestHeader("Content-Type","application/json")
	xhr.send(JSON.stringify(RequestArray));
	
}

function checkAndPay(){
	var checkAndPay=document.getElementById("checkAndPay");
	checkAndPay.innerHTML='<div class="PayDiv">'+
 ' <div class="CardNumber">'+
    '<input type="text" maxlength="16" placeholder="enter the cardNumber" id="CardNumber"/>'+
  '</div>'+
  '<div class="ExpCSV">'+
    '<input type="text" placeholder="MM/YY" maxlength="5" id="ExpDate"/>'+
    '<input text="text" placeholder="cvv" maxlength="3" id="Csv"/>'+
  '</div>'+
  '<div class="Pay" onclick="Pay()">Pay</div>'+
'</div>';
	
}
function Pay(){
	console.log("Pay")
var xhr=new XMLHttpRequest();
xhr.onreadystatechange=function(){
	console.log(this.readyState);
	if(this.readyState==4 && this.status==200){
		alert("Success");
		window.location.href="./FlightBooking.html";

	}
}
var PaymentObj={};
PaymentObj.CardNumber=document.getElementById("CardNumber").value;
PaymentObj.ExpDate= document.getElementById("ExpDate").value;
PaymentObj.Csv=document.getElementById("Csv").value;
PaymentObj.Price=""+(Price*Travalers);
xhr.open("POST","./User/PaymentDetails");
xhr.setRequestHeader("Content-Type","application/json");
xhr.send(JSON.stringify(PaymentObj));
	
}





				
				
				
				
				