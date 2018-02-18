package evaluator;

import com.google.common.base.Preconditions;
import operation.Operation;
import operation.OperationType;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * Created by mtumilowicz on 2018-02-02.
 */
public final class Evaluator {

    private Evaluator() {
    }

    public static final int eval(List<Operation> operations) {
        Preconditions.checkArgument(operations != null, "List of operations cannot be null.");
        if (operations.isEmpty()) {
            return 0;
        }

        Deque<Operation> deque = new ArrayDeque<>(operations);

        Operation applyOperation = deque.removeLast();
        Preconditions.checkState(applyOperation.getType() == OperationType.APPLY, 
                "Last operation has to be APPLY.");

        int eval = applyOperation.getAmount();
        while (!deque.isEmpty()) {
            eval = deque.removeFirst().eval(eval);
        }

        return eval;
    }
}
