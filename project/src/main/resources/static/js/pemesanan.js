document.addEventListener("DOMContentLoaded", () => {
    const dayBtn = document.getElementById("day-btn");
    const monthBtn = document.getElementById("month-btn");
    const yearBtn = document.getElementById("year-btn");
    const checkIn = document.getElementById("check-in");
    const checkOut = document.getElementById("check-out");
    const durationGroup = document.getElementById("duration-group");
    const durationSelect = document.getElementById("duration");
    const form = document.getElementById("booking-form");
    const submitBtn = document.getElementById("submit-btn");


    // Initialize mode
    let mode = null;

    // // ini fitur Toggle between modes, kalo jadi boleh dipake, kalo nggak jadi yaudah
    // const toggleButtons = [dayBtn, monthBtn, yearBtn];
    // toggleButtons.forEach((btn) => {
    //     btn.addEventListener("click", () => {
    //         toggleButtons.forEach((b) => b.classList.remove("active"));
    //         btn.classList.add("active");

    //         mode = btn.id === "day-btn" ? "day" : btn.id === "month-btn" ? "month" : "year";

    //         if (mode === "day") {
    //             checkIn.removeAttribute("readonly");
    //             checkOut.removeAttribute("readonly");
    //             durationGroup.style.display = "none";
    //             checkOut.value = "";
    //         } else {
    //             checkOut.setAttribute("readonly", true);
    //             durationGroup.style.display = "block";
    //             updateDuration(mode === "month" ? 12 : 3);
    //         }
    //     });
    // });

    // Ensure valid Check-in and Check-out dates (NOTE: berhubungan dengan togle modes)
    checkIn.addEventListener("change", () => {
        if (mode === "day") {
            validateDayModeDates();
        } else {
            updateCheckOut();
        }
    });

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

    // Update duration options for Month/Year Modes (NOTE: berhubungan dengan togle modes)
    function updateDuration(max) {
        durationSelect.innerHTML = "";
        for (let i = 1; i <= max; i++) {
            const option = document.createElement("option");
            option.value = i;
            option.textContent = `${i} ${mode}${i > 1 ? "s" : ""}`;
            durationSelect.appendChild(option);
        }
        durationSelect.value = 1; // Default duration
        updateCheckOut();
    }

    

        //METHOD YANG TIDAK MENGGUNAKAN TOGGLE MODES, DILENGKAPI DENGAN ERROR MITIGATION
        // Helper function to display error messages
    function showError(message) {
        alert(message); // Use alert for simplicity. Replace with custom error display if needed.
    }

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

});

//redirect
function goToPembayaran() {
    // Redirect to the review page with the room ID
    // You may change the URL as per your application's routing
    window.location.href = `pembayaran?`;
}


