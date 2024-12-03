//TODO
//Rombak total buat bikin jadi perhari aja, hapus bagian perbulan dan pertahun

document.addEventListener("DOMContentLoaded", () => {
    const checkIn = document.getElementById("check-in");
    const checkOut = document.getElementById("check-out");
    const durationSelect = document.getElementById("duration");
    const form = document.getElementById("booking-form");
    const submitBtn = document.getElementById("submit-btn");

    // Ensure valid Check-in and Check-out dates (NOTE: berhubungan dengan togle modes)
    checkIn.addEventListener("change", updateCheckOut);
    checkOut.addEventListener("change", validateDayModeDates);

    durationSelect.addEventListener("change", updateCheckOut);

    // Validate Day Mode Dates (NOTE: berhubungan dengan togle modes)
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
    const mode = null;

    // Update Check-out Date for Month/Year Modes (NOTE: berhubungan dengan togle modes)
    function updateCheckOut() {
        if (!checkIn.value || mode === "day") return;

        const duration = parseInt(durationSelect.value, 10);
        const checkInDate = new Date(checkIn.value);
        let checkOutDate;

        if (mode === "month") {
            checkOutDate = new Date(checkInDate);
            checkOutDate.setMonth(checkOutDate.getMonth() + duration);
        } else if (mode === "year") {
            checkOutDate = new Date(checkInDate);
            checkOutDate.setFullYear(checkOutDate.getFullYear() + duration);
        }

        checkOut.value = checkOutDate.toISOString().split("T")[0];
    }

        //METHOD YANG TIDAK MENGGUNAKAN TOGGLE MODES, DILENGKAPI DENGAN ERROR MITIGATION
        // Helper function to display error messages
    
    // Helper function to validate the form
    function validateForm() {
        const checkInDate = new Date(checkIn.value);
        const checkOutDate = new Date(checkOut.value);

        if (!checkIn.value) {
            showError("Check-in date is required.");
            return false;
        }

        if (!checkOut.value) {
            showError("Check-out date is required.");
            return false;
        }

        if (checkOutDate <= checkInDate) {
            showError("Check-out date must be later than check-in date.");
            return false;
        }

        return true;
    }

    // Submit button click handler
    submitBtn.addEventListener("click", () => {
        if (validateForm()) {
            goToPembayaran(); // Redirect function
        }
    });

    const totalPriceContainer = document.querySelector(".total-price-container");
    const basePriceElement = document.getElementById("base-price");


    const basePrice = parseFloat(basePriceElement.querySelector("span").textContent.replace('Rp.', '').replace(',', '').trim()); // Assuming base price is in "Rp."


    checkIn.addEventListener("change", updatePrice);
    checkOut.addEventListener("change", updatePrice);

    // Function to calculate duration between check-in and check-out dates
    function calculateDuration(checkInDate, checkOutDate) {
        const timeDifference = checkOutDate - checkInDate;
        return timeDifference / (1000 * 3600 * 24); // Convert time difference to days
    }

    // Function to update total price based on duration
    function updatePrice() {
        if (!checkIn.value || !checkOut.value) return;

        const checkInDate = new Date(checkIn.value);
        const checkOutDate = new Date(checkOut.value);

        // Calculate the number of days
        const duration = calculateDuration(checkInDate, checkOutDate);

        // Validate that the check-out date is later than check-in
        if (duration <= 0) {
            showError("Check-out date must be later than check-in date.");
            return;
        }

        // Calculate the total price based on duration
        const totalPrice = duration * basePrice;

        // Update the total price display
        totalPriceContainer.querySelector("#total-price").textContent = `Total Price: Rp. ${totalPrice.toLocaleString()}`;
        
        const savePriceInput = document.getElementById("save-price");
        savePriceInput.value = totalPrice;
    }

    // Helper function to display error messages
    function showError(message) {
        alert(message); // You can replace this with a custom error message display
    }

    // Initial price update in case of pre-filled check-in/check-out values
    updatePrice();

});

//redirect
function goToPembayaran() {
    // Redirect to the review page with the room ID
    // You may change the URL as per your application's routing
    window.location.href = `pembayaran?`;
}


