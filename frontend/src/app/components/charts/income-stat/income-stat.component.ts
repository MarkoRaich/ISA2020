import { formatDate } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { DateTime } from 'luxon';
import { BaseChartDirective } from 'ng2-charts';
import { ToastrService } from 'ngx-toastr';
import { IncomeList } from 'src/app/models/incomeList';
import { PharmacyService } from 'src/app/services/pharmacy.service';

@Component({
  selector: 'app-income-stat',
  templateUrl: './income-stat.component.html',
  styleUrls: ['./income-stat.component.css']
})
export class IncomeStatComponent implements OnInit {

  @ViewChild(BaseChartDirective, { static: true }) chart: BaseChartDirective;

  startDate: DateTime;
  endDate: DateTime;
  maxDate = new Date();
  pharmacyRevenue: IncomeList;

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

  public barChartLabels = [];
  public barChartType = 'bar';
  public barChartLegend = true;

  public barChartData = [
    { data: [], label: 'Prihodi u RSD' }
  ];

  public chartColors: any[] =
    [
      {
        backgroundColor: '#87C7F3',
        borderColor: '#87C7F3'
      }
    ]

  constructor(private toastr: ToastrService,private pharmacyService: PharmacyService) { }

  ngOnInit(): void {

   
  }

  search(){

    if (!(this.startDate || this.endDate)) {
      this.toastr.error('Odaberite početni i krajnji datume', 'Finansijski izveštaj');
      return;
    }

    if (this.startDate >= this.endDate) {
      this.toastr.error('Početni datum mora biti pre krajnjeg datuma.', 'Finansijski izveštaj');
      return;
    }
    this.pharmacyService.getPharmacyIncomeForPeriod(formatDate(this.startDate.toString(), 'yyyy-MM-dd', 'en-US'),
                                                    formatDate(this.endDate.toString(), 'yyyy-MM-dd', 'en-US')).subscribe(
        (data: IncomeList) => {
        this.pharmacyRevenue = data;
        this.populateChart();
      },
        () => {
          this.toastr.error('Došlo je do greške', 'Finansijski izveštaj');
        }
      );

  }

  populateChart(){
    this.barChartLabels = this.pharmacyRevenue.labels;
    this.barChartData = [ { data: this.pharmacyRevenue.values, label: 'Prihodi u RSD'
  }];
  }


}
