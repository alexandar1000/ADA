import { Injectable, Output, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RepoForm } from './classes/repoform';
import { User } from './classes/user';
import { Observable, BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class UserService {
  private repoFormUrl: string;
  private userListUrl: string;
  user = new BehaviorSubject<User>(new User());
  currentUser = this.user.asObservable();

  constructor(private http: HttpClient) { 
    this.repoFormUrl = 'http://localhost:8080/repo-metadata';
    this.userListUrl = 'http://localhost:8080/users'
  }

  public getUser(repoForm: RepoForm): Observable<User> {
    return this.http.post<User>(this.repoFormUrl, repoForm);
  }

  public getUserList(): Observable<User[]> {
    return this.http.get<User[]>(this.userListUrl);
  }

  public addUserToNavigation(user: User) {
    this.user.next(user);
  }
}
