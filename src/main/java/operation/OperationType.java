package operation;

import com.google.common.math.IntMath;

import java.math.RoundingMode;
import java.util.function.IntBinaryOperator;

/**
 * Created by mtumilowicz on 2018-02-02.
 */
public enum OperationType {
    ADD(IntMath::checkedAdd), 
    SUBTRACT(IntMath::checkedSubtract), 
    MULTIPLY(IntMath::checkedMultiply), 
    DIVIDE((x,y) -> IntMath.divide(x,y, RoundingMode.FLOOR)), 
    APPLY((x,y) -> y);
    
    private final IntBinaryOperator evalMethod;

    OperationType(IntBinaryOperator eval) {
        this.evalMethod = eval;
    }
    
    public final int evaluate(int eval, int amount) {
        return evalMethod.applyAsInt(eval, amount);
    }
}
