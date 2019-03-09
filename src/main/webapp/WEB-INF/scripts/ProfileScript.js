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