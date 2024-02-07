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

/**
 * 
 * Test class for CalculatorAdvanced class.
 * @author Luka
 *
 */
class CalculatorAdvancedTest {

	/**
	 * Calculator that is going to be tested.
	 */
	private CalculatorAdvanced calculator = new CalculatorAdvanced();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for method calculateAdvanced where execution without exceptions
	 * are tested.
	 * @param operand represents parameter which is set on current value place.
	 * @param action represents action or advanced operation which is going to be executed.
	 * @param expectedResult represents expected result of operation.
	 * @throws NotSupportedOperationException
	 * @throws NumberNotInAreaException
	 */
	@ParameterizedTest
	@MethodSource("goodParametersMethod")
	void testCalculateAdvanced(Double operand, char action, Double expectedResult) throws NotSupportedOperationException, NumberNotInAreaException {
		calculator.setCurrentValue(operand);
		calculator.calculateAdvanced(action);
		assertThat(expectedResult, is(calculator.getCurrentValue()));
		
	}
	
	/**
	 * @return stream of arguments for testCalculateAdvanced() method.
	 */
	private static Stream<Arguments> goodParametersMethod() {
		return Stream.of(
				Arguments.of(4.5,'!', Double.valueOf(24)),
				Arguments.of(Double.valueOf(5), '!' , Double.valueOf(120)),
				Arguments.of(4.0,'!', Double.valueOf(24)),
		
				Arguments.of(4.5,'2', Double.valueOf(16)),
				Arguments.of(3.7,'3', Double.valueOf(27)),
				Arguments.of(Double.valueOf(5),'0',1.0)
				);
	
	}
	
	/**
	 * Test for situation when calculateAdvanced() method throws an
	 * NotSupportedOperationException
	 * @param currentValue represents value which is set on current value place.
	 * @param exponent
	 */
	@ParameterizedTest
	@MethodSource("notSupportedOperationMethod")
	void testCalculateAdvancedNotSupportedOperationMethod(Double currentValue, char exponent) {
		calculator.setCurrentValue(currentValue);
		Exception ex = assertThrows(NotSupportedOperationException.class, () -> calculator.calculateAdvanced(exponent));
		assertThat(ex, instanceOf(NotSupportedOperationException.class));
	}
	
	/**
	 * @return stream of arguments that makes situation where NotSupportedOperationException is thrown.
	 */
	private static Stream<Arguments> notSupportedOperationMethod(){
		return Stream.of(
				Arguments.of(3.0, ';'),
				Arguments.of(3.0, ">"),
				Arguments.of(3.0, "^")
				);
	}
	
	/**
	 * Test for situation when calculateAdvanced() method throws an
	 * NumberNotInAreaException
	 * @param operand represents value which is set on current value place.
	 */
	@ParameterizedTest
	@MethodSource("badParametersMethod")
	void testCalculateAdvancedNumberNotInAreaException(Double operand) {
		calculator.setCurrentValue(operand);
		Exception ex = assertThrows(NumberNotInAreaException.class, () -> calculator.calculateAdvanced('!'));
		assertThat (ex, instanceOf(NumberNotInAreaException.class));
	}
	
	/**
	 * @return stream of arguments that makes situation where NumberNotInAreaException is thrown.
	 */
	private static Stream<Arguments> badParametersMethod(){
		return Stream.of(
				Arguments.of(-4.5),
				Arguments.of(14.5),
				Arguments.of(Double.valueOf(-4)),
				Arguments.of(Double.valueOf(14)));
	}

	/**
	 * Test for hasCharacteristic method when its return value is true.
	 * @param currentValue represents value which is set on current value place.
	 * @param value represents which characteristic are going to be detected. 'A' is for
	 * Armstrong's number and 'P' is for Perfect number.
	 * @throws NotSupportedOperationException
	 * @throws NumberNotInAreaException
	 */
	@ParameterizedTest
	@MethodSource("goodParametersHasCharacteristic")
	void testHasCharacteristicTrue(Double currentValue, char value) throws NotSupportedOperationException, NumberNotInAreaException {
		calculator.setCurrentValue(currentValue);
		Boolean bool = calculator.hasCharacteristic(value);
		assertThat(bool, is(true));
		
	}
	
	/**
	 * Test for hasCharacteristic method when its return value is false.
	 * @param currentValue represents value which is set on current value place.
	 * @param value represents which characteristic are going to be detected. 'A' is for
	 * Armstrong's number and 'P' is for Perfect number.
	 * @throws NotSupportedOperationException
	 * @throws NumberNotInAreaException
	 */
	@ParameterizedTest
	@MethodSource("badParametersHasCharacteristic")
	void testHasCharacteristicFalse(Double currentValue, char value) throws NotSupportedOperationException, NumberNotInAreaException {
		calculator.setCurrentValue(currentValue);
		Boolean bool = calculator.hasCharacteristic(value);
		assertThat(bool, is(false));
		
	}
	
	/**
	 * @return stream of arguments that makes situation where hasCharacteristic method returns true.
	 */
	private static Stream<Arguments> goodParametersHasCharacteristic(){
		return Stream.of(
				Arguments.of(Double.valueOf(153), 'A'),
				Arguments.of(Double.valueOf(1634), 'A'),
			
				Arguments.of(Double.valueOf(28.5),'P'),
				Arguments.of(Double.valueOf(6.2), 'P')
				
				);
	}
	
	/**
	 * @return stream of arguments that makes situation where hasCharacteristic method returns false.
	 */
	private static Stream<Arguments> badParametersHasCharacteristic(){
		return Stream.of(
				Arguments.of(Double.valueOf(111), 'A'),
				Arguments.of(Double.valueOf(77),'A'),
				
				Arguments.of(Double.valueOf(7.1),'P'),
				Arguments.of(Double.valueOf(11),'P')
				);
	}
	
	/**
	 * Test for hasCharacteristic() method when it throws NotSupportedOperationException.
	 */
	@Test
	void testHasCharacteristicNSOE() {
		calculator.setCurrentValue(Double.valueOf(12));
		Exception ex = assertThrows(NotSupportedOperationException.class,() -> calculator.hasCharacteristic('3'));
		assertThat(ex, instanceOf(NotSupportedOperationException.class));
	}
	
	/**
	 * Test for hasCharacteristic() method when it throws NumberNotInAreaException.
	 */
	@Test
	void testHasCharacteristicNNIAE() {
		calculator.setCurrentValue(Double.valueOf(0));
		Exception ex = assertThrows(NumberNotInAreaException.class,() -> calculator.hasCharacteristic('P'));
		assertThat(ex, instanceOf(NumberNotInAreaException.class));
	}

}
