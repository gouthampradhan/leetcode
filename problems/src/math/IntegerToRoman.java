public class IntegerToRoman{
    public static void main(String[] args){
        numberToRoman(55);
    }

    public static String numberToRoman(int number){//Converts a number into a roman numerial
        String roman = "";
        while(number != 0){//checks if the number is not equal to 0, if it is not equal then there is more processing to be done
            if(number >= 1000){//if the number is larger than 1000, then add an M to the roman string and subtract 1000 from number
                roman += "M";
                number -= 1000;
            }
            else if(number >= 900){//if the number is larger than 900, then add an CM to the roman string and subtract 900 from number
                roman += "CM";
                number -= 900;
            }
            else if(number >= 500){//if the number is larger than 500, then add an D to the roman string and subtract 500 from number
                roman += "D";
                number -= 500;
            }
            else if(number >= 400){//if the number is larger than 400, then add an CD to the roman string and subtract 400 from number
                roman += "CD";
                number -= 400;
            }
            else if(number >= 100){//if the number is larger than 100, then add an C to the roman string and subtract 100 from number
                roman += "C";
                number -= 100;
            }
            else if(number >= 90){//if the number is larger than 90, then add an XC to the roman string and subtract 90 from number
                roman += "XC";
                number -= 90;
            }
            else if(number >= 50){//if the number is larger than 50, then add an L to the roman string and subtract 50 from number
                roman += "L";
                number -= 50;
            }
            else if(number >= 40){//if the number is larger than 40, then add an XL to the roman string and subtract 40 from number
                roman += "XL";
                number -= 40;
            }
            else if(number >= 10){//if the number is larger than 10, then add an X to the roman string and subtract 10 from number
                roman += "X";
                number -= 10;
            }
            else if(number >= 9){//if the number is larger than 9, then add an IX to the roman string and subtract 9 from number
                roman += "IX";
                number -= 9;
            }
            else if(number >= 5){//if the number is larger than 5, then add an V to the roman string and subtract 5 from number
                roman += "V";
                number -= 5;
            }
            else if(number >= 4){//if the number is larger than 4, then add an IV to the roman string and subtract 4 from number
                roman += "IV";
                number -= 4;
            }
            else if(number >= 1){//if the number is larger than 1, then add an I to the roman string and subtract 1 from number
                roman += "I";
                number -= 1;
            }
        }
        return roman;
    }
}