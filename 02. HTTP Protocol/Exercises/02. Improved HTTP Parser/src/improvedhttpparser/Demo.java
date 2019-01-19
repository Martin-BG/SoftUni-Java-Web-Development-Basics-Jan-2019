package improvedhttpparser;

import improvedhttpparser.http.*;

public class Demo {

    private static final String HEADERS = "" +
            "Date: 17/01/2019\r\n" +
            "Host: localhost:8000\r\n" +
            "Content-Type: application/xml\r\n" +
            "Authorization: Basic UGVzaG8=\r\n";

    public static void main(String[] args) {
        HttpRequest postRequest = new HttpRequestImpl("" +
                "POST /url HTTP/1.1\r\n" +
                HEADERS +
                "\r\n" +
                "name=Yum&quantity=50&price=10");

        HttpRequest getRequest = new HttpRequestImpl("" +
                "GET /url HTTP/1.1\r\n" +
                HEADERS);

        System.out.println("getRequest isResource: " + getRequest.isResource());

        HttpRequest getResourceRequest = new HttpRequestImpl("" +
                "GET /url/index.html HTTP/1.1\r\n" +
                HEADERS);

        System.out.println("getResourceRequest isResource: " + getResourceRequest.isResource());

        HttpResponse httpResponse = new HttpResponseImpl();
        httpResponse.setStatusCode(StatusCode.OK);
        postRequest.getHeaders().forEach(httpResponse::addHeader);
        httpResponse.setContent("Hello World!".getBytes(Constants.CHARSET));

        System.out.println(new String(httpResponse.getBytes(), Constants.CHARSET));
    }
}
