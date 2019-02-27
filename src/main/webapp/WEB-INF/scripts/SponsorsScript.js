function sendMoney(id, sId) {

    let teamId = $('#teamId' + id).val();
    let allMoney = $('#allMoney').val();
    let moneySp = $('#moneySp' + id).val();
    let spId = $('#spId' + sId).val();

    let notEnoughMoney = document.getElementById("notEnough");
    let done = document.getElementById("done");

    notEnoughMoney.hidden = true;
    done.hidden = true;

    if ((moneySp <= allMoney) && (moneySp >= 0)) {

        $.ajax({
            type : "POST",
            url : "/sponsor/sponse",
            data : {
                "teamId" : teamId,
                "money" : moneySp,
                "spId" : spId
            },
            success: function(data) { done.hidden = false; location.reload();}
        });

    } else {
        notEnoughMoney.hidden = false;
    }

}

function openTeamAdd() {
    document.getElementById('addTeamButtonOpener').hidden = true;
    document.getElementById('addTeamSpForm').hidden = false;
}

function sendNewTeam(sId) {

    let no = document.getElementById("noTeam");

    no.hidden = true;

    let team = $('#teamToSponse').val();
    let spId = $('#spId' + sId).val();

    $.ajax({
        type : "POST",
        url : "/sponsor/new",
        data : {
            "teamName" : team ,
            "sponsor" : spId
        },
        success: function(data) {
            if (!data) {
                no.hidden = false;
            } else {
                location.reload();
            }
        }
    });

}