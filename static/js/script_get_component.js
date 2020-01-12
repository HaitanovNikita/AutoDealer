class ComponentCar{

    constructor(id,component,container){
        // document.getElementById('auto-cards-container').innerHTML+=this.getCard();
        this.id = id;
        this.component = component;
        document.getElementById(container).innerHTML+=this.getComponent();  
        
    }

    getComponent(){
        return `
        <option type="text" name = "`+this.component+` "value="`+this.id+`">`+this.component+`</option>
        `;
    }

}


    


