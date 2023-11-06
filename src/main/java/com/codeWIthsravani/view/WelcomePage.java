package com.codeWIthsravani.view;

import com.codeWIthsravani.utils.StringUtil;

import static com.codeWIthsravani.utils.Utils.println;

public class WelcomePage {
    public void printAuthMenu() {
        println(StringUtil.AUTH_MENU);
    }
    public void welcome() {
        System.out.println(StringUtil.WELCOME_MESSAGE);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void check(){
        System.out.println();
    }
}
