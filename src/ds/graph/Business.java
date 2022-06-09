package com.company.BusinessGraphClasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class Business {
    private String name;
    private boolean isVisited = false;
    private ArrayList<Person> edges;

    public Business(String name) {
        this.name = name;
        this.edges = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public boolean getVisited() {
        return this.isVisited;
    }

    public void setVisited(boolean visited) {
        this.isVisited = visited;
    }

    public ArrayList<Person> getEdges() {
        return edges;
    }

    /**
     *
     * @param dest
     * @param route
     * */
    public void addEdge(Business dest, Person route) {

        //Check if the edges contains a person
        if (this.edges.contains(route)) {
            //If the person infectiveness is highest from the original replace it to the new Person
            if (this.edges.get(this.edges.indexOf(route)).getInfectiveness() < route.getInfectiveness()) {
                this.edges.remove(route);
                route.setBusiness(dest);
                this.edges.add(route);
            }
        }
        else{
            route.setBusiness(dest);
            this.edges.add(route);

        }

    }


    public void removeEdge(Business dest) {
        this.edges.removeIf(p -> p.getBusiness().equals(dest));
    }

    public ArrayList<Person> getEdgesByInfectiveness(){
        ArrayList<Person> listOfcontacts = new ArrayList<>(this.edges);
        listOfcontacts.sort(new CustomComparator());
        return listOfcontacts;
    }

    public class CustomComparator implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return Math.round(o1.getInfectiveness()-o2.getInfectiveness());
        }
    }
    @Override
    public String toString() {
        return "" +
                "name='" + name + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Business business = (Business) o;
        return name.equals(business.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
