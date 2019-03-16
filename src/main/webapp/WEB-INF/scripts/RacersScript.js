var lastTime;

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

function getNews() {
    $.ajax({
        type : "GET",
        data : {"lastTime" : lastTime},
        url: "/raceTime-racer/news",
        success: function (data) {

            if ((data[0][0]) !== "nothing") {
                let mess = "Из пункта " + data[0][3] + " в пункт " + data[0][4] + " перенесено: ";
                let typeM = "";

                if (data[0][2] === "TOUGH") {
                    typeM = " жесткие шины в количестве ";
                }
                if (data[0][2] === "SOFT") {
                    typeM = " мягкие шины в количестве ";
                }
                if (data[0][2] === "FUEL") {
                    typeM = " топливо в объеме ";
                }

                let transTable = $('#transfersTable');
                let $tr = $('<tr>').append(
                    $('<td>').text(data[0][0]),
                    $('<td>').text(mess + typeM + data[0][1]));
                transTable.append($tr);

                lastTime = data[0][5];
            }
        }
    });
}

setInterval(getNews,2000);

window.onload = function()
 {

    $.ajax({
        type : "GET",
        url : "/raceTime-mechanic/time",
        success: function(data) {
            lastTime = data;
        }
    });

};