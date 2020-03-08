import { Component, OnInit, Input } from '@angular/core';
import { OwnerService } from '../owner.service';

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

  constructor(private ownerService: OwnerService) { }

  ngOnInit() {
    this.clicked = false;
    this.cashed = false;
  }

  getBranchesList(owner: string, repository: string): void {
    if (!this.clicked && !this.cashed) {
      this.ownerService.getBranchesList(owner, repository).subscribe(branches => {
        this.branches = [];
        branches.forEach(branch => {
          this.branches.push(branch.branchName);
        });
      })
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
}
