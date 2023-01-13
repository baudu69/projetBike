export interface IEtape {
  id?: number,
  numEtape: number,
  latitude: number,
  longitude: number,
  distanceFromPrevious: number,
  heureEtape: Date,
  vitesseMoyenneFromPrevoius: number,
}
