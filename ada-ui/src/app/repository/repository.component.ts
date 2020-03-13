import { Component, OnInit, Input } from '@angular/core';
import { SidebarService } from '../sidebar.service';

@Component({
  selector: 'app-repository',
  templateUrl: './repository.component.html',
  styleUrls: ['./repository.component.css']
})
export class RepositoryComponent implements OnInit {
  @Input() owner: string;
  @Input() repository: string;
  private branches: string[];
  private clicked: boolean;
  private cashed: boolean;
  private entry: string[];

  @Input()
  set newEntryBranch(entry: string[]) {
    this.entry = entry;
    let owner = entry[0];
    let repository = entry[1];
    let branch = entry[2];
    if (!this.branches) {
      this.branches = [];
    }
    if (repository === this.repository) {
      if (!this.cashed) {
        this.getBranchesList(owner, repository);
      }
      else {
        this.clicked = true;
      }
      if (!this.isBranchInList(branch)) {
        this.branches.push(branch);
      }
    }
  }

  constructor(private sidebarService: SidebarService) { }

  ngOnInit() {
    this.clicked = false;
    this.cashed = false;
  }

  getBranchesList(owner: string, repository: string): void {
    if (!this.clicked && !this.cashed) {
      this.sidebarService.getBranchesList(owner, repository).subscribe(branches => {
        this.branches = [];
        branches.forEach(branch => {
          this.branches.push(branch.branchName);
        });
        this.clicked = true;
        this.cashed = true;
      })

    }
    else if (this.cashed && this.clicked) {
      this.clicked = false;
    }
    else if (this.cashed && !this.clicked) {
      this.clicked = true;
    }
  }

  isBranchInList(branch: string): boolean {
    for (let index=0; index<this.branches.length; index++) {
      if (this.branches[index] === branch) {
        return true;
      }
    }
    return false;
  }
}
