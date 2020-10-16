import { Routes, RouterModule } from '@angular/router';
import { AppsComponent } from './apps.component';

const childRoutes: Routes = [
    {
        path: '',
        component: AppsComponent
    }
];

export const routing = RouterModule.forChild(childRoutes);
