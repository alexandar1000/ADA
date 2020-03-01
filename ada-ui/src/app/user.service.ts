import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
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
    this.userListUrl = 'http://localhost:8080/users'
  }

  public getUser(urlForm: string, branchName: string): Observable<any> {
    let params = new HttpParams()
      .set('url', urlForm)
      .set('branch', branchName);
    return this.http.post<any>(this.userListUrl, params);
  }

  public getUserList(): Observable<User[]> {
    return this.http.get<User[]>(this.userListUrl);
  }

  public addUserToNavigation(user: User) {
    this.user.next(user);
  }
}
