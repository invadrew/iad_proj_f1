
function openRes(res) {
    year = $('#season_selector').val();
    let i;
    let x = document.getElementsByClassName("infotab");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    document.getElementById(res).style.display = "block";
    if (res === 'WorldCup') {
    getSeasonWorldInfo(); } else {
        getConstrInfo();
    }
}

function getSeasonWorldInfo() {
    let world = $('#worldTable');
    world.find("tr:gt(0)").remove();
    $.ajax({
        type : "GET",
        url : "/ranks/world",
        data : {
            "year" : year
        },
        success: function(data) {
            $('#worldHeader').text('Кубок мира').append(' '+ year);
            for (let i=0; i < data.length; i++) {
                let ref = '/profile?id=' + data[i][5];
                let refT = '/team?id=' + data[i][6];
                let $tr = $('<tr>').append(
                    $('<td>').text(data[i][0]),
                    $('<td>').append($('<a class="redirHref">').attr('href',ref).text(data[i][1] + data[i][2])),
                    $('<td>').append($('<a class="redirHref">').attr('href',refT).text(data[i][3])),
                    $('<td>').text(data[i][4]));
                world.append($tr);
            }
        }
    });
}

function getConstrInfo() {

    let constr = $('#constrTable');
    constr.find("tr:gt(0)").remove();
    $.ajax({
        type : "GET",
        url : "/ranks/constr",
        data : {
            "year" : year
        },
        success: function(data) {
            $('#constrHeader').text('Кубок конструкторов').append(' ' + year);
            for (let i=0; i < data.length; i++) {
                let refT = '/team?id=' + data[i][3];
                let $tr = $('<tr>').append(
                    $('<td>').text(data[i][0]),
                    $('<td>').append($('<a class="redirHref">').attr('href',refT).text(data[i][1])),
                    $('<td>').text(data[i][2]));
                constr.append($tr);
            }
        }
    });

}