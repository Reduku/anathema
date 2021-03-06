package net.sf.anathema.character.test.generic.magic.charms.duration;

import net.sf.anathema.character.generic.magic.charms.duration.IDuration;
import net.sf.anathema.character.generic.magic.charms.duration.SimpleDuration;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SimpleDurationTest {

  @Test
  public void testInstantDuration() throws Exception {
    IDuration duration = SimpleDuration.getDuration("Instant");
    assertEquals(SimpleDuration.INSTANT_DURATION, duration);
  }

  @Test
  public void testOtherDuration() throws Exception {
    SimpleDuration duration = SimpleDuration.getDuration("OtherDuration");
    assertFalse(SimpleDuration.INSTANT_DURATION == duration);
    assertFalse(SimpleDuration.PERMANENT_DURATION == duration);
    assertEquals("OtherDuration", duration.getText());
  }

  @Test
  public void testPermanentDuration() throws Exception {
    IDuration duration = SimpleDuration.getDuration("Permanent");
    assertEquals(SimpleDuration.PERMANENT_DURATION, duration);
  }
}