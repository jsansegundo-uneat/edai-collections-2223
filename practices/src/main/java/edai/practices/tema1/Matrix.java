package edai.practices.tema1;

import java.util.Arrays;

public class Matrix {

    int[] innerMatrix;
    int rowCount;
    int columnCount;


    public Matrix(){
        rowCount = 0;
        columnCount = 0;
        innerMatrix = new int[0];
    }

    public Matrix(int rows, int columns, int[] initialValues) {
        innerMatrix = new int[ rows *columns ];
        rowCount = rows;
        columnCount = columns;

        int maxItemsCount = Math.min(innerMatrix.length, initialValues.length);

        for(int i = 0; i < maxItemsCount; ++i){
            innerMatrix[i] = initialValues[i];
        }
    }

    public Matrix(int rows, int columns) {
        innerMatrix = new int[ rows *columns ];
        rowCount = rows;
        columnCount = columns;
    }

    public static Matrix identity(int dim) {
        var output = new Matrix(dim, dim);

        for(int i = 0; i < dim; ++i){
            output.setValue(i, i, 1);
        }

        return output;
    }

    public int getRows() {
        return rowCount;
    }

    public int getColumns() {
        return columnCount;
    }

    public boolean isSquare() {
        return  rowCount == columnCount && rowCount != 0;
    }

    public int[] toArray() {
        int[] output = new int[innerMatrix.length];

        for(int i = 0; i < innerMatrix.length; ++i){
            output[i] = innerMatrix[i];
        }

        return output;
    }

    public void setRow(int row, int[] rowValues) {
        int rowIndex = indexOfRow(row);

        int maxLength = Math.min(rowValues.length, this.columnCount);

        int k = 0;
        for(int i = rowIndex; i < rowIndex+maxLength; ++i, ++k){
            innerMatrix[i] = rowValues[k];
        }

    }

    public int[] getRow(int row) {
        int rowIndex = indexOfRow(row);

        int[] output = new int[this.columnCount];

        for(int i = 0; i < this.columnCount; ++i){
            output[i] = innerMatrix[rowIndex + i];
        }

        return output;
    }

    private int indexOfRow(int row){
        return row * this.columnCount;
    }

    public void setValue(int row, int column, int value) {
        int arrayIndex = indexOfPosition(row, column);
        innerMatrix[arrayIndex] = value;
    }

    private int indexOfPosition(int row, int column){
        return indexOfRow(row) +  column;
    }

    public int getValue(int row, int column) {
        return innerMatrix[ indexOfPosition(row, column) ];
    }

    public Matrix add(Matrix other) {
        throwIfNotSameDimensions(other);

        int[] output = new int[ this.rowCount * this.columnCount ];

        for(int i = 0; i < innerMatrix.length; ++i){
            output[i] = innerMatrix[i] + other.innerMatrix[i];
        }

        return new Matrix(this.rowCount, this.columnCount, output);
    }

    private void throwIfNotSameDimensions(Matrix other) {
        if(columnCount != other.columnCount || rowCount != other.rowCount){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if( !(obj instanceof Matrix) ) return false;

        Matrix otherMatrix = (Matrix) obj;

        return this.rowCount == otherMatrix.rowCount
                && this.columnCount == otherMatrix.columnCount
                && Arrays.equals(this.innerMatrix, otherMatrix.innerMatrix);

    }
}
