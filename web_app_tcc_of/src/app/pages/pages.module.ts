import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { routing } from './pages.routing';

import { LayoutModule } from '../shared/layout.module';
import { SharedModule } from '../shared/shared.module';

/* components */
import { PagesComponent } from './pages.component';
import { LoginComponent } from './login/login.component';
import { ReactiveFormsModule, ControlContainer, FormsModule } from '@angular/forms';
import { UsuarioService } from '../services/usuario.service';
import { AuthService } from '../services/auth.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { LocaisService } from '../services/locais.service';
import { CategoriasService } from '../services/categorias.service';
import { CadastroComponent } from './cadastro/cadastro.component';
import { NotFoundComponent } from './not-found/not-found.component';

@NgModule({
    imports: [
        CommonModule,
        LayoutModule,
        SharedModule,
        routing,
        ReactiveFormsModule,
        HttpClientModule, 
        FormsModule
    ],
    declarations: [
        PagesComponent,
        LoginComponent,
        CadastroComponent,
        NotFoundComponent
    ],
    providers:[
        UsuarioService,
        AuthService,
        HttpClient,
        LocaisService,
        CategoriasService,
        NotFoundComponent
    ]
})
export class PagesModule { }
