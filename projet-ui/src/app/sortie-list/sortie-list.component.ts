import {Component, OnInit} from '@angular/core';
import {ISortie} from "../shared/model/isortie";
import {SortieService} from "../shared/service/sortie.service";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-sortie-list',
  templateUrl: './sortie-list.component.html',
  styleUrls: ['./sortie-list.component.css']
})
export class SortieListComponent implements OnInit {

  sorties: ISortie[] = [];
  displayedColumns: string[] = ['dateSortie', 'lieuDepart', 'heureDepart', 'heureArrivee', 'distanceParcourue', 'view'];

  constructor(private sortieService: SortieService) { }

  ngOnInit(): void {
    this.loadSorties();
  }

  private loadSorties(): void {
    this.sortieService.getAllSorties().subscribe((sorties: ISortie[]) => {
      this.sorties = sorties.sort((a, b) => new Date(a.dateSortie).getTime() - new Date(b.dateSortie).getTime());
    });
  }

  delete(id: number): void {
    if (!confirm("Voulez-vous supprimer définitivement la sortie ?\nCette action est irréversible !")) return;
    this.sortieService.deleteSortieById(id).subscribe({
      next: () => {
        this.loadSorties();
      },
      error: (error: HttpErrorResponse) => {
        alert("Une erreur est survenue : " + error.status + " " + error.statusText);
      }
    });
  }

}
