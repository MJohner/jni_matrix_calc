import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Random;

public class Matrix {
    public int cols, rows;
    public double[] values;

    public Matrix(int rows, int cols){
        values = new double[cols * rows];
        Random r = new Random();
        this.cols = cols;
        this.rows = rows;
        for(int i=0; i < values.length; i++){
            values[i] = r.nextDouble();
        }
    }

    public Matrix(int rows, int cols, double number){
        values = new double[cols * rows];
        this.cols = cols;
        this.rows = rows;
        for(int i=0; i < values.length; i++){
            values[i] = number;
        }
    }

    public Matrix(int rows, int cols, double[] numbers){
        values = new double[cols * rows];
        this.cols = cols;
        this.rows = rows;
        if(numbers.length == values.length){
            values = numbers;
        }else{
            throw new IllegalArgumentException("The given numbers array does not fit into the requested matrix");
        }
    }

    public Matrix multiplyCpp(Matrix m){
        double[] result = new double[this.rows * m.cols];
        multiplyNative(this.values, m.values, result, this.cols, m.cols, this.rows);
        return new Matrix(this.rows, m.cols, result);
    }

    public native void multiplyNative(double[] m1, double[] m2, double[] result, int m1_cols, int m2_cols, int result_cols);
    public native void powerNative(double[] m, double[] result, int m_cols, int exp);

    public Matrix powerCpp(int exp){
        if(exp<0){
            throw new IllegalArgumentException("The exponent must be 0 or greater");
        }
        if(this.cols != this.rows){
            throw new UnsupportedOperationException("The matrix is not a square matrix rows: " + this.rows + " cols: " + this.cols);
        }
        if(exp == 0){
            return identity(this);
        }
        if(exp==1){
            return new Matrix(this.cols, this.cols, this.values);
        }

        double[] result = new double[this.cols * this.cols];
        powerNative(this.values, result, this.cols, exp);
        return new Matrix(this.cols, this.cols, result);
    }

    public Matrix power(int exp){
        if(exp<0){
          throw new IllegalArgumentException("The exponent must be 0 or greater");
        }
        if(this.cols != this.rows){
            throw new UnsupportedOperationException("The matrix is not a square matrix rows: " + this.rows + " cols: " + this.cols);
        }
        if(exp == 0){
            return identity(this);
        }
        Matrix result = new Matrix(this.rows, this.cols, this.values);
        for(int i=1;i < exp; i++){
            multiply(this, result);
        }
        return result;
    }


    public Matrix identity(Matrix m){
        double result[] = new double[m.rows * m.cols];
        for(int row = 0; row < m.rows; row++){
            result[row*m.cols+row] = 1;
        }
        return new Matrix(m.rows, m.cols, result);
    }

    @Override
    public boolean equals(Object o){
        if(o == null || !(o instanceof Matrix)){
            return false;
        }
        Matrix m = (Matrix) o;
        boolean isEqual = (m.rows == this.rows && m.cols == this.cols);
        return isEqual && Arrays.equals(this.values, m.values);
    }

    private void multiply(Matrix m, Matrix r){
        if(m == null || r == null){
            throw new InvalidParameterException("The Matrix m or r is null");
        }
        if(m.cols != r.rows){
            throw new InvalidParameterException("The Matrix A has: "+ m.cols + " columns and the Matrix B has: " + r.rows + " rows. Multiply not possible.");
        }
        double[] result = new double[m.rows * r.cols];
        for(int row = 0; row < m.rows; row++){
            for(int col = 0; col < r.cols; col++){
                double sum = 0;
                for(int i = 0; i < m.cols; i++){
                    sum += m.values[row*m.cols +i] * r.values[col+i*r.cols];
                }
                result[row*r.cols + col] = sum;
            }
        }
        r.rows = m.rows;
        r.values = result;
    }

    public Matrix multiply(Matrix m){
        multiply(this, m);
        return m;
    }

}
