import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AddPharmacistComponent } from '../add-pharmacist/add-pharmacist.component';
import { Pharmacist } from '../../models/pharmacist';
import { PharmacistService } from '../../services/pharmacist.service';

@Component({
  selector: 'app-list-pharmacists',
  templateUrl: './list-pharmacists.component.html',
  styleUrls: ['./list-pharmacists.component.css']
})
export class ListPharmacistsComponent implements OnInit {

  pharmacistsDataSource: MatTableDataSource<Pharmacist>;
  displayedColumns: string[] = ['firstName', 'lastName', 'email', 'phoneNumber', 'workhours', 'rating', 'obrisi'];
  searchString: string;
  searchFirstName: string = '';
  searchLastName: string = '';
  itemsPerPage = environment.itemsPerPage;
  successCreatedPharmacist: Subscription;

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;


  constructor(public dialog: MatDialog,
              public pharmacistService: PharmacistService,
              public toastr: ToastrService) { }

  ngOnInit() {
    
    this.getPharmacistsForAdmin();

    this.successCreatedPharmacist = this.pharmacistService.createSuccessEmitter.subscribe(
      () =>{
        this.getPharmacistsForAdmin();
      }
    )
  }

  getPharmacistsForAdmin() {
    this.pharmacistService.getAllPharmacistsForAdmin().subscribe(
      (data) => {
        this.pharmacistsDataSource = new MatTableDataSource(data);
        this.pharmacistsDataSource.sort = this.sort;
        this.pharmacistsDataSource.paginator = this.paginator;
      }
    )
  }

  
  search() {
    this.pharmacistService.searchPharmacistsForAdminRequest(this.searchFirstName, this.searchLastName).subscribe((data) => {
      this.pharmacistsDataSource = new MatTableDataSource(data);
      this.pharmacistsDataSource.sort = this.sort;
      this.pharmacistsDataSource.paginator = this.paginator;
    })
  }

  openCreatingDialog() {
    this.dialog.open(AddPharmacistComponent);
  }

  applyFilter(filterValue: string) {
    this.pharmacistsDataSource.filter = filterValue.trim().toLowerCase();
  }

  deletePharmacist(pharmacist: Pharmacist) {

    this.pharmacistService.deletePharmacist(pharmacist.id).subscribe(
      () => {
        this.getPharmacistsForAdmin();
        this.toastr.success('Uspešno Ste obrisali farmaceuta.', 'Brisanje farmaceuta');
      },
      () => {
        this.toastr.error('Farmaceut ima zakazana savetovanja i ne može se obrisati.', 'Brisanje farmaceuta');
      }
    );
  }
  

}
