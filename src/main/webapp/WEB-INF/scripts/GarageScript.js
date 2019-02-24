
window.onload = function (ev) {
    carsArea = document.getElementById("carsZone");
    storArea = document.getElementById("storage");
    filterArea = document.getElementById("filter");
    changesArea = document.getElementById("changes");
    carcaseFilter = document.getElementById("carcase_filter");
    engineFilter = document.getElementById("engine_filter");
    chassisFilter = document.getElementById("chassis_filter");
    electronicsFilter = document.getElementById("electronics_filter");
    componentType = document.getElementById("compType");
};

function chooseCars() {
    carsArea.hidden = false;
    storArea.hidden = true;
    filterArea.hidden = true;
    changesArea.hidden = true;
}

function chooseStore() {
    carsArea.hidden = true;
    storArea.hidden = false;
    filterArea.hidden = false;
    changesArea.hidden = true;
}

function chooseChange() {

    carsArea.hidden = true;
    storArea.hidden = true;
    filterArea.hidden = true;
    changesArea.hidden = false;

}

function chooseFilter() {

 switch (componentType.value) {

     case 'any':
         carcaseFilter.hidden = true;
         chassisFilter.hidden = true;
         engineFilter.hidden = true;
         electronicsFilter.hidden = true;
         break;

     case 'carcase':
         carcaseFilter.hidden = false;
         chassisFilter.hidden = true;
         engineFilter.hidden = true;
         electronicsFilter.hidden = true;
         break;

     case 'chassis':
         carcaseFilter.hidden = true;
         chassisFilter.hidden = false;
         engineFilter.hidden = true;
         electronicsFilter.hidden = true;
         break;

     case 'engine':
         carcaseFilter.hidden = true;
         chassisFilter.hidden = true;
         engineFilter.hidden = false;
         electronicsFilter.hidden = true;
         break;

     case 'electronics':
         carcaseFilter.hidden = true;
         chassisFilter.hidden = true;
         engineFilter.hidden = true;
         electronicsFilter.hidden = false;
         break;
 }


}
