// Handle switching between "Book for Myself" and "Book for Others"
document.getElementById("for-myself").addEventListener("change", function () {
    toggleGuestInfoSection(false);
});
document.getElementById("for-others").addEventListener("change", function () {
    toggleGuestInfoSection(true);
});

function toggleGuestInfoSection(show) {
    const guestInfoSection = document.querySelector(".guest-info");
    if (show) {
        guestInfoSection.style.display = "block";
    } else {
        guestInfoSection.style.display = "none";
    }
}

// Show payment method section when terms are accepted
document.getElementById("terms-checkbox").addEventListener("change", function (e) {
    const paymentMethodPart = document.querySelector(".payment-method-part");
    const submitButton = document.querySelector(".submit-btn");
    if (e.target.checked) {
        paymentMethodPart.style.display = "block";
        submitButton.style.display = "block";
    } else {
        paymentMethodPart.style.display = "none";
        submitButton.style.display = "none";
    }
});

// Toggle payment details based on payment method selection
document.querySelectorAll("input[name='paymentMethod']").forEach((input) => {
    input.addEventListener("change", function (e) {
        const paymentDetailsPart = document.querySelector(".payment-details-part");
        const creditCardDetails = document.querySelector(".credit-card-details");
        const bankTransferDetails = document.querySelector(".bank-transfer-details");
        if (e.target.value === "creditCard") {
            creditCardDetails.style.display = "block";
            bankTransferDetails.style.display = "none";
        } else if (e.target.value === "bankTransfer") {
            creditCardDetails.style.display = "none";
            bankTransferDetails.style.display = "block";
        }
        paymentDetailsPart.style.display = "block";
    });
});

