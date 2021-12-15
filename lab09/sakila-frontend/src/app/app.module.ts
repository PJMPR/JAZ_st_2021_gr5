import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import {TableModule} from 'primeng/table';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FilmsTableComponent } from './films-table/films-table.component';
import { FilmRowComponent } from './film-row/film-row.component';
import {ButtonModule} from 'primeng/button';
import {InputTextModule} from 'primeng/inputtext';
import {InputNumberModule} from 'primeng/inputnumber';
import {DropdownModule} from 'primeng/dropdown';
import {ProgressBarModule} from 'primeng/progressbar';
import { HttpClientInMemoryWebApiModule } from 'angular-in-memory-web-api';
import { InMemoryDataService } from './services/in-memory-data-service';
import { HttpClientModule } from '@angular/common/http';
import { FilmsHttpService } from './services/FilmsHttpService';
@NgModule({
  declarations: [	
    AppComponent,
    FilmsTableComponent,
      FilmRowComponent
   ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    TableModule,
    ButtonModule,
    InputTextModule,
    InputNumberModule,
    DropdownModule,
    ProgressBarModule,
    HttpClientModule
    ,HttpClientInMemoryWebApiModule.forRoot( InMemoryDataService, {dataEncapsulation: false, delay:1200})
  ],
  providers: [FilmsHttpService],
  bootstrap: [AppComponent]
})
export class AppModule { }
