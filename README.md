# introduce to reggie_takeaway_platform

<img width="1418" alt="image" src="https://user-images.githubusercontent.com/115228154/226215864-3277e901-b7b0-41dc-a275-d40200675668.png">


This is a login page. 
When you login in with the account and password :

1. Encrypt the password submitted on the page with md5
2. Query the database according to the username submitted on the page
3. If no query is found, return the login failure result
4. Password comparison, if not consistent, return login failure result
5. Check the status of the employee. If it is disabled, return the result that the employee is disabled
6. Login successfully, store the employee id in Session and return the login success result

The following is some functions on the this page:

![image](https://user-images.githubusercontent.com/115228154/226217143-9d4193d9-1f29-4162-b98a-9c2d1186e4a4.png)
Then it turns to 
<img width="1217" alt="image" src="https://user-images.githubusercontent.com/115228154/226217160-35329eb7-2b95-4292-974d-95b2faaf08c4.png">

![image](https://user-images.githubusercontent.com/115228154/226217265-1c98b4f5-b97a-45d3-a518-2e321527b6df.png)
Then it turns to 
<img width="1412" alt="image" src="https://user-images.githubusercontent.com/115228154/226217293-c8427726-547f-451a-bdae-67456b82e1b3.png">

![image](https://user-images.githubusercontent.com/115228154/226217545-dffb0734-676c-48f3-ad10-253c64296961.png)

![image](https://user-images.githubusercontent.com/115228154/226217608-a94b4380-f686-46c9-a51a-3be2ae92bb93.png)
Then it turns to
<img width="1417" alt="image" src="https://user-images.githubusercontent.com/115228154/226217634-c8fe17c3-0783-458e-8d9b-30880b2224d6.png">
you can edit member's info here

