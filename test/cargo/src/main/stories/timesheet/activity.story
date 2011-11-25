Input Activity

Meta:
@category advanced
@color red

Narrative: 

User Story Input Activity

Scenario: Input Activity 

Given I am on Login Form TimeSheet  
When do login user user
Then authenticated 
When click menu Activity menu
Then show Activity form
When fill in data to Activity Form Date value 08/31/2011 , Time from value 08:30 , Time to value 17:30 , Select CDG , Activities Name TDD LAB , Case User Story One , ICenter No JIRA-121 , Location Aprisma , Status Pending , Remark is no Remark .
And click Activity Save Button 
Then show message data Activity has been saved

Scenario: Activity fill only mandatory field 

Given I am on Login Form TimeSheet  
When do login user user
Then authenticated 
When click menu Activity menu
Then show Activity form
When fill in Mandatory data to Activity Form Date value 08/31/2011 , Time from value 08:30 , Time to value 17:30 , Activities Name TDD LAB .
And click Activity Save Button 
Then show message data Activity has been saved