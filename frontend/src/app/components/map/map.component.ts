import { AfterViewInit, Component, OnInit } from '@angular/core';

import { Pharmacy } from 'src/app/models/Pharmacy';
import { PharmacyService } from 'src/app/services/pharmacy.service';
import { GeoSearchControl, OpenStreetMapProvider } from 'leaflet-geosearch';
import { Subscription } from 'rxjs';
import * as L from 'leaflet';
import { UserService } from 'src/app/services/user.service';
import { isUndefined } from 'util';

let map;
let mark: L.Marker;
let deletedFirst = true;
let selectedPharmacy: Pharmacy;
let service: PharmacyService;
declare let require;
const convert = require('cyrillic-to-latin');

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements AfterViewInit, OnInit {

  provider = new OpenStreetMapProvider();
  searchControl = new GeoSearchControl({
    provider: this.provider,
  });
  editPharmacy: Subscription;
  addPharmacy: Subscription;


  constructor(private pharmacyService: PharmacyService, private userService: UserService) {
    service = pharmacyService;
  }


  ngOnInit() {
    
    if (this.userService.isPharmacyAdmin()) {
      this.pharmacyService.getPharmacyInWhichPharmacyAdminWorks().subscribe((data: Pharmacy) => {
        selectedPharmacy = data;
        this.pharmacyService.get(selectedPharmacy.address).subscribe((data) => {
          if (!isUndefined(data) && data && !isUndefined(data[0])) {
            map.setView(new L.LatLng(Number.parseFloat(data[0].lat), Number.parseFloat(data[0].lon)), 20);
            const latlng = new L.LatLng(Number.parseFloat(data[0].lat), Number.parseFloat(data[0].lon));
            mark = new L.Marker(latlng, { draggable: false });
            map.addLayer(mark);
            deletedFirst = false;
          }

        });
      });
    } else {
      selectedPharmacy = new Pharmacy('', '', '');
    }

    this.editPharmacy = this.pharmacyService.editPharmacyEmitter.subscribe(
      (pharmacy: Pharmacy) => {
        this.markAddress(pharmacy.address);
      }
    );

    this.addPharmacy = this.pharmacyService.addPharmacyAdressEmiter.subscribe(
      (pharmacy: Pharmacy) => {
        this.markAddress(pharmacy.address);
      }
    );
  
  }

  markAddress(address: string): void {
    this.pharmacyService.get(address).subscribe((data) => {
      let i = 0;
      map.eachLayer(function (layer) {
        if (i === 2) {
          map.removeLayer(layer);
        }
        i++;
      });
      deletedFirst = false;
      map.setView(new L.LatLng(Number.parseFloat(data[0].lat), Number.parseFloat(data[0].lon)), 17);
      const latlng = new L.LatLng(Number.parseFloat(data[0].lat), Number.parseFloat(data[0].lon));
      mark = new L.Marker(latlng, { draggable: false });
      map.addLayer(mark);
    });
  }

  ngAfterViewInit(): void {
    this.initMap();
  }

  initMap(): void {
    map = L.map('map', {
      center: [39.8282, -98.5795],
      zoom: 10
    });
    const tiles = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 19,
      attribution: "&copy; <a href='http://www.openstreetmap.org/copyright'>OpenStreetMap</a>"
    });

    tiles.addTo(map);
/*
    new GeoSearchControl({
      provider: this.provider,
      showMarker: true,
      showPopup: false,
      marker: {
        icon: new L.Icon.Default(),
        draggable: false
      },
      popupFormat: ({ query, result }) => result.label,
      maxMarkers: 1,
      retainZoomLevel: false,
      animateZoom: true,
      autoClose: true,
      searchLabel: 'Enter address',
      keepResult: true
    }).addTo(map);
*/
    map.on('geosearch/showlocation', function (e) {
      if (!deletedFirst) {
        let i = 0;
        map.eachLayer(function (layer) {
          if (i === 2) {
            map.removeLayer(layer);
          }
          i++;
        });
        deletedFirst = true;
      }
      selectedPharmacy.address = convert(e.location.label);
      service.searchAddressPharmacyEmitter.next(selectedPharmacy);
      service.addSearchAddresPharmacyEmitter.next(selectedPharmacy);
    });
  }
 

}
