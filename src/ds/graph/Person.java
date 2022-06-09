package com.company.BusinessGraphClasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class Person {
    private float socialHygiene;
    private int  age;
    private String name;
    private ArrayList<Person>contacts = new ArrayList<>();
    private Boolean visited = false;

    //Business Graph attribute requirements
    private Business business;

    public Person(String name, int age, float socialHygiene){
        this.name = name;
        this.age = age;
        this.socialHygiene = socialHygiene;
    }

    public float getSocialHygiene() {
        return socialHygiene;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Person> getContacts() {
        return contacts;
    }

    public float getInfectiveness(){
        return (this.age/(float)100)*this.age - socialHygiene;
    }

    public Boolean isVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    public ArrayList<Person> getContactByInfectiveness(){
        ArrayList<Person> listOfcontacts = new ArrayList<>(this.contacts);
        listOfcontacts.sort(new CustomComparator());
        Collections.reverse(listOfcontacts);
        return listOfcontacts;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public Business getBusiness() {
        return business;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String toString(){
        if(business != null)
            return this.name+", "+this.age+" , Business: "+this.business.getName();
        return this.name+", "+this.age+", Contacts: "+this.contacts.size();
    }

    public class CustomComparator implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return Math.round(o1.getInfectiveness()-o2.getInfectiveness());
        }
    }
}
