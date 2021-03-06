import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'rxjs';
import { Dermatologist } from 'src/app/models/dermatologist';
import { DermatologistService } from 'src/app/services/dermatologist.service';
import { environment } from 'src/environments/environment';
import { AddDermatologistComponent } from '../add-dermatologist/add-dermatologist.component';

@Component({
  selector: 'app-list-dermatologists',
  templateUrl: './list-dermatologists.component.html',
  styleUrls: ['./list-dermatologists.component.css']
})
export class ListDermatologistsComponent implements OnInit {

  dermatologistsDataSource: MatTableDataSource<Dermatologist>;
  displayedColumns: string[] = ['firstName', 'lastName', 'email', 'phoneNumber', 'workhours', 'rating', 'obrisi'];
  searchString: string;
  searchFirstName: string = '';
  searchLastName: string = '';
  itemsPerPage = environment.itemsPerPage;
  successCreatedDermatologist: Subscription;

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;


  constructor(public dialog: MatDialog,
              public dermatologistService: DermatologistService,
              public toastr: ToastrService) { }

  ngOnInit() {

    this.getDermatologistsForAdmin();

    this.dermatologistsDataSource.filterPredicate = 
    (data: Dermatologist, filter: string) => data.rating.toString().indexOf(filter) != -1;
    
    this.successCreatedDermatologist = this.dermatologistService.createSuccessEmitter.subscribe(
      () =>{
        this.getDermatologistsForAdmin();
      }
    )
  }

  getDermatologistsForAdmin() {
    this.dermatologistService.getAllDermatologistsForAdmin().subscribe(
      (data) => {
        this.dermatologistsDataSource = new MatTableDataSource(data);
        this.dermatologistsDataSource.sort = this.sort;
        this.dermatologistsDataSource.paginator = this.paginator;
      }
    )
  }

  
  search() {
    this.dermatologistService.searchDermatologistsForAdminRequest(this.searchFirstName, this.searchLastName).subscribe((data) => {
      this.dermatologistsDataSource = new MatTableDataSource(data);
      this.dermatologistsDataSource.sort = this.sort;
      this.dermatologistsDataSource.paginator = this.paginator;
    })
  }

  openCreatingDialog() {
    this.dialog.open(AddDermatologistComponent);
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dermatologistsDataSource.filter = filterValue;
  }

  deleteDermatologist(dermatologist: Dermatologist) {

    this.dermatologistService.deleteDermatologist(dermatologist.id).subscribe(
      () => {
        this.getDermatologistsForAdmin();
        this.toastr.success('Uspešno Ste obrisali dermatologa.', 'Brisanje dermatologa');
      },
      () => {
        this.toastr.error('Dermatolog ima zakazane preglede i ne može se obrisati.', 'Brisanje dermatologa');
      }
    );
  }
  

}
