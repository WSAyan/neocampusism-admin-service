import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule} from "@angular/router";
import {DepartmentsComponent} from "./components/department-list/departments.component";



@NgModule({
  declarations: [DepartmentsComponent],
  imports: [
    CommonModule,
      RouterModule.forChild([
          {
              path: '',
                pathMatch : 'full',
                redirectTo: 'list'
          },
          {
              path: 'list',
              component: DepartmentsComponent
          }
      ])
  ]
})
export class DepartmentsModule { }
