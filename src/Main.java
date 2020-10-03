import java.util.Arrays;

public class Main {
    static{
        System.loadLibrary("lib/Cpp_Matrix");
    }
    public static void main(String[] args){
//        Matrix m1 = new Matrix(500,6000);
//        Matrix m2 = new Matrix(6000,400);
//        Matrix mj = measureMultiplyJava(m1, m2);
//        Matrix mc = measureMultiplyCpp(m1, m2);
//        System.out.println("Is Matrix equal: " + mj.equals(mc));
//
         Matrix m = new Matrix(2,2,2);
        System.out.println(Arrays.toString(m.powerCpp(1).values));
        System.out.println(Arrays.toString(m.powerCpp(2).values));
        System.out.println(Arrays.toString(m.powerCpp(3).values));
        System.out.println(Arrays.toString(m.powerCpp(4).values));
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


}
