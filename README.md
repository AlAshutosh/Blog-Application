<h1>Blog Application</h1>
This is a blog management application built with Spring Boot and MySQL. It allows users to create, update, delete, and fetch blogs, as well as generate reports based on the most frequently used words in blogs created by the user.<br>
<h2>Features</h2>
Login: Allows users to authenticate.<br>
Create Blog: Allows users to create a new blog.<br>
Fetch Blog: Retrieves blogs associated with a user by userId.<br>
Update Blog: Updates an existing blog.<br>
Delete Blog: Deletes an existing blog by blogId.<br>
Generate Report: Generates a report with the top 5 most frequently used words from all blogs created by the user.<br>
<h2>Technologies Used</h2>
Spring Boot: A Java-based framework to build and run the application.<br>
MySQL: Used to store user and blog data.<br>
Thymeleaf: Used to render HTML templates for the UI.<br>
<h2>Prerequisites</h2>
Java 11 or higher<br>
MySQL Database<br>
Maven or Gradle<br>
<h3>1. Clone the Repository</h3>
bash<br>
git clone https://github.com/AlAshutosh/blog_assignment.git<br>
<h3>2. Configure MySQL Database</h3>
Create a new database in MySQL named blogd.<br>
Create the necessary tables for User and Blog.<br>
You can refer to the application.properties file for connection details.<br>
<h3>3.Setup Application Properties</h3>
In the src/main/resources/application.properties file, configure your database connection:<br>
spring.datasource.url=jdbc:mysql://localhost:3306/blog_app <br>
spring.datasource.username=root <br>
spring.datasource.password=yourpassword <br>
spring.jpa.hibernate.ddl-auto=update <br>
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver <br>
<h3>4. Build and Run the Application</h3> 
To build and run the Spring Boot application, execute the following commands:

bash mvn clean install mvn spring-boot:run The application will start on localhost:8081.<br>

<h2>API Endpoints</h2>

1. SignUp URL:(http://localhost:8081/auth/signup)<br>
Method: POST <br>
Description: SignUp a user by username and password.<br>
Request Body:
json Copy Edit { "username": "your-username", "password": "your-password" } <br>

2. Login URL:(http://localhost:8081/auth/login) <br>
Method: POST <br>
Description: Authenticates a user by username and password.<br> 
Request Body:
json Copy Edit { "username": "your-username", "password": "your-password" } <br>

3. Create a Blog URL:(http://localhost:8081/blogs/create)<br> 
Method: POST <br>
Description: Creates a new blog. <br>
Request Body:
json Copy Edit { "title": "Your Blog Title", "body": "Your Blog Content" } <br>

4. Fetch a Blog by User URL:(http://localhost:8081/blogs/dashboard)<br>
Method: GET <br>
Description: Retrieves all blogs for a specific user by userId.<br>

5. Update a Blog URL:(http://localhost:8081/blogs/{blogId}/edit) <br>
Method: PUT <br>
Description: Updates an existing blog. <br>
Request Body:
json Copy Edit { "title": "Updated Blog Title", "body": "Updated Blog Content" } <br>

6. Delete a Blog URL: /blogs/delete/{blogId} <br>
Method: DELETE <br>
Description: Deletes a blog by blogId.<br>

7. Generate Report URL:(http://localhost:8081/report) <br>
Method: GET <br>
Description: Generates a report showing the top 5 most frequently used words in blogs for a specific user.<br>
<h2>UI </h2>
The front-end for interacting with the API can be built using HTML, Thymeleaf, and JavaScript.<br>
