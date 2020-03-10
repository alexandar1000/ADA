import { Component, OnInit, Input } from '@angular/core';
import { SidebarService } from '../sidebar.service';

@Component({
  selector: 'app-owner',
  templateUrl: './owner.component.html',
  styleUrls: ['./owner.component.css']
})
export class OwnerComponent implements OnInit {
  @Input() owner: string;
  private repositories: string[];
  private clicked: boolean;
  private cashed: boolean;
  private entry: string[];

  @Input()
  set newEntryRepository(entry: string[]) {
    this.entry = entry;
    let owner = entry[0];
    let repository = entry[1];
    if (owner === this.owner && this.repositories) {
      if (!this.isRepositoryInList(repository)) {
        this.repositories.push(repository);
      }
    }
  }

  constructor(private sidebarService: SidebarService) { }

  ngOnInit() {
    this.clicked = false;
    this.cashed = false;
  }

  getReposList(ownerName: string): void {
    if (!this.cashed && !this.clicked) {
      this.sidebarService.getReposList(ownerName).subscribe(repositories => {
        this.repositories = [];
        repositories.forEach(repository => {
          this.repositories.push(repository.repoName);
        });
      });
      this.clicked = true;
      this.cashed = true;
    }
    else if (this.cashed && this.clicked) {
      this.clicked = false;
    }
    else if (this.cashed && !this.clicked) {
      this.clicked = true;
    }
  }

  isRepositoryInList(repository: string): boolean {
    for (let index=0; index<this.repositories.length; index++) {
      if (this.repositories[index] === repository) {
        return true;
      }
    }
    return false;
  }
}
