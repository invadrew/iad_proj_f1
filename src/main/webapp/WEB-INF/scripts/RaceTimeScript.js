function getTime() {

    $.ajax({
        type : "GET",
        url : "/raceTime-mechanic/time",
        success: function(data) {
            document.getElementById("time").innerText = data;
        }
    });

}

setInterval(getTime,1000);