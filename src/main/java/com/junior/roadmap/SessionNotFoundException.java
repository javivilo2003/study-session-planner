package com.junior.roadmap;

public class SessionNotFoundException extends Exception{
    
    public SessionNotFoundException(String message){
        super(message);
    }

    public SessionNotFoundException(){}
}
