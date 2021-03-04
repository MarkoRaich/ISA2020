import { Component, OnInit, ViewChild } from '@angular/core';
import { BaseChartDirective } from 'ng2-charts';
import { PharmacyService } from 'src/app/services/pharmacy.service';

@Component({
  selector: 'app-quartal-statistic',
  templateUrl: './quartal-statistic.component.html',
  styleUrls: ['./quartal-statistic.component.css']
})
export class QuartalStatisticComponent implements OnInit {

  @ViewChild(BaseChartDirective, { static: true }) chart: BaseChartDirective;

  public barChartOptions = {
    scaleShowVerticalLines: false,
    responsive: true,
    scales: {
      yAxes: [{
        ticks: {
          beginAtZero: true
        }
      }]
    }
  };

  public barChartLabels = ['PRVI','DRUGI','TREĆI','ČETVRTI'];
  public barChartType = 'bar';
  public barChartLegend = true;

  public barChartData = [
    { data: [], label: 'Broj pregleda' }
  ];

  public chartColors: any[] =
    [
      {
        backgroundColor: '#87C7F3',
        borderColor: '#87C7F3'
      }
    ]

  constructor(private pharmacyService: PharmacyService) { }

  ngOnInit(): void {

    this.pharmacyService.getQuartalStatistic().subscribe(
      (data: Number[]) => {
        this.barChartData = [
          { data: data,
            label: 'Broj pregleda'
          }
        ];
      }
    )
  }



}