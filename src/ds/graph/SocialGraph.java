package com.company.dsSocial;

import java.util.*;

public class SocialGraph extends SocialGraphException {
    public ArrayList<Person> vertices;

    public SocialGraph() {
        this.vertices = new ArrayList<>();
    }

    /**
     * Add the given person to the graph. The person needs to be added to the list of vertices.
     *
     * @param p
     * @throws PersonAlreadyExists If the person is already present in the graph,
     *                             this method should throw a PersonAlreadyPresent exception.
     */
    public void addVertex(Person p) {
        try {
            if (this.vertices.contains(p))
                throw new PersonAlreadyExists("Person is already exist");
            else
                this.vertices.add(p);
        } catch (PersonAlreadyExists personAlreadyExists) {
            personAlreadyExists.printStackTrace();
        }


    }


    /**
     * Remove the given Person from the graph. Any edges to this person should also be removed.
     *
     * @param p
     * @throws PersonDoesNotExist If the given person is not found inside the graph.
     */
    public void removeVertex(Person p) {
        if (!this.vertices.contains(p))
            try {
                throw new PersonDoesNotExist("");//Throws exception here
            } catch (PersonDoesNotExist personDoesNotExist) {
                personDoesNotExist.printStackTrace();
            }
        else
            this.vertices.remove(p);
    }

    /**
     * Add an edge between the two people (vertices) in the graph. The graph is undirected, so
     * both People will need to list the other, in their list of contacts.
     * <p>
     * If the edge already exists, this method should return true.
     *
     * @param a
     * @param b
     * @throws PersonDoesNotExist If the person is not found within the graph.
     */
    public void addEdge(Person a, Person b) {

       try{
           /**
            * To determined if the two person has edges
            * check if has the contact of each other
            * */
           if(this.vertices.contains(a) && this.vertices.contains(b)){
               if (!a.getContacts().contains(b) && !b.getContacts().contains(a)) {
                   a.getContacts().add(b); // add person b to contact of person a
                   b.getContacts().add(a); // add person a to contact of person b
               }
           }
           else{
               throw new PersonDoesNotExist("Person Does not exist");
           }
       }catch (PersonDoesNotExist e){
           e.printStackTrace();
       }
    }

    /**
     * Remove the edge between the given People from the graph.
     * If no edge existed between these people, this method should throw an EdgeDoesNotExist exception.
     *
     * @param a
     * @param b
     * @throws EdgeDoesNotExist
     */
    public void removeEdge(Person a, Person b) {
        try{
            if (a.getContacts().contains(b) && b.getContacts().contains(a)) {
                a.getContacts().remove(b);
                b.getContacts().remove(a);
            } else {
                throw new EdgeDoesNotExist("Edge does not exist");
            }
        }catch (EdgeDoesNotExist e){
            e.printStackTrace();
        }
    }

    /**
     * Implement a breadth-first search, from Person start to target.
     * This method should consider the graph unweighted: the order that the Persons are stored inside the contacts list will
     * determine the order that the BFS operates.
     *
     * @param start
     * @param target
     * @return A list of nodes that must be traversed to get to target, from start.
     * @throws PersonDoesNotExist if either start or target are not in the graph.
     */
    public ArrayList<Person> searchBFS(Person start, Person target) {

        if(!this.vertices.contains(start) || !this.vertices.contains(target)){
            try {
                throw new PersonDoesNotExist("Person Doest not exist");
            } catch (PersonDoesNotExist personDoesNotExist) {
                personDoesNotExist.printStackTrace();
            }
        }else{
            /**
             * We store object start contacts to queue
             * We add start object to visited
             * */
            Queue<Person> queue = new LinkedList<>(start.getContacts());
            ArrayList<Person> visited = new ArrayList<>();
            visited.add(start);
            while(!queue.isEmpty()){
                //get the head of a queue
                Person v = queue.poll();
                //place the person if it is not visited
                if(!visited.contains(v)){
                    visited.add(v);
                    /*
                     * Since we need to check if every front of the queue is equal to the target
                     * we must break
                     * **/
                    if(v.equals(target)){
                        break;
                    }else{
                        /* We need to add from the back of the queue every adjacency(Contacts)
                         * and we check if it is visited
                         */
                        ArrayList<Person> adjacent = new ArrayList<>(v.getContacts());
                        for(int i = 0 ; i <adjacent.size(); i++){
                            if(!visited.contains(adjacent.get(i)) && adjacent.get(i).equals(target)){
                                queue.add(adjacent.get(i)); // add the vertex(Person) to queue if its not visited
                            }

                        }
                    }
                }
            }
            return visited;
        }

        return null;
    }



    /**
     * Implement a breadth-first search, from Person start to target.
     * The weights associated with each edge should determine the order that the BFS operates.
     * Higher weights are preferred over lower weights. The weight is found by calling getInfectiveness() on the Person.
     *
     * @param start
     * @param target
     * @return A list of nodes that must be traversed to get to target, from start.
     * @throws PersonDoesNotExist if either start or target are not in the graph.
     */
    public ArrayList<Person> searchWeightedBFS(Person start, Person target) {
        if(!this.vertices.contains(start) || !this.vertices.contains(target)){
            try {
                throw new PersonDoesNotExist("Person Doest not exist");
            } catch (PersonDoesNotExist personDoesNotExist) {
                personDoesNotExist.printStackTrace();
            }
        }else{
            /**
             * We store object start contacts to queue
             * We add start object to visited
             * */
            Queue<Person> queue = new LinkedList<>(start.getContactByInfectiveness());
            ArrayList<Person> visited = new ArrayList<>();
            visited.add(start);
            while(!queue.isEmpty()){
                //get the head of a queue
                Person v = queue.poll();
                //place the person if it is not visited
                if(!visited.contains(v)){
                    visited.add(v);
                    /*
                     * Since we need to check if every front of the queue is equal to the target
                     * we must break
                     * **/
                    if(v.equals(target)){
                        break;
                    }else{
                        /* We need to add from the back of the queue every adjacency(Contacts)
                         * and we check if it is visited
                         */
                        ArrayList<Person> adjacent = new ArrayList<>(v.getContactByInfectiveness());
                        for(int i = 0 ; i <adjacent.size(); i++){
                            if(!visited.contains(adjacent.get(i)) && adjacent.get(i).equals(target)){
                                queue.add(adjacent.get(i)); // add the vertex(Person) to queue if its not visited
                            }

                        }
                    }
                }
            }
            return visited;
        }

        return null;
    }


    /**
     * Implement a depth-first search, from Person start to target.
     * This method should consider the graph unweighted: the order that the Persons are stored inside the contacts list will
     * determine the order that the DFS operates.
     *
     * @param start
     * @param target
     * @return A list of nodes that must be traversed to get to target, from start.
     * @throws PersonDoesNotExist if either start or target are not in the graph.
     */
    public ArrayList<Person> searchDFS(Person start, Person target) {
        if(!this.vertices.contains(start) || !this.vertices.contains(target)){
            try {
                throw new PersonDoesNotExist("Person Doest not exist");
            } catch (PersonDoesNotExist personDoesNotExist) {
                personDoesNotExist.printStackTrace();
            }
        }else{
            Stack<Person> stack = new Stack<>();
            /*
             * We use for loop because LILO in stack
             * to be able to retrieve the 1st contact
             * */
            for(int i = start.getContacts().size()-1 ; i >=0 ; i--){
                stack.push(start.getContacts().get(i));
            }

            ArrayList<Person> visited = new ArrayList<>();
            visited.add(start); // add start to visited

            while(!stack.isEmpty()){
                Person v = stack.pop(); // get the top of the stack

                //Visit the vertex in the stack
                if(!visited.contains(v)){
                    visited.add(v);
                    if(v.equals(target))
                        break;
                    //check for adjacency
                    //get the neighbors of visited vertex
                    ArrayList<Person> adjacent = new ArrayList<>(v.getContacts());
                    for(int i =0 ;i < adjacent.size(); i++){
                        //if the neighbor is already in the stack we must skip
                        //if the neighbor is already visited we must skip
                        if(stack.contains(adjacent.get(i)) || visited.contains(adjacent.get(i))){
                            continue;
                        }
                        //else we must add it on the top of the stack
                        else{
                            stack.push(adjacent.get(i));
                        }
                    }
                }

            }
            return visited;
        }

        return null;
    }

    /**
     * Implement a depth-first search, from Person start to target.
     * The weights associated with each edge should determine the order that the DFS operates.
     * Higher weights are preferred over lower weights. The weight is found by calling getInfectiveness() on the Person.
     *
     * @param start
     * @param target
     * @return A list of nodes that must be traversed to get to target, from start.
     * @throws PersonDoesNotExist if either start or target are not in the graph.
     */
    public ArrayList<Person> searchWeightedDFS(Person start, Person target) {
        if(!this.vertices.contains(start) || !this.vertices.contains(target)){
            try {
                throw new PersonDoesNotExist("Person Doest not exist");
            } catch (PersonDoesNotExist personDoesNotExist) {
                personDoesNotExist.printStackTrace();
            }
        }else{
            Stack<Person> stack = new Stack<>();
            /*
             * We use for loop because LILO in stack
             * to be able to retrieve the 1st contact
             * */
            for(int i = start.getContactByInfectiveness().size()-1 ; i >=0 ; i--){
                stack.push(start.getContactByInfectiveness().get(i));
            }

            ArrayList<Person> visited = new ArrayList<>();
            visited.add(start); // add start to visited

            while(!stack.isEmpty()){
                Person v = stack.pop(); // get the top of the stack

                //Visit the vertex in the stack
                if(!visited.contains(v)){
                    visited.add(v);
                    if(v.equals(target))
                        break;
                    //check for adjacency
                    //get the neighbors of visited vertex
                    ArrayList<Person> adjacent = new ArrayList<>(v.getContactByInfectiveness());
                    for(int i =0 ;i < adjacent.size(); i++){
                        //if the neighbor is already in the stack we must skip
                        //if the neighbor is already visited we must skip
                        if(stack.contains(adjacent.get(i)) || visited.contains(adjacent.get(i))){
                            continue;
                        }
                        //else we must add it on the top of the stack
                        else{
                            stack.push(adjacent.get(i));
                        }
                    }
                }

            }
            return visited;
        }

        return null;
    }

    /**
     * This method should return an int value showing the total number of contacts-of-contacts of the start person.
     * This is the equivalent to doing a BFS around the start person, and
     * counting the vertices 2 steps away from the start node.
     *
     * @param start
     * @return
     * @throws PersonDoesNotExist if either start or target are not in the graph.
     */
    public int countContacts(Person start) {
        if(!this.vertices.contains(start)){
            try {
                throw new PersonDoesNotExist("Person Doest not exist");
            } catch (PersonDoesNotExist personDoesNotExist) {
                personDoesNotExist.printStackTrace();
            }
        }else{
            /**
             * We store object start contacts to queue
             * We add start object to visited
             * */
            int count = 0;
            int step = 0;
            Queue<Person> queue = new LinkedList<>(start.getContacts());
            ArrayList<Person> visited = new ArrayList<>();
            visited.add(start);
            while(!queue.isEmpty()){

                //get the head of a queue
                Person v = queue.poll();
                //place the person if it is not visited
                if(!visited.contains(v)){
                    visited.add(v);
                    /*
                     * Since we need to check if every front of the queue is equal to the target
                     * we must break
                     * **/
                    if(step == 2){
                        break;
                    }else{
                        /* We need to add from the back of the queue every adjacency(Contacts)
                         * and we check if it is visited
                         */
                        ArrayList<Person> adjacent = new ArrayList<>(v.getContacts());
                        for(int i = 0 ; i <adjacent.size(); i++){
                            if(!visited.contains(adjacent.get(i))){
                                queue.add(adjacent.get(i)); // add the vertex(Person) to queue if its not visited

                            }
                        }
                    }
                }
                step += 1;
                count += 1;

            }
            return count;
        }
        return 0;
    }
}
