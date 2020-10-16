import { Component, OnInit } from '@angular/core';
import { LocaisService } from '../../services/locais.service';
import { FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { CategoriasService } from '../../services/categorias.service';

@Component({
  selector: 'app-locais',
  templateUrl: './locais.component.html',
  styleUrls: ['./locais.component.scss'],
})
export class LocaisComponent implements OnInit {

  public listLocals: any[] = [];
  public listCategories: any[] = [];
  public localsForm: FormGroup;

  constructor(public localService: LocaisService,public categoriesService: CategoriasService, private fb: FormBuilder) { }

  ngOnInit() {
    this.initForms();
    this.getLocals();
    this.getCategories();
  }

  private initForms() {
    this.localsForm = this.fb.group({
      nome: new FormControl(""),
      latitude: new FormControl(""),
      longitude: new FormControl(""),
      categoria: new FormControl(""),
      imagePath1: new FormControl(""),
      imagePath2: new FormControl("")
    });
  }

  private createLocal(){
    this.localService.createLocals(this.localsForm.value).subscribe(
      res=> {
        this.getLocals();
        this.localsForm.reset();
      },
      err => {
        console.log(err);
      }
    );
  }

  private getLocals(){
      this.localService.getLocals().subscribe(
        res =>{
          this.listLocals = res['locais'];
          console.log(res["locais"]);
          
        },
        err =>{
          console.log(err);
        }
      );
  }

  //PEGA CATEGORIA PARA MOSTRAR NO SPINNER
  private getCategories(){
    this.categoriesService.getCategories().subscribe(
      res =>{
        this.listCategories = res;
      },
      err =>{
        console.log(err);
      });
    }


  private deleteLocals(id){
    this.localService.deleteLocals(id).subscribe(
      res=> {
        this.getLocals();
        console.log(res);
      },
      err => {
        console.log(err);
      }
    );
  }
}
