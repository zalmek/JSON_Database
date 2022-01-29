import java.util.function.*;


class Operator {

    public static LongBinaryOperator binaryOperator = (x, y) -> {
        long sum = 1;
        for (int i = (int) x; i <= y; i++) {
            sum *= i;
        }
        return sum;
    };
}