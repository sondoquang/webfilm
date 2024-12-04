package com.webmovie.utils;


public class XValidate {
    public static boolean name(String name) {
        String regex = "\\p{L}+[\\p{L}\\p{M}\\p{Pd}'’]*\\p{L}+";
        return name.matches(regex);
    }

    public static boolean checkName(String name){
        String regax = "[a-zA-Z ÀÁÂÃÈÉÊẾÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêếìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỂưăạảấầẩẫậắằẳẵặẹẻẽềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵýỷỹ]+";
        return name.matches(regax);
    }

    /**
     *XValidate the given String whether its resembles an email address
     *
     * @param email The string to be validated
     * @return true if its an email, otherwise false
     */
    public static boolean email(String email) {
        String regex = "\\w{1,20}+@gmail+(\\.\\w{1,5}){1,2}";
        return email.matches(regex);
    }

    /**
     *XValidate the given String whether its resembles a phone number
     *
     * @param numbers The string to be validated
     * @return true if its a phone number, otherwise false
     */
    public static boolean phoneNumber(String numbers) {
        String regex = "0\\d{9,10}";
        return numbers.matches(regex);
    }

    /**
     *XValidate the given String whether its resembles an Id number
     *
     * @param numbers The string to be validated
     * @return true if its an Id number, otherwise false
     */
    public static boolean idNumber(String numbers) {
        String regex = "0\\d{8,11}";
        return numbers.matches(regex);
    }

    /**
     *XValidate the given String whether its resembles a decimal number
     *
     * @param number The string to be validated
     * @return true if its a decimal number, otherwise false
     */
    public static boolean number(String number) {
        try {
            Double.valueOf(number);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     *XValidate the given String whether its resembles a positive decimal number
     *
     * @param number The string to be validated
     * @return true if its a positive decimal number, otherwise false
     */
    public static boolean positiveNumb(String number) {
        try {
            double i = Double.parseDouble(number);
            return i >= 0;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     *XValidate the given String whether its resembles an integer
     *
     * @param number The string to be validated
     * @return true if its an integer, otherwise false
     */
    public static boolean integer(String number) {
        try {
            Integer.valueOf(number);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean positiveInt(String number) {
        try {
            int i = Integer.parseInt(number);
            return i >= 0;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean ngaySinh(String Date){
        String regex = "/^(0?[1-9]|1[012])[- /.](0?[1-9]|[12][0-9]|3[01])[- /.](19|20)?[0-9]{2}$/";
        if(Date.matches(regex)){
            return true;
        }
        return false;
    }
}
