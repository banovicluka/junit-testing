package projekat;

/**
 * Class that expands Calculator class with factorial and exponent operations, and that can recognize is the number
 * Armstrong's or Perfect.
 * @author Luka
 *
 */
public class CalculatorAdvanced extends Calculator{
	
	/**
	 * 
	 * Method for additional operations such as calculating factorial or power of a number.
	 * @param action represents argument that shows us which action(operation) will be applied.
	 * @throws NotSupportedOperationException
	 * @throws NumberNotInAreaException
	 */
	public void calculateAdvanced(char action) throws NotSupportedOperationException, NumberNotInAreaException {
		if(action == '!') {
			if(this.getCurrentValue()<0.0 || this.getCurrentValue()>10.0) {
				throw new NumberNotInAreaException();
			}else {
				this.setCurrentValue(factorialCalculator());
			}

		}else {
			int number;
			try {
				number = Integer.parseInt(Character.toString(action));
			}catch(NumberFormatException ex) {
				throw new NotSupportedOperationException();
			}
			this.setCurrentValue(exponentCalculator(number));
		}
	}
	
	/**
	 * 
	 * Method for calculating factorial of a number.
	 * @return factorial of a current value.
	 */
	private Double factorialCalculator() {
		int number = this.getCurrentValue().intValue();
		int result = 1;
		for(int i=1;i<=number;i++) {
			result = result * i;
		}
		return (double) result;
	}
	
	/**
	 * Method that calculates power of a number.
	 * @param number represents exponent.
	 * @return power of a current value, where base number is current value and exponent
	 * is number given as argument.
	 */
	private Double exponentCalculator(int number) {
		Double result = 1.0;
		for(int i=0; i<number;i++) {
			result = result * this.getCurrentValue().intValue();
		}
		return result;
	}
	
	/**
	 * @param value represents arguments that shows us which characteristic we need to
	 * detect. 'A' is for Armstrong's number and 'P' is for Perfect numbers.
	 * @return boolean that shows us has the current value required characteristic or not.
	 * @throws NotSupportedOperationException
	 * @throws NumberNotInAreaException
	 */
	public Boolean hasCharacteristic(char value) throws NotSupportedOperationException, NumberNotInAreaException {
		
		int number = this.getCurrentValue().intValue();
		if(number<1)
			throw new NumberNotInAreaException();
		if(value == 'A') {
			int counter = 0;
			int num = number;
			while( num != 0) {
				num=num/10;
				counter++;
			}
			int sum = 0;
			for(int i=0;i<counter;i++) {
				int digit = number%10;
				int addition=1;
				for(int j =0;j<counter;j++) {
					addition = addition * digit;
				}
				sum = sum + addition;
				number = number/10;
			}
			if(this.getCurrentValue().intValue() == sum)
				return true;
			else return false;
		}else if(value == 'P') {
			int i = 1;
			int sum = 0;
			while(i != number) {
				if(number % i == 0) {
					sum = sum + i;
				}
				i++;
			}
			if(number == sum)
				return true;
			else return false;
		}else
			throw new NotSupportedOperationException();
		
	}
}
