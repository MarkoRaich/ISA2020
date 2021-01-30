import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditProfilePharmacyAdminComponent } from './edit-profile-pharmacy-admin.component';

describe('EditProfilePharmacyAdminComponent', () => {
  let component: EditProfilePharmacyAdminComponent;
  let fixture: ComponentFixture<EditProfilePharmacyAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditProfilePharmacyAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditProfilePharmacyAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
