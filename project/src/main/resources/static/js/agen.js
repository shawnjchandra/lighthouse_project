(function(){
    let doc = document.location.pathname;

    switch(doc){
        case "/edit-data":
            editDataFunc();    
            break;
    }
    
}())

function editDataFunc(){
    document.querySelector("#unitForm").addEventListener("submit",addToDataForm);

}

//Fitur di Edit Data
function addToDataForm(event){
            console.log("edit data")

    event.preventDefault();

    let tower = document.querySelector("#tower-select").value; 
    let floor = document.querySelector("#floor").value; 
    let unitNum = document.querySelector("#unitNumber").value; 
    let type = document.querySelector("#unitType").value; 

    let tbody = document.querySelector("#unitTable tbody");
    let newRow =document.createElement("tr");

    newRow.innerHTML = 
                `<td>${tower}</td>
                <td>${floor}</td>
                <td>${unitNum}</td>
                 <td>${type}</td>
                 <td>
                    <button onclick="editUnitData('${tower}',${floor},${unitNum},${type})">Edit</button> 
                 </td>`
    ;

    tbody.appendChild(newRow);

    document.querySelector("#unitForm").reset();
}

function editUnitData(tower,floor,unitNum,type){
    document.querySelector("#editTower").value = tower;
    document.querySelector("#editFloor").value = floor;
    document.querySelector("#editUnitNumber").value = unitNum;
    document.querySelector("#editUnitType").value = type;

    document.querySelector("#editModal").style.display = "block";
}

function closeEditModal(){
    document.querySelector("#editModal").style.display = "none";

}

function saveChanges(){
    let editTower = document.querySelector("#editTower").value;
    let editFloor = document.querySelector("#editFloor").value;
    let editUnitNum = document.querySelector("#editUnitNumber").value;
    let editType = document.querySelector("#editUnitType").value;

}