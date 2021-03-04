import { Component, OnInit, ViewChild } from '@angular/core';
import { BaseChartDirective } from 'ng2-charts';
import { PharmacyService } from 'src/app/services/pharmacy.service';

@Component({
  selector: 'app-year-stat-drugs',
  templateUrl: './year-stat-drugs.component.html',
  styleUrls: ['./year-stat-drugs.component.css']
})
export class YearStatDrugsComponent implements OnInit{

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

  public barChartLabels = ['2019', '2020', '2021'];
  public barChartType = 'bar';
  public barChartLegend = true;

  public barChartData = [
    { data: [], label: 'Broj prodatih lekova' }
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

    this.pharmacyService.getYearStatisticDrugs().subscribe(
      (data: Number[]) => {
        this.barChartData = [
          { data: data,
            label: 'Broj prodatih lekova'
          }
        ];
      }
    )
  }



}