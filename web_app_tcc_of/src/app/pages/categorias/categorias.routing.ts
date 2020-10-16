import { Routes, RouterModule } from '@angular/router';
import { CategoriasComponent } from './categorias.component';

const childRoutes: Routes = [
    {
        path: '',
        component: CategoriasComponent
    }
];

export const routing = RouterModule.forChild(childRoutes);
