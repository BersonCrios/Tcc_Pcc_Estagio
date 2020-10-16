import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { routing } from './categorias.routing';
import { SharedModule } from '../../shared/shared.module';
import { CategoriasComponent } from './categorias.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
    imports: [
        CommonModule,
        SharedModule,
        routing,
        ReactiveFormsModule
    ],
    declarations: [
        CategoriasComponent,
    ]
})
export class IconModule { }
