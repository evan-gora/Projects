import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AppTest {

    App a = new App ("Alpha", "Test App", "AppVault", "Game", "All", "1.0", 9.99, "www.testing.com", "Hello");
    App b = new App ("Beta", "Test App", "AppVault", "Game", "All", "1.0", 9.99, "www.testing.com", "Hello");
    
    
   
    @Test
    void testAdd() {
        String testComment = "Test Comment";
        assertTrue(a.addComment(testComment, true));
        assertFalse(a.addComment(testComment, false));
    }
    
    @Test
    void testDelete() {
        b.addComment("Another Comment", true);
        assertTrue(a.deleteComment("Hello"));
        assertFalse(a.deleteComment("Hello"));
        assertTrue(b.deleteComment("Another Comment"));
    }
}
