import {IEtape} from "./ietape";

export interface ISortie {
  id?: number,
  numUtil: number,
  dateSortie: Date,
  heureDepart: Date,
  heureArrivee: Date,
  lieuDepart: string,
  distanceParcourue: number,
  etapes: IEtape[],
}
