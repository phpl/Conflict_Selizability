package com.company;

import java.util.*;

@SuppressWarnings("DefaultFileTemplate")
class Schedule {

    private List<Operation> scheduleList;

    Schedule(List<Operation> scheduleList) {
        this.scheduleList = scheduleList;
    }

    Schedule(String transaction1, String transaction2, String transaction3) {
        String[] t1 = transaction1.split(" ");
        String[] t2 = transaction2.split(" ");
        String[] t3 = transaction3.split(" ");
        ArrayList<Operation> t1List = createTransaction(t1);
        ArrayList<Operation> t2List = createTransaction(t2);
        ArrayList<Operation> t3List = createTransaction(t3);

        ArrayList<ArrayList<Operation>> toPermute = new ArrayList<>();
        toPermute.add(new ArrayList<>());
        toPermute.add(new ArrayList<>());
        toPermute.add(new ArrayList<>());
        toPermute.get(0).addAll(t1List);
        toPermute.get(1).addAll(t2List);
        toPermute.get(2).addAll(t3List);
        GenerateLists gen = new GenerateLists(toPermute);
        gen.generatePermutations();
    }

    private ArrayList<Operation> createTransaction(String[] t) {
        ArrayList<Operation> transactionList = new ArrayList<>();
        for (String operation : t) {
            char oper = operation.substring(0, 1).charAt(0);
            int transaction = Integer.parseInt(operation.substring(1, 2));
            char element = operation.substring(2).charAt(0);
            transactionList.add(new Operation(oper, transaction, element));
        }
        return transactionList;
    }


    boolean testForConflictSerializability() {
        HashSet<ConflictOperation> conflicts = createPrecedenceGraph();
        for (ConflictOperation outer : conflicts) {
            Operation outerFrom = outer.getFrom();
            Operation outerTo = outer.getTo();
            for (ConflictOperation inner : conflicts) {
                Operation innerFrom = inner.getFrom();
                Operation innerTo = inner.getTo();
                if (outerFrom.equals(innerTo) && outerTo.equals(innerFrom)) {
                    return false;
                }
            }
        }
        return true;
    }

    @SuppressWarnings("UnnecessaryContinue")
    HashSet<ConflictOperation> createPrecedenceGraph() {
        HashSet<ConflictOperation> conflicts = new HashSet<>();
        for (int i = 0; i < scheduleList.size(); i++) {
            Operation outerOperation = scheduleList.get(i);
            for (int j = 0; j < scheduleList.size(); j++) {
                Operation innerOperation = scheduleList.get(j);
                if (outerOperation.equals(innerOperation)) continue;
                else if (outerOperation.getOperation() == 'r' && innerOperation.getOperation() == 'r') continue;
                else if (outerOperation.getElement() != innerOperation.getElement()) continue;
                else {
                    if (i < j) conflicts.add(new ConflictOperation(outerOperation, innerOperation));
                    else if (i > j) conflicts.add(new ConflictOperation(innerOperation, outerOperation));
                }
            }
        }
        return conflicts;
    }
}

