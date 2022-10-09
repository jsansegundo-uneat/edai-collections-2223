package edai.practices.tema1;

import edai.practices.tema1.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {
    
    @Test
    void testCreateNoDimensions(){
        Matrix matrix = new Matrix();
        
        assertEquals(0, matrix.getRows());
        assertEquals(0, matrix.getColumns());
        assertFalse(matrix.isSquare());
    }

    @Test
    void testCreateWithInitialization(){
        Matrix matrix = new Matrix(2, 2, new int[]{1, 2, 3, 4});

        assertArrayEquals(new int[]{1, 2, 3, 4}, matrix.toArray());
    }

    @Test
    void testCreateWithDimensions(){
        Matrix matrix = new Matrix(2, 3);

        matrix.setRow(0, new int[]{1, 2, 3});
        matrix.setRow(1, new int[]{10, 20, 30});

        assertEquals(2, matrix.getRows());
        assertEquals(3, matrix.getColumns());
        assertArrayEquals(new int[]{1, 2, 3}, matrix.getRow(0));
        assertArrayEquals(new int[]{10, 20, 30}, matrix.getRow(1));
    }

    @Test
    void testIsSquare(){
        assertTrue(new Matrix(3, 3).isSquare());
    }

    @Test
    void testIsNotSquare(){
        assertFalse(new Matrix(3, 2).isSquare());
    }

    @Test
    void testSetValues(){
        Matrix matrix = new Matrix(2, 2);
        matrix.setValue(0, 0, 1);
        matrix.setValue(0, 1, 2);
        matrix.setValue(1, 0, 3);
        matrix.setValue(1, 1, 4);

        assertArrayEquals(new int[]{1, 2, 3, 4}, matrix.toArray());
    }

    @Test
    void testSetValueThrowsIndexOutOfBoundException(){
        Matrix matrix = new Matrix(); // No dims

        assertThrows(IndexOutOfBoundsException.class,
                () -> matrix.setValue(0, 0, 0));
    }

    @Test
    void testGetRowValues(){
        Matrix matrix = new Matrix(2, 2);

        matrix.setRow(0, new int[]{1, 2});
        matrix.setRow(1, new int[]{10, 20});

        assertEquals(1, matrix.getValue(0,0));
        assertEquals(2, matrix.getValue(0,1));
        assertEquals(10, matrix.getValue(1,0));
        assertEquals(20, matrix.getValue(1,1));
    }

    @Test
    void testGetValueThrowsIndexOutOfBoundException(){
        Matrix matrix = new Matrix(1, 1); // No dims

        assertThrows(IndexOutOfBoundsException.class,
                () -> matrix.getValue(1, 1));
    }

    @Test
    void testEquals(){
        Matrix m1 = new Matrix(2, 2);
        m1.setRow(0, new int[]{1, 1});
        m1.setRow(1, new int[]{2,2});

        Matrix m2 = new Matrix(2, 2);
        m2.setRow(0, new int[]{1, 1});
        m2.setRow(1, new int[]{2, 2});

        assertTrue(m1.equals(m2));
    }

    @Test
    void testNotEquals(){
        Matrix m1 = new Matrix(2, 2);
        m1.setRow(0, new int[]{1, 1});
        m1.setRow(1, new int[]{2,2});

        Matrix m2 = new Matrix(2, 2);
        m2.setRow(0, new int[]{0, 0});
        m2.setRow(1, new int[]{0, 0});

        assertFalse(m1.equals(m2));
    }

    @Test
    void testSumAnotherMatrix(){
        Matrix m1 = new Matrix(2, 2, new int[]{1, 1, 2, 2 });

        Matrix m2 = new Matrix(2, 2, new int[]{2, 2, 3, 3});

        Matrix expected = new Matrix(2, 2, new int[]{ 3, 3, 5, 5 });

        assertEquals(expected, m1.add(m2));
    }

    @Test
    void testAddThrowsExceptionIfDimensionsMismatch(){
        Matrix m1 = new Matrix(1, 1, new int[]{1, 1 });

        Matrix m2 = new Matrix(2, 2, new int[]{2, 2, 3, 3});

        assertThrows(IllegalArgumentException.class,
                () -> m1.add(m2));
    }

    @Test
    void testCreateIdentityMatrix(){
        Matrix identity = Matrix.identity(3);

        int[] expected = {
                1, 0, 0,
                0, 1, 0,
                0, 0, 1
        };

        assertArrayEquals(expected, identity.toArray());
    }
}
