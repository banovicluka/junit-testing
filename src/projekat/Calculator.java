package projekat;

/**
 * Class for main mathematics operations such as +, - , / , *
 * @author Luka
 *
 *
 */
public class Calculator {
	
	/**
	 *  Current value on calculator and first operand during the operations.
	 */
	private Double currentValue = 0.0;
	
	/**
	 * Getter for current value on calculator.
	 * @return returns current value on calculator.
	 */
	public Double getCurrentValue() {
		return currentValue;
	}
	
	/**
	 * Setter for current value on calculator.
	 * @param currentValue is the value that we need to set.
	 */
	public void setCurrentValue(Double currentValue) {
		this.currentValue = currentValue;
	}
	
	/**
	 * @param value is the second operand during the operations.
	 * @param operator is operation which we want to apply.
	 * @throws DivisionByZeroException 
	 * @throws NotSupportedOperationException
	 */
	public void calculate(Double value, char operator) throws DivisionByZeroException, NotSupportedOperationException {
		if(operator == '+') {
			currentValue = currentValue + value;
		}else if(operator == '-') {
			currentValue = currentValue - value;
		}else if(operator == '/') {
			if(value != 0){
				currentValue = currentValue / value;
			}else
				throw new DivisionByZeroException();
		}else if(operator == '*') {
			currentValue = currentValue * value;
		}else {
			throw new NotSupportedOperationException();
		}
	}
}
