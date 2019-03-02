
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


function addCarcase() {

    let material = $('#carcMaterial').val();
    let rear_wing = $('#rear-wing').val();
    let wings = $('#wings').val();
    let safe_arcs = $('#safe-arcs').val();
    let carcPrice = $('#carcPrice').val();

    if (material == null || rear_wing == null || wings == null || safe_arcs == null || carcPrice == null) {
     document.getElementById("carc-not-all").hidden = false; } else {
        if (carcPrice < 0) { document.getElementById("carc-bad-money").hidden = false; } else {

            $.ajax({
                type: "POST",
                url: "/add_detail/addCarcase",
                data: {
                    "material" : material,
                    "rear_wing" : rear_wing,
                    "wings" : wings,
                    "safe_arcs" : safe_arcs,
                    "carcPrice" : carcPrice
                },
                success: function (data) {
                    document.getElementById("carc-ok").hidden = false;
                }
            });

        }
    }

}


function addChassis() {

    let model = $('#chModel').val();
    let width = $('#chWidth').val();
    let height = $('#chHeight').val();
    let chassPrice = $('#chasPrice').val();

    if (model == null || width == null || height == null || chassPrice == null ) {
        document.getElementById("chass-not-all").hidden = false; } else {
        if (chassPrice < 0) { document.getElementById("chass-bad-money").hidden = false;  } else {

            $.ajax({
                type: "POST",
                url: "/add_detail/addChassis",
                data: {
                    "model" : model,
                    "width" : width,
                    "height" : height,
                    "chassPrice" : chassPrice
                },
                success: function (data) {
                    document.getElementById("chass-ok").hidden = false;
                }
            });

        }
    }

}
