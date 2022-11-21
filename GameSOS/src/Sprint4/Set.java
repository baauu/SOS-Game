package Sprint4;

public class Set implements Comparable<Set>{
	//variables
	public final int one;
	public final int two;
	
	//constructor
	public Set(int one, int two) {
		this.one = one; 
		this.two = two;
	}
	
	@Override
	public int compareTo(Set o) {
		// TODO Auto-generated method stub
		if (this.one == o.one 
				&& this.two == o.two) {
			return 0;
		} else {
			return -1;
		}
	}
	
	public String print() {
		return String.format("(%s, %s)", this.one, this.two);
	}
	
	
}
