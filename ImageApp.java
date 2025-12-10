import java.awt.Color;

public class ImageApp
{
  public static void main(String[] args)
  {

    // Use any file from the lib folder
    String pictureFile = "lib/beach.jpg";

    // get an image, get 2d array of pixels, show a color of a pixel, and display the image
    Picture origImg = new Picture(pictureFile);
    Pixel[][] origPixels = origImg.getPixels2D();
    System.out.println(origPixels[0][0].getColor());
    origImg.explore();

    // image #1 using the original image and pixels, recolor an image by changing the RGB color of each Pixel
    Picture recoloredImg = new Picture(pictureFile);
    Pixel[][] recoloredPixels = recoloredImg.getPixels2D();
    
    // recolor by swapping red and blue channels to create a color-shifted effect
    for (int row = 0; row < recoloredPixels.length; row++)
    {
      for (int col = 0; col < recoloredPixels[0].length; col++)
      {
        Color pixelColor = recoloredPixels[row][col].getColor();
        int red = pixelColor.getRed();
        int green = pixelColor.getGreen();
        int blue = pixelColor.getBlue();
        // swap red and blue channels
        recoloredPixels[row][col].setColor(new Color(blue, green, red));
      }
    }
    recoloredImg.explore();

    // Image #2 using the original image and pixels, create a photographic negative of the image
    Picture negImg = new Picture(pictureFile);
    Pixel[][] negPixels = negImg.getPixels2D();
    
    // create negative by inverting each rgb value (255 - current value)
    for (int row = 0; row < negPixels.length; row++)
    {
      for (int col = 0; col < negPixels[0].length; col++)
      {
        Color pixelColor = negPixels[row][col].getColor();
        int red = 255 - pixelColor.getRed();
        int green = 255 - pixelColor.getGreen();
        int blue = 255 - pixelColor.getBlue();
        negPixels[row][col].setColor(new Color(red, green, blue));
      }
    }
    negImg.explore();

    // image #3 Using the original image and pixels, create a grayscale version of the image
    Picture grayscaleImg = new Picture(pictureFile);
    Pixel[][] grayscalePixels = grayscaleImg.getPixels2D();
    
    // create grayscale by averaging RGB values
    for (int row = 0; row < grayscalePixels.length; row++)
    {
      for (int col = 0; col < grayscalePixels[0].length; col++)
      {
        Color pixelColor = grayscalePixels[row][col].getColor();
        int red = pixelColor.getRed();
        int green = pixelColor.getGreen();
        int blue = pixelColor.getBlue();
        // calculate average of rgb values
        int gray = (red + green + blue) / 3;
        grayscalePixels[row][col].setColor(new Color(gray, gray, gray));
      }
    }
    grayscaleImg.explore();

    // Image #4 using the original image and pixels, rotate it 180 degrees
    Picture upsidedownImage = new Picture(pictureFile);
    Pixel[][] upsideDownPixels = upsidedownImage.getPixels2D();
    
    // rotate 180 degrees by flipping both horizontally and vertically
    int height = upsideDownPixels.length;
    int width = upsideDownPixels[0].length;
    for (int row = 0; row < height / 2; row++)
    {
      for (int col = 0; col < width; col++)
      {
        Pixel topPixel = upsideDownPixels[row][col];
        Pixel bottomPixel = upsideDownPixels[height - 1 - row][width - 1 - col];
        Color tempColor = topPixel.getColor();
        topPixel.setColor(bottomPixel.getColor());
        bottomPixel.setColor(tempColor);
      }
    }
    // handle middle row if height is odd
    if (height % 2 == 1)
    {
      int midRow = height / 2;
      for (int col = 0; col < width / 2; col++)
      {
        Pixel leftPixel = upsideDownPixels[midRow][col];
        Pixel rightPixel = upsideDownPixels[midRow][width - 1 - col];
        Color tempColor = leftPixel.getColor();
        leftPixel.setColor(rightPixel.getColor());
        rightPixel.setColor(tempColor);
      }
    }
    upsidedownImage.explore();

    // image #5 Using the original image and pixels, rotate image 90 degrees clockwise
    Picture rotateImg = new Picture(pictureFile);
    Pixel[][] rotatePixels = rotateImg.getPixels2D();
    
    // Rotate 90 degrees clockwise - create new picture with swapped dimensions
    int originalHeight = rotatePixels.length;
    int originalWidth = rotatePixels[0].length;
    Picture rotated90 = new Picture(originalWidth, originalHeight);
    Pixel[][] rotated90Pixels = rotated90.getPixels2D();
    
    // copy pixels: original[row][col] -> rotated[col][originalHeight - 1 - row]
    for (int row = 0; row < originalHeight; row++)
    {
      for (int col = 0; col < originalWidth; col++)
      {
        Color pixelColor = rotatePixels[row][col].getColor();
        rotated90Pixels[col][originalHeight - 1 - row].setColor(pixelColor);
      }
    }
    rotated90.explore();

    // Image #6 using the original image and pixels, rotate image 270 degrees (-90 degrees)
    Picture rotateImg2 = new Picture(pictureFile);
    Pixel[][] rotatePixels2 = rotateImg2.getPixels2D();
    
    // rotate 270 degrees (or -90 degrees) clockwise - create new picture with swapped dimensions
    int originalHeight2 = rotatePixels2.length;
    int originalWidth2 = rotatePixels2[0].length;
    Picture rotated270 = new Picture(originalWidth2, originalHeight2);
    Pixel[][] rotated270Pixels = rotated270.getPixels2D();
    
    // Copy pixels: original[row][col] -> rotated[originalWidth - 1 - col][row]  
    for (int row = 0; row < originalHeight2; row++)
    {
      for (int col = 0; col < originalWidth2; col++)
      {
        Color pixelColor = rotatePixels2[row][col].getColor();
        rotated270Pixels[originalWidth2 - 1 - col][row].setColor(pixelColor);
      }
    }
    rotated270.explore();


    // final image - add a small image to a larger one
    /*
     * pseudocode:
     * 1. load a large image from lib folder
     * 2. load a small image from lib2 folder
     * 3. get 2d arrays of pixels from both images
     * 4. choose a position (startRow, startCol) in the large image to place the small image
     * 5. loop through each pixel in the small image:
     *    a. get the color of the small image pixel
     *    b. check if the pixel is white (or close to white) - if so, skip it (transparent background)
     *    c. if not white, copy the color to the corresponding position in the large image
     * 6. display the final combined image
     */
    
    // load large image from lib folder
    String largeImageFile = "lib/beach.jpg";
    Picture largeImg = new Picture(largeImageFile);
    Pixel[][] largePixels = largeImg.getPixels2D();
    
    // Load small image from lib2 folder
    String smallImageFile = "lib2/balloon.png";
    Picture smallImg = new Picture(smallImageFile);
    Pixel[][] smallPixels = smallImg.getPixels2D();
    
    // position to place small image (top-left corner of placement area)
    int startRow = 100;
    int startCol = 200;
    
    // add small image to large image, eliminating white background
    for (int row = 0; row < smallPixels.length && (startRow + row) < largePixels.length; row++)
    {
      for (int col = 0; col < smallPixels[0].length && (startCol + col) < largePixels[0].length; col++)
      {
        Color smallColor = smallPixels[row][col].getColor();
        int red = smallColor.getRed();
        int green = smallColor.getGreen();
        int blue = smallColor.getBlue();
        
        // check if pixel is white (or close to white) - threshold of 240 for each rgb component
        // if not white, copy the pixel to the large image
        if (!(red > 240 && green > 240 && blue > 240))
        {
          largePixels[startRow + row][startCol + col].setColor(smallColor);
        }
      }
    }
    largeImg.explore();




    // matrix multiplication demo
    System.out.println("\n=== Matrix Multiplication Demo ===");
    demonstrateMatrixMultiplication();
    
    // shape transformation demo
    System.out.println("\n=== Shape Transformation Demo ===");
    demonstrateShapeTransformations();
    
    // launch ui for interactive image manipulation
    System.out.println("\n=== Launching UI ===");
    System.out.println("You can also use the UI to interactively manipulate images!");
    javax.swing.SwingUtilities.invokeLater(() -> new ImageAppUI());
  }
  
  // demonstrates matrix multiplication with 2D arrays
  public static void demonstrateMatrixMultiplication()
  {
    // test matrices for matrix multiplication
    int[][] test1 = { { 1, 2, 3, 4 },
        { 5, 6, 7, 8 },
        { 9, 10, 11, 12 },
        { 13, 14, 15, 16 } };
    int[][] test2 = { { 1, 0, 0, 0 },
        { 0, 1, 0, 0 },
        { 0, 0, 1, 0 },
        { 0, 0, 0, 1 } };
    
    System.out.println("Matrix A (4x4):");
    printMatrix(test1);
    System.out.println("\nMatrix B (4x4 Identity):");
    printMatrix(test2);
    
    // perform matrix multiplication: C = A * B
    int[][] result = new int[4][4];
    for (int i = 0; i < 4; i++)
    {
      for (int j = 0; j < 4; j++)
      {
        int sum = 0;
        for (int k = 0; k < 4; k++)
        {
          sum += test1[i][k] * test2[k][j];
        }
        result[i][j] = sum;
      }
    }
    
    System.out.println("\nResult of A * B (Matrix Multiplication):");
    printMatrix(result);
    
    // demonstrate using Matrix class for transformations
    System.out.println("\n=== Using Matrix Class for Transformations ===");
    Matrix rot45 = Matrix.rotationMatrix(45);
    System.out.println("45° Rotation Matrix:");
    System.out.println(rot45);
    
    Matrix scale = Matrix.scalingMatrix(2.0, 2.0);
    System.out.println("2x Scaling Matrix:");
    System.out.println(scale);
    
    Matrix translate = Matrix.translationMatrix(10, 20);
    System.out.println("Translation Matrix (tx=10, ty=20):");
    System.out.println(translate);
    
    // combine transformations using matrix multiplication
    Matrix combined = translate.multiply(scale).multiply(rot45);
    System.out.println("Combined Transformation (Translate * Scale * Rotate):");
    System.out.println(combined);
    
    // transform a point
    double[] point = combined.transformPoint(5, 5);
    System.out.println("Point (5, 5) transformed: (" + 
      String.format("%.2f", point[0]) + ", " + 
      String.format("%.2f", point[1]) + ")");
  }
  
  // helper method to print a matrix
  public static void printMatrix(int[][] matrix)
  {
    for (int i = 0; i < matrix.length; i++)
    {
      System.out.print("[");
      for (int j = 0; j < matrix[i].length; j++)
      {
        System.out.print(String.format("%4d", matrix[i][j]));
        if (j < matrix[i].length - 1) System.out.print(", ");
      }
      System.out.println("]");
    }
  }
  
  // demonstrates shape transformations using matrix operations
  public static void demonstrateShapeTransformations()
  {
    // create a square shape (4 corners)
    double[][] squareCoords = {
      {0, 0},    // top-left
      {100, 0},  // top-right
      {100, 100}, // bottom-right
      {0, 100}   // bottom-left
    };
    
    Shape square = new Shape(squareCoords);
    System.out.println("Original Square:");
    System.out.println(square);
    
    // rotate 45 degrees
    Shape rotated = square.rotate(45);
    System.out.println("\nRotated 45°:");
    System.out.println(rotated);
    
    // scale 2x
    Shape scaled = square.scale(2.0, 2.0);
    System.out.println("\nScaled 2x:");
    System.out.println(scaled);
    
    // translate
    Shape translated = square.translate(50, 50);
    System.out.println("\nTranslated (50, 50):");
    System.out.println(translated);
    
    // combined transformation - rotate then translate
    Matrix rotMatrix = Matrix.rotationMatrix(90);
    Matrix transMatrix = Matrix.translationMatrix(100, 100);
    Matrix combined = transMatrix.multiply(rotMatrix);
    Shape transformed = square.transform(combined);
    System.out.println("\nCombined: Rotate 90° then Translate (100, 100):");
    System.out.println(transformed);
    
    // demonstrate dot product
    System.out.println("\n=== Vector Operations ===");
    double dot = Shape.dotProduct(1, 2, 3, 4);
    System.out.println("Dot product of (1,2) and (3,4): " + dot);
    
    double magnitude = Shape.vectorMagnitude(3, 4);
    System.out.println("Magnitude of vector (3, 4): " + magnitude);
  }
}
