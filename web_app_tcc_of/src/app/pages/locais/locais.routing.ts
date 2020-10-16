import { Routes, RouterModule } from '@angular/router';
import { LocaisComponent } from './locais.component';

const childRoutes: Routes = [
    {
        path: '',
        component: LocaisComponent
    }
];

export const routing = RouterModule.forChild(childRoutes);
