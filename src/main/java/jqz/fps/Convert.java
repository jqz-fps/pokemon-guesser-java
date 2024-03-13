package jqz.fps;

public class Convert {

    // This class only will have methods for various cases

    /**
     * The name is self-descriptive
     * @param input is the entry
     * @return the input with the first letter capitalized
     */

    public static String capitalize_first_letter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    /**
     * This method will return a number in the roman form
     * @param number is the number to convert
     * @return the roman number in string
     */

    public static String number_to_roman(int number) {
        if (number <= 0 || number > 3999) {
            throw new IllegalArgumentException("The number must be in the range of 1 to 3999.");
        }

        String[] romanSymbols = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        int[] arabicValues = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};

        StringBuilder result = new StringBuilder();

        int i = arabicValues.length - 1;
        while (number > 0) {
            if (number >= arabicValues[i]) {
                result.append(romanSymbols[i]);
                number -= arabicValues[i];
            } else {
                i--;
            }
        }

        return result.toString();
    }

}
