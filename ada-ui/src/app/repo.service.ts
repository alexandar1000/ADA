import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Repository } from './repository';

@Injectable({
  providedIn: 'root'
})

export class RepoService {
  private repoFormUrl: string;

  constructor(private http: HttpClient) { 
    this.repoFormUrl = 'http://localhost:8080/addEntry';
  }

  public download(repository: Repository) {
    return this.http.post<Repository>(this.repoFormUrl, repository);
  }
}
