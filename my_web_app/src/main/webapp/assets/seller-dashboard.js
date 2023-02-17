// Data for the chart
var data = {
    labels: ["Earphone", "Battery", "Television"],
    datasets: [{
        label: "Current Stock",
        backgroundColor: "rgba(54, 162, 235, 0.2)",
        borderColor: "rgba(54, 162, 235, 1)",
        borderWidth: 1,
        data: [50, 30, 20]
    }, {
        label: "Number Sold",
        backgroundColor: "rgba(255, 99, 132, 0.2)",
        borderColor: "rgba(255, 99, 132, 1)",
        borderWidth: 1,
        data: [20, 10, 5]
    }]
};

// Options for the chart
var options = {
    scales: {
        yAxes: [{
            ticks: {
                beginAtZero: true
            }
        }]
    }
};

// Get the canvas element
var ctx = document.getElementById("product-chart").getContext("2d");

// Create the chart
var myChart = new Chart(ctx, {
    type: 'bar',
    data: data,
    options: options
});
