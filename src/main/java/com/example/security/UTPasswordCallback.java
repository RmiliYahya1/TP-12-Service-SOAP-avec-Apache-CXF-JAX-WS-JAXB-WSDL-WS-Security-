package com.example.security;


import org.apache.wss4j.common.ext.WSPasswordCallback;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UTPasswordCallback implements CallbackHandler {

    private final Map<String, String> users = new HashMap<>();

    public UTPasswordCallback() {
        users.put("student", "secret123");
        users.put("admin", "adminPass");
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (Callback cb : callbacks) {
            if (cb instanceof WSPasswordCallback) {
                WSPasswordCallback pc = (WSPasswordCallback) cb;
                if (users.containsKey(pc.getIdentifier())) {
                    pc.setPassword(users.get(pc.getIdentifier()));
                }
            }
        }
    }
}