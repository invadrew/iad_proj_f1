function showRemoveAgain() {

    document.getElementById("leaveTeam").hidden = false;
    document.getElementById("remove-sure").hidden = true;
    document.getElementById("remove-yes").hidden = true;
    document.getElementById("remove-no").hidden = true;

}

function leaveTeamNow() {

    $.ajax({
        type: "GET",
        url: "/team/leave",
        success: function () {
            window.location.replace("/profile");
        }
    });

}

function areYouSureToLeave() {

    document.getElementById("leaveTeam").hidden = true;
    document.getElementById("remove-sure").hidden = false;
    document.getElementById("remove-yes").hidden = false;
    document.getElementById("remove-no").hidden = false;

}

function joinTeamRequest(id) {

    $.ajax({
        type: "POST",
        url: "/team/join",
        data: {
          "teamId" : id
        },
        success: function () {
            document.getElementById("join-sent").hidden = false;
        }
    });

}