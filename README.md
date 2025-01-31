
# Powerpoint Presentation For Toddler Time
[View Toddler Time Presentation](https://1drv.ms/p/c/f4d2b5d6645ba856/ESsPJWjEEeNFjXXr4zybNAIBTXMxhZ6iL8rSIGwaNbjZdA?e=E4SPTU)

# Video Demonstration 
[Video Toddler Time Presentation](https://1drv.ms/v/c/f4d2b5d6645ba856/EYTCgnSnKKtBuHBoP8NBMycBdhBTINeiaYrHOLTPAOAiaQ)

# Toddler Time

## Overview
**Toddler Time** is a fun and interactive application designed to help parents and caregivers guide toddlers through essential developmental tasks. The app allows users to create tasks for their toddlers, set alarms/timers, and track their progress in an engaging and accessible way. With features like choice-based learning, fun aesthetics, and customizable alarms, **Toddler Time** encourages independence and skill development for young children.

## Features
- **Task Management**: Create, edit, and track tasks for your toddlers.
- **Child Profiles**: Manage profiles for multiple children and track their individual progress.
- **Alarms/Timers**: Set customized alarms/timers with visual representations.
- **Choice-based Learning**: Encourage your child's independence by letting them make choices in their daily routine.
- **Parental Dashboard**: View and manage tasks, child profiles, and app settings.

## Technologies Used

The following technologies have been used to build the application:

- **Spring Boot**
- **MySQL**
- **Lombok**
- **Spring Security**
- **Thymeleaf**
- **JPA**
- **Maven**

## Reference Documentation
For further reference, please consider the following sections:

- [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
- [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/html/)
- [Create an OCI image](https://docs.spring.io/spring-boot/docs/current/reference/html/container-images.html)
- [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using.devtools)
- [Spring Security](https://docs.spring.io/spring-security/site/docs/current/reference/html5/)
- [Spring Session](https://docs.spring.io/spring-session/reference/)
- [Thymeleaf](https://www.thymeleaf.org/documentation.html)
- [Spring Web](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html)


## Installation
## Steps to Set Up the Environment

1. Start by using the Spring Initializr to set up the project and the dependencies: [Spring Initializr](https://start.spring.io)
   - Project: **Maven**
   - Language: **Java**
   - Spring Boot: **3.3.4**
   - Packaging: **Jar**
   - Java: **17**
   
2. Add the following dependencies:
   - Spring Web
   - Thymeleaf
   - Spring Security
   - Spring Data JPA
   - Lombok
   - MySQL Driver

## Steps to Set Up the Database

1. **Download and Install MySQL Server**: 
   - Visit [MySQL Installer](https://dev.mysql.com/downloads/installer/) and download the appropriate version for your operating system.
   
2. **Install MySQL Workbench**:
   - Download MySQL Workbench from the same site to connect to your MySQL Server and manage your databases visually.

3. **Create the Database**:
   - Open MySQL Workbench and connect to your MySQL Server. Then run the following SQL command to create your database:
     ```sql
     CREATE DATABASE ToddlerTimeDB;
     ```

4. **Update the `application.properties` File**:
   - Locate the `application.properties` file in `src/main/resources/` and update it with your database credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/ToddlerTimeDB
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```
   
## Open your browser and navigate to http://localhost:8080.


## Usage

### Sign Up and Profile Management
- Navigate to the **Sign Up** page to create a parent profile.
- Once registered, you can add child profiles and manage tasks for each child.

### Task Management
- Create, edit, and delete tasks from the **Parent Dashboard**.
- Assign tasks to specific children, and track their progress through the **Child Task** page.

### Alarms/Timer
- Visualize alarms and timers with fun, shape-based representations as the child completes tasks.

### Forgot Password
- If you forget your password, navigate to the **Forgot Password** page, enter your registered email, to receive a reset link.(visual only for now)

## System Requirements
- **Java** 17+
- **MySQL** 8.0+ (or compatible database)
- **Maven** 3.6+


## Contributing

We welcome contributions! If you'd like to contribute to **Toddler Time**, please contact the Author.


## Known Issues

- Timer visuals need optimization for a smoother visual transition.
- The `numberOfChildren` section needs to be optimized to accurately reflect the data visible to users as in the number of children a user has.
- Forgot Password page needs to be updated to send a reset link to user's registered email.
- Task status needs optimization to update to 'IN PROGRESS' as default once task is created.


## Author
**Rachel Moment**

- [GitHub Profile](https://github.com/kianatahleigh)  
- [LinkedIn Profile](https://www.linkedin.com/in/rachel-moment/)

## Contact
For questions or suggestions, feel free to reach out via email at: [momentrachel@gmail.com](mailto:momentrachel@gmail.com).

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

