/* 
  This class represents a coordinate on the x-axis and 
  provides functionality to retrieve the x-coordinate and 
  corresponding column.

  BUGS--------------------------------------------------------------------------
  - None at the moment
  ------------------------------------------------------------------------------

 * Timi Aina
 * January 1, 2024
 */

//IMPORTS-----------------------------------------------------------------------
// None at the moment
//------------------------------------------------------------------------------

public class XCoord {

    //Data Field----------------------------------------------------------------    
    private int x;
    private int column;
    //--------------------------------------------------------------------------

    //Constructors--------------------------------------------------------------
    /**
     * Constructs an XCoord object with a specified x-coordinate.
     * The column is derived as (x - 1) to follow zero-based indexing.
     * 
     * @param x - the x-coordinate point (1-based index)
     */
    public XCoord(int x){
        this.x = x;
        this.column = x - 1;     
    }  
    
    /**
     * Constructs an XCoord object with the default x-coordinate of 1.
     * The column is set to 0.
     */
    public XCoord(){
        this(1);
    }
    //--------------------------------------------------------------------------
    
    //Getters-------------------------------------------------------------------
    /**
     * Returns the x-coordinate value.
     * 
     * @return the x-coordinate
     */
    public int getX(){
        return x;
    }
    
    /**
     * Returns the column value.
     * 
     * @return the column 
     */
    public int getCol(){
        return column;
    }
    //--------------------------------------------------------------------------
    
    //toString------------------------------------------------------------------
    /**
     * Returns a string representation of the XCoord object.
     * The string includes the column index.
     * 
     * @return a string in the format "Col: column"
     */
    @Override
    public String toString(){
        return "Col: " + column;
    }
    //--------------------------------------------------------------------------
}//XCoord
