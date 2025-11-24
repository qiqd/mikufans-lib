package org.mikufans.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TitleResponse {
  private String id;
  private String title;
  private String titleCn;
  private String titleEn;
  private LocalDate releaseDate;
}
