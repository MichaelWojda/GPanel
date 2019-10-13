function showElement(element){
    document.getElementById(element).style.display="block";
}

function hideElement(element){
    document.getElementById(element).style.display="none";
}

function showMyDog(){
    showElement('mydog');
    hideElement('visits');
    hideElement('addDog');
}
function showVisits(){

    showElement('visits');
    hideElement('mydog');
    hideElement('addDog');

}
function showNewVisits(){

    showElement('newVisit');

}
function hideNewVisits(){

    hideElement('newVisit');

}
function addDog(){

    hideElement('visits');
    hideElement('mydog');
    showElement('addDog');


}
function searchCVisits(){
    var input,val,table,td,tr,i,filter,option;
    input=document.getElementById("searchCVisits");
    filter=input.value.toUpperCase();
    table=document.getElementById("cVisitsTable");
    tr=table.getElementsByTagName("tr");
    option=document.getElementById("selectCVisitsCriterium").value

    for(i=0;i< tr.length;i++){
        td=tr[i].getElementsByTagName("td")[option];
        if(td){
            val = td.textContent || td.innerText;
            if(val.toUpperCase().indexOf(filter) > -1){
                tr[i].style.display="";
            }
            else{
                tr[i].style.display="none";
            }

        }


    }



}
function searchHVisits(){
    var input,val,table,td,tr,i,filter,option;
    input=document.getElementById("searchHVisits");
    filter=input.value.toUpperCase();
    table=document.getElementById("hVisitsTable");
    tr=table.getElementsByTagName("tr");
    option=document.getElementById("selectHVisitsCriterium").value

    for(i=0;i< tr.length;i++){
        td=tr[i].getElementsByTagName("td")[option];
        if(td){
            val = td.textContent || td.innerText;
            if(val.toUpperCase().indexOf(filter) > -1){
                tr[i].style.display="";
            }
            else{
                tr[i].style.display="none";
            }

        }


    }
}
function searchCustomers(){
    var input,val,table,td,tr,i,filter,option;
    input=document.getElementById("searchCustomers");
    filter=input.value.toUpperCase();
    table=document.getElementById("customersTable");
    tr=table.getElementsByTagName("tr");
    option=document.getElementById("searchCustomersCriterium").value

    for(i=0;i< tr.length;i++){
        td=tr[i].getElementsByTagName("td")[option];
        if(td){
            val = td.textContent || td.innerText;
            if(val.toUpperCase().indexOf(filter) > -1){
                tr[i].style.display="";
            }
            else{
                tr[i].style.display="none";
            }

        }


    }
}
function searchDogs(){
    var input,val,table,td,tr,i,filter,option;
    input=document.getElementById("searchDogs");
    filter=input.value.toUpperCase();
    table=document.getElementById("dogsTable");
    tr=table.getElementsByTagName("tr");
    option=document.getElementById("searchDogsCriterium").value

    for(i=0;i< tr.length;i++){
        td=tr[i].getElementsByTagName("td")[option];
        if(td){
            val = td.textContent || td.innerText;
            if(val.toUpperCase().indexOf(filter) > -1){
                tr[i].style.display="";
            }
            else{
                tr[i].style.display="none";
            }

        }


    }
}

function passCheck(){

    var newPass = document.getElementById("newPass").value;
    var newPassConf = document.getElementById("newPassConf").value;
    if(newPass!==newPassConf){
        document.getElementById("passConfText").innerText="Hasła nie zgadzają się";
        document.getElementById("passChangeButton").disabled=true;
    }
    if(newPass===newPassConf){
        document.getElementById("passConfText").innerText="";
        document.getElementById("passChangeButton").disabled=false;
    }



}