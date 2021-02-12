import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {DrugInfo} from '../../models/DrugInfo';
import {environment} from '../../../environments/environment';
import {Subscription} from 'rxjs';
import {MatSort} from '@angular/material/sort';
import {MatPaginator} from '@angular/material/paginator';
import {MatDialog} from '@angular/material/dialog';
import {DrugService} from '../../services/drug.service';
import {ToastrService} from 'ngx-toastr';
import {PharmacyService} from '../../services/pharmacy.service';
import {Pharmacy} from '../../models/pharmacy';

@Component({
  selector: 'app-guest-pharmacies',
  templateUrl: './guest-pharmacies.component.html',
  styleUrls: ['./guest-pharmacies.component.css']
})
export class GuestPharmaciesComponent implements OnInit {

  pharmacyDataSource: MatTableDataSource<Pharmacy>;
  displayedColumns: string[] = [ 'name', 'address', 'description'];
  searchString: string;
  searchName: string = '';
  numberOfItems: number;
  itemsPerPage = environment.itemsPerPage;

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;


  pharmacies: Pharmacy[] = [];


  constructor(public dialog: MatDialog, private pharmacyService: PharmacyService, public toastr: ToastrService) { }

  ngOnInit(): void {
    this.getAllPharmaties();
  }


  getAllPharmaties() {
    this.pharmacyService.getAllPharmaties().subscribe(
      (data: Pharmacy[]) => {
        this.pharmacies = data;
        this.pharmacyDataSource = new MatTableDataSource(data);
        this.pharmacyDataSource.sort = this.sort;
        this.pharmacyDataSource.paginator = this.paginator;
      });
  }

}
