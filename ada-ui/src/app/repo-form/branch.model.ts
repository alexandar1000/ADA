export interface Branch {
    name:string;
    commit :{
      sha:string;
      url:string;
    }
    protected : boolean;
  }