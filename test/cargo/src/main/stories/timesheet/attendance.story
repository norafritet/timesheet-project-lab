Input Check Roll

Meta:
@category advanced
@color red

Narrative: 
User Story Attendance
1. 

Scenario: Attendance


Given I am on Login Form TimeSheet  
When do login user user
Then authenticated 
When click menu Check Roll menu
Then show Check Roll form
When fill in data to Check Roll Date value 08/31/2011 , Check In Time value 08:30 , Check Out Time value 17:30 , click Save button 
Then show message data Check Roll has been saved