import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {Department} from "../../../../../models/department";
import {DepartmentsService} from "../../services/departments.service";
import {Response} from "../../../../../models/response";

@Component({
    selector: 'app-departments',
    templateUrl: './departments.component.html',
    styleUrl: './departments.component.scss'
})
export class DepartmentsComponent implements OnInit {
    departments: Department[] = [];

    constructor(private departmentService: DepartmentsService) {
    }

    ngOnInit() {
        this.departmentService.getAll().subscribe((response: Response<Department[]>) => {
            this.departments = response?.data?.departments || [];
        });
    }

    deleteDepartment(id: number) {
        this.departmentService.delete(id).subscribe(() => {
            this.departments = this.departments.filter(department => department.departmentID !== id);
        });
    }
}
