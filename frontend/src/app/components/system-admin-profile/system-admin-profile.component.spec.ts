import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SystemAdminProfileComponent } from './system-admin-profile.component';

describe('SystemAdminProfileComponent', () => {
  let component: SystemAdminProfileComponent;
  let fixture: ComponentFixture<SystemAdminProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SystemAdminProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SystemAdminProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
