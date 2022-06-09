package com.company.dsSocial;


public class SocialGraphException extends Exception{
    /**
     * All these class inherited the parent exception class
     * to throw an exception according on what type of exception
     * */
    public class PersonAlreadyExists extends Exception{

        public PersonAlreadyExists(String message){
            super(message);
        }
    }
    public class PersonDoesNotExist extends Exception{
        public PersonDoesNotExist(String message){
            super(message);
        }
    }

    public class EdgeDoesNotExist extends Exception{
        public EdgeDoesNotExist(String message){
            super(message);
        }
    }
}
