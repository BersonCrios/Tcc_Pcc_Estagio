import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LocaisComponent } from './locais.component';
import { routing } from './locais.routing';
import { SharedModule } from '../../shared/shared.module';
import { NgxEchartsModule } from 'ngx-echarts';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
    imports: [
        CommonModule,
        SharedModule,
        NgxEchartsModule,
        routing,
        ReactiveFormsModule
    ],
    declarations: [
        LocaisComponent
    ]
})
export class IndexModule { }
