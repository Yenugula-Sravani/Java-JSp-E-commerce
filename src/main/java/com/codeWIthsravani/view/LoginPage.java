package com.codeWIthsravani.view;

import static com.codeWIthsravani.utils.Utils.println;

public class LoginPage {
    public void invalidCredentials(){
        try {
            println("#================#");
            println("Invalid Credentials!");
            println("#================#");
            Thread.sleep(1000);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}
