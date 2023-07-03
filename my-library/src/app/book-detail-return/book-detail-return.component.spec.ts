import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookDetailReturnComponent } from './book-detail-return.component';

describe('BookDetailReturnComponent', () => {
  let component: BookDetailReturnComponent;
  let fixture: ComponentFixture<BookDetailReturnComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BookDetailReturnComponent]
    });
    fixture = TestBed.createComponent(BookDetailReturnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
