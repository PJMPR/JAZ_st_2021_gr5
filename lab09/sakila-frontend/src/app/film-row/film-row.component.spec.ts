/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { FilmRowComponent } from './film-row.component';

describe('FilmRowComponent', () => {
  let component: FilmRowComponent;
  let fixture: ComponentFixture<FilmRowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FilmRowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FilmRowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
