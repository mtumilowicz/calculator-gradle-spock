package evaluator

import operation.OperationFactory
import spock.lang.Specification

/**
 * Created by mtumilowicz on 2018-02-21.
 */
class EvaluatorTest extends Specification {
    
    def "if operations are null throw IllegalArgumentException"() {
        when:
        Evaluator.eval(null)
        
        then:
        final IllegalArgumentException exception = thrown()
        
        exception.message == "List of operations cannot be null."
    }
    
    def "if last operation is not APPLY throw IllegalStateException"() {
        when:
        Evaluator.eval(Collections.singletonList(
                OperationFactory.getAdd(10)))
        
        then:
        thrown(IllegalStateException)
    }
    
    def "test all possible operations"() {
        expect:
        Evaluator.eval(operations) == expected
        
        where:
        expected || operations
        20       || [OperationFactory.getAdd(10),
                     OperationFactory.getSubtract(4),
                     OperationFactory.getMultiply(4),
                     OperationFactory.getDivide(3),
                     OperationFactory.getApply(9)]
        
        10       || [OperationFactory.getSubtract(4),
                     OperationFactory.getMultiply(4), 
                     OperationFactory.getAdd(10),
                     OperationFactory.getDivide(3),
                     OperationFactory.getApply(9)]
    }
}
