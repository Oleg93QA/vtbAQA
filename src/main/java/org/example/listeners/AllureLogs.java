package org.example.listeners;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class AllureLogs implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context)throws Exception{
        context.getExecutionException().ifPresent(x ->{
            AllureAttachment.PageScreen();
            AllureAttachment.pageSource();
            AllureAttachment.getLogs();
        });
    }



}
