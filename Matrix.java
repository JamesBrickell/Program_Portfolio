package assignment3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Matrix class builds an arbitrary sized matrix with either data 
 * or given a text file. This class allows you to add matrices, multiply 
 * multiply by a scalar, compare  and transpose a matrix. Every data point needs
 * to be of a type double for this class to work. The properties of the matrix:
 * int row, int colm, and the double nested array the matrix itself.
 * @author Jbri1
 *
 */
public class Matrix {
	//properties
	private int row;
	private int colm;
	private double[][] matrix;
	
	//behaviors
	
	/**
	 * A constructor that builds a matrix by using 
	 * a nested for loop to iterate through the data and setting
	 * the matrix equal to the point in the data.
	 * @param data double nested array that contains the data you want in the array.
	 */
	public Matrix(double[][] data) {
		this.row = data.length;
		this.colm = data[0].length;
		matrix = new double[row][colm];
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < colm; j++) {
				matrix[i][j] = data[i][j];
			}
		}
	}
	
	/**
	 * This constructor builds an empty 
	 * matrix with the desired dimensions.
	 * @param row int number of rows the user wants.
	 * @param colm int number of colms the user wants.
	 */
	public Matrix(int row, int colm) {
		this.row = row;
		this.colm = colm;
		matrix = new double[row][colm];
	}
	
	/**
	 * Builds a matrix with data from a text file
	 * however the data must be structured in the same way the 
	 * matrix would be structured.
	 * 
	 * @param fileName the name of the .txt file being passed.
	 */
	public Matrix(String fileName) {
		ArrayList<String> lines = new ArrayList<>();
		ArrayList<String[]> dataPoint = new ArrayList<>();
		ArrayList<Double> values = new ArrayList<>();
		
		Scanner s = null;
		File infile = new File(fileName);
		
		try {
			s = new Scanner(infile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (s.hasNextLine()) {
			lines.add(s.nextLine());
		}
		for (String ln : lines) {
			String[] data = ln.split("\\s+");
			dataPoint.add(data);
			for (String dv : data) {
				values.add(Double.valueOf(dv.trim()));
			}

		}
		
		row = dataPoint.size();
		colm = dataPoint.get(0).length;
		matrix = new double[row][colm];
		int count = 0;
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < colm; j++) {
				this.matrix[i][j] = values.get(count);
				count++;
			}
		}

	}
	
	/**
	 * overloads the toString method to print
	 * the matrix properly.
	 * @return returns the string version of the matrix.
	 */
	public String toString() {
		String out = "";
		
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.colm; j++) {
				
				if (j == this.colm - 1) {
					out += this.matrix[i][j] + "\n";
				} 
				
				else {
					out += this.matrix[i][j] + " ";
				}
			}
		}
		
		return out;

	}
	
	/**
	 * reverses the dimensions of the matrix:
	 * i.e takes a 2*1 matrix and makes it
	 * a 1*2 this also results in the data being
	 * moved.
	 * @return the new matrix with the swapped dimensions
	 */
	public Matrix transpose() {
		int newRow = this.colm;
		int newColm = this.row;
		
		Matrix newMatrix = new Matrix(newRow, newColm);
		
		for (int i = 0; i < newRow; i++) {
			for (int j = 0; j < newColm; j++) {
				double newData = this.matrix[j][i];
				newMatrix.matrix[i][j] = newData;
			}
		}
		
		return newMatrix;
	}
	
	/**
	 * adds to matrices together and returns a newMatrix 
	 * that is the sum, this methods also checks to make sure that 
	 * the dimensions for the matrix are equal as dictated by 
	 * matrix arithmetic.
	 * @param other the other matrix
	 * @return returns a matrix with the sum of the other two matrices.
	 */
	public Matrix add(Matrix other) {
		if (this.row != other.row || this.colm != other.colm) {
			System.out.println("invalid operation");
		} // end of if.
		
		Matrix newMatrix = new Matrix(this.row, this.colm);
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < colm; j++) {
				double sum = this.matrix[i][j] + other.matrix[i][j];
				newMatrix.matrix[i][j] = sum;
			}
		}
		
		return (Matrix) newMatrix;
	}
	
	/**
	 * Multiplies to matrices together and returns a new
	 * matrix with the dimensions this.row *this.colm;
	 * this method also ensures the operation is legal
	 * by checking that this.colm is equal to other.row.
	 * @param other the matrix wanting to be multiplied.
	 * @return the matrix with the product of the other two.
	 */
	public Matrix mult(Matrix other) {
		if (this.colm != other.row) {
			System.out.println("This will not work");
		}
		Matrix newMatrix = new Matrix(this.row, other.colm);
		for (int i = 0; i < newMatrix.row; i++) {
			for (int j = 0; j < newMatrix.colm; j++) {
				for (int k = 0; k < other.row; k++) {
					newMatrix.matrix[i][j] += this.matrix[i][k] * other.matrix[k][j];
				}
			}
		}
		return newMatrix;
	}
	
	/**
	 * goes through a matrix and multiplies each 
	 * value by x and returns a new matrix with the same 
	 * dimensions with the new values.
	 * @param x the value wanted to scale by.
	 * @return the new scaled matrix.
	 */
	public Matrix mult(double x) {
		Matrix newMatrix = new Matrix(this.row, this.colm);
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.colm; j++) {
				newMatrix.matrix[i][j] = this.matrix[i][j] * x;
			}
		}
		return newMatrix;
	}
	
	/**
	 * compares two  matrices and makes sure
	 * each value is equal to one another. 
	 * @param other the other matrix.
	 * @return the boolean false if they don't match and true if they do.
	 */
	public boolean equals(Matrix other) {
		if (this.row != other.row||this.colm != other.colm) {
			return false;
		} 
		else {
			for (int i = 0; i < this.row; i++) {
				for (int j = 0; j < this.colm; j++) {
					if (this.matrix[i][j] != other.matrix[i][j]) {
						return false;
					}
				}
			}
		}
		return true;
	}

}
