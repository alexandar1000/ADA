import { Component, OnInit, Input, SimpleChanges } from '@angular/core';
import { SidebarService } from '../sidebar.service';

@Component({
  selector: 'app-owner',
  templateUrl: './owner.component.html',
  styleUrls: ['./owner.component.css']
})
export class OwnerComponent implements OnInit {
  @Input() owner: string;
  repositories: string[];
  clicked: boolean;
  cashed: boolean;
  entry: string[];
  highlightSnapshot: string[];
  previousHighlightSnapshot: string[];
  highlighted: boolean;

  @Input()
  set newEntryRepository(entry: string[]) {
    if (entry) {
      this.entry = entry;
      let owner = entry[0];
      let repository = entry[1];
      if (!this.repositories) {
        this.repositories = [];
      }
      if (owner === this.owner) {
        if (!this.cashed) {
          this.getReposList(owner);
        }
        else {
          this.clicked = true;
        }
        if (!this.isRepositoryInList(repository)) {
          this.repositories.push(repository);
        }
      }
    }
  }

  @Input()
  set toHighlightSnapshot(entry: string[]) {
    if (entry) {
      let ownerToHighlight = entry[0];
      this.highlightSnapshot = entry;
      if (ownerToHighlight === this.owner) {
        this.highlighted = true;
      }
    }
  }

  @Input()
  set toUnHighlightSnapshot(entry: string[]) {
    if (entry) {
      let ownerToUnHighlight = entry[0];
      this.previousHighlightSnapshot = entry;
      if (ownerToUnHighlight === this.owner) {
        if (this.highlightSnapshot) {
          if (this.highlightSnapshot[0] !== this.owner) {
            this.highlighted = false;
          }
        }
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
        this.clicked = true;
        this.cashed = true;
      });
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
