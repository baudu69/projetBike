import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Icon, LatLng, Map, Marker, Polyline, Popup, TileLayer} from 'leaflet';
import {ISortie} from "../shared/model/isortie";
import {SortieService} from "../shared/service/sortie.service";
import {IEtape} from "../shared/model/ietape";

@Component({
  selector: 'app-sortie',
  templateUrl: './sortie.component.html',
  styleUrls: ['./sortie.component.css']
})
export class SortieComponent implements OnInit {

  map?: Map;
  id?: number;
  sortie?: ISortie;

  constructor(private sortieService: SortieService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.map = new Map('map', {
      center: new LatLng(45, 3),
      zoom: 3,
    });

    new TileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 18,
      minZoom: 3,
      attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    }).addTo(this.map);

    this.route.paramMap.subscribe(params => {
      this.id = parseInt(params.get('sortieId')!);
      console.log(this.id);

      if (!this.id) return;

      this.sortieService.getSortieById(this.id).subscribe({
        next: (response: ISortie) => {
          this.sortie = response;
          console.log(this.sortie);

          if (!this.sortie) return;

          let latlngs = Array(this.sortie.etapes.length);
          let coords = this.sortie.etapes.sort((a, b) => a.numEtape - b.numEtape);
          for (let i = 0; i < this.sortie.etapes.length; i++) {
            latlngs[i] = new LatLng(coords[i].latitude, coords[i].longitude);
            let marker = new Marker(latlngs[i]);
            marker.setIcon(new Icon({
              iconUrl: 'assets/etape.png',
              iconSize: [9, 9],
            }));
            marker.bindPopup(new Popup({
              content: this.popupHtml(coords[i]),
            }));
            marker.addTo(this.map!);
          }

          let polyline = new Polyline(latlngs, {color: 'red'}).addTo(this.map!);
          this.map?.fitBounds(polyline.getBounds());
        },
      });
    });

    setTimeout(() => this.map?.invalidateSize(), 0);
  }

  private popupHtml(etape: IEtape): string {
    let ret = ``;

    if (etape.numEtape == this.sortie?.etapes.length) ret += `<div>Arrivée</div>`;
    else if (etape.numEtape == 1) ret += `<div>Départ</div>`;

    ret += `<div>Étape ${etape.numEtape}</div>`;
    ret += `<div>Heure : ${etape.heureEtape}</div>`;

    return ret;
  }

}
