# Chushka
A simple Web application with Servlets and Hibernate, deployed on TomEE.
___
## Setup
Tested on Ubuntu 18.0.4 and Windows 7 x64
### Versions
* Java **11.0.2**
* Maven **3.6.0**
* MySQL with mysql-connector-java **8.0.15**
* hibernate-core **5.4.1.Final**
* Apache TomEE 8.0.0.M2 **webprofile**
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
* [pom.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/05.%20Java%20EE%20-%20Servlets%20API%204.0/Exercise/Chushka/pom.xml) - project setup - dependencies, compile, packaging
* [beans.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/05.%20Java%20EE%20-%20Servlets%20API%204.0/Exercise/Chushka/src/main/webapp/WEB-INF/beans.xml) - default setup with **bean-discovery-mode="all"**
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
* [web.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/05.%20Java%20EE%20-%20Servlets%20API%204.0/Exercise/Chushka/src/main/webapp/WEB-INF/web.xml) - handling of HTTP resp. codes 400, 404, 500 and exceptions
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
* [resources.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/05.%20Java%20EE%20-%20Servlets%20API%204.0/Exercise/Chushka/src/main/webapp/WEB-INF/resources.xml) - data sources config - type, driver, url, credentials
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
    ...
</resources>
```
* [persistence.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/05.%20Java%20EE%20-%20Servlets%20API%204.0/Exercise/Chushka/src/main/resources/META-INF/persistence.xml) - persistence unit setup
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

[Example](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/05.%20Java%20EE%20-%20Servlets%20API%204.0/Exercise/Chushka/src/main/java/chushka/repository/ProductRepositoryImpl.java)
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

[Example](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/05.%20Java%20EE%20-%20Servlets%20API%204.0/Exercise/Chushka/src/main/java/chushka/utils/BeansProducer.java)
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
* Simple HTML templating with Builder pattern

[Example](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/05.%20Java%20EE%20-%20Servlets%20API%204.0/Exercise/Chushka/src/main/java/chushka/utils/templatebuilder/TemplateBuilder.java)
```java
public final class TemplateBuilder {

    private static final String TEMPLATE_FORMAT = "{{%s}}";

    private final StringBuilder stringBuilder;

    private TemplateBuilder(String initialContent) {
        stringBuilder = new StringBuilder(initialContent);
    }

    public static TemplateBuilder from(String initialContent) {
        return new TemplateBuilder(initialContent);
    }

    public TemplateBuilder put(String placeholder, String value) {
        //...
        return this;
    }

    public TemplateBuilder put(Map<String, String> pairs) {
        //...
        return this;
    }

    public TemplateBuilder append(String content) {
        //...
        return this;
    }

    public String build() {
        return stringBuilder.toString();
    }
}

public class Consumer {

    private static final String HTML_NAME_PLACEHOLDER = "name";

    public static String getProductsHtmlList(String html, List<ProductServiceModel> products) {
        StringBuilder itemsList = new StringBuilder();
        products.forEach(product -> itemsList.append(
                TemplateBuilder
                        .from(html)
                        .put(HTML_NAME_PLACEHOLDER, product.getName())
                        .build()));
        return itemsList.toString();
    }
}

```
* Having fun with method overloads :)
```java
public interface HtmlBuilder {

    Optional<String> buildFrom(String baseTemplateUri,
                               Map<String, String> templatesUris,
                               Map<String, String> params);

    Optional<String> buildFrom(Map<String, String> templatesUris,
                               Map<String, String> params);

    Optional<String> buildFrom(String baseTemplateUri,
                               Map<String, String> params);

    Optional<String> buildFrom(Map<String, String> params);

    Optional<String> buildFrom(String baseTemplateUri);
}
```
* Lombok - deal with code noise in POJOs - [example](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/05.%20Java%20EE%20-%20Servlets%20API%204.0/Exercise/Chushka/src/main/java/chushka/domain/models/service/ProductServiceModel.java)
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
* Enum with AttributeConverter (change default string representation in DB).
In following [example](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/tree/master/05.%20Java%20EE%20-%20Servlets%20API%204.0/Exercise/Chushka/src/main/java/chushka/domain/entities) Type FOOD will be stored as "Food" in DB:
```java
public enum Type {
    FOOD("Food"),
    DOMESTIC("Domestic"),
    HEALTH("Health"),
    COSMETIC("Cosmetic"),
    OTHER("Other")
    //...
}

@Converter
public class TypeConverter implements AttributeConverter<Type, String> {

    @Override
    public String convertToDatabaseColumn(Type type) {
        return type == null ? null : type.getName();
    }

    @Override
    public Type convertToEntityAttribute(String name) {
        return Type.fromName(name);
    }
}

@Entity(name = "products")
public class Product {
    //...
    @Convert(converter = TypeConverter.class)
    private Type type;
}
```
* Read resource files by relative path - [example](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/05.%20Java%20EE%20-%20Servlets%20API%204.0/Exercise/Chushka/src/main/java/chushka/web/servlets/ProductAllServlet.java)
```java
    // uri = "/html/templates/product/list-item.html"
    public Optional<String> read(String uri) {
        InputStream inputStream;
        String content = null;

        if (uri != null && (inputStream = getClass().getResourceAsStream(uri)) != null) {
            try (final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, HTML_CHARSET))) {
                content = reader.lines().collect(Collectors.joining(HTML_LINE_SEPARATOR));
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error reading file " + uri, e);
            }
        } else {
            logger.log(Level.SEVERE, "File not found or not accessible: {0}", uri);
        }

        return Optional.ofNullable(content);
    }

```
