Input One Day Absent

Meta:
@category advanced
@color red

Narrative: 

User Story Input Absent

Scenario: Input One Day Absent

Given I am on Login Form TimeSheet  
When do login user user
Then authenticated 
When click menu One Day Absent menu
Then show One Day Absent form
When fill in data to absent Form Date value 08/31/2011 , Select value bolos for type, reason value no reason , click Save button 
Then show message data One Day Absent has been saved

Scenario: Input Range Day Absent

Given I am on Login Form TimeSheet  
When do login user user
Then authenticated 
When click menu Range Day Absent menu
Then show Range Day Absent form
When fill in data to Range Day Absent Form From Date value 08/29/2011 , To Date value 08/31/2011 , Select value cuti for type, reason value no reason , click Save button 
Then show message data Range Day Absent has been saved