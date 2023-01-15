import {HttpClient} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {ISortie} from "../model/isortie";

@Injectable({
  providedIn: 'root'
})
export class SortieService {

  constructor(private http: HttpClient) {
  }

  getAllSorties(): Observable<ISortie[]> {
    return this.http.get<ISortie[]>('/api/sortie');
  }

  getSortieById(sortieId: number): Observable<ISortie> {
    return this.http.get<ISortie>('/api/sortie/' + sortieId);
  }

  deleteSortieById(sortieId: number) {
    return this.http.delete<void>('/api/sortie/' + sortieId);
  }

}
