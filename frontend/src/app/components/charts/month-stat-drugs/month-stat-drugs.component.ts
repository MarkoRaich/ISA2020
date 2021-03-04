import { Component, OnInit, ViewChild } from '@angular/core';
import { BaseChartDirective } from 'ng2-charts';
import { PharmacyService } from 'src/app/services/pharmacy.service';

@Component({
  selector: 'app-month-stat-drugs',
  templateUrl: './month-stat-drugs.component.html',
  styleUrls: ['./month-stat-drugs.component.css']
})
export class MonthStatDrugsComponent implements OnInit {

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

  this.pharmacyService.getMothlyStatisticDrugs().subscribe(
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
