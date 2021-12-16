import { Component, OnInit } from '@angular/core';
import { Film } from '../contract/film';
import { Language } from '../contract/language';
import { FilmsHttpService, QueryParamaters } from '../services/FilmsHttpService';

@Component({
  selector: 'films-table',
  templateUrl: './films-table.component.html',
  styleUrls: ['./films-table.component.css']
})
export class FilmsTableComponent implements OnInit {

  films:Array<Film> =[];
  isProgressVisible = false;
  allFilmsCount = 1000;
  first = 0;
  rows = 1;
  queryParameters:QueryParamaters={ page:1 };
  languages:Array<Language>=[];
  page = 1;
  size = 10;


  constructor(private filmsService: FilmsHttpService) { }

  ngOnInit() {
    this.isProgressVisible=true;
   this.filmsService.getFilms().subscribe(x=>{this.films=x; this.isProgressVisible=false});
   this.filmsService.getLanguages().subscribe(x=> this.languages=x);
  }

  
  next() {
    this.isProgressVisible=true;
    const nextPage = this.queryParameters.page+1;
    this.queryParameters.language=this.queryParameters.language?.id;
    this.filmsService.getFilms(this.queryParameters).subscribe(x=>{this.films=x; this.isProgressVisible=false});
    this.queryParameters.page=nextPage;
}

prev() {
  this.isProgressVisible=true;
  const prevPage = this.queryParameters.page-1;
  this.queryParameters.language=this.queryParameters.language?.id;
  this.filmsService.getFilms(this.queryParameters)
  .subscribe(x=>{this.films=x; this.isProgressVisible=false});
  this.queryParameters.page=prevPage>0?prevPage:1;
}

reset() {
  this.isProgressVisible=true;
  this.queryParameters.language=this.queryParameters.language?.id;
  this.filmsService.getFilms(this.queryParameters)
  .subscribe(x=>{this.films=x; this.isProgressVisible=false});
  this.queryParameters.page=1;
}

isLastPage(): boolean {
    return this.films ? this.first === (this.allFilmsCount - this.rows): true;
}

isFirstPage(): boolean {
    return this.films ? this.first === 0 : true;
}

search(){
  this.isProgressVisible=true;
  this.queryParameters.language=this.queryParameters.language?.id;
  this.filmsService.getFilms(this.queryParameters).subscribe(x=>{this.films=x; this.isProgressVisible=false});
}

save(){
  this.isProgressVisible=true;
  this.filmsService.saveFilm({
    id:0,
    title:this.queryParameters.title == null?"":this.queryParameters.title,
    language:this.queryParameters.language,
    releaseYear:this.queryParameters.release_year,
    rentalDuration:this.queryParameters.rental_duration,
    rentalRate:this.queryParameters.rental_rate,
    replacementCosts:this.queryParameters.replacement_costs
  }).subscribe(x=>this.isProgressVisible=false);
}

}
