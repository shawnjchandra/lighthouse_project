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
function showThirdPart() {
    let secondPart = document.getElementById('secondPart');
    let thirdPart = document.getElementById('thirdPart');

    // Check if the first part is visible and show the second part
    if (!secondPart.classList.contains('hidden')) {
        
        thirdPart.classList.toggle('hidden');
        secondPart.classList.toggle('hidden');
    }
}

function updateKelurahan() {
    let kecamatan = document.getElementById('namaKecamatan').value;

    console.log('masuk ke update');
    if(!kecamatan){
        document.getElementById('namaKelurahan').innerHTML = '<option value="" disabled selected>Select Kelurahan</option>';
        console.log("kecamatan");
        return;
    }

    // let xhr = new XMLHttpRequest();
    // xhr.open("GET", "/update-kelurahan?namaKecamatan="+kecamatan,true);
    // xhr.setRequestHeader("Content-Type","application/json");
    // xhr.onreadystatechange= function(){
    //     if(xhr.readyState == 4 && xhr.status == 200){

    //         console.log("xhr in")
    //         let kelurahanOptions = JSON.parse(xhr.responseText);
    //         let kelurahanSelect = document.getElementById('namaKelurahan');
    //         kelurahanSelect.innerHTML = '<option value="" disabled selected>Select Kelurahan</option>'; 

    //         kelurahanOptions.forEach(
    //             function(kelurahan){
    //                 let option = document.createElement('option');
    //                 option.value = kelurahan.namakel;
    //                 option.text = kelurahan.namakel;

    //                 kelurahanSelect.appendChild(option);
    //             }

    //         );

    //     }
    // };
    fetch("/update-kelurahan?namaKecamatan=" + kecamatan)
    .then(response => response.json())
    .then(kelurahanOptions => {
        var kelurahanSelect = document.getElementById('namaKelurahan');
        kelurahanSelect.innerHTML = '<option value="" disabled selected>Select Kelurahan</option>'; // Clear current options

        // Populate the kelurahan dropdown with new options
        kelurahanOptions.forEach(function (kelurahan) {
            var option = document.createElement('option');
            option.value = kelurahan.namakel;
            option.text = kelurahan.namakel;
            kelurahanSelect.appendChild(option);
        });
    })
    .catch(error => {
        console.error('Error fetching kelurahan:', error);
    });

}