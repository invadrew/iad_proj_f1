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