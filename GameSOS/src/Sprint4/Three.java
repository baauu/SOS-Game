package Sprint4;

public class Three implements Comparable<Three>{

	public final Set one;
	public final Set two;
	public final Set three;
	
	
	//constructor
	public Three(Set one, Set two, Set three) {
		this.one = one; 
		this.two = two; 
		this.three = three;
	}
	
	@Override
	public int compareTo(Three o) {
		// TODO Auto-generated method stub
		if (this.one.compareTo(o.one) == 0 
				&& this.two.compareTo(o.two) == 0 
				&& this.three.compareTo(o.three) == 0) {
            return 0;
        } else {
            return -1;
        }
	}
	
	public String print() {
		return String.format("[%s, %s, %s]", this.one, this.two, this.three);
	}

}
