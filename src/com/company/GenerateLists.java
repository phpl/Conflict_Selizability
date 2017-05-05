package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

class GenerateLists {
    private ArrayList<ArrayList<Operation>> temp = new ArrayList<>();
    private ArrayList<Operation> list = new ArrayList<>();
    private int counter[] = new int[3];

    GenerateLists(ArrayList<ArrayList<Operation>> temp) {
        this.temp.addAll(temp);
        Arrays.fill(counter, 0);
        for (ArrayList<Operation> z : temp) {
            System.out.println(z);
        }
    }

    private static int k = 0;

    void generatePermutations() {

        boolean bottom = true;

        for (int i = 0; i < temp.size(); i++) {
            if (counter[i] < temp.get(i).size()) {
                list.add(temp.get(i).get(counter[i]));
                counter[i]++;
                generatePermutations();
                counter[i]--;
                list.remove(list.size() - 1);
                bottom = false;
            }
        }

        if (bottom) {
            Schedule schedule = new Schedule(list);
//            System.out.println(k++);
            if (schedule.testForConflictSerializability()) {
                System.out.println(k++);
                System.out.println(list);
                HashSet<ConflictOperation> conflicts = schedule.createPrecedenceGraph();
                GraphAllTopSorts g = new GraphAllTopSorts(3);
                conflicts.forEach(conflictOperation -> g.addEdge(conflictOperation.getFrom().getNumberOfTransaction() - 1, conflictOperation.getTo().getNumberOfTransaction() - 1));
                g.alltopologicalSorts();
            }
        }
    }

}
