package operation;

import com.google.common.base.Preconditions;

/**
 * Created by mtumilowicz on 2018-02-02.
 */
public final class OperationParser {

    private OperationParser() {
    }

    public static final Operation parse(String operation) {
        Preconditions.checkArgument(operation != null, "Operation to parse cannot be null.");
        Preconditions.checkArgument(operation.contains(" "), "Operation to parse doesn't contain separator.");

        String[] split = operation.split(" ");
        Preconditions.checkState(split.length == 2, "Operation to parse has more than one separator.");

        return OperationFactory.get(OperationType.valueOf(split[0].toUpperCase()), Integer.parseInt(split[1]));
    }
}
