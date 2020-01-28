import { Component, OnInit } from '@angular/core';
import { Repository } from '../repository';
import { RepoService } from '../repo.service';

@Component({
  selector: 'app-repo-form',
  templateUrl: './repo-form.component.html',
  styleUrls: ['./repo-form.component.css']
})
export class RepoFormComponent implements OnInit {
  private repository: Repository;

  constructor(private repoService: RepoService) { 
    this.repository = new Repository();
  }

  ngOnInit() {
  }

  onSubmit() {
    this.repoService.download(this.repository).subscribe(result => console.log(result));
  }

}
