package org.tetofans.entity.baike;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageData {
  //  private Map<String, Object> extData;
  private Map<String, Object> abstractAlbum;
  private Map<String, Object> navigation;
//  private List<Object> albums;
}
