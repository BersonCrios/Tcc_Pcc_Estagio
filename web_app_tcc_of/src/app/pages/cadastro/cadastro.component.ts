import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.scss']
})
export class CadastroComponent implements OnInit {

  cadastroForm: FormGroup;
  
  constructor(private fb: FormBuilder,
    private router: Router,
    private usuarioService: UsuarioService,) { }

  ngOnInit() {
    this.createForm();
  }


  createForm() {
    this.cadastroForm = this.fb.group({
      name: new FormControl('', [Validators.required]),
      username: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
    });
  }

  cadastrar() {
    this.usuarioService.cadastroUsuario(this.cadastroForm.value)
      .subscribe((response) => {
        this.router.navigate(["/entrar"]);
      }, error => {
        
      })
  }
}
