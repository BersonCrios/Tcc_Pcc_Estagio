import { Component, Input } from '@angular/core';
import { collapse } from '../../animation/collapse-animate';
import { GlobalService } from '../../services/global.service';
import { Router } from '@angular/router';

@Component({
  selector: 'du-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss'],
  animations: [collapse]
})
export class MenuComponent {
  @Input() menuInfo: any;

  constructor(private _globalService: GlobalService, private router: Router) { }

  private isToggleOn(item) {
    item.toggle === 'on' ? item.toggle = 'off' : item.toggle = 'on';
  }

  private _selectItem(item) {
    this._globalService.dataBusChanged('isActived', item);
  }

  sair(){
    localStorage.clear();
    this.router.navigate(["/entrar"]);
  }
}