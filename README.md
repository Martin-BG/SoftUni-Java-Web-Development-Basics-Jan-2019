# SoftUni-Java-Web-Development-Basics-Jan-2019
Java Web Development Basics course at SoftUni - January 2019

https://softuni.bg/trainings/2252/java-web-development-basics-january-2019

## Projects
1. [HTML and CSS](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/tree/master/01.%20Web%20Fundamentals%20Introduction%20-%20HTML%20and%20CSS)

* [Lab](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/tree/master/01.%20Web%20Fundamentals%20Introduction%20-%20HTML%20and%20CSS/Lab) - simple tasks

* [SoftUni Beers](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/tree/master/01.%20Web%20Fundamentals%20Introduction%20-%20HTML%20and%20CSS/Exercise) - simple multi-page app

2. [HTTP Protocol](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/tree/master/02.%20HTTP%20Protocol) and [State Management](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/tree/master/03.%20State%20Management) - simple parsing and constructing of HTTP requests and responses, implemented in a funny way :)

* [HTTP parser](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/tree/master/02.%20HTTP%20Protocol/Exercises/02.%20Improved%20HTTP%20Parser/src/improvedhttpparser)

* [Cookies parser](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/tree/master/03.%20State%20Management/Exercises/02.%20Improved%20HTTP%20Cookies%20Parser/src/improvedhttpcookiesparser)

3. Java EE - finally something more advanced :)

* [Fluffy Duffy Munchkin Cats](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/tree/master/04.%20Introduction%20to%20Java%20EE/Exercise/Fluffy%20Duffy%20Munchkin%20Cats) - servlets, servlet context as storage, custom [HTML](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/04.%20Introduction%20to%20Java%20EE/Exercise/Fluffy%20Duffy%20Munchkin%20Cats/src/main/java/fdmc/utils/htmlbuilder/HtmlBuilderImpl.java) [templating](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/04.%20Introduction%20to%20Java%20EE/Exercise/Fluffy%20Duffy%20Munchkin%20Cats/src/main/java/fdmc/utils/templatebuilder/TemplateBuilder.java), HTML & CSS only, use of static resources

* [Chushka](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/tree/master/05.%20Java%20EE%20-%20Servlets%20API%204.0/Exercise/Chushka) - a simple Web application with Servlets and Hibernate, deployed on TomEE. The most important part was the configuration (described in details at the link). Highlights:
  * JTA enabled EntityManager
  * Enum with AttributeConverter
  * Lombok - deal with code noise in POJOs
  * Creating beans of external classes by @Produces annotation
  * Access to resource files by relative path
  * Custom HTML templating

* [Me Tube](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/tree/master/06.%20Java%20EE%20-%20Java%20Server%20Pages/Exercise/Me%20Tube) - A simple Web application with Servlets, JSP and Hibernate, deployed on TomEE. Details provided at the link. Highlights:
  * JTA enabled EntityManager
  * Use of NamedQueries in repositories for retrieving data
  * Lombok - deal with code noise in POJOs
  * Creating beans of external classes by @Produces annotation
  * Access static resources in JSP files
  * Basic JSP templating
  * Bind model from request parameters in Filter (using Reflection)
  * Service methods used for retrieving data accept desired return data type (ViewModel)

* **Workshop** [Me Tube Extended](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/tree/master/07.%20Workshop%20-%20Java%20EE%20-%20Servlets%20JSP%20JPA/Exercise/Me%20Tube%20Extended) - Refactoring, new functionality:
  * CRUD repository, extensive use of Generics for type safety, Optional<>, logging
  * Exported common service logic to base class, Generics
  * Use of [Jargon2](https://github.com/kosprov/jargon2-api) for password hashing
  
* [Employee Register](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/tree/master/08.%20Java%20EE%20-%20JavaServer%20Faces/Exercise/Employee%20Register) - Simple single page web application with *JS*F and Hibernate, deployed on TomEE.
  * Same setup as in [Me Tube Extended](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/tree/master/07.%20Workshop%20-%20Java%20EE%20-%20Servlets%20JSP%20JPA/Exercise/Me%20Tube%20Extended)
  * [PrimeFaces](https://www.primefaces.org/)

* [Fluffy Duffy Munchkin Cats v2](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/tree/master/09.%20Java%20EE%20-%20JavaServer%20Faces%20-%20Libraries%20Tags%20and%20Templates/Exercise/Fluffy%20Duffy%20Munchkin%20Cats%20v2) - Simple 3-page web application with JSF and Hibernate, deployed on TomEE.
  * Same setup as in [Employee Register](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/tree/master/08.%20Java%20EE%20-%20JavaServer%20Faces/Exercise/Employee%20Register)
  * [datatables](https://datatables.net/) - advanced interaction controls to HTML tables - JavaScript
  * Restrict access to server resources and data - [web.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/09.%20Java%20EE%20-%20JavaServer%20Faces%20-%20Libraries%20Tags%20and%20Templates/Exercise/Fluffy%20Duffy%20Munchkin%20Cats%20v2/src/main/webapp/WEB-INF/web.xml)
  * Activate JSF 2.3 by annotation - [ApplicationConfig](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/09.%20Java%20EE%20-%20JavaServer%20Faces%20-%20Libraries%20Tags%20and%20Templates/Exercise/Fluffy%20Duffy%20Munchkin%20Cats%20v2/src/main/java/fdmcjsf/config/ApplicationConfig.java)
  * Routing with [rewrite](https://www.ocpsoft.org/rewrite/) using [HttpConfigurationProvider](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/09.%20Java%20EE%20-%20JavaServer%20Faces%20-%20Libraries%20Tags%20and%20Templates/Exercise/Fluffy%20Duffy%20Munchkin%20Cats%20v2/src/main/java/fdmcjsf/config/RewriteServletConfig.java) class
  * [Date converter](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/09.%20Java%20EE%20-%20JavaServer%20Faces%20-%20Libraries%20Tags%20and%20Templates/Exercise/Fluffy%20Duffy%20Munchkin%20Cats%20v2/src/main/java/fdmcjsf/web/converters/DateConverter.java) for parsing input strings
  * Simple JSF [templating](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/09.%20Java%20EE%20-%20JavaServer%20Faces%20-%20Libraries%20Tags%20and%20Templates/Exercise/Fluffy%20Duffy%20Munchkin%20Cats%20v2/src/main/webapp/view/templates/base-layout.xhtml)

4. Exam preparations:

* [Sboj.gb](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/tree/master/11.%20Exam%20Preparations/Sboj.bg) - [Simple](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/11.%20Exam%20Preparations/11.%20Exam%20Preparation%20I%20-%20Sboj.gb.pdf) multi-page 2-entities web application with JSF and Hibernate, deployed on TomEE.
  * Same setup as in [Fluffy Duffy Munchkin Cats v2](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/tree/master/09.%20Java%20EE%20-%20JavaServer%20Faces%20-%20Libraries%20Tags%20and%20Templates/Exercise/Fluffy%20Duffy%20Munchkin%20Cats%20v2)
  * Static resources caching by [filter](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/11.%20Exam%20Preparations/Sboj.bg/src/main/java/sbojbg/web/filters/CacheFilter.java)
  * Static resources compression using [OmniFaces](http://showcase.omnifaces.org/), setup in [web.xml](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/11.%20Exam%20Preparations/Sboj.bg/src/main/webapp/WEB-INF/web.xml)

5. Final exam (24/02/2019):

* [EXODIA](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/tree/master/12.%20Exam/Final%20Exam) - [Simple](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/blob/master/12.%20Exam/EXODIA.pdf) multi-page 2-entities web application with JSF and Hibernate, deployed on TomEE.
  * Same setup as in [Sboj.gb](https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019/tree/master/11.%20Exam%20Preparations/Sboj.bg)
