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
import {Complaint} from '../../models/Complaint';
import {ComplaintService} from '../../services/complaint.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-list-all-complaints',
  templateUrl: './list-all-complaints.component.html',
  styleUrls: ['./list-all-complaints.component.css']
})
export class ListAllComplaintsComponent implements OnInit {

  drugsDataSource: MatTableDataSource<Complaint>;
  displayedColumns: string[] = [ 'subject', 'message', 'doctorName', 'examination'];
  searchString: string;
  searchName: string = '';
  numberOfItems: number;
  itemsPerPage = environment.itemsPerPage;
  addAlergieSuccess: Subscription;

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;


  drugs: Complaint[] = [];


  constructor(public dialog: MatDialog, private complaintService: ComplaintService, public toastr: ToastrService, public router: Router) { }

  ngOnInit(): void {
    this.getAllComplaints();

    // this.addAlergieSuccess =  this.complaintService.successAddAlergie.subscribe(
    //   () => {
    //     this.getAllDrugs();
    //   }
    // )
  }


  getAllComplaints() {
    this.complaintService.getAllComplaints().subscribe(
      (data: Complaint[]) => {
        this.drugs = data;
        this.drugsDataSource = new MatTableDataSource(data);
        this.drugsDataSource.sort = this.sort;
        this.drugsDataSource.paginator = this.paginator;
      });
  }

  makeComplaint() {
    this.router.navigate(['/patient/make-complaint']);
  }

  // public addAlergie(drugId: number): void {
  //   this.toastr.success('Dodata je alergija na lek ' + drugId , 'Alergija');
  //   this.drugService.addAlergie(drugId).subscribe();
  // }

}
