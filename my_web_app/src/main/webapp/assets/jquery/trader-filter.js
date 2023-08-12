$(document).ready(function() {
    $("#filterName, #filterNid, #filterEmail, #filterRole, #filterAddress").on("input", function() {
        filterTraders();
    });
});

function filterTraders() {
    const filterName = $("#filterName").val().toUpperCase();
    const filterNid = $("#filterNid").val().toUpperCase();
    const filterEmail = $("#filterEmail").val().toUpperCase();
    const filterRole = $("#filterRole").val().toUpperCase();
    const filterAddress = $("#filterAddress").val().toUpperCase();

    $(".trader-row").each(function() {
        const nameMatch = $(this).find(".trader-name").text().toUpperCase().indexOf(filterName) > -1;
        const nidMatch = $(this).find(".trader-nid").text().toUpperCase().indexOf(filterNid) > -1;
        const emailMatch = $(this).find(".trader-email").text().toUpperCase().indexOf(filterEmail) > -1;
        const roleMatch = $(this).find(".trader-role").text().toUpperCase().indexOf(filterRole) > -1;
        const addressMatch = $(this).find(".trader-address").text().toUpperCase().indexOf(filterAddress) > -1;

        if (nameMatch && nidMatch && emailMatch && roleMatch && addressMatch) {
            $(this).show();
        } else {
            $(this).hide();
        }
    });
}
