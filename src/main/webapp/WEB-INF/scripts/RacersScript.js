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

