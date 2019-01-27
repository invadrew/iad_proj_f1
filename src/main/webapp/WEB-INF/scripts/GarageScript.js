
window.onload = function (ev) {
    carsArea = document.getElementById("carsZone");
    storArea = document.getElementById("storage");
    filterArea = document.getElementById("filter");
};

function chooseCars() {
    carsArea.hidden = false;
    storArea.hidden = true;
    filterArea.hidden = true;
}

function chooseStore() {
    carsArea.hidden = true;
    storArea.hidden = false;
    filterArea.hidden = false;
}
