Register user

Meta:
@category advanced
@color red

Narrative: 

Register user

Scenario: SignUp User

Given Form SignUp
When fill username , password , password , password Hint , First Name , Last Name , developer@yahoo.com , 123-456-7890 , http://ssssss/dddd , address , city , state , 123232 , Indonesia
And click signup button
Then show Signup success message "You have successfully registered for access to this application."