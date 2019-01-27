
window.onload = function (ev) {
    carsArea = document.getElementById("carsZone");
    storArea = document.getElementById("storage");
    filterArea = document.getElementById("filter");
    changesArea = document.getElementById("changes");
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
