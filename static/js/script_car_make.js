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

// window.onload = function () {

//     let linkToHomePage = 'http://192.168.0.103:1030';
//     var xhr = new XMLHttpRequest();

//     xhr.open('GET', this.linkToHomePage + '/', false);
//     xhr.send();

//     if (xhr.status != 200) {
//         alert(xhr.status + ': ' + xhr.statusText);
//     } else {
//         this.alert(xhr.responseText);
//     }

// }
