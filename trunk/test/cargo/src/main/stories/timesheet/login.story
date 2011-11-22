Story login

Meta:
@category advanced
@color red

Narrative: 

This is User Story authentication 

Scenario: Login fail

Given I am on Login Form TimeSheet
When do login user passwordsalah
Then login fail

Scenario: Login

Given I am on Login Form TimeSheet  
When do login user user
Then authenticated 
When logout
Then login form 
