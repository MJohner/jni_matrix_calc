import java.util.Arrays;

public class Main {
    static{
        System.loadLibrary("lib/Cpp_Matrix");
    }
    public static void main(String[] args){
        Matrix m1 = new Matrix(500,6000);
        Matrix m2 = new Matrix(6000,400);
        Matrix m3 = new Matrix(250,250);
        Matrix mj = measureMultiplyJava(m1, m2);
        m2 = new Matrix(6000,400);
        Matrix mc = measureMultiplyCpp(m1, m2);
        System.out.println("Multiply Matrix is equal: " + mj.equals(mc));
        Matrix pj = measurePowerJava(m3);
        Matrix pc = measurePowerCpp(m3);
        System.out.println("Power Matrix is equal: " + pj.equals(pc));
    }

    public static Matrix measureMultiplyJava(Matrix m1, Matrix m2){

        long startTime = System.nanoTime();
        Matrix m3 = m1.multiply(m2);
        long stopTime = System.nanoTime();
        System.out.println("Multiply calculation finished in: " + (stopTime-startTime) / 1000000+ " milliseconds (Java)");
        return m3;
    }
    public static Matrix measureMultiplyCpp(Matrix m1, Matrix m2){

        long startTime = System.nanoTime();
        Matrix m3 = m1.multiplyCpp(m2);
        long stopTime = System.nanoTime();
        System.out.println("Multiply calculation finished in: " + (stopTime-startTime) / 1000000+ " milliseconds (C++)");
        return m3;
    }

    public static Matrix measurePowerJava(Matrix m){
        long startTime = System.nanoTime();
        Matrix m3 = m.power(93);
        long stopTime = System.nanoTime();
        System.out.println("Power calculation finished in: " + (stopTime-startTime) / 1000000+ " milliseconds (Java)");
        return m3;
    }

    public static Matrix measurePowerCpp(Matrix m){
        long startTime = System.nanoTime();
        Matrix m3 = m.powerCpp(93);
        long stopTime = System.nanoTime();
        System.out.println("Power calculation finished in: " + (stopTime-startTime) / 1000000 + " milliseconds (C++)");
        return m3;
    }
}
/*
Debug DLL:
Multiply calculation finished in: 7423 milliseconds (Java)
Multiply calculation finished in: 10115 milliseconds (C++)
Multiply Matrix is equal: false
Power calculation finished in: 2104 milliseconds (Java)
Power calculation finished in: 5157 milliseconds (C++)
Power Matrix is equal: true
 */

/*
Release DLL:
Multiply calculation finished in: 7813 milliseconds (Java)
Multiply calculation finished in: 9721 milliseconds (C++)
Multiply Matrix is equal: false
Power calculation finished in: 2063 milliseconds (Java)
Power calculation finished in: 5028 milliseconds (C++)
Power Matrix is equal: true
 */