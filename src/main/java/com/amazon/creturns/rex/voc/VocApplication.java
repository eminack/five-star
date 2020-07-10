package com.amazon.creturns.rex.voc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.net.URI;

@SpringBootApplication
public class VocApplication {

    public static void main(final String[] args) {
        SpringApplication.run(VocApplication.class, args);
        openHomePage();
    }

    /**
     * this method runs on start of the application
     */
    private static void openHomePage() {
        try {
            URI homepage = new URI("http://localhost:8080/");
            System.setProperty("java.awt.headless", "false");
            Desktop.getDesktop().browse(homepage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
