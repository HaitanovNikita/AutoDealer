$(function () {

class Tour{

    constructor(id,nameHotel,starsAtHotel,travelFrom,travelTo,departureDate,travelDays,statusTour,price,container){
        this.id = id;
        this.nameHotel=nameHotel;
        this.starsAtHotel=starsAtHotel;
        this.travelFrom = travelFrom;
        this.travelTo = travelTo;
        this.departureDate=departureDate;
        this.travelDays=travelDays;
        this.statusTour=statusTour;
        this.price=price;
        document.getElementById(container).innerHTML += this.tour();
    }

    createElements(){
        let divContainerTour = $('<div>', { class: "tour" });
        let imageHotel = $('img',{ src:"images/"+this.id+".jpg", class:"border-bottom" });
        let tourText = $('div',{ class:"tour_text"});

        let spanTravelTo = $('span',{ id:"travelTo", class:"font-weight-bolder"});
        let spanSmileTravelTo = $('span',{ class:"badge badge-secondary"});
        let textSmile = document.createTextNode('&#128748;');
        let textTravelTo = document.createTextNode(this.travelTo);
            spanSmileTravelTo.appendChild(textSmile);
            spanTravelTo.appendChild(spanSmileTravelTo);
            spanTravelTo.appendChild(textTravelTo);

        let nameHotelStr = $('strong',{ class:"badge badge-success"});
        let textNameHotelStr = document.createTextNode(this.nameHotel);
            nameHotelStr.appendChild(textNameHotelStr);
        let tagBr = $('<br>');

        let numStarsAtHotel = $('span',{class:"badge badge-success"});
        let starsText =  document.createTextNode('&#11088;');
        for(let i=0;i<this.starsAtHotel;i++){
            numStarsAtHotel.appendChild(" "+starsText);
        }

        tourText.appendChild(spanTravelTo);
        tourText.appendChild(nameHotelStr);
        tourText.appendChild(tagBr);
        tourText.appendChild(numStarsAtHotel);
        divContainerTour.appendChild(imageHotel);
        divContainerTour.appendChild(tourText);
        alert(document.getElementById("container"));
        document.getElementById("container").innerHTML=divContainerTour;
    }

    starsTour(){
        let stars='';
        for(this.i=0;this.i<this.starsAtHotel;this.i++){
            stars+=' '+'&#11088;'
        }
        return stars
    }


    tour(){
        let tour = `
         <div class="tour">
        <img src="images/`+this.id+`.jpg" class="border-bottom">
        <div class="tour_text">
            <span id="travelTo" class="badge badge-warning"><span class="badge badge-secondary"> &#128748;
                </span>`+this.travelTo+`</span>
           
            <strong class="badge badge-success"> &#127976; `+this.nameHotel+`
            </strong><br>
            <span class="badge badge-success">`+ this.starsTour()+ `</span>
            <div id="price" class="badge badge-warning">`+ this.price +` </div><br>
            <span class="badge badge-success"><span class=""> &#128747; </span>`+ `From: `  + this.travelFrom +`</span><br>
            <span class="badge badge-success"> &#128198; Date: `+this.departureDate +` | `+ this.travelDays +`days`+`</span>
            
        </div>
    </div>`;
    return tour;
    }
}

new Tour(1,"Hotel",5,'HaitanovLand',"Kyiv","2019-12-21",7,'VIP','7500 $');

function getTours(operation,container){
    var xhr = new XMLHttpRequest();
    let linkToHomePage = 'http://192.168.1.102:1080';
    xhr.open('POST', linkToHomePage+'/api/get-client/?operation=' + operation, true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); // Отправляем кодировку
    
    xhr.send('operation=' + operation);
    // xhr.send('operation=' + operation+'&certainStatus=Normal');
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4) { // Ответ пришёл
            if (xhr.status == 200) { // Сервер вернул код 200 (что хорошо)
                
                let arrResp= xhr.responseText.split(/[=.:;?!~,'"&|()<>{}\[\]\r\n/\\]+/);
                // let str;
                // for( i=18;i< arrResp.length;i++){
                //     str+=arrResp[i]+' (i = '+i+') \n';
                // }
                // alert(str);
                    let id=0,nameHotel=1,numStars=2,To=3,From=4,date=5,travelDay=6,status=7,price=8;
                for(i=0;i<parseInt(arrResp.length/10)+1;i++){
                    new Tour(arrResp[id],arrResp[nameHotel],arrResp[numStars],arrResp[From],arrResp[To],arrResp[status],arrResp[date],arrResp[travelDay],arrResp[price]+'$',container);
                    // alert(id+' '+nameHotel+' '+numStars+' '+To+' '+From+' '+date+' '+travelDay+' '+status+' '+price);
                    id+=9;
                    nameHotel+=9;
                    numStars+=9;
                    To+=9;
                    From+=9;
                    date+=9;
                    travelDay+=9;
                    status+=9;
                    price+=9;
                }
            
            } else {
                alert(xhr.status + ': ' + xhr.statusText);//Если код не 200
            }
        }
    }
}

// getTours('get tour certain status');
getTours('get','body');
});