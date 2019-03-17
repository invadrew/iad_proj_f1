
var lastTime;

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

window.onload = function()
{

    $.ajax({
        type : "GET",
        url : "/raceTime-mechanic/time",
        success: function(data) {
            lastTime = data;
        }
    });

};


function getNews() {

        $.ajax({
            type: "GET",
            data: {"lastTime": lastTime},
            url: "/raceTime-racer/news",
            success: function (data) {
                if (data !== "nothing") {
                    if (data.startsWith("/garage")) {
                        window.location = data;
                    } else {
                        lastTime = data;
                        location.reload();
                    }
                }
            }
        });
}

setInterval(getNews,2000);