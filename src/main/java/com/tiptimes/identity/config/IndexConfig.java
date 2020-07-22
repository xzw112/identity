package com.tiptimes.identity.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

//@SpringBootConfiguration
public class IndexConfig {
    @EventListener({ApplicationReadyEvent.class})
    public void applicationReadyEvent() {
        String cmd = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe http://192.168.1.71:8081";
        Runtime run = Runtime.getRuntime();
        try{
            run.exec(cmd);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
