import { Component, OnInit, Input } from '@angular/core';
import { OwnerService } from '../owner.service';

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

  constructor(private ownerService: OwnerService) { }

  ngOnInit() {
    this.clicked = false;
    this.cashed = false;
  }

  getReposList(ownerName: string): void {
    if (!this.cashed && !this.clicked) {
      this.ownerService.getReposList(ownerName).subscribe(repositories => {
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
}
