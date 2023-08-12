$(document).ready(function() {
    $("#filter-id, #filter-batch, #filter-status").on("input", function() {
        productFilter();
    });
});

function productFilter() {
    const filterId = $("#filter-id").val().toUpperCase();
    const filterBatch = $("#filter-batch").val().toUpperCase();
    const filterStatus = $("#filter-status").val().toUpperCase();

    $(".product-row").each(function() {
        const idMatch = $(this).find(".product-name").text().toUpperCase().includes(filterId);
        const batchMatch = $(this).find(".product-batch").text().toUpperCase().includes(filterBatch);
        const statusMatch = $(this).find(".product-status").text().toUpperCase().includes(filterStatus);

        if (idMatch && batchMatch && statusMatch) {
            $(this).show();
        } else {
            $(this).hide();
        }
    });
}
