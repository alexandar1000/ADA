import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RepoForm } from './classes/repoform';
import { User } from './classes/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class UserService {
  private repoFormUrl: string;

  constructor(private http: HttpClient) { 
    this.repoFormUrl = 'http://localhost:8080/repo-metadata';
  }

  public getUser(repoForm: RepoForm): Observable<User> {
    return this.http.post<User>(this.repoFormUrl, repoForm);
  }
}
