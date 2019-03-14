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
                    }
                }
            });
        }
    }

}