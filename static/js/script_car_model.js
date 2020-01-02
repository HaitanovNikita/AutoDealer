class AutoModelCard{

    constructor(id,model_car){
        this.id = id;
        this.model_car=model_car;
        document.getElementById('body').innerHTML+=this.getCard();  
    }

    getCard(){
        return `
        <div class="auto-card" style="width: 18rem;">
        <div class="text-card">
            `+this.model_car+`
        </div>
        <img class="img-card" src="images/`+this.id+`.jpg"  alt="...">
        <div class="line-card"></div >
    </div>
        `;
    }

}


    let linkToHomePage = 'http://192.168.0.103:1030';
    var xhr = new XMLHttpRequest();    
    
    xhr.open('POST','/api/get-client/?operation=getAllModelCars', false);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); // Отправляем кодировку                
    xhr.send('/api/get-client/?operation=getAllModelCars');

    if (xhr.status != 200 ) {
        alert(xhr.status + ': ' + xhr.statusText+' no answer');
    } else {
        alert(xhr.responseText);
        let arrResponseText = xhr.responseText.split(" ");
        for(let i=0;i<5;i+2){
            // console.log(arrResponseText[i]);
            new AutoModelCard(arrResponseText[i],arrResponseText[i+1]);
        }
    }


