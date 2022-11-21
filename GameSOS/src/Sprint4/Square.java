package Sprint4;


public class Square implements Comparable<Square>{

	private SquareVal squareVal;
	
	
	
	
	
	//construct:
	public Square(SquareVal squareVal) {
        this.setValue(squareVal);
    }
	
	
	@Override
	public int compareTo(Square o) {
		// TODO Auto-generated method stub
		if (this.getValue() == o.getValue()) {
            return 0;
        } else {
            return 1;
        }
	}
	
	//setter: 
	public void setValue(SquareVal squareVal) {
		
        this.squareVal = squareVal;
    }
	
	//getter:
	public SquareVal getValue() {
		
        return this.squareVal;
    }
	
	
	//printing
	public String print() {
		
        switch (this.squareVal) {
        	//options:
            case O: return "O";
            case S: return "S";
            //default:
            default: return "None";
        }
    }
	
	//enumerate:
	public enum SquareVal {
		None, O, S 
	}

}
