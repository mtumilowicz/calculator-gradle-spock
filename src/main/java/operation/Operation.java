package operation;

import java.util.Objects;

/**
 * Created by mtumilowicz on 2018-02-02.
 */
public final class Operation {

    private final OperationType type;
    private final int amount;

    Operation(OperationType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public final OperationType getType() {
        return type;
    }

    public final int getAmount() {
        return amount;
    }

    public final int eval(int eval) {
        return type.evaluate(eval, amount);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return amount == operation.amount &&
                type == operation.type;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(type, amount);
    }

    @Override
    public final String toString() {
        return "operation.Operation{" +
                "type=" + type +
                ", amount=" + amount +
                '}';
    }
}
