# introduce to reggie_takeaway_platform

<img width="1418" alt="image" src="https://user-images.githubusercontent.com/115228154/226215864-3277e901-b7b0-41dc-a275-d40200675668.png">


This is a login page. When you login in with the account and password :

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
