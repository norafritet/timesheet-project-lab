Inquiry activity

Meta:
@category advanced
@color red

Narrative: 

User Story Inquiry Activity

Scenario: Weekly Activity view

Given I am on Login Form TimeSheet  
When do login user user
Then authenticated 
When click menu Inquiry Activity
Then show Inquiry Activity
When fill in inquiry form select Year 2011 , Month 08 , Week 2 .
And click inquiry button view
Then show list activity one week. 

Scenario: Weekly Activity view

Given I am on Login Form TimeSheet  
When do login user user
Then authenticated 
When click menu Inquiry Activity
Then show Inquiry Activity
When fill in inquiry form select Year 2011 , Month 08 , Week 2 .
And click inquiry button download
Then show list activity one week. 

Scenario: Monthly Activity view

Given I am on Login Form TimeSheet  
When do login user user
Then authenticated 
When click menu Inquiry Activity
Then show Inquiry Activity
When fill in inquiry form select Year 2011 , Month 08 , Week All .
And click inquiry button view
Then show list activity one month. 

Scenario: Monthly Activity download

Given I am on Login Form TimeSheet  
When do login user user
Then authenticated 
When click menu Inquiry Activity
Then show Inquiry Activity
When fill in inquiry form select Year 2011 , Month 08 , Week All .
And click inquiry button download
Then show list activity one month. 


Scenario: One Year Activity view

Given I am on Login Form TimeSheet  
When do login user user
Then authenticated 
When click menu Inquiry Activity
Then show Inquiry Activity
When fill in inquiry form select Year 2011 , Month All , Week All .
And click inquiry button view
Then show list activity one year. 

Scenario: One Year Activity view

Given I am on Login Form TimeSheet  
When do login user user
Then authenticated 
When click menu Inquiry Activity
Then show Inquiry Activity
When fill in inquiry form select Year 2011 , Month All , Week All .
And click inquiry button download
Then show list activity one year. 