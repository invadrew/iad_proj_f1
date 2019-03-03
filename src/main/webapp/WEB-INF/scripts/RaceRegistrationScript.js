function registrate(race, team) {

    let firstP = $('#firstPilotreg').val();
    let secondP = $('#secPilotreg').val();
    let firstC = $('#firstCarreg').val();
    let secondC = $('#secCarreg').val();

    if ( (firstP !== "-" && firstC !== "-") || ( secondP !== "-" && secondC !== "-" ) ) {

        if ( firstP === secondP ) {  document.getElementById("pil-err").hidden = false; } else {

            if ( firstC === secondC ) {  document.getElementById("car-err").hidden = false; } else {

                $.ajax({
                    type: "POST",
                    url: "/race-reg/registration",
                    data: {
                        "firstP" : firstP,
                        "firstC" : firstC,
                        "secondP" : secondP,
                        "secondC" : secondC,
                        "raceId" : race,
                        "team" : team
                    },
                    success: function (data) {
                        document.getElementById("ok").hidden = false;
                      //  location.reload();
                    }
                });

            }
        }

    } else {
        document.getElementById("empty-err").hidden = false;
    }
}