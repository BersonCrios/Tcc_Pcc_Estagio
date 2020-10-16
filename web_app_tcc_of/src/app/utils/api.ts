import { environment } from "../../environments/environment";

export const API = environment.API;

export class EndPoints {

  //AUTHENTICAÇÃO
  public static login = () => {
    return `${API}/users/auth`;
  };

  //USUÁRIO
  public static createUser = () => {
    return `${API}/users/create`;
  };


  //LOCAIS
  public static getLocals = () => {
    return `${API}/locals`;
  };

  public static createLocals = () => {
    return `${API}/locals/create`;
  };

  public static deleteLocals = (param) => {
    return `${API}/locals/${param}`;
  };

  public static editLocals = (param) => {
    return `${API}/locals/${param}`;
  };


  //CATEGORIAS
  public static getCategories = () => {
    return `${API}/categories`;
  };

  public static createCategories = () => {
    return `${API}/categories/create`;
  };

  public static deleteCategories = (param) => {
    return `${API}/categories/${param}`;
  };

  public static editCategories = (param) => {
    return `${API}/categories/${param}`;
  };


  //APPS
  public static CreateApps = () => {
    return `${API}/apps/create`;
  };
}
