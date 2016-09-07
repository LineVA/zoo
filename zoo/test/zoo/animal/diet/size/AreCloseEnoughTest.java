
package zoo.animal.diet.size;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import zoo.animal.feeding.Size;

/**
 *
 * @author doyenm
 */
public class AreCloseEnoughTest {
    @Test
    public void shouldReturnTrueWhenTheFirstOneIsMAndTheScondOneIsM(){
        // Given
        Size one = Size.M;
        int second = Size.M.getId();
        // When
        boolean result = one.isEnoughNearFrom(second);
        // Then
        assertTrue(result);
    }
    
     @Test
    public void shouldReturnTrueWhenTheFirstOneIsMAndTheScondOneIsL(){
        // Given
        Size one = Size.M;
        int second = Size.L.getId();
        // When
        boolean result = one.isEnoughNearFrom(second);
        // Then
        assertTrue(result);
    }
    
     @Test
    public void shouldReturnTrueWhenTheFirstOneIsMAndTheScondOneIsXL(){
        // Given
        Size one = Size.M;
        int second = Size.XL.getId();
        // When
        boolean result = one.isEnoughNearFrom(second);
        // Then
        assertTrue(result);
    }
    
     @Test
    public void shouldReturnFalseWhenTheFirstOneIsMAndTheScondOneIsXXL(){
        // Given
        Size one = Size.M;
        int second = Size.XXL.getId();
        // When
        boolean result = one.isEnoughNearFrom(second);
        // Then
        assertFalse(result);
    }
    
     @Test
    public void shouldReturnTrueWhenTheFirstOneIsMAndTheScondOneIsS(){
        // Given
        Size one = Size.M;
        int second = Size.S.getId();
        // When
        boolean result = one.isEnoughNearFrom(second);
        // Then
        assertTrue(result);
    }
    
     @Test
    public void shouldReturnTrueWhenTheFirstOneIsMAndTheScondOneIsXS(){
        // Given
        Size one = Size.M;
        int second = Size.XS.getId();
        // When
        boolean result = one.isEnoughNearFrom(second);
        // Then
        assertTrue(result);
    }
    
     @Test
    public void shouldReturnFalseWhenTheFirstOneIsMAndTheScondOneIsXXS(){
        // Given
        Size one = Size.M;
        int second = Size.XXS.getId();
        // When
        boolean result = one.isEnoughNearFrom(second);
        // Then
        assertFalse(result);
    }
    
       @Test
    public void shouldReturnTrueWhenTheFirstOneIsLAndTheScondOneIsM(){
        // Given
        Size one = Size.L;
        int second = Size.M.getId();
        // When
        boolean result = one.isEnoughNearFrom(second);
        // Then
        assertTrue(result);
    }
    
     @Test
    public void shouldReturnTrueWhenTheFirstOneIsXLAndTheScondOneIsM(){
        // Given
        Size one = Size.XL;
        int second = Size.M.getId();
        // When
        boolean result = one.isEnoughNearFrom(second);
        // Then
        assertTrue(result);
    }
    
     @Test
    public void shouldReturnFalseWhenTheFirstOneIsXXLAndTheScondOneIsM(){
        // Given
        Size one = Size.XXL;
        int second = Size.M.getId();
        // When
        boolean result = one.isEnoughNearFrom(second);
        // Then
        assertFalse(result);
    }
    
     @Test
    public void shouldReturnTrueWhenTheFirstOneIsSAndTheScondOneIsM(){
        // Given
        Size one = Size.S;
        int second = Size.M.getId();
        // When
        boolean result = one.isEnoughNearFrom(second);
        // Then
        assertTrue(result);
    }
    
     @Test
    public void shouldReturnTrueWhenTheFirstOneIsXSAndTheScondOneIsM(){
        // Given
        Size one = Size.XS;
        int second = Size.M.getId();
        // When
        boolean result = one.isEnoughNearFrom(second);
        // Then
        assertTrue(result);
    }
    
     @Test
    public void shouldReturnFalseWhenTheFirstOneIsXXSAndTheScondOneIsM(){
        // Given
        Size one = Size.XXS;
        int second = Size.M.getId();
        // When
        boolean result = one.isEnoughNearFrom(second);
        // Then
        assertFalse(result);
    }
}
