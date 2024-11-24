/*
    Bagian Login - Register
*/ 

function showSecondPart() {
    let firstPart = document.getElementById('firstPart');
    let secondPart = document.getElementById('secondPart');
    console.log("first")
    // Check if the first part is visible and show the second part
    if (!firstPart.classList.contains('hidden')) {
        
        firstPart.classList.toggle('hidden');
        secondPart.classList.toggle('hidden');
    }
}