// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tr.edu.metu.ii.sm504.domain;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.metu.ii.sm504.domain.Assistant;
import tr.edu.metu.ii.sm504.domain.AssistantDataOnDemand;
import tr.edu.metu.ii.sm504.domain.AssistantIntegrationTest;

privileged aspect AssistantIntegrationTest_Roo_IntegrationTest {
    
    declare @type: AssistantIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: AssistantIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    declare @type: AssistantIntegrationTest: @Transactional;
    
    @Autowired
    private AssistantDataOnDemand AssistantIntegrationTest.dod;
    
    @Test
    public void AssistantIntegrationTest.testCountAssistants() {
        Assert.assertNotNull("Data on demand for 'Assistant' failed to initialize correctly", dod.getRandomAssistant());
        long count = Assistant.countAssistants();
        Assert.assertTrue("Counter for 'Assistant' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void AssistantIntegrationTest.testFindAssistant() {
        Assistant obj = dod.getRandomAssistant();
        Assert.assertNotNull("Data on demand for 'Assistant' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Assistant' failed to provide an identifier", id);
        obj = Assistant.findAssistant(id);
        Assert.assertNotNull("Find method for 'Assistant' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Assistant' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void AssistantIntegrationTest.testFindAllAssistants() {
        Assert.assertNotNull("Data on demand for 'Assistant' failed to initialize correctly", dod.getRandomAssistant());
        long count = Assistant.countAssistants();
        Assert.assertTrue("Too expensive to perform a find all test for 'Assistant', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Assistant> result = Assistant.findAllAssistants();
        Assert.assertNotNull("Find all method for 'Assistant' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Assistant' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void AssistantIntegrationTest.testFindAssistantEntries() {
        Assert.assertNotNull("Data on demand for 'Assistant' failed to initialize correctly", dod.getRandomAssistant());
        long count = Assistant.countAssistants();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Assistant> result = Assistant.findAssistantEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Assistant' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Assistant' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void AssistantIntegrationTest.testFlush() {
        Assistant obj = dod.getRandomAssistant();
        Assert.assertNotNull("Data on demand for 'Assistant' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Assistant' failed to provide an identifier", id);
        obj = Assistant.findAssistant(id);
        Assert.assertNotNull("Find method for 'Assistant' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyAssistant(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Assistant' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void AssistantIntegrationTest.testMergeUpdate() {
        Assistant obj = dod.getRandomAssistant();
        Assert.assertNotNull("Data on demand for 'Assistant' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Assistant' failed to provide an identifier", id);
        obj = Assistant.findAssistant(id);
        boolean modified =  dod.modifyAssistant(obj);
        Integer currentVersion = obj.getVersion();
        Assistant merged = (Assistant)obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Assistant' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void AssistantIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'Assistant' failed to initialize correctly", dod.getRandomAssistant());
        Assistant obj = dod.getNewTransientAssistant(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Assistant' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Assistant' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        Assert.assertNotNull("Expected 'Assistant' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void AssistantIntegrationTest.testRemove() {
        Assistant obj = dod.getRandomAssistant();
        Assert.assertNotNull("Data on demand for 'Assistant' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Assistant' failed to provide an identifier", id);
        obj = Assistant.findAssistant(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'Assistant' with identifier '" + id + "'", Assistant.findAssistant(id));
    }
    
}
