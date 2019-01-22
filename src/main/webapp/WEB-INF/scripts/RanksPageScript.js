function openRes(res) {
    var i;
    var x = document.getElementsByClassName("cuptab");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    document.getElementById(res).style.display = "block";
}