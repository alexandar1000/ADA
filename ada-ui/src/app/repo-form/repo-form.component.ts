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

  public org;
  public repo;
  public repoSearchForm!: FormGroup;
  public branches = [];
  public dropdownSelectedBranch = "";
  public githubPastedOrTypedURL = "";
  public loadingMessage = "Loading..."

  constructor(public httpClient: HttpClient, private _snackBar: MatSnackBar, private analyserService: AnalyserService,
              private router: Router, private formBuilder: FormBuilder, private repoService: RepoService,
              private spinner: NgxSpinnerService, private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.reactiveForm();
    this.spinner.show();
    setTimeout(() => {
      this.spinner.hide();
    }, 3000);
  }

  public reactiveForm(): void {
    this.repoSearchForm = this.formBuilder.group({
      gitURLForm: [null, [Validators.required]], // from-UI
      branchForm: [null, [Validators.required]], // from-UI auto generated
    });
  }

  private errorSnackBar(): void {
    this.snackBar.open('Provided Repository or Branches Not Found', 'Close', {
      duration: 5000,
      verticalPosition: 'bottom',
      horizontalPosition: 'center',
    });
  }

  private languageErrorSnackBar(): void {
    this.snackBar.open('Currently We only support Java Repository', 'Close', {
      verticalPosition: 'bottom',
      horizontalPosition: 'start',
    });
  }

  private successSnackBar(): void {
    this.snackBar.open('Branches Loaded!! Select a Branch', 'Close', {
      duration: 5000,
      verticalPosition: 'bottom',
      horizontalPosition: 'start',
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
    this.loadingMessage = "Loading Branches"
    this.spinner.show();
    setTimeout(() => {
    }, 3000);
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
    this.org = org_repo[0];
    this.repo = org_repo[1];
    this.repoService.getBranches(this.org, this.repo).subscribe(
      (bran) => { //Next callback
        bran.forEach(b => {
          this.branches.push(b.name);
          this.githubPastedOrTypedURL = url;
        });
        setTimeout(() => {
          this.successSnackBar();
          this.repoSearchForm.get('gitURLForm').disable();
        }, 3000);
      },
      (error) => {
        console.error('error caught in component')
        setTimeout(() => {
          this.errorSnackBar();
          this.githubPastedOrTypedURL = "";
        }, 3000);
      }
    );
  }

  clearForm() {
    this.reactiveForm();
    this.githubPastedOrTypedURL = "";
    this.repoSearchForm.get('gitURLForm').enable();
    this.branches = [];
    this.dropdownSelectedBranch = "";


  }

  analyze() {
    this.loadingMessage = "Checking Repository Status"
    this.spinner.show();
    this.repoService.checkBranch(this.org, this.repo, this.repoSearchForm.value['branchForm']).subscribe(
      (response) => { //Next callback
        this.repoService.checkLanguage(this.org, this.repo).subscribe((response) => {
            let lan = response['items'][0]['language'];
            console.log(lan);
            if (lan == "Java") {
              console.log("given url" + this.githubPastedOrTypedURL);
              console.log("given branch" + this.dropdownSelectedBranch);
              //this.redirectToAnalysisDashBoard();
            } else {
              this.clearForm();
              this.languageErrorSnackBar();

            }
          },
          (errors) => {
          });
      },
      (error) => { //Error callback
        this.errorSnackBar()
      });
    setTimeout(() => {
      this.spinner.hide();
    }, 1000);
  }

  redirectToAnalysisDashBoard() {
    this.analyserService.repoUrl = this.githubPastedOrTypedURL;
    this.analyserService.repoBranch = this.dropdownSelectedBranch;
    this.router.navigate(['/dashboard/current']);
  }


}
