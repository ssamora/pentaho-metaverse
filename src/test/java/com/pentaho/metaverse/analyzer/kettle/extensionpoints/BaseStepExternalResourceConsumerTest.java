package com.pentaho.metaverse.analyzer.kettle.extensionpoints;

import com.pentaho.metaverse.api.model.IExternalResourceInfo;
import org.apache.commons.vfs.FileName;
import org.apache.commons.vfs.FileObject;
import org.junit.Before;
import org.junit.Test;
import org.pentaho.di.trans.step.StepMeta;

import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseStepExternalResourceConsumerTest {

  BaseStepExternalResourceConsumer consumer;

  @Before
  public void setUp() throws Exception {
    consumer = new BaseStepExternalResourceConsumer() {
      @Override
      public Class getStepMetaClass() {
        return null;
      }
    };
  }

  @Test
  public void testIsDataDriven() throws Exception {
    assertFalse( consumer.isDataDriven( null ) );
  }

  @Test
  public void testGetResourcesFromMeta() throws Exception {
    Collection<IExternalResourceConsumer> resources = consumer.getResourcesFromMeta( null );
    assertNotNull( resources );
    assertTrue( resources.isEmpty() );
  }

  @Test
  public void testGetResourcesFromRow() throws Exception {
    Collection<IExternalResourceConsumer> resources = consumer.getResourcesFromRow( null, null, null );
    assertNotNull( resources );
    assertTrue( resources.isEmpty() );
  }

  @Test
  public void testGetFileResource() {
    assertNull( consumer.getFileResource( null ) );
    FileObject mockFile = mock( FileObject.class );
    FileName mockFilename = mock( FileName.class );
    when( mockFilename.getPath() ).thenReturn( "/path/to/file" );
    when( mockFile.getName() ).thenReturn( mockFilename );
    IExternalResourceInfo resource = consumer.getFileResource( mockFile );
    assertNotNull( resource );
    assertEquals( "/path/to/file", resource.getName() );
  }
}