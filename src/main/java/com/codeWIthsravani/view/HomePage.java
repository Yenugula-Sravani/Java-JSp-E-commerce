package com.codeWIthsravani.view;

import com.codeWIthsravani.utils.StringUtil;

import static com.codeWIthsravani.utils.Utils.println;

public class HomePage {
    public void printMenu() {
        println(StringUtil.USERMENU);
    }
    public void printWelcome(){
        println(StringUtil.WELCOME_TO_LOGINPAGE);
    }
    public void printAdminMenu() {
        println(StringUtil.ADMINMENU);
    }
}
