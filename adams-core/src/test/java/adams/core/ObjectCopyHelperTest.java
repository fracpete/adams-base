/*
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * ObjectCopyHelperTest.java
 * Copyright (C) 2017 University of Waikato, Hamilton, NZ
 */

package adams.core;

import adams.core.ObjectCopyHelper.CopyType;
import adams.env.Environment;
import adams.test.AdamsTestCase;
import junit.framework.Test;
import junit.framework.TestSuite;

import java.io.File;

/**
 * TODO: What class does.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class ObjectCopyHelperTest
  extends AdamsTestCase {

  /**
   * Constructs the test case. Called by subclasses.
   *
   * @param name the name of the test
   */
  public ObjectCopyHelperTest(String name) {
    super(name);
  }

  /**
   * Tests copying single objects.
   */
  public void testSingleObjects() {
    String path = ".";
    File f = new File(path);
    assertNotNull("shouldn't be null", ObjectCopyHelper.copyObject(CopyType.NEWINSTANCE, f));
    assertEquals("paths differ", path, ((File) ObjectCopyHelper.copyObject(CopyType.NEWINSTANCE, f)).getPath());

    Double d = 3.1415;
    assertNotNull("shouldn't be null", ObjectCopyHelper.copyObject(d));
    assertEquals("values differ", d, ObjectCopyHelper.copyObject(d));
  }

  /**
   * Tests copying of arays.
   */
  public void testArrays() {
    File[] f = new File[]{
      new File("."),
      new File("."),
    };
    assertNotNull("shouldn't be null", ObjectCopyHelper.copyObjects(CopyType.NEWINSTANCE, f));
    assertEqualsArrays("paths differ", f, ObjectCopyHelper.copyObjects(CopyType.NEWINSTANCE, f));

    Double[] d = {
      3.1415,
      1.2345,
    };
    assertNotNull("shouldn't be null", ObjectCopyHelper.copyObjects(d));
    assertEqualsArrays("values differ", d, ObjectCopyHelper.copyObjects(d));
  }

  /**
   * Returns a test suite.
   *
   * @return		the test suite
   */
  public static Test suite() {
    return new TestSuite(ObjectCopyHelperTest.class);
  }

  /**
   * Runs the test from commandline.
   *
   * @param args	ignored
   */
  public static void main(String[] args) {
    Environment.setEnvironmentClass(Environment.class);
    runTest(suite());
  }
}