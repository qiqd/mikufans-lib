package org.tetofans.util;

import org.junit.jupiter.api.Test;
import org.tetofans.enumeration.AcgType;

class HtmlParserTest {
  private final HtmlParser htmlParser = new HtmlParser();

  @Test
  void parseCoverUrl() throws Exception {
    String url = htmlParser.parseCoverUrl("租借女友", AcgType.TV);
    System.out.println(url);
  }
}