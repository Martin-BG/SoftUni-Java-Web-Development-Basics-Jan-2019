# Sboj.gb
[Simple](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/11.%20Exam%20Preparations/11.%20Exam%20Preparation%20I%20-%20Sboj.gb.pdf) multi-page 2-entities web application with JSF and Hibernate, deployed on TomEE.

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

System and IDE should be configured to use:
* [Java **11.0.2**](https://docs.oracle.com/cd/E19509-01/820-3208/inst_cli_jdk_javahome_t/) - [IntelliJ](https://stackoverflow.com/questions/18987228/how-do-i-change-the-intellij-idea-default-jdk)
* [Maven **3.6.0**](http://maven.apache.org/install.html) - [IntelliJ](https://www.jetbrains.com/help/idea/maven-support.html#create_new_maven_project)
* [mysql-connector-java **8.0.15**](https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-installing-classpath.html) - [IntelliJ](https://www.jetbrains.com/help/idea/connecting-to-a-database.html)

This file should be present in **TomEE\lib** folder 
* [hibernate-core-5.4.1.Final.jar](http://central.maven.org/maven2/org/hibernate/hibernate-core/5.4.1.Final/hibernate-core-5.4.1.Final.jar)

Configure IDE to recognize [Lombok](https://projectlombok.org/) - [instructions](https://projectlombok.org/setup/overview)
___
#### Project configuration
* [pom.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/11.%20Exam%20Preparations/Sboj.bg/pom.xml) - project setup - dependencies, compile, packaging
* [beans.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/11.%20Exam%20Preparations/Sboj.bg/src/main/webapp/WEB-INF/beans.xml) - default setup with **bean-discovery-mode="all"**
* [web.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/11.%20Exam%20Preparations/Sboj.bg/src/main/webapp/WEB-INF/web.xml) - web configuration: resources, security, JSF, resources compression, encoding
* [resources.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/11.%20Exam%20Preparations/Sboj.bg/src/main/webapp/WEB-INF/resources.xml) - data sources config - type, driver, url, credentials
```html
<?xml version="1.0" encoding="UTF-8"?>
<resources>
    <Resource id="MyResource" type="DataSource">
        JdbcDriver com.mysql.cj.jdbc.Driver
        JdbcUrl jdbc:mysql://localhost:3306/sboj_bg_db?useSSL=false&amp;allowPublicKeyRetrieval=true&amp;createDatabaseIfNotExist=true&amp;useUnicode=true&amp;characterEncoding=utf-8&amp;serverTimezone=UTC
        UserName xxxxxx
        Password ****
        JtaManaged true
        LogSql true
    </Resource>
</resources>
```
* [persistence.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/11.%20Exam%20Preparations/Sboj.bg/src/main/webapp/META-INF/persistence.xml) - JTA persistence unit setup
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
Same as in [Fluffy Duffy Munchkin Cats v2](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/tree/master/09.%20Java%20EE%20-%20JavaServer%20Faces%20-%20Libraries%20Tags%20and%20Templates/Exercise/Fluffy%20Duffy%20Munchkin%20Cats%20v2)
* Implemented CRUD repository, extensive use of Generics for type safety, Optional<> as return type plus logging
* Exported common service logic to base class, Generics
* Using [JTA](https://docs.oracle.com/javaee/6/tutorial/doc/bnciy.html) enabled EntityManager - requires proper setup as described above
* Restrict access to server resources and data - [web.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/11.%20Exam%20Preparations/Sboj.bg/src/main/webapp/WEB-INF/web.xml)
* Activate JSF 2.3 by annotation - [ApplicationConfig](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/11.%20Exam%20Preparations/Sboj.bg/src/main/java/sbojbg/config/ApplicationConfig.java)
* Routing with [rewrite](https://www.ocpsoft.org/rewrite/) using [HttpConfigurationProvider](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/11.%20Exam%20Preparations/Sboj.bg/src/main/java/sbojbg/config/RewriteServletConfig.java) class
* Simple JSF [templating](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/11.%20Exam%20Preparations/Sboj.bg/src/main/webapp/view/templates/base-layout.xhtml)
* **Static resources caching** by [filter](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/11.%20Exam%20Preparations/Sboj.bg/src/main/java/sbojbg/web/filters/CacheFilter.java):
```java
@WebFilter("/faces/javax.faces.resource/*")
public class CacheFilter implements Filter {

    private static final long MAX_AGE = 60 * 60 * 24 * 30L; // 30 days in seconds
    private static final String MAX_AGE_HEADER = "max-age=" + MAX_AGE + ", public";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Cache-Control", MAX_AGE_HEADER);
        chain.doFilter(request, response);
    }
}
```
* **Static resources compression** using [OmniFaces](http://showcase.omnifaces.org/), setup in [web.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/11.%20Exam%20Preparations/Sboj.bg/src/main/webapp/WEB-INF/web.xml):
```xml
    <filter>
        <filter-name>gzipResponseFilter</filter-name>
        <filter-class>org.omnifaces.filter.GzipResponseFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>gzipResponseFilter</filter-name>
        <url-pattern>/faces/javax.faces.resource/*</url-pattern>
        <dispatcher>REQUEST</dispatcher>
        <dispatcher>ERROR</dispatcher>
    </filter-mapping>
```
