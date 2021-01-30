import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditPharmAdminProfileComponent } from './edit-pharm-admin-profile.component';

describe('EditPharmAdminProfileComponent', () => {
  let component: EditPharmAdminProfileComponent;
  let fixture: ComponentFixture<EditPharmAdminProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditPharmAdminProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditPharmAdminProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
