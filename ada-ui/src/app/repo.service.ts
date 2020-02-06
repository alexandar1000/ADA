import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RepoForm } from './repoform';

@Injectable({
  providedIn: 'root'
})

export class RepoService {
  private repoFormUrl: string;

  constructor(private http: HttpClient) { 
    this.repoFormUrl = 'http://localhost:8080/repo-metadata';
  }

  public download(repository: RepoForm) {
    return this.http.post<RepoForm>(this.repoFormUrl, repository);
  }
}
