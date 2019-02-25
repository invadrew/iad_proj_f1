
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

         document.getElementById("carcTable").hidden = false;
         document.getElementById("carcTabLabel").hidden = false;
         document.getElementById("chassTable").hidden = false;
         document.getElementById("chassTabLabel").hidden = false;
         document.getElementById("engTable").hidden = false;
         document.getElementById("engTabLabel").hidden = false;
         document.getElementById("elecTable").hidden = false;
         document.getElementById("elecTabLabel").hidden = false;

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

function sendCarcaseData(event) {

    let material = $('#carc-material').val();
    let rearWing = $('#carc-rareWing').val();
    let safetyArcs = $('#carc-safetyArcs').val();
    let wings = $('#carc-wings').val();
    let condition = $('#carc-cond').val();

    $.ajax({
        type: "GET",
        url: "/garage/carcase",
        data: {
            "material": material,
            "rearWing": rearWing,
            "safetyArcs": safetyArcs,
            "wings": wings,
            "condition": condition
        },
        success: function (data) {
            $('#carcTable').find("tr:gt(0)").remove();
            document.getElementById("carcTable").hidden = false;
            document.getElementById("carcTabLabel").hidden = false;
            document.getElementById("chassTable").hidden = true;
            document.getElementById("chassTabLabel").hidden = true;
            document.getElementById("engTable").hidden = true;
            document.getElementById("engTabLabel").hidden = true;
            document.getElementById("elecTable").hidden = true;
            document.getElementById("elecTabLabel").hidden = true;
            for (let i=0; i < data.length; i++) {
                let $tr = $('<tr>').append(
                    $('<td>').text(data[i][0]),
                    $('<td>').text(data[i][1]),
                    $('<td>').text(data[i][2]),
                    $('<td>').text(data[i][3]),
                    $('<td>').text(data[i][4]));
                $('#carcTable').append($tr);
            }

        }
    });
}

function sendChassisData(event) {

    let model = $('#chass-model').val();
    let heightFrom = $('#height-from').val();
    let heightTo = $('#height-to').val();
    let widthFrom = $('#width-from').val();
    let widthTo = $('#width-to').val();
    let condition = $('#chass-cond').val();

    if (widthFrom > widthTo) {
        let tmp = widthTo;
        widthTo = widthFrom;
        widthFrom = tmp;
    }

    if (widthTo === "") { widthTo = 9999999; }
    if (widthFrom === "") { widthFrom = 0; }
    if (heightTo === "") { heightTo = 9999999; }
    if (heightFrom === "") { heightFrom = 0; }

    $.ajax({
        type: "GET",
        url: "/garage/chassis",
        data: {
            "model": model,
            "heightFrom": heightFrom,
            "heightTo": heightTo,
            "widthFrom": widthFrom,
            "widthTo": widthTo,
            "condition": condition
        },
        success: function (data) {
            $('#chassTable').find("tr:gt(0)").remove();
            document.getElementById("carcTable").hidden = true;
            document.getElementById("carcTabLabel").hidden = true;
            document.getElementById("chassTable").hidden = false;
            document.getElementById("chassTabLabel").hidden = false;
            document.getElementById("engTable").hidden = true;
            document.getElementById("engTabLabel").hidden = true;
            document.getElementById("elecTable").hidden = true;
            document.getElementById("elecTabLabel").hidden = true;
            for (let i=0; i < data.length; i++) {
                let $tr = $('<tr>').append(
                    $('<td>').text(data[i][0]),
                    $('<td>').text(data[i][1]),
                    $('<td>').text(data[i][2]),
                    $('<td>').text(data[i][3]));
                $('#chassTable').append($tr);
            }

        }
    });


}

function sendElectronicsData(event) {

    let telemetry = $('#elec-tel').val();
    let controlSystem = $('#elec-cs').val();
    let condition = $('#elec-cond').val();

    $.ajax({
        type: "GET",
        url: "/garage/electronics",
        data: {
            "telemetry": telemetry,
            "controlSystem": controlSystem,
            "condition": condition
        },
        success: function (data) {
            $('#elecTable').find("tr:gt(0)").remove();
            document.getElementById("carcTable").hidden = true;
            document.getElementById("carcTabLabel").hidden = true;
            document.getElementById("chassTable").hidden = true;
            document.getElementById("chassTabLabel").hidden = true;
            document.getElementById("engTable").hidden = true;
            document.getElementById("engTabLabel").hidden = true;
            document.getElementById("elecTable").hidden = false;
            document.getElementById("elecTabLabel").hidden = false;
            for (let i=0; i < data.length; i++) {
                let $tr = $('<tr>').append(
                    $('<td>').text(data[i][0]),
                    $('<td>').text(data[i][1]),
                    $('<td>').text(data[i][2]));
                $('#elecTable').append($tr);
            }

        }
    });

}
