@echo off
:: 
set branchName = featCICOPelanggan
 
git checkout %branchName%
git add .
git commit -m "sync using script"
git push %branchName%

:: 
set branchName = featEditData
 
git checkout %branchName%
git add .
git commit -m "sync using script"
git push %branchName%

::
set branchName = featKelolaJadwal
 
git checkout %branchName%
git add .
git commit -m "sync using script"
git push %branchName%

::
set branchName = featPembayaran
 
git checkout %branchName%
git add .
git commit -m "sync using script"
git push %branchName%

::
set branchName = featPemesanan
 
git checkout %branchName%
git add .
git commit -m "sync using script"
git push %branchName%

::
set branchName = featPencarian
 
git checkout %branchName%
git add .
git commit -m "sync using script"
git push %branchName%

::
set branchName = featPengawasanCICO
 
git checkout %branchName%
git add .
git commit -m "sync using script"
git push %branchName%


::
set branchName = featPengawasanUnit
 
git checkout %branchName%
git add .
git commit -m "sync using script"
git push %branchName%

::
set branchName = featReview
 
git checkout %branchName%
git add .
git commit -m "sync using script"
git push %branchName%

::
set branchName = featUtilitas
 
git checkout %branchName%
git add .
git commit -m "sync using script"
git push %branchName%


pause