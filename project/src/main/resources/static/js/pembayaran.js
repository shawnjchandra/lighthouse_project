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


//method test
document.addEventListener("DOMContentLoaded", () => {
    // Select elements
    const bookingTypeInputs = document.querySelectorAll('input[name="booking-type"]');
    const guestInfoSection = document.querySelector(".guest-info");
    const termsCheckbox = document.getElementById("terms-checkbox");
    const paymentMethodPart = document.querySelector(".payment-method-part");
    const paymentDetailsPart = document.querySelector(".payment-details-part");
    const paymentMethodInputs = document.querySelectorAll('input[name="paymentMethod"]');
    const creditCardDetails = document.querySelector(".credit-card-details");
    const bankTransferDetails = document.querySelector(".bank-transfer-details");
    const submitBtn = document.querySelector(".submit-btn");

    // Function to toggle guest info section
    // function toggleGuestInfo() {
    //     const selectedType = document.querySelector('input[name="booking-type"]:checked').value;
    //     guestInfoSection.style.display = selectedType === "others" ? "block" : "none";
    // }

    // Function to toggle payment details section
    // function togglePaymentDetails() {
    //     const selectedPaymentMethod = document.querySelector('input[name="paymentMethod"]:checked')?.value;
    //     creditCardDetails.style.display = selectedPaymentMethod === "creditCard" ? "block" : "none";
    //     bankTransferDetails.style.display = selectedPaymentMethod === "bankTransfer" ? "block" : "none";
    //     paymentDetailsPart.style.display = selectedPaymentMethod ? "block" : "none";
    //     submitBtn.style.display = selectedPaymentMethod ? "inline-block" : "none";
    // }

    // Function to validate the form
    function validateForm() {
        const tenantInputs = document.querySelectorAll(".tenant-info input[required]");
        const guestInputs = guestInfoSection.querySelectorAll("input[required]");
        const creditCardInputs = creditCardDetails.querySelectorAll("input[required]");
        const bankTransferInputs = bankTransferDetails.querySelectorAll("input[required]");

        let isValid = true;

        // Validate tenant info fields
        tenantInputs.forEach((input) => {
            if (!input.value.trim()) {
                input.classList.add("error");
                isValid = false;
            } else {
                input.classList.remove("error");
            }
        });

        // Validate guest info fields if "Book for Others" is selected
        if (guestInfoSection.style.display === "block") {
            guestInputs.forEach((input) => {
                if (!input.value.trim()) {
                    input.classList.add("error");
                    isValid = false;
                } else {
                    input.classList.remove("error");
                }
            });
        }

        // Validate payment method fields
        if (creditCardDetails.style.display === "block") {
            creditCardInputs.forEach((input) => {
                if (!input.value.trim()) {
                    input.classList.add("error");
                    isValid = false;
                } else {
                    input.classList.remove("error");
                }
            });
        }

        if (bankTransferDetails.style.display === "block") {
            bankTransferInputs.forEach((input) => {
                if (!input.value.trim()) {
                    input.classList.add("error");
                    isValid = false;
                } else {
                    input.classList.remove("error");
                }
            });
        }

        // Validate terms checkbox
        if (!termsCheckbox.checked) {
            alert("You must agree to the terms and conditions.");
            isValid = false;
        }

        return isValid;
    }

    // Add event listeners
    // bookingTypeInputs.forEach((input) => {
    //     input.addEventListener("change", toggleGuestInfo);
    // });

    // paymentMethodInputs.forEach((input) => {
    //     input.addEventListener("change", togglePaymentDetails);
    // });

    submitBtn.addEventListener("click", (e) => {
        e.preventDefault();
        if (!validateForm()) {
            // Redirect to payment history
            // goToRiwayat();
            alert("Please complete all required fields.");
        } 
    });

    // Initialize on load
    // toggleGuestInfo();
    // togglePaymentDetails();
});

function submitForm(){
    document.getElementById('paymentForm').submit();
}

// function goToRiwayat(roomId) {
//     // Redirect to the review page with the room ID
//     // You may change the URL as per your application's routing
//     window.location.href = `riwayat`;
// }
