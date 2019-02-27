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