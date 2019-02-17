function sendSearch() {

    let toSearch =  $('#search').val();

    $.ajax({
        type: "GET",
        url: "/search",
        data: {
            "toSearch": toSearch
        }
        //success: window.location = '/search?toSearch=' + search
    });

}