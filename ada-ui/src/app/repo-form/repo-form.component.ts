import {Component, ElementRef, OnInit, Renderer2, ViewChild} from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';
import {AnalyserService} from "../analyser.service";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {RepoService} from './reposervice.service';
import {HttpClient} from '@angular/common/http';
import {Branch} from './branch.model';
import {NgxSpinnerService} from "ngx-spinner";


@Component({
  selector: 'app-repo-form',
  templateUrl: './repo-form.component.html',
  styleUrls: ['./repo-form.component.css']
})
export class RepoFormComponent implements OnInit {


  public repoSearchForm!: FormGroup;
  public branches = [];
  public dropdownSelectedBranch = "master";
  public githubPastedOrTypedURL="";


  constructor(public httpClient: HttpClient, private _snackBar: MatSnackBar, private analyserService: AnalyserService,
              private router: Router, private formBuilder: FormBuilder, private repoService: RepoService,
              private spinner: NgxSpinnerService) {
  }

  ngOnInit() {
    this.reactiveForm();
  }

  public reactiveForm(): void {
    this.repoSearchForm = this.formBuilder.group({
      gitURLForm: [null, [Validators.required]], // from-UI
      branchForm: [null, [Validators.required]], // from-UI auto generated
    });
  }

  // public onGitURLChanges(event: any): void {
  //   let val=event.target.value;
  //   this.loadbranches(val)
  //   this.spinner.show();
  //   setTimeout(() => {
  //     this.spinner.hide();
  //   }, 3000);
  // }

  onPaste(event: any) {
    let val = event.clipboardData.getData('Text')
    this.spinner.show();
    this.loadbranches(val)
    setTimeout(() => {
      this.spinner.hide();
    }, 3000);
  }

  public onBranchSelected(event: any): void {
    this.dropdownSelectedBranch = event.value;
  }

  private loadbranches(value): void {
    let url = value;
    let map = url.split("https://github.com/");
    let org_repo = map[1].split("/");
    let org = org_repo[0];
    let repo = org_repo[1]
    this.repoService.getBranches(org, repo).subscribe((bran: Branch[]) => {
      bran.forEach(b => {
        this.branches.push(b.name)
      });
    });
  }

  onSubmit() {
    console.log(this.repoSearchForm.value)
    this.analyserService.repoUrl = this.repoSearchForm.value['gitURLForm']
    this.analyserService.repoBranch = this.repoSearchForm.value['branchForm']
    this.router.navigate(['/dashboard/current']);
  }


}
