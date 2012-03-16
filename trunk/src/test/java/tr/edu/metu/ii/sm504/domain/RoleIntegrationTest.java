package tr.edu.metu.ii.sm504.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.metu.ii.sm504.repository.RoleRepository;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
@Transactional
@Configurable
public class RoleIntegrationTest {
    @Autowired
    private RoleDataOnDemand dod;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testCountRoles() {
        Assert.assertNotNull("Data on demand for 'Role' failed to initialize correctly", dod.getRandomRole());
        long count = roleRepository.countRoles();
        Assert.assertTrue("Counter for 'Role' incorrectly reported there were no entries", count > 0);
    }

    @Test
    public void testFindRole() {
        Role obj = dod.getRandomRole();
        Assert.assertNotNull("Data on demand for 'Role' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Role' failed to provide an identifier", id);
        obj = roleRepository.findRole(id);
        Assert.assertNotNull("Find method for 'Role' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Role' returned the incorrect identifier", id, obj.getId());
    }

    @Test
    public void testFindAllRoles() {
        Assert.assertNotNull("Data on demand for 'Role' failed to initialize correctly", dod.getRandomRole());
        long count = roleRepository.countRoles();
        Assert.assertTrue("Too expensive to perform a find all test for 'Role', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Role> result = roleRepository.findAllRoles();
        Assert.assertNotNull("Find all method for 'Role' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Role' failed to return any data", result.size() > 0);
    }

    @Test
    public void testFindRoleEntries() {
        Assert.assertNotNull("Data on demand for 'Role' failed to initialize correctly", dod.getRandomRole());
        long count = roleRepository.countRoles();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Role> result = roleRepository.findRoleEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Role' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Role' returned an incorrect number of entries", count, result.size());
    }

    @Test
    public void testFlush() {
        Role obj = dod.getRandomRole();
        Assert.assertNotNull("Data on demand for 'Role' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Role' failed to provide an identifier", id);
        obj = roleRepository.findRole(id);
        Assert.assertNotNull("Find method for 'Role' illegally returned null for id '" + id + "'", obj);
        boolean modified = dod.modifyRole(obj);
        Integer currentVersion = obj.getVersion();
        roleRepository.flush(obj);
        Assert.assertTrue("Version for 'Role' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

    @Test
    public void testMergeUpdate() {
        Role obj = dod.getRandomRole();
        Assert.assertNotNull("Data on demand for 'Role' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Role' failed to provide an identifier", id);
        obj = roleRepository.findRole(id);
        boolean modified =  dod.modifyRole(obj);
        Integer currentVersion = obj.getVersion();
        Role merged = (Role) roleRepository.merge(obj);
        roleRepository.flush(obj);
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Role' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

    @Test
    public void testPersist() {
        Assert.assertNotNull("Data on demand for 'Role' failed to initialize correctly", dod.getRandomRole());
        Role obj = dod.getNewTransientRole(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Role' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Role' identifier to be null", obj.getId());
        roleRepository.persist(obj);
        roleRepository.flush(obj);
        Assert.assertNotNull("Expected 'Role' identifier to no longer be null", obj.getId());
    }

    @Test
    public void testRemove() {
        Role obj = dod.getRandomRole();
        Assert.assertNotNull("Data on demand for 'Role' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Role' failed to provide an identifier", id);
        obj = roleRepository.findRole(id);
        roleRepository.remove(obj);
        roleRepository.flush(obj);
        Assert.assertNull("Failed to remove 'Role' with identifier '" + id + "'", roleRepository.findRole(id));
    }

    @Test
    public void testMarkerMethod() {
    }
}
