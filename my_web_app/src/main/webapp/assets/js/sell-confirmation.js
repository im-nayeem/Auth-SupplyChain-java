let count = 1;
// Get the modal
let soldModal = document.getElementById("sold-modal");

// Open the modal
function openModal() {
    soldModal.style.display = "block";
}

// Close the modal
function closeModal() {
    soldModal.style.display = "none";
}

// Confirm marking as sold
function confirmSold() {
    if(count<3)
    {
        count = count + 1;
        closeModal();
        alert("It can't be undone. Think and confirm by clicking 3 times.");
    }
    else
    {
        closeModal();
        document.getElementById("mark-sold").style.display = "block";
        document.getElementsByClassName("sold-button")[0].style.display = "none";

    }


}