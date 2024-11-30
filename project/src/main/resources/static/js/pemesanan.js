//TODO
//Rombak total buat bikin jadi perhari aja, hapus bagian perbulan dan pertahun

document.addEventListener("DOMContentLoaded", () => {
    const dayBtn = document.getElementById("day-btn");
    const monthBtn = document.getElementById("month-btn");
    const yearBtn = document.getElementById("year-btn");
    const checkIn = document.getElementById("check-in");
    const checkOut = document.getElementById("check-out");
    const durationGroup = document.getElementById("duration-group");
    const durationSelect = document.getElementById("duration");

    // Initialize mode
    let mode = null;

    // Toggle between modes
    const toggleButtons = [dayBtn, monthBtn, yearBtn];
    toggleButtons.forEach((btn) => {
        btn.addEventListener("click", () => {
            toggleButtons.forEach((b) => b.classList.remove("active"));
            btn.classList.add("active");

            mode = btn.id === "day-btn" ? "day" : btn.id === "month-btn" ? "month" : "year";

            if (mode === "day") {
                checkIn.removeAttribute("readonly");
                checkOut.removeAttribute("readonly");
                durationGroup.style.display = "none";
                checkOut.value = "";
            } else {
                checkOut.setAttribute("readonly", true);
                durationGroup.style.display = "block";
                updateDuration(mode === "month" ? 12 : 3);
            }
        });
    });

    // Ensure valid Check-in and Check-out dates
    checkIn.addEventListener("change", () => {
        if (mode === "day") {
            validateDayModeDates();
        } else {
            updateCheckOut();
        }
    });

    checkOut.addEventListener("change", validateDayModeDates);

    durationSelect.addEventListener("change", updateCheckOut);

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

    // Update Check-out Date for Month/Year Modes
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

    // Update duration options for Month/Year Modes
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
});
