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

    let uType = $('#role-selector').val();
    let login = $('#new-login').val();
    let pass = $('#new-pass').val();

    switch (uType) {
        case ("Sponsor"):

            let nazv = $('#new-nazv').val();
            let budget = $('#new-budg').val();

            if (nazv.trim() === "" || login.trim() === "" || pass.trim() === "") {

                document.getElementById("error").hidden = false;

            } else {

                $.ajax({
                    type : "POST",
                    url : "/admin/regSponsor",
                    data : {
                        "login" :  login,
                        "passw" : pass,
                        "name" : nazv,
                        "budget" : budget
                    },
                    success:  function (data) {
                        if (data === "ok") {
                        document.getElementById("reg-ready").hidden = false; } else {
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
                    type : "POST",
                    url : "/admin/regAdmin",
                    data : {
                        "login" :  login,
                        "passw" : pass
                    },
                    success:  function (data) {
                        if (data === "ok") {
                            document.getElementById("reg-ready").hidden = false; } else {
                            document.getElementById("busy").hidden = false;
                        }
                    }
                });

            }
            break;

        default:

            break;
    }

}