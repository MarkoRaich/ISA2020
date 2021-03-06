import { Component, OnInit, ViewChild } from '@angular/core';
import { BaseChartDirective } from 'ng2-charts';
import { PharmacyService } from 'src/app/services/pharmacy.service';

@Component({
  selector: 'app-monthly-statistic',
  templateUrl: './monthly-statistic.component.html',
  styleUrls: ['./monthly-statistic.component.css']
})
export class MonthlyStatisticComponent implements OnInit {

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

  public barChartLabels = ['JAN', 'FEB', 'MAR', 'APR', 'MAY', 'JUN', 'JUL', 'AUG', 'SEP', 'OCT', 'NOV', 'DEC'];
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

    this.pharmacyService.getMothlyStatistic().subscribe(
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
