<%@include file="/includes/admin-header.jsp"%>
<style>
  <%@include file="/assets/css/admin-dashboard.css"%>
</style>
<script src="https://www.gstatic.com/charts/loader.js"></script>
<script>
  <%@include file="../assets/js/dashboard.js"%>
</script>
<div class="analysis">
  <div id="chart1"></div>

  <div id="chart2"></div>
</div>
  <div class="flex-wrapper">
    <div class="single-chart">
      <svg viewBox="0 0 36 36" class="circular-chart orange">
        <path class="circle-bg"
              d="M18 2.0845
                a 15.9155 15.9155 0 0 1 0 31.831
                a 15.9155 15.9155 0 0 1 0 -31.831"
        />
        <path class="circle"
              stroke-dasharray="30, 100"
              d="M18 2.0845
                a 15.9155 15.9155 0 0 1 0 31.831
                a 15.9155 15.9155 0 0 1 0 -31.831"
        />
        <text x="18" y="20.35" class="percentage">30%</text>
      </svg>
      <p class="label">Last Week Sales Rate</p>
    </div>

    <div class="single-chart">
      <svg viewBox="0 0 36 36" class="circular-chart green">
        <path class="circle-bg"
              d="M18 2.0845
                a 15.9155 15.9155 0 0 1 0 31.831
                a 15.9155 15.9155 0 0 1 0 -31.831"
        />
        <path class="circle"
              stroke-dasharray="60, 100"
              d="M18 2.0845
                a 15.9155 15.9155 0 0 1 0 31.831
                a 15.9155 15.9155 0 0 1 0 -31.831"
        />
        <text x="18" y="20.35" class="percentage">60%</text>
      </svg>
      <p class="label">Last Month Sales Rate</p>
    </div>

    <div class="single-chart">
      <svg viewBox="0 0 36 36" class="circular-chart blue">
        <path class="circle-bg"
              d="M18 2.0845
                a 15.9155 15.9155 0 0 1 0 31.831
                a 15.9155 15.9155 0 0 1 0 -31.831"
        />
        <path class="circle"
              stroke-dasharray="96, 100"
              d="M18 2.0845
                a 15.9155 15.9155 0 0 1 0 31.831
                a 15.9155 15.9155 0 0 1 0 -31.831"
        />
        <text x="18" y="20.35" class="percentage">96%</text>
      </svg>
      <p class="label">Last Year Sales Rate</p>
    </div>
  </div>
<%@include file="/includes/admin-footer.jsp"%>

