package com.company;

@SuppressWarnings("DefaultFileTemplate")
public class Operation {
    private final char operation;
    private final int numberOfTransaction;
    private final char element;

    @Override
    public String toString() {
        return "" + operation + numberOfTransaction + element;
    }

    Operation(char operation, int numberOfTransaction, char element) {
        this.operation = operation;
        this.numberOfTransaction = numberOfTransaction;
        this.element = element;
    }

    char getOperation() {
        return operation;
    }

    int getNumberOfTransaction() {
        return numberOfTransaction;
    }

    char getElement() {
        return element;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operation)) return false;

        Operation operation = (Operation) o;

        return getNumberOfTransaction() == operation.getNumberOfTransaction();
    }

    @Override
    public int hashCode() {
        return getNumberOfTransaction();
    }
}
