function changePilot() {

    document.getElementById("pilot-change-ready").hidden = true;
    document.getElementById("pilot-change-error").hidden = true;
    document.getElementById("pilot-change-busy").hidden = true;

    let comment = $('#pilot-reason').val();
    let places = document.getElementsByName('pilot-place-select');
    let pitStop = "";

    for (let i = 0, length = places.length; i < length; i++) {
        if (places[i].checked) {
            pitStop = places[i].value;
            break;
        }
    }

    if (pitStop === "") {  document.getElementById("pilot-change-error").hidden = false; } else {

        $.ajax({
            type: "POST",
            url: "/raceTime-racer/offerPilotChange",
            data: {
                "comment": comment,
                "place": pitStop
            },
            success: function (data) {
                if (data === "ok") {
                document.getElementById("pilot-change-ready").hidden = false; } else {
                    document.getElementById("pilot-change-busy").hidden = false;
                }
            }
        });
    }
}


function askRepair() {

    document.getElementById("repair-sent").hidden = true;
    let comment = document.getElementById("repair-comment").value;

    $.ajax({
        type: "POST",
        url: "/raceTime-racer/askRepair",
        data: {
            "comment": comment,
        },
        success: function (data) {
            document.getElementById("repair-sent").hidden = false;
            }
    });


}

function confirmRepair(id, status) {

    let comment = document.getElementById("rev-rep-comm" + id).value;

    $.ajax({
        type: "POST",
        url: "/raceTime-racer/conformRepair",
        data: {
            "comment": comment,
            "id" : id,
            "status" : status
        },
        success: function (data) {

        }
    });

}


function askService() {

    document.getElementById("service-ready").hidden = true;
    document.getElementById("service-error").hidden = true;
    document.getElementById("service-not-enough").hidden = true;

    let comment = $('#askServiceComment').val();
    let fuel = $('#fuel-serv').val();
    let tires = $('#tire-change').val();
    let pitStop = "";
    let places = document.getElementsByName('place-select');

    for (let i = 0, length = places.length; i < length; i++) {
        if (places[i].checked) {
            pitStop = places[i].value;
            break;
        }
    }

    if (pitStop === "") {  document.getElementById("service-error").hidden = false; } else {

        $.ajax({
            type: "POST",
            url: "/raceTime-racer/askService",
            data: {
                "comment": comment,
                "place": pitStop,
                "fuel" : fuel,
                "tires" : tires
            },
            success: function (data) {
                if (data === "ok") {
                    document.getElementById("service-ready").hidden = false; } else {
                    document.getElementById("service-not-enough").hidden = false;
                }
            }
        });
    }

}

function confirmService(id, status) {

    let comment = document.getElementById("servAnsw" + id).value;

    $.ajax({
        type: "POST",
        url: "/raceTime-mechanic/service",
        data: {
            "comment" : comment,
            "id" : id,
            "status" : status
        },
        success: function (data) {

        }
    });

}
