(function(){

}())

//Fitur di Edit Data
function addToDataForm(){

}

function goToPemesanan(roomId) {
            // Redirect to the review page with the room ID
            // You may change the URL as per your application's routing
            // window.location.href = `pemesanan?roomId=${roomId}`;
            console.log('form-'+roomId);

            document.getElementById('form-'+roomId).submit();

        }

function goToPembayaran() {
            // Redirect to the review page with the room ID
            // You may change the URL as per your application's routing
            window.location.href = `pembayaran?`;
        }

function goToRiwayat(roomId) {
            // Redirect to the review page with the room ID
            // You may change the URL as per your application's routing
            window.location.href = `riwayat`;
        }