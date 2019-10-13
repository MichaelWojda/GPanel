function showPlan(){
    var divs=document.getElementsByClassName("insideDiv");
    for(i=0;i<divs.length;i++){
        divs[i].style.display="none"
    }
    var odivs=document.getElementsByClassName("optionalDiv");
    for(i=0;i<odivs.length;i++){
        odivs[i].style.display="none"
    }
    document.getElementById("planDaySetUpRegular").style.display="block";
}
function showVisits(){
    var divs=document.getElementsByClassName("insideDiv");
    for(i=0;i<divs.length;i++){
        divs[i].style.display="none"
    }
    var odivs=document.getElementsByClassName("optionalDiv");
    for(i=0;i<odivs.length;i++){
        odivs[i].style.display="none"
    }
    document.getElementById("displayPlanOfTheDay").style.display="block";
}

