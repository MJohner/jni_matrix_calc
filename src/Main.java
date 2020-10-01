import java.util.Arrays;

public class Main {

    public static void main(String[] args){
        Matrix m1 = new Matrix(1,2);
        Matrix m2 = new Matrix(2,1);
        Matrix m3 = m1.multiply(m2);
        System.out.println(Arrays.toString(m1.values));
        System.out.println(Arrays.toString(m2.values));
        System.out.println(Arrays.toString(m3.values));
    }


}
