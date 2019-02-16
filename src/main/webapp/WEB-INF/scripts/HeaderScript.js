function sendSearch() {

    let search = document.getElementById('search').value;

    $.ajax({
        type: "GET",
        url: "/search",
        data: {
            "toSearch": search
        },
        success: window.location = '/search?toSearch' + search
    });

}