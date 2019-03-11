
var files = [];

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

    if (label.trim() === "" || model.trim() === "") { document.getElementById("carFailfields").hidden = false; } else {
        if (carcase.trim() === "" || chassis.trim() === "" || engine.trim() === "" || electronics.trim() === "" ) {
            document.getElementById("carFail").hidden = false;
        } else {

            if (files[0] == null) {

                $.ajax({
                    type: "POST",
                    url: "/add_detail/addCar",
                    data: {
                        "label": label,
                        "model": model,
                        "carcase": carcase,
                        "chassis": chassis,
                        "engine": engine,
                        "electronics": electronics
                    },
                    success: function (data) {
                        document.getElementById("carOk").hidden = false;
                        location.reload();
                    }
                });
            } else {

                let data = new FormData();
                data.append("file",files[0]);
                data.append("label", label);
                data.append("model", model);
                data.append("carcase", carcase);
                data.append("chassis", chassis);
                data.append("engine", engine);
                data.append("electronics", electronics);

                $.ajax({
                    url : "/add_detail/addCarWithPhoto",
                    data: data,
                    type : "POST",
                    enctype: 'multipart/form-data',
                    processData: false,
                    contentType:false,
                    success:  function (data) {
                        document.getElementById("carOk").hidden = false;
                        location.reload();
                    }});

            }
        }
    }

}

function newFile(event) {
    files = event.target.files;
}





function addCarcase(budget) {

    let material = $('#carcMaterial').val();
    let rear_wing = $('#rear-wing').val();
    let wings = $('#wings').val();
    let safe_arcs = $('#safe-arcs').val();
    let carcPrice = $('#carcPrice').val();

    if (material.trim() === ""  || rear_wing.trim() === ""  || wings.trim() === ""  || safe_arcs.trim() === ""  || carcPrice.trim() === "" ) {
     document.getElementById("carc-not-all").hidden = false; } else {
        if (carcPrice < 0) { document.getElementById("carc-bad-money").hidden = false; } else {
            if (carcPrice > budget) { document.getElementById("carc-not-enough").hidden = false; } else {
                $.ajax({
                    type: "POST",
                    url: "/add_detail/addCarcase",
                    data: {
                        "material": material,
                        "rear_wing": rear_wing,
                        "wings": wings,
                        "safe_arcs": safe_arcs,
                        "carcPrice": carcPrice
                    },
                    success: function (data) {
                        document.getElementById("carc-ok").hidden = false;
                    }
                });
            }
        }
    }

}


function addChassis(budget) {

    let model = $('#chModel').val();
    let width = $('#chWidth').val();
    let height = $('#chHeight').val();
    let chassPrice = $('#chasPrice').val();

    if (model.trim() === ""  || width.trim() === ""  || height.trim() === ""  || chassPrice.trim() === ""  ) {
        document.getElementById("chass-not-all").hidden = false; } else {
        if (chassPrice < 0) { document.getElementById("chass-bad-money").hidden = false;  } else {
            if (chassPrice > budget) {
                document.getElementById("chass-not-enough").hidden = false;
            } else {

                $.ajax({
                    type: "POST",
                    url: "/add_detail/addChassis",
                    data: {
                        "model": model,
                        "width": width,
                        "height": height,
                        "chassPrice": chassPrice
                    },
                    success: function (data) {
                        document.getElementById("chass-ok").hidden = false;
                    }
                });

            }
        }
    }
}

function addEngine(budget) {

    let model = $('#engModel').val();
    let cyclinders = $('#cyclNum').val();
    let capacity = $('#capacity').val();
    let mass = $('#mass').val();
    let stroke = $('#stroke').val();
    let engPrice = $('#engPrice').val();

    if (model.trim() === "" || cyclinders.trim() === "" || capacity.trim() === ""  || mass.trim() === ""  || stroke.trim() === ""  || engPrice.trim() === "" ) {
        document.getElementById("eng-not-all").hidden = false; } else {
        if (engPrice < 0) { document.getElementById("eng-bad-money").hidden = false } else {
            if (engPrice > budget) { document.getElementById("eng-not-enough").hidden = false; } else {

                $.ajax({
                    type: "POST",
                    url: "/add_detail/addEngine",
                    data: {
                        "model": model,
                        "cyclinders": cyclinders,
                        "capacity": capacity,
                        "mass": mass,
                        "stroke": stroke,
                        "engPrice": engPrice
                    },
                    success: function (data) {
                        document.getElementById("eng-ok").hidden = false;
                    }
                });
            }
        }
    }
}


function addElectronics(budget) {

    let telemetry = $('#telemetry').val();
    let controlSystem = $('#contrModel').val();
    let elecPrice = $('#elecPrice').val();

    if (telemetry.trim() === "" || controlSystem.trim() === "" || elecPrice.trim() === "") {
        document.getElementById("elec-not-all").hidden = false; } else {
        if (elecPrice < 0) { document.getElementById("elec-bad-money").hidden = false } else {
            if (elecPrice > budget) { document.getElementById("elec-not-enough").hidden = false; } else {

                $.ajax({
                    type: "POST",
                    url: "/add_detail/addElectronics",
                    data: {
                        "telemetry": telemetry,
                        "controlSystem": controlSystem,
                        "elecPrice": elecPrice
                    },
                    success: function (data) {
                        document.getElementById("elec-ok").hidden = false;
                    }
                });
            }
        }

    }

}
