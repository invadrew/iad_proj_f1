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