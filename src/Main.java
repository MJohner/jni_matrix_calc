import java.util.Arrays;

public class Main {
    static{
        System.loadLibrary("lib/Cpp_Matrix");
    }
    public static void main(String[] args){
        Matrix m1Java = new Matrix(500,6000);
        Matrix m2Java = new Matrix(6000,400);
        Matrix m3Java = new Matrix(250,250);

        Matrix m1Cpp = new Matrix(500,6000, m1Java.values); // <-- This took me 4 hours -.-
        Matrix m2Cpp = new Matrix(6000,400, m2Java.values );
        Matrix m3Cpp = new Matrix(250,250, m3Java.values);

        Matrix mj = measureMultiplyJava(m1Java, m2Java);
        Matrix mc = measureMultiplyCpp(m1Cpp, m2Cpp);
        System.out.println("Multiply Matrix is equal: " + mj.equals(mc));
        Matrix pj = measurePowerJava(m3Java);
        Matrix pc = measurePowerCpp(m3Cpp);
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
Multiply calculation finished in: 7614 milliseconds (Java)
Multiply calculation finished in: 10126 milliseconds (C++)
Multiply Matrix is equal: true
Power calculation finished in: 2149 milliseconds (Java)
Power calculation finished in: 5557 milliseconds (C++)
Power Matrix is equal: true
 */

/*
Release DLL:
Multiply calculation finished in: 7577 milliseconds (Java)
Multiply calculation finished in: 7475 milliseconds (C++)
Multiply Matrix is equal: true
Power calculation finished in: 2213 milliseconds (Java)
Power calculation finished in: 1842 milliseconds (C++)
Power Matrix is equal: true
 */