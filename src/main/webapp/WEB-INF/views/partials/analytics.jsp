<div class="row">
	<div class="col-md-3">
		<select ng-model='selectedChartType'>
          <option value="pie">Pie</option>
          <option value="bar">Bar</option>
          <option value="line">Line</option>
          <option value="point">Point</option>
          <option value="area">Area</option>
        </select>
	</div>
</div>

<div ac-chart="selectedChartType" ac-data="chartData"
	ac-config="chartConfig" id='chart' class='chart'></div>