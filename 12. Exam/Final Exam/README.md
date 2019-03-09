# EXODIA 
[The Final Exam](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/12.%20Exam/EXODIA.pdf) (24/02/2019) - multi-page 2-entities web application with JSF and Hibernate, deployed on TomEE.

___
## Setup
Tested on Ubuntu 18.0.4 and Windows 7 x64
### Versions
* Java **11.0.2**
* Maven **3.6.0**
* Maven Compiler **3.8.0**
* MySQL with mysql-connector-java **8.0.15**
* hibernate-core **5.4.1.Final**
* Apache TomEE 8.0.0.M2 **webprofile**
### Other tools:
* [ModelMapper](http://modelmapper.org/)
* [Lombok](https://projectlombok.org/)
* [PrimeFaces](https://www.primefaces.org/)
* [rewrite](https://www.ocpsoft.org/rewrite/) - Routing ↑↓ and /url/{rewriting} solution for Servlet, Java Web Frameworks, and Java EE
* [OmniFaces](http://showcase.omnifaces.org/) - Powerful JSF 2 utility library
* [Markdown2Pdf](https://mvnrepository.com/artifact/eu.de-swaef.pdf/Markdown2Pdf) - .md to .pdf converter

System and IDE should be configured to use:
* [Java **11.0.2**](https://docs.oracle.com/cd/E19509-01/820-3208/inst_cli_jdk_javahome_t/) - [IntelliJ](https://stackoverflow.com/questions/18987228/how-do-i-change-the-intellij-idea-default-jdk)
* [Maven **3.6.0**](http://maven.apache.org/install.html) - [IntelliJ](https://www.jetbrains.com/help/idea/maven-support.html#create_new_maven_project)
* [mysql-connector-java **8.0.15**](https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-installing-classpath.html) - [IntelliJ](https://www.jetbrains.com/help/idea/connecting-to-a-database.html)

This file should be present in **TomEE\lib** folder 
* [hibernate-core-5.4.1.Final.jar](http://central.maven.org/maven2/org/hibernate/hibernate-core/5.4.1.Final/hibernate-core-5.4.1.Final.jar)

Configure IDE to recognize [Lombok](https://projectlombok.org/) - [instructions](https://projectlombok.org/setup/overview)
___
#### Project configuration
* [pom.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/12.%20Exam/Final%20Exam/pom.xml) - project setup - dependencies, compile, packaging
* [beans.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/12.%20Exam/Final%20Exam/src/main/webapp/WEB-INF/beans.xml) - default setup with **bean-discovery-mode="all"**
* [web.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/12.%20Exam/Final%20Exam/src/main/webapp/WEB-INF/web.xml) - web configuration: resources, security, JSF, resources compression, encoding
* [resources.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/12.%20Exam/Final%20Exam/src/main/webapp/WEB-INF/resources.xml) - data sources config - type, driver, url, credentials
```html
<?xml version="1.0" encoding="UTF-8"?>
<resources>
    <Resource id="MyResource" type="DataSource">
        JdbcDriver com.mysql.cj.jdbc.Driver
        JdbcUrl jdbc:mysql://localhost:3306/exodia_db?useSSL=false&amp;allowPublicKeyRetrieval=true&amp;createDatabaseIfNotExist=true&amp;useUnicode=true&amp;characterEncoding=utf-8&amp;serverTimezone=UTC
        UserName xxxxxx
        Password ****
        JtaManaged true
        LogSql true
    </Resource>
</resources>
```
* [persistence.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/12.%20Exam/Final%20Exam/src/main/webapp/META-INF/persistence.xml) - JTA persistence unit setup
```html
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xmlns="http://xmlns.jcp.org/xml/ns/persistence"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence 
                http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd"
             version="2.2">
    <persistence-unit name="myUnitName" transaction-type="JTA">
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
        <jta-data-source>java:openejb/Resource/MyResource</jta-data-source>
        <properties>
            <property name="tomee.jpa.factory.lazy" value="true"/>
            <property name="openjpa.jdbc.SynchronizeMappings" value="buildSchema(ForeignKeys=true)"/>
            <property name="hibernate.dialect" value="org.hibernate.dialect.MySQL8Dialect"/>
            <property name="hibernate.hbm2ddl.auto" value="update"/>
            <property name="hibernate.show_sql" value="true"/>
            <property name="hibernate.format_sql" value="true"/>
        </properties>
    </persistence-unit>
</persistence>
```
___
## Takeaways
Same as in [Sboj.gb](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/tree/master/11.%20Exam%20Preparations/Sboj.bg)
* Implemented CRUD repository, extensive use of Generics for type safety, Optional<> as return type plus logging
* Exported common service logic to base class, Generics
* Using [JTA](https://docs.oracle.com/javaee/6/tutorial/doc/bnciy.html) enabled EntityManager - requires proper setup as described above
* Restrict access to server resources and data - [web.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/12.%20Exam/Final%20Exam/src/main/webapp/WEB-INF/web.xml)
* Activate JSF 2.3 by annotation - [ApplicationConfig](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/12.%20Exam/Final%20Exam/src/main/java/org/softuni/exam/config/ApplicationConfig.java)
* Routing with [rewrite](https://www.ocpsoft.org/rewrite/) using [HttpConfigurationProvider](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/12.%20Exam/Final%20Exam/src/main/java/org/softuni/exam/config/RewriteServletConfig.java) class
* Simple JSF [templating](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/12.%20Exam/Final%20Exam/src/main/webapp/view/templates/base-layout.xhtml)
* Static resources caching by [filter](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/12.%20Exam/Final%20Exam/src/main/java/org/softuni/exam/web/filters/CacheFilter.java):
* Static resources compression using [OmniFaces](http://showcase.omnifaces.org/), setup in [web.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/12.%20Exam/Final%20Exam/src/main/webapp/WEB-INF/web.xml):
* Convert Markdown data to PDF format with [Markdown2Pdf](https://mvnrepository.com/artifact/eu.de-swaef.pdf/Markdown2Pdf)
* **Build of PDF file and download by client**
