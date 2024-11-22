import { Pipe, PipeTransform } from '@angular/core';
import { Offre } from '../modules/Offre';

@Pipe({
  name: 'SearchOffre',
})
export class FilterOffresPipe implements PipeTransform {
  transform(offres: Offre[], searchAdd: string): Offre[] {
    if (!offres || !searchAdd) {
      return offres;
    }
    return offres.filter(res =>
      res.nom.toLowerCase().includes(searchAdd.toLowerCase()) || res.address.toLowerCase().includes(searchAdd.toLowerCase())
    );
  }
}
