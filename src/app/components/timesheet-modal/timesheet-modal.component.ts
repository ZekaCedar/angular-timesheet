import { ChangeDetectorRef, Component, Inject, InjectionToken, OnInit, Optional } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { TimesheetService } from 'src/app/services/timesheet.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Timesheet } from 'src/app/common/timesheet';
import { UserService } from 'src/app/services/user.service';
import { StatusService } from 'src/app/services/status.service';
import { Status } from 'src/app/common/status';
import { User } from 'src/app/common/user';

@Component({
  selector: 'app-timesheet-modal',
  templateUrl: './timesheet-modal.component.html',
  styleUrls: ['./timesheet-modal.component.css']
})
export class TimesheetModalComponent implements OnInit {

  timesheetFormGroup!: FormGroup;
  update: boolean = false;
  timesheetId: number = 0;
  statusList: Status[] = [];
  userList: User[] = [];
  selectedUserId: number = 0;
  selectedStatusId: number = 0;

  constructor(private formBuilder: FormBuilder,
              private timesheetService: TimesheetService,
              private userService: UserService,
              private statusService: StatusService,
              private router: Router,
              private cdr: ChangeDetectorRef,
              @Optional() public dialogRef: MatDialogRef<TimesheetModalComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any){
                console.log("this Data is from TimesheetModalComponent");
                console.log(data);
              }

  ngOnInit(): void {
    this.timesheetFormGroup = this.formBuilder.group({
      timesheet: this.formBuilder.group({
        id:[''],
        taskName:[''],
        projectName:[''],
        assignUser:[''],
        taskStatus:[''],
        taskStartDt:[''],
        taskEndDt:[''],
     /*    createUser:[''],
        createDate:[''],
        modifyUser:[''],
        modifyDate:[''],
        taskId:[''],
        statusId:[''] */
      })
    });

    // Populate the form if data is available
    if (this.data && this.data.timesheetData) {
      this.populateForm(this.data.timesheetData);
      // Set the flag based on the presence of an identifier (e.g., id)
      this.update = !!this.data.timesheetData.id;
      this.timesheetId = this.data.timesheetData.id;
    }

    // populate status drop down list
    this.statusService.getStatusList().subscribe(
      data => {
        console.log("Retrieved status data: "+ JSON.stringify(data));
        this.statusList = data;
      }
    )
    // populate assign user drop down list
    this.userService.getUserList().subscribe(
      data => {
        console.log("Retrieved status data: "+ JSON.stringify(data));
        this.userList = data;
      }
    )

  }


  populateForm(timesheetData: any) {
    this.timesheetFormGroup.patchValue({
      timesheet: {
        taskName: timesheetData.taskName,
        projectName: timesheetData.projectName,
        assignUser: timesheetData.assignUser,
        taskStatus: timesheetData.taskStatus,
        taskStartDt: timesheetData.taskStartDt,
        taskEndDt: timesheetData.taskEndDt,
        // ... other fields ...
      }
    });
  }

  onSubmit(){
    console.log('Handling the submit button');
    console.log(this.timesheetFormGroup.get('timesheet')?.value);
    console.log("The Project Name is :" + this.timesheetFormGroup.get('timesheet')?.value.projectName);

    let TimesheetFormData = this.timesheetFormGroup.get('timesheet')?.value;

    let timesheet = new Timesheet(
      TimesheetFormData.Id,
      TimesheetFormData.taskName,
      TimesheetFormData.projectName,
      TimesheetFormData.assignUser,
      TimesheetFormData.taskStatus,
      new Date(TimesheetFormData.taskStartDt),
      new Date(TimesheetFormData.taskEndDt),
      // TimesheetFormData.createUser="siti",
      // TimesheetFormData.createDate = new Date(""),
      // TimesheetFormData.modifyUser = "null",
      // TimesheetFormData.modifyDate = new Date(""),
      TimesheetFormData.userId = this.selectedUserId,
      TimesheetFormData.statusId = this.selectedStatusId,
    );

    console.log(timesheet);

    if(this.update){
      this.handleUpdateTimesheet(this.timesheetId, timesheet);
    }else{
      this.handleCreateTimesheet(timesheet);
    }

  }

   // refactor section

  private handleCreateTimesheet(timesheet: Timesheet) {
    this.timesheetService.createTimesheet(timesheet).subscribe(
      {
        next: response => {
          alert(`Your Timesheet is sucessfully created`);
        },
        error: err => {
          alert(`There was an error: ${err.message}`);
        }
      }
    );
  }

  private handleUpdateTimesheet(timesheetId:number, timesheet: Timesheet) {
    // Call REST API via TimesheetService
    this.timesheetService.updateTimesheet(timesheetId, timesheet).subscribe(
      {
        next: response => {
          alert(`Your Timesheet is sucessfully updated`);
        },
        error: err => {
          alert(`There was an error: ${err.message}`);
        }
      }
    );
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  selectedHandlerUserId(event: any) {
    // Retrieve the selected value from the event object
    const selectedValue = event.target.value;
    console.log("Selected User :", selectedValue);
    // Now you can use this value as needed

    // Split the string at the colon and take the first part
    const userIdString = selectedValue.split(':')[0].trim();

    // Optionally convert it to a number
    let userId = parseInt(userIdString, 10);

    console.log("Extracted User ID:", userId+=1);

    // this.selectedUserId = userId == 0 ? 1 : userId;
    this.selectedUserId = userId+=1;

  }

  selectedHandlerStatusId(event: any) {
    // Retrieve the selected value from the event object
    const selectedValue = event.target.value;
    console.log("Selected Status:", selectedValue);
    // Now you can use this value as needed

    // Split the string at the colon and take the first part
    const statusIdString = selectedValue.split(':')[0].trim();

    // Optionally convert it to a number
    let statusId = parseInt(statusIdString, 10);

    console.log("Extracted Status ID:", statusId+=1);

    // this.selectedStatusId = statusId == 0 ? 1 : statusId;
    this.selectedStatusId = statusId+=1;
  }


}

