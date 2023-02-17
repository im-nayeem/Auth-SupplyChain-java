google.charts.load('current', {packages: ['corechart']});

// Load the data for the first chart
google.charts.setOnLoadCallback(drawChart1);

function drawChart1() {
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Product');
    data.addColumn('number', 'Sales');
    data.addRows([
        ['Earphone', 1000],
        ['Battery(HT03XL)', 500],
        ['AC', 800],
        ['Television', 1200],
        ['Iron', 1500]
    ]);

    var options = {
        title: 'Sales Rate By Product',
        is3D: true
    };

    var chart = new google.visualization.PieChart(document.getElementById('chart1'));
    chart.draw(data, options);
}

// Load the data for the second chart
google.charts.setOnLoadCallback(drawChart2);

function drawChart2() {
    var data = google.visualization.arrayToDataTable([
        ['Region', 'Earphone', 'Battery', 'Iron', 'AC', 'Telivison'],
        ['Dhaka', 500, 300, 400, 600, 700],
        ['Mymensingh', 300, 100, 200, 400, 500],
        ['Chattogram', 200, 100, 300, 500, 600],
        ['Sylhet', 400, 200, 400, 700, 800],
        ['Rajshahi', 400, 200, 400, 700, 800],
        ['Barisal', 450, 50, 600, 700, 500]


    ]);

    var options = {
        title: 'Product Sales Rate By Region',
        vAxis: {title: 'Sales'},
        hAxis: {title: 'Region'},
        seriesType: 'bars',
        series: {6: {type: 'line'}}
    };

    var chart = new google.visualization.ComboChart(document.getElementById('chart2'));
    chart.draw(data, options);
}
