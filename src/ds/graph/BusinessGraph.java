package com.company.BusinessGraphClasses;

import java.util.*;

public class BusinessGraph {
    private ArrayList<Business> vertices;

    public BusinessGraph() {
        this.vertices = new ArrayList<>();
    }

    public void addVertex(Business b) {
        if(!this.vertices.contains(b)){
            this.vertices.add(b);
        }
    }

    public void removeVertex(Business b) {

       //check first if the business is in the vertex
        if(vertices.contains(b)){
            //Remove any reference of a vertex
            for (int i = 0; i < this.vertices.size(); i++) {
                for (int x = 0; x < this.vertices.get(i).getEdges().size(); x++) {
                    if (this.vertices.get(i).getEdges().get(x).getBusiness().equals(b)) {
                        this.vertices.get(i).getEdges().remove(x);
                    }
                }
            }
            this.vertices.removeIf(p -> p.equals(b));
        }
    }

    public int totalPersonsInfected(Business start) {
        ArrayList<Business> visited = new ArrayList<>();
        int step = 0;
        int count = 0;
        //check if start object is in the vertex
        if (vertices.contains(start)) {
            visited.add(this.vertices.get(vertices.indexOf(start)));
            Queue<Business> queue = new LinkedList<>();
            //add neighbors to queue
            for (int i = 0; i < start.getEdges().size(); i++) {
                queue.add(start.getEdges().get(i).getBusiness());
            }

            while (queue.size() != 0) {
                if (step == 3)
                    break;
                Business b = queue.poll(); // get the top of the queue
                if (!visited.contains(b)) {
                    //Count the person inside business (Edges)
                    count += b.getEdges().size();
                    visited.add(b); // add to visited
                    //traverse the neighbors of a vertex
                    for (int i = 0; i < b.getEdges().size(); i++) {
                        //If the neighbors is not visited we must add to the queue
                        if (!visited.contains(b.getEdges().get(i).getBusiness())) {
                            queue.add(b.getEdges().get(i).getBusiness());
                        }
                    }
                }

                step++;

            }

        }

        return count;
    }

    public int minStepsToDestFromStart(Business start, Business dest) {
        Stack<Business> stackVertex = new Stack<>();
        //Get all neighbor of the root vertex
        for (int i = start.getEdgesByInfectiveness().size() - 1; i >= 0; i--) {
            stackVertex.push(start.getEdgesByInfectiveness().get(i).getBusiness());
        }

        ArrayList<Business> visited = new ArrayList<>();
        visited.add(start);
        while (!stackVertex.isEmpty()) {
            /*
             * Since DFS is not accurate when it comes to get the shortest path
             * We need to determined which edges is the lowest weight
             * We apply collection sort
             * */
            Business b = stackVertex.pop();
            //Here we already get the sorted values by Infectiveness of a person
            ArrayList<Person> edges = new ArrayList<>(b.getEdgesByInfectiveness());

            //check if there's no edges
            if (edges.size() != 0) {
                //We assume that 1st element on a list is the lowest because we sorted it already
                //and it is not the destination
                if (!visited.contains(edges.get(0).getBusiness())) {
                    visited.add(b);
                    stackVertex.push(edges.get(0).getBusiness());
                }
                //We also need to check if destination is equal to the top of stack
                else if (dest.equals(b)) {
                    visited.add(b);
                    break;
                }

            }

        }
        return visited.size();
    }

    public boolean isStronglyConnected(Business start) {
        Stack<Business> stackVertex = new Stack<>();

        //Get all neighbor of the root vertex
        for (int i = start.getEdgesByInfectiveness().size() - 1; i >= 0; i--) {
            stackVertex.push(start.getEdgesByInfectiveness().get(i).getBusiness());
        }
        ArrayList<Business> visited = new ArrayList<>();
        visited.add(start);
        while (!stackVertex.isEmpty()) {
            Business b = stackVertex.pop();
            ArrayList<Person> edges = new ArrayList<>(b.getEdges());
            //check if there's no edges
            if (edges.size() != 0) {
                //If there edges traverse any neighbor vertex
                for (int i = 0; i < edges.size(); i++) {
                    // check if the stack or visited has already have the business vertex and return true
                    if (visited.contains(edges.get(i).getBusiness()) || stackVertex.contains(edges.get(i).getBusiness()))
                        return true;
                    //Add to stack
                    else
                        stackVertex.push(edges.get(i).getBusiness());
                }


            }

        }

        return false;
    }

    public ArrayList<Business> getVertices() {
        return vertices;
    }
}
