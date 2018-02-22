package operation

import spock.lang.Specification

/**
 * Created by mtumilowicz on 2018-02-22.
 */
class OperationFactoryTest extends Specification {
    
    def "throw IllegalArgumentException if operation type is null"() {
        when:
        OperationFactory.get(null, 0)
        
        then:
        thrown(IllegalArgumentException)
    }
    
    def "get operations using specific methods"() {
        expect:
        def operation = OperationFactory.get(type as OperationType, amount as int)
        operation.type == type
        operation.amount == amount
        
        where:
        type << OperationType.values()
        amount << [1, 1, 1, 1, 1]
    }
    
    def "throw IllegalArgumentException if divide by zero"() {
        when:
        OperationFactory.get(OperationType.DIVIDE, 0)
        OperationFactory.getDivide(0)
        
        then:
        thrown(IllegalArgumentException)
    }
    
    def "eval all operations"() {
        expect:
        def operation = OperationFactory.get(type, 5)
        expected == operation.eval(5)
        
        where:
        type << [OperationType.ADD,
                 OperationType.SUBTRACT,
                 OperationType.DIVIDE,
                 OperationType.MULTIPLY,
                 OperationType.APPLY]
        
        expected << [10, 0, 1, 25, 5]
    }
    
    def "test overflow"() {
        when:
        OperationFactory.getAdd(5).eval(Integer.MAX_VALUE)
        
        then:
        thrown(ArithmeticException)
    }
    
    def "test explicit methods in factory to get operation"() {
        expect:
        operation.type == type
        operation.amount == 5
        
        where:
        operation << [OperationFactory.getAdd(5),
                      OperationFactory.getSubtract(5),
                      OperationFactory.getDivide(5),
                      OperationFactory.getMultiply(5),
                      OperationFactory.getApply(5),
        ]
        
        type << [OperationType.ADD,
                    OperationType.SUBTRACT,
                    OperationType.DIVIDE,
                    OperationType.MULTIPLY,
                    OperationType.APPLY]
    }
}
