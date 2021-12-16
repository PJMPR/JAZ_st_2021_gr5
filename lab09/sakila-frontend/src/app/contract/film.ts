import { Language } from "./language";

export interface Film{
    id:number,
    title:string,
    releaseYear?:number,
    language?:Language,
    rentalDuration?:number,
    rentalRate?:number,
    replacementCosts?:number
}