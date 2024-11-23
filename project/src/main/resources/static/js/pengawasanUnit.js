// const { response } = require("express");

document.addEventListener("DOMContentLoaded", () => {
    const checkIn = document.getElementById("check-in");
    const checkOut = document.getElementById("check-out");
    const searchButton = document.getElementById("searchButton");
    const table = document.getElementById("dynamicTable");

    searchButton.addEventListener("click", () =>{
        table.style.visibility = "visible";
    });

    // Ensure valid Check-in and Check-out dates
    checkIn.addEventListener("input", checkDates);
    checkOut.addEventListener("input", checkDates)
    
    // Ensure valid Check-in and Check-out dates
    checkIn.addEventListener("change", () => {
        validateDayModeDates();
        
        // updateCheckOut();
    });
    
    // Ensure valid Check-in and Check-out dates
    checkOut.addEventListener("change", () => {
        validateDayModeDates();
    });

    // Function to check if both dates are filled
    function checkDates() {
        if (checkIn.value && checkOut.value) {
            searchButton.style.visibility = "visible"; // Enable the button
        } else {
            searchButton.style.visibility = "hidden";  // Disable the button
        }
    }
    
    // Validate Day Mode Dates
    function validateDayModeDates() {
        const checkInDate = new Date(checkIn.value);
        const checkOutDate = new Date(checkOut.value);
    
        if (checkOut.value && checkOutDate < checkInDate) {
            checkOut.value = checkIn.value;
        }
    
        checkOut.setAttribute("min", checkIn.value);
    
        if (checkIn.value && checkInDate > checkOutDate) {
            checkOut.value = checkIn.value;
        }
    }
    
    // // Update Check-out Date for Month/Year Modes
    // function updateCheckOut() {
    //     if (!checkIn.value || mode === "day") return;
    
    //     const duration = parseInt(durationSelect.value, 10);
    //     const checkInDate = new Date(checkIn.value);
    //     let checkOutDate;
    
    //     // if (mode === "month") {
    //     //     checkOutDate = new Date(checkInDate);
    //     //     checkOutDate.setMonth(checkOutDate.getMonth() + duration);
    //     // } else if (mode === "year") {
    //     //     checkOutDate = new Date(checkInDate);
    //     //     checkOutDate.setFullYear(checkOutDate.getFullYear() + duration);
    //     // }
    
    //     checkOut.value = checkOutDate.toISOString().split("T")[0];
    // }

});

function fetchResponseToBE(event){
    let checkIn = document.getElementById("check-in").value;
    let checkOut = document.getElementById("check-out").value;

    if(checkIn== null || checkOut == null){
        alert('Ada yang belum diisi');
        return;
    }
    
    let data = {checkIn, checkOut};
     
    fetch('/atyp/search-between-dates',{
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(result => {
        console.log(result);
        // window.location.href ='/atyp/pengawasan-unit';
        
        if (result.success) {
            // On success, refresh the page or update the table
            // alert("Unit added successfully!");
            
            // location.reload(); // Reload the page to show updated data
        } else {
            alert("Error adding unit.");
        }
    })
    .catch(errpr =>{
        console.error("Error:", error);
        alert("An error occurred.");
    });
}