import { Routes, RouterModule } from '@angular/router';
import { PagesComponent } from './pages.component';
import { LoginComponent } from './login/login.component';
import { CadastroComponent } from './cadastro/cadastro.component';
import { NotFoundComponent } from './not-found/not-found.component';

export const childRoutes: Routes = [
    {
        path: 'entrar',
        component: LoginComponent,
    },
    {
        path: 'cadastrar-se',
        component: CadastroComponent,
    },
    {
        path: 'not-found',
        component: NotFoundComponent,
    },
    {
        path: 'pages',
        component: PagesComponent,
        children: [
            { path: '', redirectTo: 'entrar', pathMatch: 'full' },
            { path: 'locais', loadChildren: './locais/locais.module#IndexModule' },
            { path: 'categorias', loadChildren: './categorias/categorias.module#IconModule' },
            { path: 'apps', loadChildren: './apps/apps.module#AppsModule' }
        ]
    }
];

export const routing = RouterModule.forChild(childRoutes);
