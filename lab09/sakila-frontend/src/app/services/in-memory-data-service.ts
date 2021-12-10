import { Injectable } from "@angular/core";
import { InMemoryDbService, RequestInfo } from "angular-in-memory-web-api";
import { Observable } from "rxjs";
import { Film } from "../contract/film";
import { Language } from "../contract/language";

@Injectable({
    providedIn: 'root',
  })
export class InMemoryDataService implements InMemoryDbService{

    createDb(reqInfo?: RequestInfo): {} | Observable<{}> | Promise<{}> {
        const films:Array<Film> = [ 
            {
            id:1,
            releaseYear:2020,
            rentalDuration:3,
            rentalRate:2.99,
            replacementCosts:20.99,
            title:"Testowy tytuł"
          },
          {
          id:2,
          releaseYear:2020,
          rentalDuration:3,
          rentalRate:2.99,
          replacementCosts:20.99,
          title:"Testowy tytuł"
        },
        {
        id:3,
        releaseYear:2020,
        rentalDuration:3,
        rentalRate:2.99,
        replacementCosts:20.99,
        title:"Testowy tytuł"
      },
      {
      id:4,
      releaseYear:2020,
      rentalDuration:3,
      rentalRate:2.99,
      replacementCosts:20.99,
      title:"Testowy tytuł"
      },
      {
      id:5,
      releaseYear:2020,
      rentalDuration:3,
      rentalRate:2.99,
      replacementCosts:20.99,
      title:"Testowy tytuł"
      },
      {
      id:6,
      releaseYear:2020,
      rentalDuration:3,
      rentalRate:2.99,
      replacementCosts:20.99,
      title:"Testowy tytuł"
      },
      {
      id:7,
      releaseYear:2020,
      rentalDuration:3,
      rentalRate:2.99,
      replacementCosts:20.99,
      title:"Testowy tytuł"
      },
      {
      id:8,
      releaseYear:2020,
      rentalDuration:3,
      rentalRate:2.99,
      replacementCosts:20.99,
      title:"Testowy tytuł"
      },
      {
      id:9,
      releaseYear:2020,
      rentalDuration:3,
      rentalRate:2.99,
      replacementCosts:20.99,
      title:"Testowy tytuł"
      },
      {
      id:10,
      releaseYear:2020,
      rentalDuration:3,
      rentalRate:2.99,
      replacementCosts:20.99,
      title:"Testowy tytuł"
      },
      {
      id:11,
      releaseYear:2020,
      rentalDuration:3,
      rentalRate:2.99,
      replacementCosts:20.99,
      title:"Testowy tytuł"
      },
      {
      id:12,
      releaseYear:2020,
      rentalDuration:3,
      rentalRate:2.99,
      replacementCosts:20.99,
      title:"Testowy tytuł"
      },
      {
      id:13,
      releaseYear:2020,
      rentalDuration:3,
      rentalRate:2.99,
      replacementCosts:20.99,
      title:"Testowy tytuł"
      },
      {
      id:14,
      releaseYear:2020,
      rentalDuration:3,
      rentalRate:2.99,
      replacementCosts:20.99,
      title:"Testowy tytuł"
      },
      {
      id:15,
      releaseYear:2020,
      rentalDuration:3,
      rentalRate:2.99,
      replacementCosts:20.99,
      title:"Testowy tytuł"
      },
      {
      id:16,
      releaseYear:2020,
      rentalDuration:3,
      rentalRate:2.99,
      replacementCosts:20.99,
      title:"Testowy tytuł"
      },
      {
      id:17,
      releaseYear:2020,
      rentalDuration:3,
      rentalRate:2.99,
      replacementCosts:20.99,
      title:"Testowy tytuł"
      },
      {
      id:18,
      releaseYear:2020,
      rentalDuration:3,
      rentalRate:2.99,
      replacementCosts:20.99,
      title:"Testowy tytuł"
      },
      {
      id:19,
      releaseYear:2020,
      rentalDuration:3,
      rentalRate:2.99,
      replacementCosts:20.99,
      title:"Testowy tytuł"
      },
      {
      id:20,
      releaseYear:2020,
      rentalDuration:3,
      rentalRate:2.99,
      replacementCosts:20.99,
      title:"Testowy tytuł"
      },
      {
      id:21,
      releaseYear:2020,
      rentalDuration:3,
      rentalRate:2.99,
      replacementCosts:20.99,
      title:"Testowy tytuł"
      },
      {
      id:22,
      releaseYear:2020,
      rentalDuration:3,
      rentalRate:2.99,
      replacementCosts:20.99,
      title:"Testowy tytuł"
      },
      {
      id:23,
      releaseYear:2020,
      rentalDuration:3,
      rentalRate:2.99,
      replacementCosts:20.99,
      title:"Testowy tytuł"
      },
      {
      id:24,
      releaseYear:2020,
      rentalDuration:3,
      rentalRate:2.99,
      replacementCosts:20.99,
      title:"Testowy tytuł"
      },
      {
      id:25,
      releaseYear:2020,
      rentalDuration:3,
      rentalRate:2.99,
      replacementCosts:20.99,
      title:"Testowy tytuł"
      }];
        const languages:Array<Language> =[
            {
                id:1,
                name:"polish"
              },
              {
                id:2,
                name:"english"
              },
              {
                id:3,
                name:"german"
              }
        ]
        return {films, languages};
    }

    genId(films: Film[]): number {
        return films.length > 0 ? Math.max(...films.map(film => film.id)) + 1 : 11;
      }
}