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
Then default activity date is today
When fill in data to Activity Form Date value 08/31/2011 , Time from value 08:30 , Time to value 17:30 , click Save button 
Then show message data Activity has been saved
