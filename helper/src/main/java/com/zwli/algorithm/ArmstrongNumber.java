package com.zwli.algorithm;

/**
 * 如果一个n位正整数等于其各位数字的n次方之和,则称该数为阿姆斯特朗数。 例如1^3 + 5^3 + 3^3 = 153 当n=3时，又称水仙花数，特指一种三位数，其各个数之立方和等于该数。
 * 水仙花数共有4个，分别为：153、370、371、407。 <br>
 * An Armstrong number is a 3 digit number for which sum of cube of its digit is equal to the number. Example of
 * Armstrong number is 153 as 153= 1+ 125+27 which 1^3+5^3+3^3. Another Armstrong number is 371.
 */
public class ArmstrongNumber {

    public boolean isArmstrongNumber(int number) {
        int tmp = number;
        int noOfDigits = String.valueOf(number).length();
        int sum = 0;
        int div = 0;
        while (tmp > 0) {
            div = tmp % 10;
            int temp = 1;
            for (int i = 0; i < noOfDigits; i++) {
                temp *= div;
            }
            sum += temp;
            tmp = tmp / 10;
        }
        if (number == sum) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String a[]) {
        ArmstrongNumber man = new ArmstrongNumber();
        System.out.println("Is 371 Armstrong number? " + man.isArmstrongNumber(371));
        System.out.println("Is 523 Armstrong number? " + man.isArmstrongNumber(523));
        System.out.println("Is 153 Armstrong number? " + man.isArmstrongNumber(153));
    }
}
