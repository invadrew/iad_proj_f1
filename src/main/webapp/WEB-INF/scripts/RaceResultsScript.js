
window.onload = function () {
    getChamps();
};

function getChamps() {

    let year = $('#season_selector').val();
    let champ = $('#champ_selector');

    $.ajax({
        type: "GET",
        url: "/race-res/champs",
        data: {
            "year": year
        },
        success: function (data) {
            champ.find('option').remove();
            for (let i=0; i<data.length; i++) {
            champ.append($("<option></option>")
                    .attr("value",data[i])
                    .text(data[i]));
            }
            getData();
        }
    });
}

function getData() {

    let year = $('#season_selector').val();
    let champ = $('#champ_selector');
    let race = $('#race-table');
    let name = champ.val();
    race.find("tr:gt(0)").remove();

    $.ajax({
        type: "GET",
        url: "/race-res/data",
        data: {
            "year" : year,
            "name": name
        },
        success: function (data) {
            for (let i=0; i < data.length; i++) {
                let ref = '/profile?id=' + data[i][6];
                let refT = '/team?id=' + data[i][7];
                let $tr = $('<tr>').append(
                    $('<td>').text(data[i][0]),
                    $('<td>').append($('<a class="redirHref">').attr('href',ref).text(data[i][1] + data[i][2])),
                    $('<td>').append($('<a class="redirHref">').attr('href',refT).text(data[i][3])),
                    $('<td>').text(data[i][4]),
                    $('<td>').text(data[i][5]));
                race.append($tr);
            }
        }
    });

}