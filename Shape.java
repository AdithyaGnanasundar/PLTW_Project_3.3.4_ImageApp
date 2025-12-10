import java.util.ArrayList;

public class Shape
{
  private ArrayList<double[]> coordinates; // list of [x, y] coordinates
  
  // constructor - creates empty shape
  public Shape()
  {
    coordinates = new ArrayList<double[]>();
  }
  
  // Constructor - creates shape from list of coordinates
  public Shape(double[][] coords)
  {
    coordinates = new ArrayList<double[]>();
    for (double[] coord : coords)
    {
      coordinates.add(new double[]{coord[0], coord[1]});
    }
  }
  
  // add a coordinate point to the shape
  public void addPoint(double x, double y)
  {
    coordinates.add(new double[]{x, y});
  }
  
  // Get coordinate at index
  public double[] getPoint(int index)
  {
    return coordinates.get(index);
  }
  
  // get number of points in shape
  public int size()
  {
    return coordinates.size();
  }
  
  // Transform all coordinates using a transformation matrix
  // uses matrix multiplication: newPoint = matrix * [x, y, 1]
  public Shape transform(Matrix transformMatrix)
  {
    Shape transformed = new Shape();
    for (double[] point : coordinates)
    {
      double[] newPoint = transformMatrix.transformPoint(point[0], point[1]);
      transformed.addPoint(newPoint[0], newPoint[1]);
    }
    return transformed;
  }
  
  // rotate shape around origin
  public Shape rotate(double angleDegrees)
  {
    Matrix rotMatrix = Matrix.rotationMatrix(angleDegrees);
    return transform(rotMatrix);
  }
  
  // Scale shape
  public Shape scale(double scaleX, double scaleY)
  {
    Matrix scaleMatrix = Matrix.scalingMatrix(scaleX, scaleY);
    return transform(scaleMatrix);
  }
  
  // translate (move) shape
  public Shape translate(double tx, double ty)
  {
    Matrix transMatrix = Matrix.translationMatrix(tx, ty);
    return transform(transMatrix);
  }
  
  // Calculate dot product of two vectors
  public static double dotProduct(double x1, double y1, double x2, double y2)
  {
    return x1 * x2 + y1 * y2;
  }
  
  // calculate vector magnitude (length)
  public static double vectorMagnitude(double x, double y)
  {
    return Math.sqrt(x * x + y * y);
  }
  
  // Get all coordinates as array
  public double[][] getCoordinates()
  {
    double[][] result = new double[coordinates.size()][2];
    for (int i = 0; i < coordinates.size(); i++)
    {
      result[i][0] = coordinates.get(i)[0];
      result[i][1] = coordinates.get(i)[1];
    }
    return result;
  }
  
  // string representation
  public String toString()
  {
    StringBuilder sb = new StringBuilder("Shape[");
    for (int i = 0; i < coordinates.size(); i++)
    {
      double[] point = coordinates.get(i);
      sb.append("(").append(point[0]).append(",").append(point[1]).append(")");
      if (i < coordinates.size() - 1) sb.append(", ");
    }
    sb.append("]");
    return sb.toString();
  }
}

