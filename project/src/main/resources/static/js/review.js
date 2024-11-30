document.addEventListener("DOMContentLoaded", function () {
    const reviewBody = document.querySelector(".review-body");
    const unitNameElement = document.querySelector(".unit-name");
    const form = document.querySelector(".review-form");
    const submitButton = document.querySelector(".submit-button");
    const container = document.querySelector(".container");
    const navbar = document.querySelector("navbar");

    // Function to handle enabling/disabling buttons
    const handleButtons = (rowElement, buttonType) => {
        const checkInButton = rowElement.querySelector(".checkin");
        const checkOutButton = rowElement.querySelector(".checkout");
        // checkOutButton.style.backgroundcolor = grey;
        const reviewButton = rowElement.querySelector(".review-button");

        if (buttonType === "checkin") {
            checkOutButton.disabled = false; // Enable "Check-out" when "Check-in" is clicked
        } else if (buttonType === "checkout") {
            reviewButton.disabled = false; // Enable "Review" when "Check-out" is clicked
        }
    };

    // Attach event listeners to Check-in buttons
    document.querySelectorAll(".checkin").forEach((button) => {
        button.addEventListener("click", function () {
            const row = button.closest("tr"); // Get the current row
            alert("Checked in!"); // Optional: Confirm action
            handleButtons(row, "checkin"); // Enable "Check-out"
            button.disabled = true; // Disable "Check-in"
        });
    });

    // Attach event listeners to Check-out buttons
    document.querySelectorAll(".checkout").forEach((button) => {
        button.addEventListener("click", function () {
            const row = button.closest("tr"); // Get the current row
            alert("Checked out!"); // Optional: Confirm action
            handleButtons(row, "checkout"); // Enable "Review"
            button.disabled = true; // Disable "Check-out"
        });
    });

    // Function to open the review pop-up
    window.openReviewPopup = function (button) {
        const row = button.closest("tr"); // Get the current row
        const tower = row.querySelector("td:nth-child(2)").textContent.split(" - ")[0];
        const floor = row.querySelector("td:nth-child(2)").textContent.split(" - ")[1];
        const number = row.querySelector("td:nth-child(2)").textContent.split(" - ")[2];

        // Update the unit name in the pop-up
        unitNameElement.textContent = `${tower} - Floor ${floor} - Unit ${number}`;

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

        reviewBody.classList.add("hidden");
        container.classList.remove("blur");
        navbar.classList.remove("blur");

        const [tower, floor, unit] = unitNameElement.textContent.split(" - ");
        const floorNumber = floor.replace("Floor ", "");
        const unitNumber = unit.replace("Unit ", "");
        
        // fetch("/submit-review", {
        //     method: "POST",
        //     headers: {
        //         "Content-Type": "application/json",
        //     },
        //     body: JSON.stringify({
        //         tower: tower.trim(),
        //         floor: floorNumber.trim(),
        //         number: unitNumber.trim(),
        //         rating: rating,
        //         review: reviewText.trim(),
        //     }),
        // })
        //     .then((response) => {
        //         if (response.ok) {
        //             alert("Review submitted successfully!");
        //             reviewBody.classList.add("hidden"); // Hide pop-up
        //             form.reset();
        //         } else {
        //             alert("Error submitting review. Please try again.");
        //         }
        //     })
        //     .catch((error) => {
        //         console.error("Error:", error);
        //         alert("An error occurred. Please try again.");
        //     });
    });
});
