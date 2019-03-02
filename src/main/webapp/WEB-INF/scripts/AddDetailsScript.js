
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

function addCar() {

    let label = $('#carLabel').val();
    let model = $('#carModel').val();
    let carcase = $('#carCarcSelect').val();
    let chassis = $('#carChassSelect').val();
    let engine = $('#carEngSelect').val();
    let electronics = $('#carElecSelect').val();

    if (label === "" || model === "") { document.getElementById("carFailfields").hidden = false; } else {
        if (carcase == null || chassis == null || engine == null || electronics == null) {
            document.getElementById("carFail").hidden = false;
        } else {

            $.ajax({
                type: "POST",
                url: "/add_detail/addCar",
                data: {
                    "label" : label,
                    "model" : model,
                    "carcase" : carcase,
                    "chassis" : chassis,
                    "engine" : engine,
                    "electronics" : electronics
                },
                success: function (data) {
                    document.getElementById("carOk").hidden = false;
                    location.reload();
                }
            });

        }
    }

}