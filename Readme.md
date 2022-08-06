# Book catalogue
Project that allows you to manage your database with books.

## Building
Just click on "package.bat" (works only with installed maven on pc, and settled up environment variable`s). That will generate "*.war" file with your project in "target" folder.

## Used libs info

1) spring-webmvc 
2) postgresql (Driver)
3) javax.servlet-api (ServletContext, registration)
4) spring-security-web (for base authentication)
5) spring-security-config (configuration via annotations)
6) lombok (Insert regular code constructions like loggers, to string etc)
7) spring-aspects (For creating logging aspect)
8) jstl (taglib for jsp)

## Basic theses about the project.
1) Created on MVC design pattern(provided by spring)
2) Have layered architecture.
3) Using logging with Slf4j+lombock(And for method invokes loggging used AOP).
4) All spring configurations realised with annotations.
5) Realised two types of connection resolvers(From weblogic server, and default DriverManager).
6) Created two roles for managing database(ADMIN,USER).
7) All views represented as "*.jsp" files.
8) For designing pages used bootstrap, and some custom css.
9) Used .js files for sending forms, creating pagination, and other stuff.
10) Created custom error pages.
11) Database startup initialization with "tableCreator.sql".
12) Mock testing with "mockito".

## Layers
1) DAO layer for connecting to database and sql query-execution.
2) Service layer(Business logic), main logic of project.
3) Presentation layer
   1) Model - have info from service.
   2) View some template or static file.
   3) Controller layer that controls Data Transfer between business logic, Model and View.
