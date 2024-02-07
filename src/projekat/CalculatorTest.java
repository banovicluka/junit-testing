package projekat;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;



import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CalculatorTest {
	
	private Calculator calculator = new Calculator();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		calculator.setCurrentValue(0.0);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void testCalculator() {
		assertNotNull(calculator);
	}

	@Test
	void testGetCurrentValue() {
		assertEquals(Double.valueOf(0), calculator.getCurrentValue());
	}

	@Test
	void testSetCurrentValue() {
		calculator.setCurrentValue(24.0);
		assertEquals(Double.valueOf(24), calculator.getCurrentValue());
	}

	@ParameterizedTest
	@MethodSource("parametersMethod")
	void testCalculate(Double operand1, Double operand2, char operation, Double expectedResult) throws DivisionByZeroException, NotSupportedOperationException {
		calculator.setCurrentValue(operand1);
		calculator.calculate(operand2, operation);
		assertThat(expectedResult, is(calculator.getCurrentValue()));
		
	}
	
	private static Stream<Arguments> parametersMethod(){
		
		return Stream.of(
				Arguments.of(Double.valueOf(14),Double.valueOf(2), "+", Double.valueOf(16)),
				Arguments.of(Double.valueOf(14), Double.valueOf(2),"-", Double.valueOf(12)),
				Arguments.of(Double.valueOf(14),Double.valueOf(2),"*",Double.valueOf(28)),
				Arguments.of(Double.valueOf(14), Double.valueOf(2), "/", Double.valueOf(7))
				);
	}
	
	@Test
	void testCalculateDivisionByZeroException(){
		Exception ex = assertThrows(DivisionByZeroException.class, () -> calculator.calculate(Double.valueOf(0), '/'));
		assertThat(ex,instanceOf(DivisionByZeroException.class));
	}
	
	@Test
	void testCalculateNotSupportedOperationException() {
		Exception ex = assertThrows(NotSupportedOperationException.class, () -> calculator.calculate(Double.valueOf(0), '$'));
		assertThat(ex, instanceOf(NotSupportedOperationException.class));
	}

}
