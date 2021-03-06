package ba.unsa.etf.rpr.tutorijal06;

public class Model {
    public double calculate(double number1, double number2, String operator){
        switch (operator){
            case "+": return number1+number2;
            case "-": return number1-number2;
            case "x": return number1*number2;
            case "/":
                if(number2==0)
                    return 0;
                return number1/number2;
            case "%": return (double)(int)number1%(int)number2;
            default:
                return 0;
        }
    }
}
