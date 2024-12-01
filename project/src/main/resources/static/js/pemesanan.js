document.addEventListener("DOMContentLoaded", () => {
    const checkIn = document.getElementById("check-in");
    const checkOut = document.getElementById("check-out");
    const submitBtn = document.getElementById("submit-btn");
    const basePrice = document.getElementById("base-price");
    const totalPrice = document.getElementById("total-price");

    // Ensure valid Check-in and Check-out dates
    checkIn.addEventListener("change", () => {
        validateDayModeDates();
        validatePriceChange();
    });

    checkOut.addEventListener("change", () => {
        validateDayModeDates();
        validatePriceChange();
    });

    // Submit button click handler
    submitBtn.addEventListener("click", () => {
        if (validateForm()) {
            goToPembayaran(); // Redirect function
        }
    });

    // Validate Day Mode Dates
    function validateDayModeDates() {
        let checkInDate = new Date(checkIn.value);
        let checkOutDate = new Date(checkOut.value);

        if (checkOut.value && checkOutDate < checkInDate) {
            checkOut.value = checkIn.value;
        }

        checkOut.setAttribute("min", checkIn.value);

        if (checkIn.value && checkInDate > checkOutDate) {
            checkOut.value = checkIn.value;
        }
    }

    // Helper function to validate the form
    function validateForm() {
        let checkInDate = new Date(checkIn.value);
        let checkOutDate = new Date(checkOut.value);

        if (!checkIn.value) {
            showError("Check-in date is required.");
            return false;
        }

        if (!checkOut.value) {
            showError("Check-out date is required.");
            return false;
        }

        if (checkOutDate <= checkInDate) {
            showError("Check-out date must be later than the check-in date.");
            return false;
        }

        return true;
    }

    // Function to calculate the duration in days
    function durationCalculation(checkInDate, checkOutDate) {
        return (checkOutDate - checkInDate) / (24 * 60 * 60 * 1000);
    }

    // Function to validate price changes
    function validatePriceChange() {
        // Retrieve the base price from the HTML element
        const basePrice = parseInt(document.getElementById("base-price").textContent.replace(/[^0-9]/g, ""), 10);

        // Parse the check-in and check-out dates
        let checkInDate = new Date(checkIn.value);
        let checkOutDate = new Date(checkOut.value);

        // Calculate duration using the separate function
        let duration = durationCalculation(checkInDate, checkOutDate);

        // Validate duration
        if (isNaN(duration) || duration <= 0) {
            totalPrice.textContent = "Total Price: Invalid duration";
            return;
        }

        // Calculate the total price
        let calculatedPrice = basePrice * duration;

        // Update the total price element
        totalPrice.textContent = `Total Price: $${calculatedPrice}`;
    }


    // Helper function to display error messages
    function showError(message) {
        alert(message); // Use alert for simplicity. Replace with custom error display if needed.
    }

    //redirect
    function goToPembayaran() {
        // Redirect to the review page with the room ID
        // You may change the URL as per your application's routing
        window.location.href = `pembayaran?`;
    }
});

