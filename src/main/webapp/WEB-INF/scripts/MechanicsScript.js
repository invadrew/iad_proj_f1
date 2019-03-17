function doTransfer() {

    let pitFrom = $('#place-from-transfer').val();
    let pitTo = $('#place-to-transfer').val();

    document.getElementById("transfer-same").hidden = true;
    document.getElementById("transfer-not-enough").hidden = true;
    document.getElementById("transfer-ready").hidden = true;
    document.getElementById("transfer-wait").hidden = false;
    document.getElementById("transfer-empty").hidden = true;

    let item = $('#item-transfer').val();
    let num = $('#transfer-num').val();

    if (pitFrom === pitTo) { document.getElementById("transfer-same").hidden = false; document.getElementById("transfer-wait").hidden = true; } else {

        if (!num) {  document.getElementById("transfer-empty").hidden = false; document.getElementById("transfer-wait").hidden = true; } else {

            $.ajax({
                type: "POST",
                url: "/raceTime-mechanic/transfer",
                data: {
                    "from": pitFrom,
                    "to": pitTo,
                    "item": item,
                    "num": num
                },
                success: function (data) {
                    document.getElementById("transfer-wait").hidden = true;
                    if (data[0] === "not-enough") {
                        document.getElementById("transfer-not-enough").hidden = false;
                    } else {
                        document.getElementById("transfer-wait").hidden = true;
                        document.getElementById("transfer-ready").hidden = false;

                        let mess = "Из пункта " +  data[3] +" в пункт " + data[4] + " перенесено: ";
                        let typeM = "";

                        if (data[2] === "TOUGH") { typeM = " жесткие шины в количестве ";}
                        if (data[2] === "SOFT") { typeM = " мягкие шины в количестве ";}
                        if (data[2] === "FUEL") { typeM = " топливо в объеме ";}

                        let transTable = $('#transfersTable');
                        let $tr = $('<tr>').append(
                            $('<td>').text(data[0]),
                            $('<td>').text(mess + typeM + data[1]));
                        transTable.append($tr);

                        document.getElementById("shini" + data[12]).innerText = "Шины: Жесткие х"+ data[5]+", Мягкие х" + data[6];
                        document.getElementById("shini" + data[11]).innerText = "Шины: Жесткие х"+ data[8]+", Мягкие х" + data[9];

                        document.getElementById("fuel" + data[12]).innerText = "Топливо: " + data[7] + " литров";
                        document.getElementById("fuel" + data[11]).innerText = "Топливо: " + data[10] + " литров";


                    }
                }
            });
        }
    }

}


function handleChangePilot(id, status) {

    let comment = document.getElementById("pcAnsw" + id).value;
    let pilot = document.getElementById("pilot4Change" + id).value;

    $.ajax({
        type: "POST",
        url: "/raceTime-mechanic/pilotChange",
        data: {
            "comment" : comment,
            "id" : id,
            "pilot" : pilot,
            "status" : status
        },
        success: function (data) {
            location.reload();
        }
    });
    
}

function handleRepair(id, status) {

    let comment = document.getElementById("repAnsw" + id).value;

    $.ajax({
        type: "POST",
        url: "/raceTime-mechanic/repair",
        data: {
            "comment" : comment,
            "id" : id,
            "status" : status
        },
        success: function (data) {

        }
    });

}

function chooseRepair(id) {
    let val = document.getElementById("offer-reason" + id).value;
    if (val === 'repair') { document.getElementById("repair-menu" + id).hidden = false;
    document.getElementById("service-menu" + id).hidden = true; } else {
        document.getElementById("repair-menu" + id).hidden = true;
        document.getElementById("service-menu" + id).hidden = false;
    }

}

function offerRepair(id) {

    document.getElementById("of-rep-ready" + id).hidden = true;

    let comment = document.getElementById("repair-offer-comm" + id).value;

    $.ajax({
        type: "POST",
        url: "/raceTime-mechanic/offer-repair",
        data: {
            "id" : id,
            "comment" : comment
        },
        success: function (data) {
            document.getElementById("of-rep-ready" + id).hidden = false;
        }
    });

}

function handleService(id, status) {

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

function offerService(id) {

    document.getElementById("service-ready" + id).hidden = true;
    document.getElementById("service-error" + id).hidden = true;
    document.getElementById("service-not-enough" + id).hidden = true;

    let comment = document.getElementById("askServiceComment" + id).value;
    let fuel = document.getElementById("fuel-serv" + id).value;
    let tires = document.getElementById("tire-change" + id).value;
    let pitStop = "";
    let places = document.getElementsByName('place-select' + id);

    for (let i = 0, length = places.length; i < length; i++) {
        if (places[i].checked) {
            pitStop = places[i].value;
            break;
        }
    }

    if (pitStop === "") {  document.getElementById("service-error" + id).hidden = false; } else {

        $.ajax({
            type: "POST",
            url: "/raceTime-mechanic/offerService",
            data: {
                "comment": comment,
                "place": pitStop,
                "fuel" : fuel,
                "tires" : tires,
                "id" : id
            },
            success: function (data) {
                if (data === "ok") {
                    document.getElementById("service-ready" + id).hidden = false; } else {
                    document.getElementById("service-not-enough" + id).hidden = false;
                }
            }
        });
    }

}