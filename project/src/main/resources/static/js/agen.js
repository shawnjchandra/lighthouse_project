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

function addToDataForm(event) {
    // event.preventDefault();
    
    // Collect the form values
    let tower = document.querySelector("#tower-select").value;
    let lantai = document.querySelector("#floor").value;
    let nomor = document.querySelector("#unitNumber").value;
    let jenis = document.querySelector("#unitType").value;
    let tarifsewa = document.querySelector("#unitPrice").value;
    
    // Send data to the server using Fetch API
    let data = { tower, lantai, nomor, jenis, tarifsewa};
    
    fetch('/atyp/add-unit-to-form', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(result => {
        
        location.reload();
        // if (result.success) {
        //     // On success, refresh the page or update the table
        //     alert("Unit added successfully!");
        //     location.reload(); // Reload the page to show updated data
        // } else {
        //     alert("Error adding unit.");
        // }
    })
    .catch(error => {
        console.error("Error:", error);
        alert("An error occurred.");
    });
}

function openEditModal(button) {
    // Retrieve values from the selected row using data attributes
    let tower = button.getAttribute('data-tower');
    let floor = button.getAttribute('data-lantai');
    let unitNumber = button.getAttribute('data-nomor');
    let unitType = button.getAttribute('data-jenis');
    let price = button.getAttribute('data-tarif');

    console.log(price)

    // Set hidden input values (original values)
    document.getElementById('originalFloor').value = floor;
    document.getElementById('originalUnitNumber').value = unitNumber;
    document.getElementById('originalUnitType').value = unitType;
    document.getElementById('originalPrice').value = price;

    // Populate the form fields with the selected row's values
    document.getElementById('editTower').value = tower; // Non-editable
    document.getElementById('editFloor').value = floor; // Non-editable
    document.getElementById('editUnitNumber').value = unitNumber; // Editable
    document.getElementById('editUnitType').value = unitType; // Editable
    document.getElementById('editPrice').value = price; // Editable

    // Show the modal
    document.getElementById('editModal').style.display = 'block';
}


function editUnitData(tower, floor, unitNum, type) {
    // document.querySelector("#editTower").value = tower;
    document.querySelector("#editFloor").value = floor;
    document.querySelector("#editUnitNumber").value = unitNum;
    document.querySelector("#editUnitType").value = type;
    document.querySelector("#editModal").style.display = "block";
}

function closeEditModal() {
    document.querySelector("#editModal").style.display = "none";
}

function saveChanges(event) {
    // event.preventDefault();

    // Get updated values from the modal
    let originalFloor = document.getElementById('originalFloor').value;
    let originalUnitNumber = document.getElementById('originalUnitNumber').value;
    let originalUnitType = document.getElementById('originalUnitType').value;
    let originalPrice = document.getElementById('originalPrice').value ;

    let editTower = document.querySelector("#editTower").value;
    let editFloor = document.querySelector("#editFloor").value;
    let editUnitNumber = document.querySelector("#editUnitNumber").value;
    let editUnitType = document.querySelector("#editUnitType").value;
    let editPrice = document.querySelector("#editPrice").value;

    // Send the updated data to the server
    let data = {originalFloor,originalUnitNumber,originalUnitType,originalPrice,
         editTower, editFloor, editUnitNumber, editUnitType, editPrice };

    fetch('/atyp/save-unit', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(result => {
        console.log(result)

        location.reload();
        // if (result.success) {
        //     alert("Unit updated successfully!");
        //     location.reload(); // Reload page to reflect changes
        // } else {
        //     alert("Error saving changes.");
        // }
    })
    .catch(error => {
        console.error("Error:", error);
        alert("An error occurred while saving.");
    });

    // Close the modal after saving
    closeEditModal();
}


// Fitur Kelola Jadwal

function updateStatus(rowId){
    console.log(rowId);
    document.getElementById('changeStatus-'+rowId).submit();
}

function updateFilter(filterName, filter) {

    document.getElementById(filterName + '-filter').value = filter;
    console.log(filter);
    document.getElementById('filter-form').submit();
}


function validateDates() {
    const startDate = document.getElementById('start-date').value;
    const endDate = document.getElementById('end-date').value;
    console.log(startDate)
    console.log(endDate)
    // Only submit the form if both dates are filled
    if (startDate && endDate) {
        document.getElementById('filter-form').submit();
    }
}

function applyFilter(key, value) {
    // Get the current query string
    const urlParams = new URLSearchParams(window.location.search);

    // Set or update the filter
    if (value) {
        urlParams.set(key, value);
    } else {
        urlParams.delete(key);
    }

    // Redirect with the updated filters
    window.location.search = urlParams.toString();
}

function applyFilters() {
    const urlParams = new URLSearchParams(window.location.search);

    // Update filters based on form inputs
    const startDate = document.getElementById('start-date').value;
    const endDate = document.getElementById('end-date').value;
    const status = document.getElementById('status-filter').value;

    if (startDate && endDate) {
        urlParams.set('startDate', startDate);
        urlParams.set('endDate', endDate);
    } else {
        urlParams.delete('startDate');
        urlParams.delete('endDate');
    }

    if (status) {
        urlParams.set('status', status);
    } else {
        urlParams.delete('status');
    }

    // Redirect with the updated filters
    window.location.search = urlParams.toString();
}


// function applyFilters() {
//     let startDate = document.getElementById("start-date").value;
//     let endDate = document.getElementById("end-date").value;
//     let table = document.getElementById("availability-table");
//     let rows = table.getElementsByTagName("tr");

//     for (let i = 1; i < rows.length; i++) {
//         let codeCell = rows[i].getElementsByTagName("td")[0];
//         let startDateCell = rows[i].getElementsByTagName("td")[1];
//         let endDateCell = rows[i].getElementsByTagName("td")[2];
//         let statusCell = rows[i].getElementsByTagName("td")[3];
        
//         if (codeCell && startDateCell && endDateCell && statusCell) {
//             let rowCode = codeCell.textContent || codeCell.innerText;
//             let rowStartDate = startDateCell.textContent || startDateCell.innerText;
//             let rowEndDate = endDateCell.textContent || endDateCell.innerText;
//             let rowStatus = statusCell.textContent || statusCell.innerText;

//             let isWithinDateRange = (!startDate || rowStartDate >= startDate) && (!endDate || rowEndDate <= endDate);
//             let isApartmentMatch = currentApartmentFilter === 'APT' || rowCode === currentApartmentFilter;

//             // Check if the row is within the date range and if it is available
//             if (isApartmentMatch && isWithinDateRange) {
//                 rows[i].style.display = rowStatus === "Tersedia" ? "" : "none";
//             } else {
//                 rows[i].style.display = "none";
//             }
//         }
//     }

//     fetch('/atyp/filter-ketersediaan', {
//         method: 'POST',
//         headers: { 'Content-Type': 'application/json' },
//         body: JSON.stringify(data)
//     })
//     .then(response => response.json())
//     .then(result => {
//         console.log(result)

//         location.reload();
//         // if (result.success) {
//         //     alert("Unit updated successfully!");
//         //     location.reload(); // Reload page to reflect changes
//         // } else {
//         //     alert("Error saving changes.");
//         // }
//     })
//     .catch(error => {
//         console.error("Error:", error);
//         alert("An error occurred while saving.");
//     });

// }

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

