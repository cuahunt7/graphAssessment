package com.company.BusinessGraphClasses;

public class Tester {
    public static void main(String[] args) {
        BusinessGraph bg = new BusinessGraph();
        Business b1 = new Business("B1");
        Business b2 = new Business("B2");
        Business b3 = new Business("B3");
        Business b4 = new Business("B4");
        Business b5 = new Business("B5");
        Business b6 = new Business("B6");
        Business b7 = new Business("B7");

        Business b8 = new Business("B8");
        Business b9 = new Business("B9");



        Person p1 = new Person("p1",21,.44f);
        Person p2 = new Person("p2",22,.5f);
        Person p3 = new Person("p3",23,.6f);


        Person p4 = new Person("p4",31,.7f);
        Person p5 = new Person("p5",32,.8f);
        Person p6 = new Person("p6",33,.9f);


        Person p7 = new Person("p7",41,.71f);
        Person p8 = new Person("p8",42,.72f);
        Person p9 = new Person("p9",43,.73f);
        Person p10 = new Person("p10",51,.74f);
        Person p11 = new Person("p11",52,.75f);
        Person p12 = new Person("p12",53,.76f);
        Person p13 = new Person("p13",54,.76f);
        Person p14 = new Person("p14",55,.76f);
        Person p15 = new Person("p15",56,.76f);

        b1.addEdge(b2,p1);
        b1.addEdge(b3,p2);
        b1.addEdge(b4,p3);

        b2.addEdge(b5,p4);
        b2.addEdge(b3,p10);

        b3.addEdge(b6,p5);

        b4.addEdge(b7,p6);
        b4.addEdge(b3,p15);
        b4.addEdge(b1,p9);

        b5.addEdge(b8,p7);

        b6.addEdge(b9,p8);
        b6.addEdge(b5,p14);
        b9.addEdge(b8,p11);
        b8.addEdge(b9,p12);


        b7.addEdge(b6,p13);

        bg.addVertex(b1);
        bg.addVertex(b2);
        bg.addVertex(b3);
        bg.addVertex(b4);
        bg.addVertex(b5);
        bg.addVertex(b6);
        bg.addVertex(b7);
        bg.addVertex(b8);
        bg.addVertex(b9);
        bg.removeVertex(b9);

        System.out.println("Total person infected: "+bg.totalPersonsInfected(new Business("b1000")));
        System.out.println("Min step: "+bg.minStepsToDestFromStart(b1,b7));
        System.out.println("Strongly Connected "+bg.isStronglyConnected(b1));


        for(int i = 0 ; i < bg.getVertices().size(); i++){
            System.out.println(bg.getVertices().get(i).getName()+":"+bg.getVertices().get(i).getEdges());
        }

    }
}
