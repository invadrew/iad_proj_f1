
window.onload = function (ev) {
     carcBlock = document.getElementById("carcAddArea");
     chBlock = document.getElementById("chasAddArea");
     engBlock = document.getElementById("engAddArea");
     elecBlock = document.getElementById("elecAddArea");
};

function chooseCarcase() {

    carcBlock.hidden = false;
    chBlock.hidden = true;
    engBlock.hidden = true;
    elecBlock.hidden = true;

}

function chooseChassis() {

    carcBlock.hidden = true;
    chBlock.hidden = false;
    engBlock.hidden = true;
    elecBlock.hidden = true;

}

function chooseEngine() {

    carcBlock.hidden = true;
    chBlock.hidden = true;
    engBlock.hidden = false;
    elecBlock.hidden = true;
    
}

function chooseElectronics() {

    carcBlock.hidden = true;
    chBlock.hidden = true;
    engBlock.hidden = true;
    elecBlock.hidden = false;

}