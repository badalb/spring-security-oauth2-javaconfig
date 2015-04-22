# spring-security-oauth2-javaconfig

1. create mysql database with name test

2. To start the application from your favourite editor right click on Application.java ->Run As-> Java Application
   or
  Package it to war file and deploy it to tomcat
3. Execute the script below -
  INSERT INTO `oauth_client_details` (`client_id`,`resource_ids`,`client_secret`,`scope`,`authorized_grant_types`,`web_server_redirect_uri`,`authorities`,`access_token_validity`,`refresh_token_validity`,`additional_information`,`autoapprove`) VALUES ('my-trusted-client','rest_api',NULL,'trust,read,write','client_credentials,authorization_code,implicit,password,refresh_token',NULL,'ROLE_USER,ROLE_TRUSTED_CLIENT',600,6000,NULL,NULL);
  INSERT INTO `oauth_client_details` (`client_id`,`resource_ids`,`client_secret`,`scope`,`authorized_grant_types`,`web_server_redirect_uri`,`authorities`,`access_token_validity`,`refresh_token_validity`,`additional_information`,`autoapprove`) VALUES ('my-trusted-client-with-secret','rest_api','somesecret','trust,read,write','authorization_code,implicit,password,refresh_token',NULL,'ROLE_USER,ROLE_TRUSTED_CLIENT',30,60,NULL,NULL);

4. Try Accessing the URL: http://localhost:8080/api/v1/secure/hello  you will get the out put like 
<oauth>
<error_description>
An Authentication object was not found in the SecurityContext
</error_description>
<error>unauthorized</error>
</oauth>   from your browser.

Accessing it from your terminal will give you - $ curl http://localhost:8080/api/v1/secure/hello
{"error":"unauthorized","error_description":"An Authentication object was not found" }


5. curl hu-my-trusted-client:@localhost:8080/oauth/token -d grant_type=password -d username=assetmanager -d password=password
{"access_token":"0d96070f-c193-44e7-8be9-20985a121326","token_type":"bearer","refresh_token":"9a9c38a7-a53d-43de-a449-aee1776997e6","expires_in":59,"scope":"read trust write"}

6. curl -H "Authorization:Bearer 0d96070f-c193-44e7-8be9-20985a121326" http://localhost:8080/api/v1/secured/hello will print Hello World
