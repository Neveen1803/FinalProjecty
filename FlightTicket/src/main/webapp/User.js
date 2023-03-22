/**
 * 
 */
 window.onload=function(){
	document.getElementById("mid-see").style.display="none";
			document.getElementById("mid-cancel").style.display="none";

}


var FlightNumber,Flight,Name,Time,date,Seat,Departure,Arrival;


 function ShowBookedTicket(){
		document.getElementById("mid-cancel").style.display="none";

	document.getElementById("mid-see").style.display="block";
	var xhr=new XMLHttpRequest();
	xhr.onreadystatechange=function(){
		console.log(this.readyState);
		if(this.readyState==4 && this.status==200){
						 document.getElementById("mid-see").innerHTML="";
			var respObj=JSON.parse(xhr.responseText);
			console.log(respObj)
				for(let i=0;i<respObj.length;i++){
					FlightNumber=respObj[i].FlightId;
					Flight=respObj[i].FlightName;
					Name=respObj[i].Name;
					Time=respObj[i].DepartureTime;
					date=respObj[i].DepartureDate;
					Seat=respObj[i].SelectedSeat;
					Departure=respObj[i].Departure;
					Arrival=respObj[i].Arrival;
					CreateBook();
				}
		}
	}
	xhr.open("POST","./User/ViewBookedTicket");
	xhr.setRequestHeader("Content-Type","application/json");
	xhr.send();
	

}
let count=0;
var CancelTicketArray=[];
function CancelTicket(){
		document.getElementById("mid-see").style.display="none";
	document.getElementById("mid-cancel").style.display="block";
	var xhr=new XMLHttpRequest();
	xhr.onreadystatechange=function(){
		console.log(this.readyState);
		if(this.readyState==4 && this.status==200){
									 document.getElementById("mid-cancel").innerHTML="";

			var respObj=JSON.parse(xhr.responseText);
			console.log(respObj)
				for(let i=0;i<respObj.length;i++){
					FlightNumber=respObj[i].FlightId;
					Flight=respObj[i].FlightName;
					Name=respObj[i].Name;
					Time=respObj[i].DepartureTime;
					date=respObj[i].DepartureDate;
					Seat=respObj[i].SelectedSeat;
					Departure=respObj[i].Departure;
					Arrival=respObj[i].Arrival;
					let CancelTicketObject={};
					CancelTicketObject.Name=respObj[i].Name;
					CancelTicketObject.PassengerNumber=respObj[i].PassengerNumber;
					CancelTicketObject.SelectedSeat=respObj[i].SelectedSeat;
					CancelTicketObject.BookedUserNumber=respObj[i].BookedUserNumber;
					CancelTicketObject.FlightId=respObj[i].FlightId;
					CancelTicketObject.FlightName=respObj[i].FlightName;
					CancelTicketObject.DepartureDate=respObj[i].DepartureDate;
					CancelTicketObject.Departure=respObj[i].Departure;
					CancelTicketObject.Arrival=respObj[i].Arrival;
					CancelTicketObject.DepartureTime=respObj[i].DepartureTime;
					CancelTicketObject.ArrivalTime=respObj[i].ArrivalTime;
					CancelTicketObject.BookingId=respObj[i].BookingId;
					CancelTicketArray[i]=CancelTicketObject;
					CancelFLight();
					count++;
				}
		}
	}
	xhr.open("POST","./User/ViewBookedTicketToCancel");
	xhr.setRequestHeader("Content-Type","application/json");
	xhr.send();
}
function CreateBook(){
    document.getElementById("mid-see").innerHTML+=
   
   '<div id="Ticket">'+
   '   <div id="TicketLeft">'+
   '    <div class="Top">Boarding Pass</div>'+
   '    <div class="mid">'+
   '      <div id="qr"></div>'+
   '      <div class="TicketDetails">'+
   '          <div class="PassengerDetails">'+
   '            <div class="Name">'+
   '            <div id="LableName">Name of The Passenger</div>'+
   '            <div id="Name" class="Value">'+Name+'</div>'+
   '            </div>'+
   '            <div Class="Flight">'+
   '              <div id="LableFlight">Flight</div>'+
   '              <div id="Flight" class="Value">'+FlightNumber+'</div>'+
   '            </div>'+
   '            <div class="Time">'+
   '              <div id="LableTime">Time</div>'+
   '              <div id="Time" class="Value">'+date+'</div>'+
   '            </div>'+
   '            <div class="Seat">'+
   '              <div id="LableTime">Seat</div>'+
   '              <div id="Time" class="Value">'+Seat+'</div>'+
   '            </div>'+
   '          </div>'+
   '          <div class="FromTo">'+
   '            <div class="From">'+
   '              <div id="LableFrom">From</div>'+
   '              <div id="From" class="Value">'+Departure+'</div>'+
   '            </div>'+
   '            <div class="To">'+
   '              <div id="LableTo">To</div>'+
   '              <div id="To" class="Value">'+Arrival+'</div>'+
   '            </div>'+
   '          </div>'+
   '          <div class="DateTime">'+
   '              <div id="LableBoardingTime">BoardingTime</div>'+
   '              <div id="BoardingTime" class="Value">'+Time+'</div>'+
   '          </div>'+
   '          <div class="info">'+
   '            Gate Closes 25 Minutes before Departure'+
   '          </div>'+
   
   '      </div>'+

   '    </div>'+
   '  </div>'+
   '  <div id="TicketRight">'+
   '    <div class="Top">'+Flight+'</div>'+
   '    <div class="mid1">'+
   '      <div class="PassengerDetails1">'+
   '        <div class="Name1">'+
   '        <div id="LableName">Name of The Passenger</div>'+
   '        <div id="Name" class="Value">'+Name+'</div>'+
   '        </div>'+
   '      </div>'+
   '      <div class="FromTo">'+
   '        <div class="From">'+
   '          <div id="LableFrom">From</div>'+
   '          <div id="From" class="Value">'+Departure+'</div>'+
   '        </div>'+
   '        <div class="To">'+
   '          <div id="LableTo">To</div>'+
   '          <div id="To" class="Value">'+Arrival+'</div>'+
   '        </div>'+
   '      </div>'+
   '      <div class="DateTime1">'+
   '        <div Class="Flight">'+
   '          <div id="LableFlight">Flight</div>'+
   '          <div id="Flight" class="Value">'+FlightNumber+'</div>'+
   '        </div>'+
   '        <div class="BoardingTime">'+
   '          <div id="LableBoardingTime">BoardingTime</div>'+
   '          <div id="BoardingTime" class="Value">'+Time+'</div>'+
   '        </div>'+
            
   '    </div>'+
   '    <div class="SeatTime">'+
   '      <div class="Time">'+
   '        <div id="LableTime">Time</div>'+
   '        <div id="Time" class="Value">'+date+'</div>'+
   '      </div>'+
   '      <div class="Seat">'+
   '        <div id="LableTime">Seat</div>'+
   '        <div id="Time" class="Value">'+Seat+'</div>'+
   '      </div>'+
   '    </div>'+
   '    </div>'+
   
   '   </div>'+
   '</div>';
  
}

function CancelFLight(){
	       document.getElementById("mid-cancel").innerHTML+=

   '<div id="Ticket">'+
   '   <div id="TicketLeft">'+
   '    <div class="Top">Boarding Pass</div>'+
   '    <div class="mid">'+
   '      <div id="qr"></div>'+
   '      <div class="TicketDetails">'+
   '          <div class="PassengerDetails">'+
   '            <div class="Name">'+
   '            <div id="LableName">Name of The Passenger</div>'+
   '            <div id="Name" class="Value">'+Name+'</div>'+
   '            </div>'+
   '            <div Class="Flight">'+
   '              <div id="LableFlight">Flight</div>'+
   '              <div id="Flight" class="Value">'+FlightNumber+'</div>'+
   '            </div>'+
   '            <div class="Time">'+
   '              <div id="LableTime">Time</div>'+
   '              <div id="Time" class="Value">'+date+'</div>'+
   '            </div>'+
   '            <div class="Seat">'+
   '              <div id="LableTime">Seat</div>'+
   '              <div id="Time" class="Value">'+Seat+'</div>'+
   '            </div>'+
   '          </div>'+
   '          <div class="FromTo">'+
   '            <div class="From">'+
   '              <div id="LableFrom">From</div>'+
   '              <div id="From" class="Value">'+Departure+'</div>'+
   '            </div>'+
   '            <div class="To">'+
   '              <div id="LableTo">To</div>'+
   '              <div id="To" class="Value">'+Arrival+'</div>'+
   '            </div>'+
   '          </div>'+
   '          <div class="DateTime">'+
   '              <div id="LableBoardingTime">BoardingTime</div>'+
   '              <div id="BoardingTime" class="Value">'+Time+'</div>'+
   '          </div>'+
   '          <div class="info">'+
   '            Gate Closes 25 Minutes before Departure'+
   '<div class="cancel" onclick="cancel('+count+')">Cancel Ticket</div>'+
   '          </div>'+
   
   '      </div>'+

   '    </div>'+
   '  </div>'+
   '  <div id="TicketRight">'+
   '    <div class="Top">'+Flight+'</div>'+
   '    <div class="mid1">'+
   '      <div class="PassengerDetails1">'+
   '        <div class="Name1">'+
   '        <div id="LableName">Name of The Passenger</div>'+
   '        <div id="Name" class="Value">'+Name+'</div>'+
   '        </div>'+
   '      </div>'+
   '      <div class="FromTo">'+
   '        <div class="From">'+
   '          <div id="LableFrom">From</div>'+
   '          <div id="From" class="Value">'+Departure+'</div>'+
   '        </div>'+
   '        <div class="To">'+
   '          <div id="LableTo">To</div>'+
   '          <div id="To" class="Value">'+Arrival+'</div>'+
   '        </div>'+
   '      </div>'+
   '      <div class="DateTime1">'+
   '        <div Class="Flight">'+
   '          <div id="LableFlight">Flight</div>'+
   '          <div id="Flight" class="Value">'+FlightNumber+'</div>'+
   '        </div>'+
   '        <div class="BoardingTime">'+
   '          <div id="LableBoardingTime">BoardingTime</div>'+
   '          <div id="BoardingTime" class="Value">'+Time+'</div>'+
   '        </div>'+
            
   '    </div>'+
   '    <div class="SeatTime">'+
   '      <div class="Time">'+
   '        <div id="LableTime">Time</div>'+
   '        <div id="Time" class="Value">'+date+'</div>'+
   '      </div>'+
   '      <div class="Seat">'+
   '        <div id="LableTime">Seat</div>'+
   '        <div id="Time" class="Value">'+Seat+'</div>'+
   '      </div>'+
   '    </div>'+
   '    </div>'+
   
   '   </div>'+
   '</div>';
  
}
function cancel(i){
	console.log("hi")
	var xhr=new XMLHttpRequest();
	xhr.onreadystatechange=function(){
		console.log(this.readyState)
		if(xhr.readyState==4 && xhr.status==200){
			CancelTicket();
		}
	}
	var obj=CancelTicketArray[i];
	console.log(CancelTicketArray)
	console.log(obj);
	xhr.open("POST","./User/CancelTicket");
	xhr.setRequestHeader("Content-Type","application/json");
	xhr.send(JSON.stringify(obj));
}
