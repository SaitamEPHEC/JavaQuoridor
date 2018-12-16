package model;

public class Case {
	 private int X;
	 private int Y;
	 
	 public Case(int X, int Y) {
		 this.X = X;
		 this.Y = Y;
	 }
	 
	 public int getX() {
		 return X;
	 }
	 
	 public int getY() {
		 return Y;
	 }
	 
	 public boolean equals(Case other) {
	 if(this.X == other.getX() && this.Y == other.getY()) {return true;}
	 else {return false;}
	 }
	 
	 public String toString() {
		 return (X + ", " + Y);
	 }
	}
