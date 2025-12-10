public class Matrix
{
  private double[][] data;
  private int rows;
  private int cols;
  
  // constructor for a matrix with given dimensions
  public Matrix(int rows, int cols)
  {
    this.rows = rows;
    this.cols = cols;
    this.data = new double[rows][cols];
  }
  
  // Constructor from 2D array
  public Matrix(double[][] data)
  {
    this.rows = data.length;
    this.cols = data[0].length;
    this.data = new double[rows][cols];
    for (int i = 0; i < rows; i++)
    {
      for (int j = 0; j < cols; j++)
      {
        this.data[i][j] = data[i][j];
      }
    }
  }
  
  // get value at position (row, col)
  public double get(int row, int col)
  {
    return data[row][col];
  }
  
  // Set value at position (row, col)
  public void set(int row, int col, double value)
  {
    data[row][col] = value;
  }
  
  // matrix multiplication: this * other
  public Matrix multiply(Matrix other)
  {
    if (this.cols != other.rows)
    {
      throw new IllegalArgumentException("Matrix dimensions incompatible for multiplication");
    }
    
    Matrix result = new Matrix(this.rows, other.cols);
    for (int i = 0; i < this.rows; i++)
    {
      for (int j = 0; j < other.cols; j++)
      {
        double sum = 0;
        for (int k = 0; k < this.cols; k++)
        {
          sum += this.data[i][k] * other.data[k][j];
        }
        result.set(i, j, sum);
      }
    }
    return result;
  }
  
  // Create a 2D rotation matrix
  public static Matrix rotationMatrix(double angleDegrees)
  {
    double angleRad = Math.toRadians(angleDegrees);
    double cos = Math.cos(angleRad);
    double sin = Math.sin(angleRad);
    
    double[][] rotData = {
      {cos, -sin, 0},
      {sin, cos, 0},
      {0, 0, 1}
    };
    return new Matrix(rotData);
  }
  
  // create a 2D scaling matrix
  public static Matrix scalingMatrix(double scaleX, double scaleY)
  {
    double[][] scaleData = {
      {scaleX, 0, 0},
      {0, scaleY, 0},
      {0, 0, 1}
    };
    return new Matrix(scaleData);
  }
  
  // Create a 2D translation matrix
  public static Matrix translationMatrix(double tx, double ty)
  {
    double[][] transData = {
      {1, 0, tx},
      {0, 1, ty},
      {0, 0, 1}
    };
    return new Matrix(transData);
  }
  
  // transform a point using this matrix
  // point is represented as [x, y, 1] in homogeneous coordinates
  public double[] transformPoint(double x, double y)
  {
    Matrix point = new Matrix(3, 1);
    point.set(0, 0, x);
    point.set(1, 0, y);
    point.set(2, 0, 1);
    
    Matrix result = this.multiply(point);
    return new double[]{result.get(0, 0), result.get(1, 0)};
  }
  
  // Get number of rows
  public int getRows()
  {
    return rows;
  }
  
  // get number of columns
  public int getCols()
  {
    return cols;
  }
  
  // string representation of matrix
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < rows; i++)
    {
      sb.append("[");
      for (int j = 0; j < cols; j++)
      {
        sb.append(String.format("%.2f", data[i][j]));
        if (j < cols - 1) sb.append(", ");
      }
      sb.append("]\n");
    }
    return sb.toString();
  }
}

