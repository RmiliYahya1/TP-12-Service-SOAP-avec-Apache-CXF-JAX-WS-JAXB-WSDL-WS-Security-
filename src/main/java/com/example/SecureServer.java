package com.example;

import com.example.impl.HelloServiceImpl;
import com.example.security.UTPasswordCallback;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;

import java.util.HashMap;
import java.util.Map;

public class SecureServer {
    public static void main(String[] args) {
        Map<String, Object> inProps = new HashMap<>();
        inProps.put("action", "UsernameToken");
        inProps.put("passwordType", "PasswordText");
        inProps.put("passwordCallbackRef", new UTPasswordCallback());

        WSS4JInInterceptor wssIn = new WSS4JInInterceptor(inProps);

        String address = "http://localhost:8080/services/hello-secure";
        JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
        factory.setServiceClass(HelloServiceImpl.class);
        factory.setAddress(address);

        factory.getInInterceptors().add(wssIn);

        factory.create();

        System.out.println("Secure Server ready...");
        System.out.println("WSDL available at: " + address + "?wsdl");
    }
}
