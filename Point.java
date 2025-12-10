public class Point
{
    private int row;
    private int col;
    
    // Constructor for point [newRow,newCol]
    public Point (int newRow, int newCol)
    {
        row = newRow;
        col = newCol;
    }
    
    // accessor for row
    public int getRow()
    {
        return row;
    }

    // Accessor for col
    public int getCol()
    {
        return col;
    }   
    
    // mutator for row
    public void setRow(int newRow)
    {
        row = newRow;
    }

    // Mutator for col
    public void setCol(int newCol)
    {
        col = newCol;
    }
    
    // returns a string with info about this point
    public String toString()
    {
        return "row: " + row + " col: " + col;
    }
}