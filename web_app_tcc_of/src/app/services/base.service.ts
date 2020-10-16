import { HttpHeaders } from '@angular/common/http';

export class BaseService {
    protected headers: HttpHeaders;
    protected token: string;

    constructor() {
        if (localStorage.getItem('token')) {
            this.token = localStorage.getItem('token');
            this.headers = new HttpHeaders()
                .set("Accept", "application/json")
                .set("Authorization", this.token);
        } else {
            this.headers = new HttpHeaders()
                .set("Accept", "application/json")
        }
    }

    setHeader(token?:string) {
        this.token = localStorage.getItem('token');
        token = token ? token : this.token ? this.token : '';
        this.headers = new HttpHeaders()
            .set("Accept", "application/json")
            .set("Authorization", token);
    }


    setHeaderImage(token = '') {
        token = token ? token : this.token ? this.token : '';
        this.headers = new HttpHeaders()
            .set('Accept', 'x-www-form-urlencoded')
            .set("Access-Control-Allow-Origin", "*")
            .set("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept")
            .set("Authorization", token);
    }

    toURLSearchParams(formValue): string {
        let params = new URLSearchParams();
        for (let key in formValue) {
            params.set(key, formValue[key])
        }
        return `${params.toString()}`;
    }
}
