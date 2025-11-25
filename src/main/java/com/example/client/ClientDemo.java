package com.example.client;

import com.example.api.HelloService;
import jakarta.xml.ws.Service;
import javax.xml.namespace.QName;
import java.net.URL;

public class ClientDemo {
    public static void main(String[] args) throws Exception {
        // 1. Adresse du WSDL (le contrat)
        URL wsdlUrl = new URL("http://localhost:8080/services/hello?wsdl");


        QName serviceName = new QName("http://api.soap.projet.ma/", "HelloService");

        // 3. Création du factory de service
        Service service = Service.create(wsdlUrl, serviceName);

        // 4. Récupération du proxy (l'objet Java qui masque l'appel réseau)
        HelloService port = service.getPort(HelloService.class);

        // 5. Appels distants
        System.out.println("Appel sayHello...");
        System.out.println(port.sayHello("ClientJava"));

        System.out.println("\nAppel findPersonById...");
        System.out.println("Nom reçu : " + port.findPersonById("P-777").getName());
    }
}
