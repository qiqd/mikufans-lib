package org.mikufans.entity.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RatingInfo {
  private String rating;
  private String count;
  private String oneStar;
  private String twoStar;
  private String threeStar;
  private String fourStar;
  private String fiveStar;
}
