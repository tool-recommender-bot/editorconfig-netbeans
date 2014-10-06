package org.editorconfig.netbeans.parser;

import java.net.URISyntaxException;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;
import static org.junit.Assert.*;

public class EditorConfigParserTest {

  private static final Logger LOG = Logger.getLogger(EditorConfigParserTest.class.getName());

  private final EditorConfigParser parser;

  private final String testFilePath = "org/editorconfig/example/editorconfig-test.ini";

  private final String[] sampleFiles = new String[]{
    "src/main/webapp/categories.xhtml",
    "src/main/webapp/resources/js/wlc/DocumentHandler.js",
    "src/main/webapp/resources/js/wlc/Rollbar.js",
    "src/main/webapp/resources/less/sidebar-widgets.less",
    "src/main/java/com/welovecoding/Debugger.java",
    "src/main/java/com/welovecoding/StringUtils.java"
  };

  public EditorConfigParserTest() {
    parser = new EditorConfigParser();
  }

  @Test
  public void testParseConfig() throws URISyntaxException, EditorConfigParserException {
//    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//    URL resource = classLoader.getResource(testFilePath);
//    Map<String, List<EditorConfigProperty>> config = parser.parseConfig(resource);
//
//    assertNotNull("it can find the test file", resource);
//    assertEquals("it parses the correct number of sections", config.size(), 5);
  }

  @Test
  public void testWildCardRegEx() {
    String regEx = "*";

    String file = "DocumentHandler";
    String jsFile = "src/main/webapp/resources/js/wlc/DocumentHandler.js";
    String pyFile = "src/main/webapp/resources/js/wlc/DocumentHandler.py";

    String javaRegEx = parser.convertRegEx(regEx);
    Pattern pattern = Pattern.compile(javaRegEx);

    Matcher fileMatch = pattern.matcher(file);
    Matcher jsMatch = pattern.matcher(jsFile);
    Matcher pyMatch = pattern.matcher(pyFile);

    assertEquals(fileMatch.matches(), true);
    assertEquals(jsMatch.matches(), true);
    assertEquals(pyMatch.matches(), true);
  }

  @Test
  public void testFileEndingRegEx() {
    String regEx = "*.js";
    String jsFile = "src/main/webapp/resources/js/wlc/DocumentHandler.js";
    String pyFile = "src/main/webapp/resources/js/wlc/DocumentHandler.py";

    String javaRegEx = parser.convertRegEx(regEx);
    Pattern pattern = Pattern.compile(javaRegEx);

    Matcher jsMatch = pattern.matcher(jsFile);
    Matcher pyMatch = pattern.matcher(pyFile);

    assertEquals(jsMatch.matches(), true);
    assertEquals(pyMatch.matches(), false);
  }

}