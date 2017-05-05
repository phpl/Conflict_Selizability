package com.company;

@SuppressWarnings("DefaultFileTemplate")
class ConflictOperation {
    private final Operation from;
    private final Operation to;

    ConflictOperation(Operation from, Operation to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConflictOperation)) return false;

        ConflictOperation that = (ConflictOperation) o;

        return getFrom().equals(that.getFrom()) && getTo().equals(that.getTo());
    }

    @Override
    public int hashCode() {
        int result = getFrom().hashCode();
        result = 31 * result + getTo().hashCode();
        return result;
    }

    Operation getFrom() {
        return from;
    }

    Operation getTo() {
        return to;
    }
}
