package tr.edu.metu.ii.sm504.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.test.RooIntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.metu.ii.sm504.service.PermissionService;

import java.util.List;

@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
@Transactional
public class PermissionIntegrationTest {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PermissionDataOnDemand dod;

    @Test
    public void testCountPermissions() {
        Assert.assertNotNull("Data on demand for 'Permission' failed to initialize correctly", dod.getRandomPermission());
        long count = permissionService.countPermissions();
        Assert.assertTrue("Counter for 'Permission' incorrectly reported there were no entries", count > 0);
    }

    @Test
    public void testFindPermission() {
        Permission obj = dod.getRandomPermission();
        Assert.assertNotNull("Data on demand for 'Permission' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Permission' failed to provide an identifier", id);
        obj = permissionService.findPermission(id);
        Assert.assertNotNull("Find method for 'Permission' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Permission' returned the incorrect identifier", id, obj.getId());
    }

    @Test
    public void testFindAllPermissions() {
        Assert.assertNotNull("Data on demand for 'Permission' failed to initialize correctly", dod.getRandomPermission());
        long count = permissionService.countPermissions();
        Assert.assertTrue("Too expensive to perform a find all test for 'Permission', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Permission> result = permissionService.findAllPermissions();
        Assert.assertNotNull("Find all method for 'Permission' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Permission' failed to return any data", result.size() > 0);
    }

    @Test
    public void testFindPermissionEntries() {
        Assert.assertNotNull("Data on demand for 'Permission' failed to initialize correctly", dod.getRandomPermission());
        long count = permissionService.countPermissions();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Permission> result = permissionService.findPermissionEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Permission' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Permission' returned an incorrect number of entries", count, result.size());
    }

    @Test
    public void testFlush() {
        Permission obj = dod.getRandomPermission();
        Assert.assertNotNull("Data on demand for 'Permission' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Permission' failed to provide an identifier", id);
        obj = permissionService.findPermission(id);
        Assert.assertNotNull("Find method for 'Permission' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyPermission(obj);
        Integer currentVersion = obj.getVersion();
        permissionService.flush(obj);
        Assert.assertTrue("Version for 'Permission' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

    @Test
    public void testMergeUpdate() {
        Permission obj = dod.getRandomPermission();
        Assert.assertNotNull("Data on demand for 'Permission' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Permission' failed to provide an identifier", id);
        obj = permissionService.findPermission(id);
        boolean modified =  dod.modifyPermission(obj);
        Integer currentVersion = obj.getVersion();
        Permission merged = (Permission)permissionService.merge(obj);
        permissionService.flush(obj);
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Permission' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

    @Test
    public void testPersist() {
        Assert.assertNotNull("Data on demand for 'Permission' failed to initialize correctly", dod.getRandomPermission());
        Permission obj = dod.getNewTransientPermission(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Permission' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Permission' identifier to be null", obj.getId());
        permissionService.persist(obj);
        permissionService.flush(obj);
        Assert.assertNotNull("Expected 'Permission' identifier to no longer be null", obj.getId());
    }

    @Test
    public void testRemove() {
        Permission obj = dod.getRandomPermission();
        Assert.assertNotNull("Data on demand for 'Permission' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Permission' failed to provide an identifier", id);
        obj = permissionService.findPermission(id);
        permissionService.remove(obj);
        permissionService.flush(obj);
        Assert.assertNull("Failed to remove 'Permission' with identifier '" + id + "'", permissionService.findPermission(id));
    }

    @Test
    public void testMarkerMethod() {
    }
}
