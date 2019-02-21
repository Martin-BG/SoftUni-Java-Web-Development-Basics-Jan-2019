# Fluffy Duffy Munchkin Cats v2
Simple 3-page web application with JSF and Hibernate, deployed on TomEE.

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
* [datatables](https://datatables.net/) - advanced interaction controls to HTML tables - JavaScript

System and IDE should be configured to use:
* [Java **11.0.2**](https://docs.oracle.com/cd/E19509-01/820-3208/inst_cli_jdk_javahome_t/) - [IntelliJ](https://stackoverflow.com/questions/18987228/how-do-i-change-the-intellij-idea-default-jdk)
* [Maven **3.6.0**](http://maven.apache.org/install.html) - [IntelliJ](https://www.jetbrains.com/help/idea/maven-support.html#create_new_maven_project)
* [mysql-connector-java **8.0.15**](https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-installing-classpath.html) - [IntelliJ](https://www.jetbrains.com/help/idea/connecting-to-a-database.html)

This file should be present in **TomEE\lib** folder 
* [hibernate-core-5.4.1.Final.jar](http://central.maven.org/maven2/org/hibernate/hibernate-core/5.4.1.Final/hibernate-core-5.4.1.Final.jar)

Configure IDE to recognize [Lombok](https://projectlombok.org/) - [instructions](https://projectlombok.org/setup/overview)
___
#### Project configuration
* [pom.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/09.%20Java%20EE%20-%20JavaServer%20Faces%20-%20Libraries%20Tags%20and%20Templates/Exercise/Fluffy%20Duffy%20Munchkin%20Cats%20v2/pom.xml) - project setup - dependencies, compile, packaging
* [beans.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/09.%20Java%20EE%20-%20JavaServer%20Faces%20-%20Libraries%20Tags%20and%20Templates/Exercise/Fluffy%20Duffy%20Munchkin%20Cats%20v2/src/main/webapp/WEB-INF/beans.xml) - default setup with **bean-discovery-mode="all"**
* [web.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/09.%20Java%20EE%20-%20JavaServer%20Faces%20-%20Libraries%20Tags%20and%20Templates/Exercise/Fluffy%20Duffy%20Munchkin%20Cats%20v2/src/main/webapp/WEB-INF/web.xml) - web configuration: resources, security, JSF
* [resources.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/09.%20Java%20EE%20-%20JavaServer%20Faces%20-%20Libraries%20Tags%20and%20Templates/Exercise/Fluffy%20Duffy%20Munchkin%20Cats%20v2/src/main/webapp/WEB-INF/resources.xml) - data sources config - type, driver, url, credentials
```html
<?xml version="1.0" encoding="UTF-8"?>
<resources>
    <Resource id="MyResource" type="DataSource">
        JdbcDriver com.mysql.cj.jdbc.Driver
        JdbcUrl jdbc:mysql://localhost:3306/metube_extended_db?useSSL=false&amp;createDatabaseIfNotExist=true&amp;useUnicode=true&amp;characterEncoding=utf-8&amp;serverTimezone=UTC
        UserName xxxxxx
        Password ****
        JtaManaged true
        LogSql true
    </Resource>
</resources>
```
* [persistence.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/09.%20Java%20EE%20-%20JavaServer%20Faces%20-%20Libraries%20Tags%20and%20Templates/Exercise/Fluffy%20Duffy%20Munchkin%20Cats%20v2/src/main/webapp/META-INF/persistence.xml) - persistence unit setup
```html
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xmlns="http://xmlns.jcp.org/xml/ns/persistence"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd"
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
Same as in [Employee Register](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/tree/master/08.%20Java%20EE%20-%20JavaServer%20Faces/Exercise/Employee%20Register)
* Implemented CRUD repository, extensive use of Generics for type safety, Optional<> as return type plus logging
* Exported common service logic to base class, Generics
* Using [JTA](https://docs.oracle.com/javaee/6/tutorial/doc/bnciy.html) enabled EntityManager - requires proper setup as described above
* Restrict access to server resources and data - [web.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/09.%20Java%20EE%20-%20JavaServer%20Faces%20-%20Libraries%20Tags%20and%20Templates/Exercise/Fluffy%20Duffy%20Munchkin%20Cats%20v2/src/main/webapp/WEB-INF/web.xml)
```xml
    <context-param>
        <param-name>javax.faces.WEBAPP_RESOURCES_DIRECTORY</param-name>
        <param-value>WEB-INF/resources</param-value>
    </context-param>

    <security-constraint>
        <display-name>No access to /resources folder</display-name>
        <web-resource-collection>
            <web-resource-name>The /resources folder</web-resource-name>
            <url-pattern>/resources/*</url-pattern>
        </web-resource-collection>
    </security-constraint>

    <security-constraint>
        <display-name>No access to Facelets source</display-name>
        <web-resource-collection>
            <web-resource-name>XHTML</web-resource-name>
            <url-pattern>*.xhtml</url-pattern>
        </web-resource-collection>
        <auth-constraint/>
    </security-constraint>
```
* Activate JSF 2.3 by annotation - [ApplicationConfig](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/09.%20Java%20EE%20-%20JavaServer%20Faces%20-%20Libraries%20Tags%20and%20Templates/Exercise/Fluffy%20Duffy%20Munchkin%20Cats%20v2/src/main/java/fdmcjsf/config/ApplicationConfig.java)
```java
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class ApplicationConfig {
    // ...
}
```
* Routing with [rewrite](https://www.ocpsoft.org/rewrite/) using [HttpConfigurationProvider](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/09.%20Java%20EE%20-%20JavaServer%20Faces%20-%20Libraries%20Tags%20and%20Templates/Exercise/Fluffy%20Duffy%20Munchkin%20Cats%20v2/src/main/java/fdmcjsf/config/RewriteServletConfig.java) class
```java
@RewriteConfiguration
public class RewriteServletConfig extends HttpConfigurationProvider {

    @Override
    public int priority() {
        return 10;
    }

    @Override
    public Configuration getConfiguration(final ServletContext context) {
        return ConfigurationBuilder.begin()
                .addRule()
                .when(Direction.isInbound().and(Path.matches("/{path}")))
                .perform(Log.message(Logger.Level.INFO, "Client requested path: {path}"))
                .where("path").matches(".*")
                .addRule(Join.path("/").to("/faces/view/index.xhtml"))
                .addRule(Join.path("/index").to("/faces/view/index.xhtml"))
                .addRule(Join.path("/create").to("/faces/view/create.xhtml"))
                .addRule(Join.path("/all").to("/faces/view/all.xhtml"));
    }
}
```
* [Date converter](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/09.%20Java%20EE%20-%20JavaServer%20Faces%20-%20Libraries%20Tags%20and%20Templates/Exercise/Fluffy%20Duffy%20Munchkin%20Cats%20v2/src/main/java/fdmcjsf/web/converters/DateConverter.java) for parsing input strings
```java
@FacesConverter("dateConverter")
public class DateConverter extends DateTimeConverter {

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String LOCAL_DATE_TYPE = "localDate";

    private static final Logger LOG = Logger.getLogger(DateConverter.class.getName());

    public DateConverter() {
        super.setPattern(DATE_PATTERN);
        super.setType(LOCAL_DATE_TYPE);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            return super.getAsObject(context, component, value);
        } catch (ConverterException e) {
            LOG.log(Level.SEVERE, "Invalid date: " + value, e);
            return null;
        }
    }
}
``` 
```xml
    <h:outputLabel for="addedOn" value="Added On"/><br/>
    <h:inputText pt:type="date" id="addedOn" required="true"
                 styleClass="form-control input-field-width"
                 label="Added On Date" requiredMessage="Added On Date is required"
                 converter="dateConverter" value="#{catCreate.model.addedOn}"/>
    <h:message id="m7" for="addedOn" styleClass="error-message"/>
```
* [datatables](https://datatables.net/) - advanced interaction controls to HTML tables. As simple as this:
```javascript
$('#table').dataTable();
```
* Simple JSF [templating](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/09.%20Java%20EE%20-%20JavaServer%20Faces%20-%20Libraries%20Tags%20and%20Templates/Exercise/Fluffy%20Duffy%20Munchkin%20Cats%20v2/src/main/webapp/view/templates/base-layout.xhtml)
