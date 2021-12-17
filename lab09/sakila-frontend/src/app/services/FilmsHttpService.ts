import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Film } from "../contract/film";
import { Language } from "../contract/language";
import { PagingInfo } from "./paging-info";

@Injectable({providedIn:'root'})
export class FilmsHttpService{

    /**
     *
     */
    constructor(private http: HttpClient) {

    }

    private filmsUrl = '/api/films';
    private languagesUrl = '/api/languages';

    getFilms(query?: any): Observable<Film[]>{
        //return this.http.get<Film[]>(this.filmsUrl+'?page='+page.page+'&size='+page.size);
        return this.http.get<Film[]>(this.filmsUrl);
    }

    updateFilm(film:Film):Observable<any>{
        return this.http.put(this.filmsUrl+'/'+film.id, film);
    }

    deleteFilm(id:number):Observable<any>{
        return this.http.delete(this.filmsUrl+'/'+id);
    }
    getLanguages(): Observable<Language[]>{

        return this.http.get<Language[]>(this.languagesUrl);
    }

    saveFilm(film:Film):Observable<any>{
        return this.http.post(this.filmsUrl, film);
    }

}

export interface QueryParamaters{
        id?:number,
  title?:string,
  language?:any,
  release_year?:number,
  rental_duration?:number,
  rental_rate?:number,
  replacement_costs?:number,
  page:number
}
