import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Film } from '../contract/film';
import { Language } from '../contract/language';
import { FilmsHttpService } from '../services/FilmsHttpService';

@Component({
  selector: '[film-row]',
  templateUrl: './film-row.component.html',
  styleUrls: ['./film-row.component.css'], 
})
export class FilmRowComponent implements OnInit {

  editModeEnable = false;
  languages:Array<Language>=[];

  @Output()
  refresh = new EventEmitter<Film> ();

  @Input()
  film : any;
  constructor(private filmsService: FilmsHttpService) { }

  ngOnInit() {
    this.filmsService.getLanguages().subscribe(x=> this.languages=x);
  }

  turnToEditMode(){
    this.editModeEnable=!this.editModeEnable;
  }

  updateFilm(){
    this.filmsService.updateFilm(this.film).subscribe(x=>{
      this.editModeEnable=!this.editModeEnable;
      this.refresh.emit(this.film);
    });
  }

  deleteFilm(){
    this.filmsService.deleteFilm(this.film.id).subscribe(x=>{
      this.editModeEnable=!this.editModeEnable;
      this.refresh.emit(this.film);
    });
  }

}
