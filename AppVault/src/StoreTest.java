import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class StoreTest {

    Store store1 = new Store();
    App a = new App("Alpha", "Test App", "AppVault", "Game", "All", "1.0", 9.99,
            "www.testing.com", "Hello");
    App b = new App("Beta", "Test App", "AppVault", "Social Media", "All",
            "1.0", 0.99, "www.testing.com", "Second Test App");
    App c = new App("Three", "Test App", "AppVault", "Game", "All", "1.0", 1.99,
            "www.testing.com", "Another Test App");

    Request req1 = new Request("www.request1.com", "request object for test");
    Request req2 = new Request("www.request2.com", "request object for test");;
    Request req3 = new Request("www.request3.com", "request object for test");

    @Test
    void testFileLoad() {
        store1.loadFromFile("store.txt");
        // Makse sure all objects from the text file have been loaded.
        assertEquals(store1.searchApp("TestApp"), store1.getApps().get(0));
        assertEquals(store1.getRequests().get(0).getDescription(),
                "Description");
        assertEquals(store1.getUsers().get(0).getUsername(), "dc77");
    }

    @Test
    void testAddReq() {
        assertTrue(store1.addRequest(req1));
        assertTrue(store1.addRequest(req2));
        assertFalse(store1.addRequest(req1));
    }

    @Test
    void testRemoveReq() {
        store1.addRequest(req1);
        store1.addRequest(req2);
        assertTrue(store1.removeRequest(req1));
        assertFalse(store1.removeRequest(req1));
        assertFalse(store1.removeRequest(req3));
    }

    @Test
    void testAddApp() {
        assertTrue(store1.addApp(a));
        assertFalse(store1.addApp(a));
    }

    @Test
    void testSearchApp() {
        store1.addApp(a);
        assertEquals(store1.searchApp("Alpha"), a);
        assertEquals(store1.searchApp("fail"), null);
    }

    @Test
    void testAddUser() {
        User testUser = new User("egora15", "password", true, true, true);
        assertTrue(store1.addUser(testUser));
        assertFalse(store1.addUser(testUser));
    }

    @Test
    void testGetApps() {
        store1.addApp(a);
        ArrayList<App> testAppList = new ArrayList<App>();
        testAppList.add(a);

        assertEquals(store1.getApps(), testAppList);
    }

    @Test
    void testGetReqs() {
        store1.addRequest(req1);
        store1.addRequest(req2);
        ArrayList<Request> reqs1 = new ArrayList<Request>();
        reqs1.add(req1);
        reqs1.add(req2);
        ArrayList<Request> reqs2 = new ArrayList<Request>();
        reqs2.add(req1);

        assertEquals(store1.getRequests(), reqs1);
        assertNotEquals(store1.getRequests(), reqs2);
    }

    @Test
    void testCheckCredentials() {
        store1.loadFromFile("store.txt");
        assertTrue(store1.checkCredentials("dc77", "password12"));
        assertFalse(store1.checkCredentials("dc77", "password21"));
    }
}
