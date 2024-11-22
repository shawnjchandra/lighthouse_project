(function(){
    let doc = document.location.pathname;
    console.log(doc)
    switch(doc){
        case "/edit-data":
            editDataFunc();    
            break;
        case "/kelola-jadwal":
            const currentApartmentFilter = 'APT'; // Default to show all apartments
            filterByApartment(currentApartmentFilter);
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

    // 
}

// Fitur Kelola Jadwal


function filterByApartment(apartmentCode) {

    currentApartmentFilter = apartmentCode;
    applyFilters();
}

function applyFilters() {
    let startDate = document.getElementById("start-date").value;
    let endDate = document.getElementById("end-date").value;
    let table = document.getElementById("availability-table");
    let rows = table.getElementsByTagName("tr");

    for (let i = 1; i < rows.length; i++) {
        let codeCell = rows[i].getElementsByTagName("td")[0];
        let startDateCell = rows[i].getElementsByTagName("td")[1];
        let endDateCell = rows[i].getElementsByTagName("td")[2];
        let statusCell = rows[i].getElementsByTagName("td")[3];
        
        if (codeCell && startDateCell && endDateCell && statusCell) {
            let rowCode = codeCell.textContent || codeCell.innerText;
            let rowStartDate = startDateCell.textContent || startDateCell.innerText;
            let rowEndDate = endDateCell.textContent || endDateCell.innerText;
            let rowStatus = statusCell.textContent || statusCell.innerText;

            let isWithinDateRange = (!startDate || rowStartDate >= startDate) && (!endDate || rowEndDate <= endDate);
            let isApartmentMatch = currentApartmentFilter === 'APT' || rowCode === currentApartmentFilter;

            // Check if the row is within the date range and if it is available
            if (isApartmentMatch && isWithinDateRange) {
                rows[i].style.display = rowStatus === "Tersedia" ? "" : "none";
            } else {
                rows[i].style.display = "none";
            }
        }
    }
}

function checkIn(button) {
            button.disabled = true; // Disable checkin button
            const checkoutButton = button.parentElement.querySelector('.checkout');
            checkoutButton.disabled = false; // Enable checkout button
        }

function checkOut(button) {
            button.disabled = true; // Disable checkout button
            const checkinButton = button.parentElement.querySelector('.checkin');
            checkinButton.disabled = true; // Disable checkin button as well
        }

function goToReview(transactionId) {
            // Redirect to the review page with the transaction ID
            // You may change the URL as per your application's routing
            window.location.href = `review?transactionId=${transactionId}`;
        }

