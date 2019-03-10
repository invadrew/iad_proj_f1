function sendTeamRequest(id) {

    let teamName = $('#crNewTeam').val();

    document.getElementById("teamSent").hidden = true;
    document.getElementById("teamEmpty").hidden = true;
    document.getElementById("teamBusy").hidden = true;

    if (!teamName) {
        document.getElementById("teamEmpty").hidden = false;
    } else {

        $.ajax({
            type : "POST",
            url : "/profile/addTeam",
            data : {
                "name" : teamName,
                "id" : id
            },
            success: function(data) {
                if (data === "ok") {
                    document.getElementById("teamSent").hidden = false;
                } else {
                    document.getElementById("teamBusy").hidden = false;
                }
            }
        });

    }

}

function giveBuyAbility() {

    let user = $('#chooseToGiveBuy').val();

    $.ajax({
        type: "POST",
        url: "/profile/givePermission",
        data: {
            "user" : user
        },
        success: function (data) {
            location.reload();
        }

    });
}

function requestBuyAbility() {

    let name = $('#askButton').val();
    let comm = $('#bpComm').val();

    $.ajax({
        type: "POST",
        url: "/profile/askPermission",
        data: {
            "name" : name,
            "comment" : comm
        },
        success: function (data) {
            document.getElementById("bpreqSent").hidden = false;
        }

    });

}

function confirmBuyAbility(id, status) {

    let comment = $('#bpSComm' + id).val();

    $.ajax({
        type: "POST",
        url: "/profile/confirmPermission",
        data: {
            "id" : id,
            "comment" : comment,
            "status" : status
        },
        success: function (data) {
            document.getElementById("bpRow"+ id).hidden = true;
            location.reload();
        }

    });

}

function confirmCar(id, status) {

    let comment = $('#carComm' + id).val();

    $.ajax({
        type: "POST",
        url: "/profile/confirmCar",
        data: {
            "id" : id,
            "comment" : comment,
            "status" : status
        },
        success: function (data) {
            location.reload();
        }

    });

}

function confirmDetailCarc(status, id, budget) {

    let price = $('#carcPrice' + id).val();
    let comment = $('#carcComm' + id).val();

    if ((price > budget) && (status)) { document.getElementById("carcNotE" + id).hidden = false; } else {

        $.ajax({
            type: "POST",
            url: "/profile/confirmCarcase",
            data: {
                "id": id,
                "comment": comment,
                "status": status
            },
            success: function (data) {
                location.reload();
            }

        });
    }

}

function confirmDetailChass(status, id, budget) {

    let price = $('#chassPrice' + id).val();
    let comment = $('#chassComm' + id).val();

    if ((price > budget) && (status)) { document.getElementById("chassNotE" + id).hidden = false; } else {

        $.ajax({
            type: "POST",
            url: "/profile/confirmChassis",
            data: {
                "id": id,
                "comment": comment,
                "status": status
            },
            success: function (data) {
                location.reload();
            }

        });
    }

}


function confirmDetailEng(status, id, budget) {

    let price = $('#engPrice' + id).val();
    let comment = $('#engComm' + id).val();

    if ((price > budget) && (status)) { document.getElementById("engNotE" + id).hidden = false; } else {

        $.ajax({
            type: "POST",
            url: "/profile/confirmEngine",
            data: {
                "id": id,
                "comment": comment,
                "status": status
            },
            success: function (data) {
                location.reload();
            }

        });
    }

}

function confirmDetailElec(status, id, budget) {

    let price = $('#elecPrice' + id).val();
    let comment = $('#elecComm' + id).val();

    if ((price > budget) && (status)) { document.getElementById("elecNotE" + id).hidden = false; } else {

        $.ajax({
            type: "POST",
            url: "/profile/confirmElectronics",
            data: {
                "id": id,
                "comment": comment,
                "status": status
            },
            success: function (data) {
                location.reload();
            }

        });
    }
}

function confirmTeamMember(id, status) {

    $.ajax({
        type: "POST",
        url: "/profile/confirmTeamMember",
        data: {
            "id": id,
            "status": status
        },
        success: function (data) {
            document.getElementById("tmRow" + id).hidden = true;
            location.reload();
        }

    });

}