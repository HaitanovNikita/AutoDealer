document.getElementById("body").innerHTML+=`<header>
<div class="pos-f-t">
    <div class="collapse" id="navbarToggleExternalContent">
      <div class=" bg-dark p-4">
        <h4 class="text-white-50">Меню</h4>	
        <div class ="flip-container">
            <div class="flip">
                <a href="/index.html" target="model_car">
                    <div class="front">Модельний ряд</div>
                    <div class="back">Модельний ряд</div>
              </a>
            </div>
            <div class="flip">
            <a href="/audiConnect.html" target="about">
                <div class="front">Audi Connect</div>
                <div class="back">Audi Connect</div>
            </a>
            </div>
            <div class="flip">
                <a href="/searchCar.html" target="order">
                    <div class="front">Пошук авто</div>
                    <div class="back">Пошук авто</div>
              </a>
            </div>
            <div class="flip">
                <a href="/legalInformation.html" target="info">
                    <div class="front">Юр. інформація</div>
                    <div class="back">Юр. інформація</div>
              </a>
            </div>
            <div class="flip">
                <a href="/contacts.html" target="contacts">
                    <div class="front">Контакти</div>
                    <div class="back">Контакти</div>
              </a>
            </div>
          </div>
          <div id="container_model_cars"></div>
      </div>
    </div>
    
    <nav class="navbar navbar-dark bg-dark">
        <h5 class="text-white-50" >Audi Ukraine</h5>
       <a href=/index.html> <img class="logo-img" src="images/logo1.png" alt="Audi"></a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
    </nav>
</div>
</header>`;
