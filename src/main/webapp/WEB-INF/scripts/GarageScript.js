
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
    selectCompType();
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

    if (heightFrom > heightTo) {
        let tmp = heightTo;
        heightTo = heightFrom;
        heightFrom = tmp;
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


function sendEngineData(event) {

    let model = $('#eng-model').val();
    let cyclFrom = $('#cycl-from').val();
    let cyclTo = $('#cycl-to').val();
    let capFrom = $('#cap-from').val();
    let capTo = $('#cap-to').val();
    let strokeFrom = $('#stroke-from').val();
    let strokeTo = $('#stroke-to').val();
    let massFrom = $('#mass-from').val();
    let massTo = $('#mass-to').val();
    let condition = $('#eng-cond').val();

    if (cyclFrom > cyclTo) {
        let tmp = cyclTo;
        cyclTo = cyclFrom;
        cyclFrom = tmp;
    }

    if (strokeFrom > strokeTo) {
        let tmp = strokeTo;
        strokeTo = strokeFrom;
        strokeFrom = tmp;
    }

    if (capFrom > capTo) {
        let tmp = capTo;
        capTo = capFrom;
        capFrom = tmp;
    }

    if (massFrom > massTo) {
        let tmp = massTo;
        massTo = massFrom;
        massFrom = tmp;
    }


    if (capTo === "") { capTo = 9999999; }
    if (capFrom === "") { capFrom = 0; }
    if (massTo === "") { massTo = 9999999; }
    if (massFrom === "") { massFrom = 0; }
    if (strokeTo === "") { strokeTo = 9999999; }
    if (strokeFrom === "") { strokeFrom = 0; }
    if (cyclTo === "") { cyclTo = 9999999; }
    if (cyclFrom === "") { cyclFrom = 0; }

    $.ajax({
        type: "GET",
        url: "/garage/engines",
        data: {
            "model": model,
            "cyclFrom": cyclFrom,
            "cyclTo": cyclTo,
            "capFrom": capFrom,
            "capTo": capTo,
            "massFrom": massFrom,
            "massTo": massTo,
            "strokeFrom": strokeFrom,
            "strokeTo": strokeTo,
            "condition": condition
        },
        success: function (data) {
            $('#engTable').find("tr:gt(0)").remove();
            document.getElementById("carcTable").hidden = true;
            document.getElementById("carcTabLabel").hidden = true;
            document.getElementById("chassTable").hidden = true;
            document.getElementById("chassTabLabel").hidden = true;
            document.getElementById("engTable").hidden = false;
            document.getElementById("engTabLabel").hidden = false;
            document.getElementById("elecTable").hidden = true;
            document.getElementById("elecTabLabel").hidden = true;
            for (let i=0; i < data.length; i++) {
                let $tr = $('<tr>').append(
                    $('<td>').text(data[i][0]),
                    $('<td>').text(data[i][1]),
                    $('<td>').text(data[i][2]),
                    $('<td>').text(data[i][3]),
                    $('<td>').text(data[i][4]),
                    $('<td>').text(data[i][5]));
                $('#engTable').append($tr);
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

function selectCompType() {

    let carcChange = document.getElementById("carcForChange");
    let chassChange = document.getElementById("chassForChange");
    let engChange = document.getElementById("engForChange");
    let elecChange = document.getElementById("elecForChange");

    let carId = $('#car-select').val();

    let typeSelector = document.getElementById("change-type-select");

    switch (typeSelector.value) {

        case "Carcase":
            carcChange.hidden = false;
            chassChange.hidden = true;
            engChange.hidden = true;
            elecChange.hidden = true;

            $.ajax({
                type: "GET",
                url: "/garage/carcaseInfo",
                data: {
                    "carId" : carId
                },
                success: function (data) {
                    $('#currCarc').html("Текущая комплектация: " + data);
                }
            });

            break;

        case "Chassis":
            carcChange.hidden = true;
            chassChange.hidden = false;
            engChange.hidden = true;
            elecChange.hidden = true;

            $.ajax({
                type: "GET",
                url: "/garage/chassisInfo",
                data: {
                    "carId" : carId
                },
                success: function (data) {
                    $('#currChass').html("Текущая комплектация: " + data[0] + " выс: " + data[1] + " шир: " + data[2]);
                }
            });

            break;

        case "Engine":
            carcChange.hidden = true;
            chassChange.hidden = true;
            engChange.hidden = false;
            elecChange.hidden = true;

            $.ajax({
                type: "GET",
                url: "/garage/engineInfo",
                data: {
                    "carId" : carId
                },
                success: function (data) {
                    $('#currEng').html("Текущая комплектация: " + data[0] + " цил: " + data[4] + " объем: " + data[3]);
                }
            });

            break;

        case "Electronics":
            carcChange.hidden = true;
            chassChange.hidden = true;
            engChange.hidden = true;
            elecChange.hidden = false;

            $.ajax({
                type: "GET",
                url: "/garage/electronicsInfo",
                data: {
                    "carId" : carId
                },
                success: function (data) {
                    $('#currElec').html("Текущая комплектация: телеметрия: " + data[0] + " контроль: " + data[1]);
                }
            });

            break;

    }

}

function change_carcase() {

    let car = $('#car-select').val();
    let carc = $('#change-detail-carc').val();

    if (carc!=null) {

        $.ajax({
            type: "POST",
            url: "/garage/changeCarc",
            data: {
                "carId": car,
                "carcaseId": carc
            },
            success: function (data) {

                location.reload();
            }
        });
    } else {
        document.getElementById("neok").hidden = false;
    }
}

function change_chassis() {

    let car = $('#car-select').val();
    let chass = $('#change-detail-chass').val();

    if (chass!=null) {

        $.ajax({
            type: "POST",
            url: "/garage/changeChass",
            data: {
                "carId": car,
                "chassisId": chass
            },
            success: function (data) {

                location.reload();
            }
        });
    } else {
        document.getElementById("neok").hidden = false;
    }

}

function change_engine() {

    let car = $('#car-select').val();
    let eng = $('#change-detail-eng').val();

    if (eng!=null) {

        $.ajax({
            type: "POST",
            url: "/garage/changeEng",
            data: {
                "carId": car,
                "engineId": eng
            },
            success: function (data) {

                location.reload();
            }
        });
    } else {
        document.getElementById("neok").hidden = false;
    }

}

function change_electronics() {

    let car = $('#car-select').val();
    let elec = $('#change-detail-elec').val();

    if (elec!=null) {

        $.ajax({
            type: "POST",
            url: "/garage/changeElectronics",
            data: {
                "carId": car,
                "electronicsId": elec
            },
            success: function (data) {

                location.reload();
            }
        });
    } else {
        document.getElementById("neok").hidden = false;
    }

}

function confirmDisass(id) {

    document.getElementById("car-destroyer" + id).hidden = true;
    document.getElementById("really" + id).hidden = false;
    document.getElementById("yes" + id).hidden = false;
    document.getElementById("no" + id).hidden = false;

}

function disassCar(id) {

    $.ajax({
        type: "POST",
        url: "/garage/disassemble",
        data: {
            "carId": id
        },
        success: function (data) {
            location.reload();
        }
    });

}

function goBack(id) {

    document.getElementById("car-destroyer" + id).hidden = false;
    document.getElementById("really" + id).hidden = true;
    document.getElementById("yes" + id).hidden = true;
    document.getElementById("no" + id).hidden = true;

}

function repairCarc(id) {

    let ca = document.getElementById("ca" + id);

    $.ajax({
        type: "POST",
        url: "/garage/repairCarcase",
        data: {
            "carId": id
        },
        success: function (data) {
            if (data === "ok") {
            ca.hidden = true;
            location.reload(); } else {
                document.getElementById("notEnoughMoneyCarc" + id).hidden = false;
            }
        }
    });

}

function repairChass(id) {

    let chs = document.getElementById("chs" + id);

    $.ajax({
        type: "POST",
        url: "/garage/repairChassis",
        data: {
            "carId": id
        },
        success: function (data) {
            if (data === "ok") {
            chs.hidden = true;
            location.reload(); } else {
                document.getElementById("notEnoughMoneyChass" + id).hidden = false;
            }
        }
    });

}

function repairEng(id) {

    let en = document.getElementById("en" + id);

    $.ajax({
        type: "POST",
        url: "/garage/repairEngine",
        data: {
            "carId": id
        },
        success: function (data) {
            if (data === "ok") {
            en.hidden = true;
            location.reload(); } else {
                document.getElementById("notEnoughMoneyEng" + id).hidden = false;
            }
        }
    });

}

function repairElec(id) {

    let el = document.getElementById("el" + id);

    $.ajax({
        type: "POST",
        url: "/garage/repairElectronics",
        data: {
            "carId": id
        },
        success: function (data) {
            if (data === "ok") {
            el.hidden = true;
            location.reload(); } else {
                document.getElementById("notEnoughMoneyElec" + id).hidden = false;
            }
        }
    });

}
