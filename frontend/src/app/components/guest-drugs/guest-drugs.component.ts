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

@Component({
  selector: 'app-guest-drugs',
  templateUrl: './guest-drugs.component.html',
  styleUrls: ['./guest-drugs.component.css']
})
export class GuestDrugsComponent implements OnInit {

  drugsDataSource: MatTableDataSource<DrugInfo>;
  displayedColumns: string[] = [ 'name', 'code', 'manufacturer', 'form', 'type'];
  searchString: string;
  searchName: string = '';
  numberOfItems: number;
  itemsPerPage = environment.itemsPerPage;
  addAlergieSuccess: Subscription;

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;


  drugs: DrugInfo[] = [];


  constructor(public dialog: MatDialog, private drugService: DrugService, public toastr: ToastrService) { }

  ngOnInit(): void {
    this.getAllDrugs();
  }


  getAllDrugs() {
    this.drugService.getAllDrugs().subscribe(
      (data: DrugInfo[]) => {
        this.drugs = data;
        this.drugsDataSource = new MatTableDataSource(data);
        this.drugsDataSource.sort = this.sort;
        this.drugsDataSource.paginator = this.paginator;
      });
  }

}
