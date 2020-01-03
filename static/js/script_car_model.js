class AutoModelCard{

    constructor(id,model_car){
        this.id = id;
        this.model_car=model_car;
        document.getElementById('auto-cards-container').innerHTML+=this.getCard();  
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


    


