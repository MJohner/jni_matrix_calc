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
        Matrix mc = measureMultiplyCpp(m1, m2);
        System.out.println("Multiply Matrix is equal: " + mj.equals(mc));
        Matrix pj = measurePowerJava(m3);
        Matrix pc = measurePowerCpp(m3);
        System.out.println("Power Matrix is equal: " +pj.equals(pc));

    }

    public static Matrix measureMultiplyJava(Matrix m1, Matrix m2){

        long startTime = System.nanoTime();
        Matrix m3 = m1.multiply(m2);
        long stopTime = System.nanoTime();
        System.out.println("Multiply calculation finished in: " + (stopTime-startTime) + " nanoseconds (Java)");
        return m3;
    }
    public static Matrix measureMultiplyCpp(Matrix m1, Matrix m2){

        long startTime = System.nanoTime();
        Matrix m3 = m1.multiplyCpp(m2);
        long stopTime = System.nanoTime();
        System.out.println("Multiply calculation finished in: " + (stopTime-startTime) + " nanoseconds (C++)");
        return m3;
    }

    public static Matrix measurePowerJava(Matrix m){
        long startTime = System.nanoTime();
        Matrix m3 = m.power(93);
        long stopTime = System.nanoTime();
        System.out.println("Power calculation finished in: " + (stopTime-startTime) + " nanoseconds (Java)");
        return m3;
    }

    public static Matrix measurePowerCpp(Matrix m){
        long startTime = System.nanoTime();
        Matrix m3 = m.powerCpp(93);
        long stopTime = System.nanoTime();
        System.out.println("Power calculation finished in: " + (stopTime-startTime) + " nanoseconds (C++)");
        return m3;
    }
}

/*
Output Test debug dll:
    Multiply calculation finished in: 7188819099 nanoseconds (Java)
    Multiply calculation finished in: 8969594400 nanoseconds (C++)
    Multiply Matrix is equal: true
    Power calculation finished in: 2069102900 nanoseconds (Java)
    Power calculation finished in: 4961769700 nanoseconds (C++)
    Power Matrix is equal: true
*/

/*
Output Test release dll:
    Multiply calculation finished in: 7282346100 nanoseconds (Java)
    Multiply calculation finished in: 6873522800 nanoseconds (C++)
    Multiply Matrix is equal: true
    Power calculation finished in: 2126658100 nanoseconds (Java)
    Power calculation finished in: 1746182900 nanoseconds (C++)
    Power Matrix is equal: true
*/