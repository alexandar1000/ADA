import { Component, OnInit } from '@angular/core';
import { RepoForm } from '../classes/repoform';
import { UserService } from '../user.service';
// import { User } from '../classes/user';

@Component({
  selector: 'app-repo-form',
  templateUrl: './repo-form.component.html',
  styleUrls: ['./repo-form.component.css']
})
export class RepoFormComponent implements OnInit {
  private repositoryForm: RepoForm;

  constructor(private userService: UserService) { 
    this.repositoryForm = new RepoForm();
  }

  ngOnInit() {
  }

  onSubmit() {
    this.userService.getUser(this.repositoryForm).subscribe(user => console.log(user));
  }
}
