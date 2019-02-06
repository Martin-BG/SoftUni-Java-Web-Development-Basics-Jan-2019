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
* modelmapper
* lombok
### Environment configuration
System and IDE should be configured to use:
* Java **11.0.2**
* Maven **3.6.0**
* MySQL with mysql-connector-java **8.0.15**

This file should be present in **TomEE\lib** folder 
* hibernate-core-5.4.1.Final.jar
___
#### Project configuration
* [pom.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/tree/master/05.%20Java%20EE%20-%20Servlets%20API%204.0/Exercise/Chushka) - dependencies and versions
* [beans.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/05.%20Java%20EE%20-%20Servlets%20API%204.0/Exercise/Chushka/src/main/webapp/WEB-INF/beans.xml) - default setup with **bean-discovery-mode="all"**
* [web.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/05.%20Java%20EE%20-%20Servlets%20API%204.0/Exercise/Chushka/src/main/webapp/WEB-INF/web.xml) - handling of HTTP resp. codes 400, 404, 500 and exceptions 
* [resources.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/05.%20Java%20EE%20-%20Servlets%20API%204.0/Exercise/Chushka/src/main/webapp/WEB-INF/resources.xml) - data sources config - type, driver, url, credentials

Example:
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
* [persistence.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/05.%20Java%20EE%20-%20Servlets%20API%204.0/Exercise/Chushka/src/main/resources/META-INF/persistence.xml) - persistence unit setup

Example:
```html
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xmlns="http://xmlns.jcp.org/xml/ns/persistence"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd"
             version="2.2">
    <persistence-unit name="XXXX" transaction-type="JTA">
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
        <jta-data-source>java:openejb/Resource/MyResource</jta-data-source>
        <non-jta-data-source>java:openejb/Resources/MyResourceUnmanaged</non-jta-data-source>
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
* Creating beans for external classes with **@Produces** annotation

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
                        .put("HTML_NAME_PLACEHOLDER", product.getName())
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