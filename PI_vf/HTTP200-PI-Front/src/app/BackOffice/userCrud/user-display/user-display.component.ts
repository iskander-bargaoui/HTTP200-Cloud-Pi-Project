import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/FrontOffice/Services/auth.service';
import { User } from 'src/app/Models/user';
import { NgxQRCodeModule, NgxQrcodeElementTypes, NgxQrcodeErrorCorrectionLevels } from '@techiediaries/ngx-qrcode';
import * as XLSX from 'xlsx';

@Component({
  selector: 'app-user-display',
  templateUrl: './user-display.component.html',
  styleUrls: ['./user-display.component.css']
})
export class UserDisplayComponent implements OnInit{
  fileName= 'UsersDataBase.xlsx';
  user!: User[];
  p: number =1;
  count: number =0;
  title = 'UserQR';
  elementType = NgxQrcodeElementTypes.URL;
  correctionLevel = NgxQrcodeErrorCorrectionLevels.HIGH;
  value = 'u';

  constructor(private userService: AuthService,
    private router: Router ) { }

  ngOnInit(): void {
    this.getUsers();
  }

  private getUsers(){
    this.userService.getAllUsers().subscribe(data => {
      this.user = data;
    });
  }

  // fonctionDetails(id: number){
  //   this.router.navigate(['fonction-details', id]);
  // }

  updateUser(id: number){
    this.router.navigate(['update-event', id]);
  }

  deleteUser(id: number){

    this.userService.deleteUser(id).subscribe( data => {
      console.log(data);
      this.getUsers();

    })

    let R = confirm("Are you sure you want to delete this user?");
    if (R){
    this.userService.deleteUser(id).subscribe( data => {
      console.log(data);
      alert("Successfully deleted user!");
      this.getUsers();

    })}

  }
  enabledUser(id: number){
    this.userService.enableUser(id).subscribe( data => {
      console.log(data);
      this.getUsers();

    })
  }
  disableUser(id: number){
    this.userService.disableUser(id).subscribe( data => {
      console.log(data);
      this.getUsers();

    })
  }
  // Export DB as Excel
  exportexcel(): void
  {
    /* pass here the table id */
    let element = document.getElementById('UsersTable');
    const ws: XLSX.WorkSheet =XLSX.utils.table_to_sheet(element);
 
    /* generate workbook and add the worksheet */
    const wb: XLSX.WorkBook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');
 
    /* save to file */  
    XLSX.writeFile(wb, this.fileName);
 
  }
}
