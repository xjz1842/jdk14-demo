package com.jdk9.demo.http;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Http2Test {

    public static void main(String[] args) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            // GET
            HttpResponse<String> response = client.send(
                    HttpRequest
                            .newBuilder(new URI("http://www.foo.com/"))
                            .headers("Foo", "foovalue", "Bar", "barvalue")
                            .GET()
                            .build(),
                    HttpResponse.BodyHandler.asString()
            );
            int statusCode = response.statusCode();
            String body = response.body();
            System.out.println(statusCode+"+body:"+body);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
