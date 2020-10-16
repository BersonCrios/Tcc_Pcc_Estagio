import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UsuarioService } from '../../services/usuario.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  loginFormLoading: boolean = false;
  loginFormErrors: any;
  modalSwal: any;

  constructor(private fb: FormBuilder,
    private router: Router,
    private authService: AuthService
    ) {
    this.createForm();
  }

  ngOnInit() {
    this.autenticado();
  }

  ngOnChanges() {
    this.autenticado();
  }

  autenticado() {
    if (localStorage.getItem('token')) {
      // if (helper.isTokenExpired(localStorage.getItem('token'))) {
      //   this.router.navigate(["/comercial"]);
      // } else {
      //   localStorage.clear();
      //   this.router.navigate(["/entrar"]);
      // }      
    }
  }

  createForm() {
    this.loginForm = this.fb.group({
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
    });


  }


  entrar() {
    this.loginFormLoading = true;
    this.authService.login(this.loginForm.value)
      .subscribe((response) => {
        localStorage.setItem('user', JSON.stringify(response['user'])); 
        this.router.navigate(["/pages/locais"]);
      }, error => {
        
      })
  }

  chamaCadastro(){
    this.router.navigate(["/cadastrar-se"]);
  }
}
