# Me Tube
A simple Web application with Servlets, JSP and Hibernate, deployed on TomEE.
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
* jstl 1.2
### Other tools:
* [ModelMapper](http://modelmapper.org/)
* [Lombok](https://projectlombok.org/)
### Environment configuration
System and IDE should be configured to use:
* [Java **11.0.2**](https://docs.oracle.com/cd/E19509-01/820-3208/inst_cli_jdk_javahome_t/) - [IntelliJ](https://stackoverflow.com/questions/18987228/how-do-i-change-the-intellij-idea-default-jdk)
* [Maven **3.6.0**](http://maven.apache.org/install.html) - [IntelliJ](https://www.jetbrains.com/help/idea/maven-support.html#create_new_maven_project)
* [mysql-connector-java **8.0.15**](https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-installing-classpath.html) - [IntelliJ](https://www.jetbrains.com/help/idea/connecting-to-a-database.html)

This file should be present in **TomEE\lib** folder 
* [hibernate-core-5.4.1.Final.jar](http://central.maven.org/maven2/org/hibernate/hibernate-core/5.4.1.Final/hibernate-core-5.4.1.Final.jar)

Configure IDE to recognize [Lombok](https://projectlombok.org/) - [instructions](https://projectlombok.org/setup/overview)
___
#### Project configuration
* [pom.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/06.%20Java%20EE%20-%20Java%20Server%20Pages/Exercise/Me%20Tube/pom.xml) - project setup - dependencies, compile, packaging
* [beans.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/06.%20Java%20EE%20-%20Java%20Server%20Pages/Exercise/Me%20Tube/src/main/webapp/WEB-INF/beans.xml) - default setup with **bean-discovery-mode="all"**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns="http://xmlns.jcp.org/xml/ns/javaee"
        xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
            http://xmlns.jcp.org/xml/ns/javaee/beans_1_1.xsd"
        bean-discovery-mode="all">
</beans>
```
* [web.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/06.%20Java%20EE%20-%20Java%20Server%20Pages/Exercise/Me%20Tube/src/main/webapp/WEB-INF/web.xml) - handling of HTTP resp. codes 400, 404, 500 and exceptions
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns="http://xmlns.jcp.org/xml/ns/javaee"
        xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
            http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
        version="4.0">

    <error-page>
        <error-code>code number</error-code>
        <location>html or JSP</location>
    </error-page>

    <error-page>
        <exception-type>ex. - java.lang.Throwable</exception-type>
        <location>html or JSP</location>
    </error-page>
</web-app>
``` 
* [resources.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/06.%20Java%20EE%20-%20Java%20Server%20Pages/Exercise/Me%20Tube/src/main/webapp/WEB-INF/resources.xml) - data sources config - type, driver, url, credentials
```html
<?xml version="1.0" encoding="UTF-8"?>
<resources>
    <Resource id="MyResource" type="DataSource">
        JdbcDriver com.mysql.cj.jdbc.Driver
        JdbcUrl jdbc:mysql://localhost:3306/my_db?useSSL=false&amp;createDatabaseIfNotExist=true&amp;serverTimezone=UTC
        UserName xxxxxx
        Password ****
        JtaManaged true
        LogSql true
    </Resource>
</resources>
```
* [persistence.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/06.%20Java%20EE%20-%20Java%20Server%20Pages/Exercise/Me%20Tube/src/main/webapp/META-INF/persistence.xml) - persistence unit setup
```html
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xmlns="http://xmlns.jcp.org/xml/ns/persistence"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd"
             version="2.2">
    <persistence-unit name="myUnitName" transaction-type="JTA">
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
        <jta-data-source>java:openejb/Resource/MyResource</jta-data-source>
        <!--<non-jta-data-source>java:openejb/Resources/MyResourceUnmanaged</non-jta-data-source>-->
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
* Using [JTA](https://docs.oracle.com/javaee/6/tutorial/doc/bnciy.html) enabled EntityManager - requires proper setup as described above

[Example](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/06.%20Java%20EE%20-%20Java%20Server%20Pages/Exercise/Me%20Tube/src/main/java/metube/util/BeansProducer.java)
```java
@Stateless
public class ProductRepository {

    @PersistenceContext(unitName = "myUnitName")
    private EntityManager entityManager;

    public void save(Product entity) {
        entityManager.persist(entity);
    }
}
```
* Creating beans of external classes by **@Produces** annotation

[Example](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/06.%20Java%20EE%20-%20Java%20Server%20Pages/Exercise/Me%20Tube/src/main/java/metube/util/BeansProducer.java)
```java
public class BeansProducer {

    @Produces
    ModelMapper createModelMapper() {
        return new ModelMapper();
    }
}

public class Consumer {

    private final ModelMapper modelMapper;

    @Inject
    public ProductServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
```
* Lombok - deal with code noise in POJOs

[Example](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/06.%20Java%20EE%20-%20Java%20Server%20Pages/Exercise/Me%20Tube/src/main/java/metube/domain/models/view/TubeViewModel.java)
```java
@Getter
@Setter
@NoArgsConstructor
public class Model {

    private String id;
    private String name;
    private String description;
    private Type type;
}
```
* Use NamedQueries in repositories for retrieving data instead of literals (early error detection)

[Example](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/06.%20Java%20EE%20-%20Java%20Server%20Pages/Exercise/Me%20Tube/src/main/java/metube/domain/entities/Tube.java)  
```java
@NamedQueries({
        @NamedQuery(name = "Tube.findById", query = "SELECT t FROM Tube t WHERE t.id = :id"),
        @NamedQuery(name = "Tube.findByName", query = "SELECT t FROM Tube t WHERE t.name = :name"),
        @NamedQuery(name = "Tube.findAllOrderByName", query = "SELECT t FROM Tube t ORDER BY t.name")
})
public class Tube extends BaseEntity {
    // ...
}

public class TubeRepositoryImpl implements TubeRepository {
    // ...
    @Override
    public List<Tube> findAll() {
        return entityManager
                .createNamedQuery("Tube.findAllOrderByName", Tube.class)
                .getResultList();
    }
}
```
* Access static resources in JSP files

[Example](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/06.%20Java%20EE%20-%20Java%20Server%20Pages/Exercise/Me%20Tube/src/main/webapp/WEB-INF/jsps/templates/head.jsp)
```xml
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>MeTube</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap-4.2.1.min.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />">
<link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/ico/favicon.ico" />">
<script src="<c:url value="/resources/js/jquery-3.3.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.bundle-4.2.1.min.js" />"></script>
```
* Bind model from request parameters in Filter (using Reflection)

[Example](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/06.%20Java%20EE%20-%20Java%20Server%20Pages/Exercise/Me%20Tube/src/main/java/metube/web/filters/TubeCreateFilter.java)
```java
    private static <T extends Bindable> T getBindingModelFromParams(ServletRequest request, Class<T> clazz) {
        try {
            T model = clazz.getConstructor().newInstance();
            for (Field field : clazz.getDeclaredFields()) {
                String value = request.getParameter(field.getName());
                if (value != null) {
                    String parameterStr = value.trim();
                    Object parameterValue = parseValue(field.getType(), parameterStr);
                    field.setAccessible(true);
                    field.set(model, parameterValue);
                }
            }
            return model;
        } catch (IllegalAccessException | InstantiationException |
                NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
    }
```
* Service methods used for retrieving data accept desired desired return data type (ViewModel) - simplify Servlets and allows for use with various view models

[Example](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/06.%20Java%20EE%20-%20Java%20Server%20Pages/Exercise/Me%20Tube/src/main/java/metube/services/TubeServiceImpl.java)
```java
public interface TubeService {

    <T extends Bindable<Tube>> void saveTube(T model);

    <T extends Viewable<Tube>> T findByName(String name, Class<T> clazz);

    <T extends Viewable<Tube>> List<T> findAll(Class<T> clazz);
}

    
public class TubeDetailsServlet extends HttpServlet {
    //...
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getTubeName(req.getQueryString())
                .map(name -> tubeService.findByName(name, TubeViewModel.class))
                .ifPresent(tube -> req.setAttribute(WebConstants.ATTRIBUTE_MODEL, tube));

        req.getRequestDispatcher(WebConstants.JSP_TUBE_DETAILS).forward(req, resp);
    }
}
```
