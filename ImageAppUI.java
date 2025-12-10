import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class ImageAppUI extends JFrame
{
  private JComboBox<String> largeImageCombo;
  private JComboBox<String> smallImageCombo;
  private JComboBox<String> largeColorCombo;
  private JComboBox<String> smallColorCombo;
  private JSlider largeRotateSlider;
  private JSlider smallRotateSlider;
  private JSlider transparencySlider;
  private JTextField xPosField;
  private JTextField yPosField;
  private JButton processButton;
  private JLabel largeRotateLabel;
  private JLabel smallRotateLabel;
  private JLabel transparencyLabel;
  
  private String[] largeImages = {
    "arch.jpg", "beach.jpg", "bicycle.jpg", "bridge.jpg", "city-street.jpg",
    "dock-at-lake.jpg", "door.jpg", "field.jpg", "forest-path.jpg", "forest.jpg",
    "gorge.jpg", "koala.jpg", "moon-surface.jpg", "motorcycle.jpg", "picnic.jpg",
    "rainbow.jpg", "ruins.jpg", "sports-car.jpg", "taj-mahal.jpg", "temple.jpg"
  };
  
  private String[] smallImages = {
    "balloon.png", "bird.png", "butterfly-g61ce9fba8_1280.png", "cat.png", "dog.png",
    "fish.png", "flowers.png", "frog.png", "robot-g13e1b1d67_1280.png", "rocket.png"
  };
  
  private String[] colorOptions = {
    "None", "Swap Red/Blue", "Negative", "Grayscale", "Sepia"
  };
  
  public ImageAppUI()
  {
    setTitle("Image Manipulation App - Matrix Transformations");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    
    // create main panel
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
    // image selection panel
    JPanel imagePanel = new JPanel(new GridLayout(2, 2, 10, 10));
    imagePanel.setBorder(BorderFactory.createTitledBorder("Image Selection"));
    
    imagePanel.add(new JLabel("Large Image (lib):"));
    largeImageCombo = new JComboBox<>(largeImages);
    imagePanel.add(largeImageCombo);
    
    imagePanel.add(new JLabel("Small Image (lib2):"));
    smallImageCombo = new JComboBox<>(smallImages);
    imagePanel.add(smallImageCombo);
    
    // Color manipulation panel
    JPanel colorPanel = new JPanel(new GridLayout(2, 2, 10, 10));
    colorPanel.setBorder(BorderFactory.createTitledBorder("Color Manipulation"));
    
    colorPanel.add(new JLabel("Large Image Color:"));
    largeColorCombo = new JComboBox<>(colorOptions);
    colorPanel.add(largeColorCombo);
    
    colorPanel.add(new JLabel("Small Image Color:"));
    smallColorCombo = new JComboBox<>(colorOptions);
    colorPanel.add(smallColorCombo);
    
    // rotation panel (using matrix transformations)
    JPanel rotationPanel = new JPanel(new GridLayout(2, 3, 10, 10));
    rotationPanel.setBorder(BorderFactory.createTitledBorder("Matrix-Based Rotation"));
    
    rotationPanel.add(new JLabel("Large Image Rotation:"));
    largeRotateSlider = new JSlider(0, 360, 0);
    largeRotateSlider.setMajorTickSpacing(90);
    largeRotateSlider.setPaintTicks(true);
    largeRotateSlider.setPaintLabels(true);
    largeRotateLabel = new JLabel("0°");
    largeRotateSlider.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        largeRotateLabel.setText(largeRotateSlider.getValue() + "°");
      }
    });
    rotationPanel.add(largeRotateSlider);
    rotationPanel.add(largeRotateLabel);
    
    rotationPanel.add(new JLabel("Small Image Rotation:"));
    smallRotateSlider = new JSlider(0, 360, 0);
    smallRotateSlider.setMajorTickSpacing(90);
    smallRotateSlider.setPaintTicks(true);
    smallRotateSlider.setPaintLabels(true);
    smallRotateLabel = new JLabel("0°");
    smallRotateSlider.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        smallRotateLabel.setText(smallRotateSlider.getValue() + "°");
      }
    });
    rotationPanel.add(smallRotateSlider);
    rotationPanel.add(smallRotateLabel);
    
    // position and transparency panel
    JPanel positionPanel = new JPanel(new GridLayout(3, 2, 10, 10));
    positionPanel.setBorder(BorderFactory.createTitledBorder("Position & Transparency"));
    
    positionPanel.add(new JLabel("X Position:"));
    xPosField = new JTextField("200");
    positionPanel.add(xPosField);
    
    positionPanel.add(new JLabel("Y Position:"));
    yPosField = new JTextField("100");
    positionPanel.add(yPosField);
    
    positionPanel.add(new JLabel("Transparency:"));
    transparencySlider = new JSlider(0, 100, 50);
    transparencySlider.setMajorTickSpacing(25);
    transparencySlider.setPaintTicks(true);
    transparencySlider.setPaintLabels(true);
    transparencyLabel = new JLabel("50%");
    transparencySlider.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        transparencyLabel.setText(transparencySlider.getValue() + "%");
      }
    });
    positionPanel.add(transparencySlider);
    
    // add panels to main panel
    mainPanel.add(imagePanel);
    mainPanel.add(Box.createVerticalStrut(10));
    mainPanel.add(colorPanel);
    mainPanel.add(Box.createVerticalStrut(10));
    mainPanel.add(rotationPanel);
    mainPanel.add(Box.createVerticalStrut(10));
    mainPanel.add(positionPanel);
    mainPanel.add(Box.createVerticalStrut(20));
    
    // process button
    processButton = new JButton("Process Images");
    processButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
    processButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        processImages();
      }
    });
    mainPanel.add(processButton);
    
    add(mainPanel, BorderLayout.CENTER);
    
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }
  
  // process images based on user selections
  private void processImages()
  {
    try {
      // get selected images
      String largeImageName = (String) largeImageCombo.getSelectedItem();
      String smallImageName = (String) smallImageCombo.getSelectedItem();
      
      // load images
      Picture largeImg = new Picture("lib/" + largeImageName);
      Picture smallImg = new Picture("lib2/" + smallImageName);
      
      // apply color transformations
      String largeColor = (String) largeColorCombo.getSelectedItem();
      String smallColor = (String) smallColorCombo.getSelectedItem();
      
      applyColorTransformation(largeImg, largeColor);
      applyColorTransformation(smallImg, smallColor);
      
      // Apply matrix-based rotations
      int largeRotate = largeRotateSlider.getValue();
      int smallRotate = smallRotateSlider.getValue();
      
      if (largeRotate != 0) {
        largeImg = rotateImageMatrix(largeImg, largeRotate);
      }
      if (smallRotate != 0) {
        smallImg = rotateImageMatrix(smallImg, smallRotate);
      }
      
      // get position
      int xPos = Integer.parseInt(xPosField.getText());
      int yPos = Integer.parseInt(yPosField.getText());
      
      // get transparency (0-100, convert to alpha 0.0-1.0)
      double alpha = transparencySlider.getValue() / 100.0;
      
      // combine images with semi-transparent blending
      combineImagesSemiTransparent(largeImg, smallImg, xPos, yPos, alpha);
      
      // display result
      largeImg.explore();
      
      JOptionPane.showMessageDialog(this, 
        "Image processing complete!\n" +
        "Large image: " + largeImageName + "\n" +
        "Small image: " + smallImageName + "\n" +
        "Using matrix transformations for rotations.",
        "Success", JOptionPane.INFORMATION_MESSAGE);
        
    } catch (Exception e) {
      JOptionPane.showMessageDialog(this, 
        "Error processing images: " + e.getMessage(),
        "Error", JOptionPane.ERROR_MESSAGE);
      e.printStackTrace();
    }
  }
  
  // apply color transformation to image
  private void applyColorTransformation(Picture img, String transformation)
  {
    Pixel[][] pixels = img.getPixels2D();
    
    if (transformation.equals("None")) {
      return;
    }
    
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < pixels[0].length; col++)
      {
        Color pixelColor = pixels[row][col].getColor();
        int red = pixelColor.getRed();
        int green = pixelColor.getGreen();
        int blue = pixelColor.getBlue();
        Color newColor = null;
        
        switch (transformation) {
          case "Swap Red/Blue":
            newColor = new Color(blue, green, red);
            break;
          case "Negative":
            newColor = new Color(255 - red, 255 - green, 255 - blue);
            break;
          case "Grayscale":
            int gray = (red + green + blue) / 3;
            newColor = new Color(gray, gray, gray);
            break;
          case "Sepia":
            int tr = (int)(0.393 * red + 0.769 * green + 0.189 * blue);
            int tg = (int)(0.349 * red + 0.686 * green + 0.168 * blue);
            int tb = (int)(0.272 * red + 0.534 * green + 0.131 * blue);
            tr = Math.min(255, tr);
            tg = Math.min(255, tg);
            tb = Math.min(255, tb);
            newColor = new Color(tr, tg, tb);
            break;
        }
        
        if (newColor != null) {
          pixels[row][col].setColor(newColor);
        }
      }
    }
  }
  
  // rotate image using matrix transformations
  // uses Shape and Matrix classes for coordinate transformation
  private Picture rotateImageMatrix(Picture img, double angleDegrees)
  {
    Pixel[][] originalPixels = img.getPixels2D();
    int height = originalPixels.length;
    int width = originalPixels[0].length;
    
    // create rotation matrix
    Matrix rotationMatrix = Matrix.rotationMatrix(angleDegrees);
    
    // calculate center of image
    double centerX = width / 2.0;
    double centerY = height / 2.0;
    
    // create transformation: translate to origin, rotate, translate back
    Matrix translateToOrigin = Matrix.translationMatrix(-centerX, -centerY);
    Matrix translateBack = Matrix.translationMatrix(centerX, centerY);
    Matrix fullTransform = translateBack.multiply(rotationMatrix).multiply(translateToOrigin);
    
    // Calculate bounding box of rotated image
    double[][] corners = {
      {0, 0}, {width, 0}, {width, height}, {0, height}
    };
    
    double minX = Double.MAX_VALUE, minY = Double.MAX_VALUE;
    double maxX = Double.MIN_VALUE, maxY = Double.MIN_VALUE;
    
    for (double[] corner : corners) {
      double[] transformed = fullTransform.transformPoint(corner[0], corner[1]);
      minX = Math.min(minX, transformed[0]);
      minY = Math.min(minY, transformed[1]);
      maxX = Math.max(maxX, transformed[0]);
      maxY = Math.max(maxY, transformed[1]);
    }
    
    int newWidth = (int)Math.ceil(maxX - minX);
    int newHeight = (int)Math.ceil(maxY - minY);
    
    // create new picture
    Picture rotated = new Picture(newHeight, newWidth);
    Pixel[][] rotatedPixels = rotated.getPixels2D();
    
    // create inverse transformation to map back
    // for rotation, inverse is rotation by negative angle
    Matrix inverseRotation = Matrix.rotationMatrix(-angleDegrees);
    Matrix translateToOriginInv = Matrix.translationMatrix(-centerX, -centerY);
    Matrix translateBackInv = Matrix.translationMatrix(centerX, centerY);
    Matrix inverseTransform = translateBackInv.multiply(inverseRotation).multiply(translateToOriginInv);
    
    // fill rotated image
    for (int row = 0; row < newHeight; row++)
    {
      for (int col = 0; col < newWidth; col++)
      {
        // map this position back to original image
        double[] originalPos = inverseTransform.transformPoint(col, row);
        int origCol = (int)Math.round(originalPos[0]);
        int origRow = (int)Math.round(originalPos[1]);
        
        if (origRow >= 0 && origRow < height && origCol >= 0 && origCol < width)
        {
          Color color = originalPixels[origRow][origCol].getColor();
          rotatedPixels[row][col].setColor(color);
        }
      }
    }
    
    return rotated;
  }
  
  // combine images with semi-transparent blending
  // uses alpha blending: result = (1-alpha) * background + alpha * foreground
  private void combineImagesSemiTransparent(Picture largeImg, Picture smallImg, 
                                           int xPos, int yPos, double alpha)
  {
    Pixel[][] largePixels = largeImg.getPixels2D();
    Pixel[][] smallPixels = smallImg.getPixels2D();
    
    for (int row = 0; row < smallPixels.length && (yPos + row) < largePixels.length; row++)
    {
      for (int col = 0; col < smallPixels[0].length && (xPos + col) < largePixels[0].length; col++)
      {
        if (yPos + row >= 0 && xPos + col >= 0)
        {
          Color smallColor = smallPixels[row][col].getColor();
          Color largeColor = largePixels[yPos + row][xPos + col].getColor();
          
          // check if small pixel is white/transparent (skip white background)
          int red = smallColor.getRed();
          int green = smallColor.getGreen();
          int blue = smallColor.getBlue();
          
          if (!(red > 240 && green > 240 && blue > 240))
          {
            // alpha blending: result = (1-alpha) * background + alpha * foreground
            int blendedRed = (int)((1 - alpha) * largeColor.getRed() + alpha * red);
            int blendedGreen = (int)((1 - alpha) * largeColor.getGreen() + alpha * green);
            int blendedBlue = (int)((1 - alpha) * largeColor.getBlue() + alpha * blue);
            
            // clamp values to 0-255
            blendedRed = Math.max(0, Math.min(255, blendedRed));
            blendedGreen = Math.max(0, Math.min(255, blendedGreen));
            blendedBlue = Math.max(0, Math.min(255, blendedBlue));
            
            largePixels[yPos + row][xPos + col].setColor(
              new Color(blendedRed, blendedGreen, blendedBlue));
          }
        }
      }
    }
  }
  
  public static void main(String[] args)
  {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new ImageAppUI();
      }
    });
  }
}

