package com.poc.demo.exception.userdefined;

public class EmptyFieldsException extends Exception{
    @Override
    public String toString() {
        return "The Fields annot be empty";
    }
}
