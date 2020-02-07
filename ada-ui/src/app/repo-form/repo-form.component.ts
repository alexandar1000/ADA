import { Component, OnInit } from '@angular/core';
import { RepoForm } from '../repoform';
import { RepoService } from '../repo.service';

@Component({
  selector: 'app-repo-form',
  templateUrl: './repo-form.component.html',
  styleUrls: ['./repo-form.component.css']
})
export class RepoFormComponent implements OnInit {
  private repository: RepoForm;

  constructor(private repoService: RepoService) { 
    this.repository = new RepoForm();
  }

  ngOnInit() {
  }

  onSubmit() {
    this.repoService.download(this.repository).subscribe(result => this.goToRepoMetadata());
  }

  goToRepoMetadata() {
    
  }

}
