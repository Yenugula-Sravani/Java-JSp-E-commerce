package com.codeWIthsravani.view;

import com.codeWIthsravani.utils.StringUtil;

import static com.codeWIthsravani.utils.Utils.println;

public class RegisterPage {
    public void printRegistrationSuccessful(){
        try {
            println("#================#");
            println(StringUtil.REGISTRATION_SUCCESSFUL);
            println("#================#");
            Thread.sleep(1000);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void passwordMisMatch(){
        try {
            println("#================#");
            println(StringUtil.PASSWORD_MISMATCH);
            println("#================#");
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
