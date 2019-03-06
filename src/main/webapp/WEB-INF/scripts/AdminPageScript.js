function fieldsHandler() {

    var selectobject = document.getElementById("role-selector");

    var budgInp = document.getElementById("new-budg");
    var budgLab = document.getElementById("new-budg-id");
    var nameLab = document.getElementById("new-name-id");
    var nameInp = document.getElementById("new-name");
    var surLab = document.getElementById("new-sur-id");
    var surInp = document.getElementById("new-sur");
    var nazvLab = document.getElementById("new-nazv-id");
    var nazvInp = document.getElementById("new-nazv");

    if (selectobject.value === "Sponsor") {
        nazvInp.removeAttribute("hidden");
        nazvLab.removeAttribute("hidden");
        budgLab.removeAttribute("hidden");
        budgInp.removeAttribute("hidden");
        surInp.hidden = true;
        surLab.hidden = true;
        nameInp.hidden = true;
        nameLab.hidden = true;
    }

    if ((selectobject.value !== "Sponsor") && (selectobject.value !== "Admin")) {
        surInp.removeAttribute("hidden");
        surLab.removeAttribute("hidden");
        nameInp.removeAttribute("hidden");
        nameLab.removeAttribute("hidden");
        nazvLab.hidden = true;
        nazvInp.hidden = true;
        budgInp.hidden = true;
        budgLab.hidden = true;
    }

    if (selectobject.value === "Admin") {
        surInp.hidden = true;
        surLab.hidden = true;
        nameInp.hidden = true;
        nameLab.hidden = true;
        nazvLab.hidden = true;
        nazvInp.hidden = true;
        budgInp.hidden = true;
        budgLab.hidden = true;
    }
}

function addUser() {

    document.getElementById("reg-ready").hidden = true;
    document.getElementById("error").hidden = true;
    document.getElementById("busy").hidden = true;

    let uType = $('#role-selector').val();
    let login = $('#new-login').val();
    let pass = $('#new-pass').val();
    let roles = ["Mechanic","Racer","Sponsor","Manager"];

    switch (uType) {
        case ("Sponsor"):

            let nazv = $('#new-nazv').val();
            let budget = $('#new-budg').val();

            if (nazv.trim() === "" || login.trim() === "" || pass.trim() === "") {

                document.getElementById("error").hidden = false;

            } else {

                $.ajax({
                    type: "POST",
                    url: "/admin/regSponsor",
                    data: {
                        "login": login,
                        "passw": pass,
                        "name": nazv,
                        "budget": budget
                    },
                    success: function (data) {
                        if (data === "ok") {
                            document.getElementById("reg-ready").hidden = false;
                        } else {
                            document.getElementById("busy").hidden = false;
                        }
                    }
                });
            }

            break;

        case ("Admin"):

            if (login.trim() === "" || pass.trim() === "") {
                document.getElementById("error").hidden = false;
            } else {

                $.ajax({
                    type: "POST",
                    url: "/admin/regAdmin",
                    data: {
                        "login": login,
                        "passw": pass
                    },
                    success: function (data) {
                        if (data === "ok") {
                            document.getElementById("reg-ready").hidden = false;
                        } else {
                            document.getElementById("busy").hidden = false;
                        }
                    }
                });

            }
            break;
    }
        if (roles.includes(uType)) {

            let memberName = $('#new-name').val();
            let memberSurname = $('#new-sur').val();

            if ((login.trim() === "") || (pass.trim() === "") || (memberName.trim() === "") || (memberSurname.trim() === "")) {
                document.getElementById("error").hidden = false;
            } else {

                $.ajax({
                    type : "POST",
                    url : "/admin/regTeamMember",
                    data : {
                        "login" : login,
                        "passw" : pass,
                        "name" : memberName,
                        "surname" : memberSurname,
                        "type" : uType
                    },
                    success:  function (data) {
                        if (data === "ok") {
                            document.getElementById("reg-ready").hidden = false; } else {
                            document.getElementById("busy").hidden = false;
                        }
                    }
                });

            }
    }

}

function addRace() {

    let date = $('#race-date').val();
    let champ = $('#champ').val();
    let track = $('#track').val();
    let raceNum = $('#race-num').val();
    let teamNum = $('#team-num').val();
    let country = $('#country').val();

    let now = new Date();
    let currDate = new Date(date);

    document.getElementById("race-ready").hidden = true;
    document.getElementById("race-error").hidden = true;
    document.getElementById("too-early").hidden = true;

    if (!date || !champ || !track || !raceNum || !teamNum || !country) {
        document.getElementById("race-error").hidden = false;
    } else {

        if ((currDate < now)) {
            document.getElementById("too-early").hidden = false;
        } else {

            $.ajax({
                type: "POST",
                url: "/admin/addRace",
                data: {
                    "date": date,
                    "champ": champ,
                    "track": track,
                    "raceNum": raceNum,
                    "teamNum": teamNum,
                    "country" : country
                },
                success: function (data) {
                    document.getElementById("race-ready").hidden = false;
                }
            });
        }
    }
}

function handleTeamRequest(team, status, sender) {

    let comment = $('#tComment' + team).val();

    $.ajax({
        type: "POST",
        url: "/admin/handleTeamRequest",
        data: {
            "comment" : comment,
            "status" : status,
            "teamid" : team,
            "senderid" : sender
        },
        success: function (data) {
            document.getElementById("tRow"+ team).hidden = true;
        }
    });

}