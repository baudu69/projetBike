import {Component, OnInit} from '@angular/core';
import {ISortie} from "../shared/model/isortie";
import {SortieService} from "../shared/service/sortie.service";

@Component({
  selector: 'app-sortie-list',
  templateUrl: './sortie-list.component.html',
  styleUrls: ['./sortie-list.component.css']
})
export class SortieListComponent implements OnInit {

  sorties: ISortie[] = [];
  displayedColumns: string[] = ['id', 'utilisateur', 'dateSortie', 'lieuDepart', 'heureDepart', 'heureArrivee', 'distanceParcourue', 'view'];

  constructor(private sortieService: SortieService) { }

  ngOnInit(): void {
    this.sortieService.getAllSorties().subscribe((sorties: ISortie[]) => {
      this.sorties = sorties;
    });
  }

}
