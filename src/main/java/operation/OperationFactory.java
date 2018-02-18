package operation;

import com.google.common.base.Preconditions;

/**
 * Created by mtumilowicz on 2018-02-02.
 */
public final class OperationFactory {
    
    private OperationFactory() {
    }

    public static final Operation get(OperationType type, int value) {
        Preconditions.checkArgument(type != null, "Type cannot be null.");

        Operation toReturn;
        switch (type) {
            case ADD:
                toReturn = getAdd(value);
                break;
            case SUBTRACT:
                toReturn = getSubtract(value);
                break;
            case DIVIDE:
                toReturn = getDivide(value);
                break;
            case MULTIPLY:
                toReturn = getMultiply(value);
                break;
            case APPLY:
                toReturn = getApply(value);
                break;
            default:
                throw new IllegalStateException("Type not supported: " + type);
        }
        return toReturn;
    }

    public static final Operation getAdd(int value) {
        return new Operation(OperationType.ADD, value);
    }

    public static final Operation getSubtract(int value) {
        return new Operation(OperationType.SUBTRACT, value);
    }

    public static final Operation getDivide(int value) {
        Preconditions.checkArgument(value != 0, "Cannot divide by zero!");
        return new Operation(OperationType.DIVIDE, value);
    }

    public static final Operation getMultiply(int value) {
        return new Operation(OperationType.MULTIPLY, value);
    }

    public static final Operation getApply(int value) {
        return new Operation(OperationType.APPLY, value);
    }
}
