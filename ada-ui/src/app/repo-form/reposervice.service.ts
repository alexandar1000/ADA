import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, throwError} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError, retry} from 'rxjs/operators';
import {Branch} from './branch.model';


//https://api.github.com/repos/joomla/joomla-cms/branches

@Injectable()
export class RepoService {
  private readonly BASE_API_URL = 'https://api.github.com/repos';
  private readonly LANGUAGE_Query = "https://api.github.com/search/repositories?q=repo:"

  dataChange: BehaviorSubject<Branch[]> = new BehaviorSubject<Branch[]>([]);
  // Temporarily stores data from dialogs
  dialogData: any;

  constructor(private httpClient: HttpClient) {
  }


  // get data(): Repo[] {
  //     return this.dataChange.value;
  // }
  // getDialogData() {
  //     return this.dialogData;
  // }


  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  getBranches(org: string, repo: string): Observable<Branch[]> {
    return this.httpClient.get<Branch[]>(this.BASE_API_URL + '/' + org + "/" + repo + '/branches');
  }

  checkBranch(org: string, repo: string, branch: string): Observable<any> {
    return this.httpClient.get(this.BASE_API_URL + '/' + org + "/" + repo + '/branches/' + branch);
  }

  checkLanguage(org: string, repo: string): Observable<any> {
    return this.httpClient.get(this.LANGUAGE_Query+ org + "/" + repo);
  }

  handleError = (error: any) => {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  }

}
