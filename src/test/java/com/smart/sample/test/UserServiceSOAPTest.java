package com.smart.sample.test;

import com.smart.plugin.ws.SOAPHelper;
import com.smart.sample.entity.User;
import com.smart.sample.ws.soap.UserService;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class UserServiceSOAPTest {

    @Test
    public void loginTest() {
        String wsdl = "http://localhost:8080/smart-sample/ws/UserService";
        UserService userService = SOAPHelper.createClient(wsdl, UserService.class);

        Map<String, Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put("username", "admin");
        fieldMap.put("password", "admin");

        User user = userService.login(fieldMap);
        System.out.println(user);
    }
}