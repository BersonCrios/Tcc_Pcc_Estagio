import { Component, Input } from '@angular/core';
import { GlobalService } from '../../services/global.service';

@Component({
  selector: 'pages-top',
  templateUrl: './pages-top.component.html',
  styleUrls: ['./pages-top.component.scss'],
})
export class PagesTopComponent {
  avatarImgSrc: string = 'assets/images/sem-foto.jpg';
  userName: string;

  sidebarToggle: boolean = true;
  tip = { ring: true, email: true };

  

  usuario;

  constructor(private _globalService: GlobalService) { 
    this.pegaUsuario();
  }

  public _sidebarToggle() {
    this._globalService.data$.subscribe(data => {
      if (data.ev === 'sidebarToggle') {
        this.sidebarToggle = data.value;
      }
    }, error => {
      console.log('Error: ' + error);
    });
    this._globalService.dataBusChanged('sidebarToggle', !this.sidebarToggle);
  }

  pegaUsuario(){
      this.usuario = JSON.parse(localStorage.getItem('user'));      
      this.userName = this.usuario.name;
  }
}
