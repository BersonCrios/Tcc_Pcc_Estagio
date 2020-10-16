import { Component, OnInit } from '@angular/core';
import { CategoriasService } from '../../services/categorias.service';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';

@Component({
  selector: 'app-categoria',
  templateUrl: './categorias.component.html',
  styleUrls: ['./categorias.component.scss']
})
export class CategoriasComponent implements OnInit {

  public listCategories: any[] = [];
  public categoriaForm: FormGroup;

  constructor(public categoriesService: CategoriasService, private fb: FormBuilder) { }

  ngOnInit() {
    this.initForms();
    this.getCategories();
  }

  private initForms() {
    this.categoriaForm = this.fb.group({
      nome: new FormControl("")
    });
  }

  private getCategories(){
    this.categoriesService.getCategories().subscribe(
      res =>{
        this.listCategories = res;
      },
      err =>{
        console.log(err);
      });
    }

  private createCategories(){
    this.categoriesService.createCategories(this.categoriaForm.value).subscribe(
      res=> {
        this.getCategories();
        this.categoriaForm.reset();
      },
      err => {
        console.log(err);
      }
    );
  }

  private deleteCategoria(id){
    this.categoriesService.deleteCategories(id).subscribe(
      res=> {
        this.getCategories();
        console.log(res);
      },
      err => {
        console.log(err);
      }
    );
  }

  private editCategoria(id){
    this.categoriesService.editCategories(id, this.categoriaForm.value).subscribe(
      res=> {
        console.log(res);
      },
      err => {
        console.log(err);
      }
    );
  }
}