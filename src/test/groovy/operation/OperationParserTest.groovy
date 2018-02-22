package operation

import spock.lang.Specification

/**
 * Created by mtumilowicz on 2018-02-21.
 */
class OperationParserTest extends Specification {

    def "throw IllegalArgumentException if argument is null"() {
        
        when:
        OperationParser.parse(null)

        then:
        thrown(IllegalArgumentException)
    }

    def "throw IllegalArgumentException if argument has no separator"() {
        
        when:
        OperationParser.parse("test")

        then:
        thrown(IllegalArgumentException)
    }

    def "throw IllegalArgumentException if argument contains not supported operation"() {
        
        when:
        OperationParser.parse("notSupportedOperation 1")
        
        then:
        thrown(IllegalArgumentException)
    }

    def "throw IllegalArgumentException if argument has operation without number"() {
        
        when:
        OperationParser.parse("notSupportedOperation a")

        then:
        thrown(IllegalArgumentException)
    }
    
    def "parse each operation uppercase and lowercase"() {
        
        expect:
        def parsedFromUppercase = OperationParser.parse(operation)
        def parsedFromLowercase = OperationParser.parse(operation)
        parsedFromUppercase.type == expectedType
        parsedFromUppercase.amount == expectedAmount
        parsedFromLowercase.type == expectedType
        parsedFromLowercase.amount == expectedAmount
        
        where:
        operation << ['ADD 5',
                      'SUBTRACT 5',
                      'DIVIDE 5',
                      'MULTIPLY 5',
                      'APPLY 5',
        ]
        expectedType << [OperationType.ADD,
                         OperationType.SUBTRACT,
                         OperationType.DIVIDE,
                         OperationType.MULTIPLY,
                         OperationType.APPLY]
        expectedAmount << [5, 5, 5, 5, 5]
        
    }

    def "throw IllegalArgumentException if divide by zero"() {

        when:
        OperationParser.parse("DIVIDE 0")
        OperationParser.parse("divide 0")

        then:
        thrown(IllegalArgumentException)
    }
}
