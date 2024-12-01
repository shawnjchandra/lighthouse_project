document.addEventListener("DOMContentLoaded", function () {
    const reviewBody = document.querySelector(".review-body");
    const unitNameElement = document.querySelector(".unit-name");
    const form = document.querySelector(".review-form");
    const submitButton = document.querySelector(".submit-button");
    const container = document.querySelector(".container");
    const navbar = document.querySelector(".navbar");

    // Function to enable/disable buttons and update their styles
    const updateButtonState = (button, isDisabled) => {
        button.disabled = isDisabled;
        if (isDisabled) {
            button.style.backgroundColor = "grey";
            button.style.cursor = "not-allowed";
        } else {
            button.style.backgroundColor = ""; // Reset to default
            button.style.cursor = "pointer";
        }
    };

    // Disable all Check-out and Review buttons on page load
    document.querySelectorAll(".checkout, .review-button").forEach((button) => {
        updateButtonState(button, true); // Ensure disabled state and styling
    });

    // Function to handle enabling/disabling buttons
    const handleButtons = (rowElement, buttonType) => {
        const checkInButton = rowElement.querySelector(".checkin");
        const checkOutButton = rowElement.querySelector(".checkout");
        const reviewButton = rowElement.querySelector(".review-button");

        if (buttonType === "checkin") {
            updateButtonState(checkOutButton, false); // Enable "Check-out"
        } else if (buttonType === "checkout") {
            updateButtonState(reviewButton, false); // Enable "Review"
        }
    };

    // Attach event listeners to Check-in buttons
    document.querySelectorAll(".checkin").forEach((button) => {
        button.addEventListener("click", function () {
            const row = button.closest("tr"); // Get the current row
            alert("Checked in!"); // Optional: Confirm action
            handleButtons(row, "checkin"); // Enable "Check-out"
            updateButtonState(button, true); // Disable "Check-in"
        });
    });

    // Attach event listeners to Check-out buttons
    document.querySelectorAll(".checkout").forEach((button) => {
        button.addEventListener("click", function () {
            const row = button.closest("tr"); // Get the current row
            alert("Checked out!"); // Optional: Confirm action
            handleButtons(row, "checkout"); // Enable "Review"
            updateButtonState(button, true); // Disable "Check-out"
        });
    });

    // Attach event listeners to review buttons
    // document.querySelectorAll(".review").forEach((button) => {
    //     button.addEventListener("click", function () {
    //         const row = button.closest("tr"); // Get the current row
    //         // alert("Checked out!"); // Optional: Confirm action
    //         // handleButtons(row, "checkout"); // Enable "Review"
    //         updateButtonState(button, true); // Disable "Check-out"
    //     });
    // });     

    // Function to open the review pop-up
    window.openReviewPopup = function (button) {
        const tower = button.getAttribute("data-tower");
        const floor = button.getAttribute("data-lantai");
        const unitNumber = button.getAttribute("data-nomor");
        const unitType = button.getAttribute("data-jenis");
        const idtrsk = button.getAttribute("data-trsk");
        
        // Update the unit name in the pop-up
        unitNameElement.textContent = `${tower}-${floor}-${unitNumber} (${unitType})`;

        //update the trsk value
        const hiddenInput = document.querySelector(".trsk");
        hiddenInput.value = idtrsk;

        // Show the review pop-up
        reviewBody.classList.remove("hidden");
        container.classList.add("blur");
        navbar.classList.add("blur");
    };

    // Submit button functionality for the pop-up
    submitButton.addEventListener("click", function (event) {
        event.preventDefault();

        const rating = form.querySelector("input[name='rating']:checked")?.value || null;
        const reviewText = form.querySelector("textarea[name='review']").value;

        if (!rating) {
            alert("Please select a rating!");
            return;
        }

        // Reset and close the review pop-up
        reviewBody.classList.add("hidden");
        container.classList.remove("blur");
        navbar.classList.remove("blur");
        form.reset();
        alert("Review submitted successfully!");
    });
});
