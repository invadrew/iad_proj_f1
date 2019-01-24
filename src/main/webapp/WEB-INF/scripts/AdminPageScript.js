function fieldsHandler() {

    var selectobject = document.getElementById("role-selector");

    var nameLab = document.getElementById("new-name-id");
    var nameInp = document.getElementById("new-name");
    var surLab = document.getElementById("new-sur-id");
    var surInp = document.getElementById("new-sur");
    var nazvLab = document.getElementById("new-nazv-id");
    var nazvInp = document.getElementById("new-nazv");

    if (selectobject.value === "Sponsor") {
        nazvInp.removeAttribute("hidden");
        nazvLab.removeAttribute("hidden");
        surInp.hidden = true;
        surLab.hidden = true;
        nameInp.hidden = true;
        nameLab.hidden = true;
    }

    if ((selectobject.value !== "Sponsor") && (selectobject.value !== "Admin")) {
        surInp.removeAttribute("hidden");
        surLab.removeAttribute("hidden");
        nameInp.removeAttribute("hidden");
        nameLab.removeAttribute("hidden");
        nazvLab.hidden = true;
        nazvInp.hidden = true;
    }

    if (selectobject.value === "Admin") {
        surInp.hidden = true;
        surLab.hidden = true;
        nameInp.hidden = true;
        nameLab.hidden = true;
        nazvLab.hidden = true;
        nazvInp.hidden = true;
    }
}