package org.mikufans.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 游戏作品表实体类
 * 包含所有游戏的完整信息
 */
@Schema(description = "游戏作品信息")
@Data
@EqualsAndHashCode(callSuper = true)
public class Game extends BaseWork {
  /**
   * 开发公司
   */
  @Schema(description = "开发公司", example = "FromSoftware")
  private String developer;

  /**
   * 发行公司
   */
  @Schema(description = "发行公司", example = "Bandai Namco")
  private String publisher;

  /**
   * 支持平台，存储为 JSON 字符串
   */
  @Schema(description = "支持平台", example = "PC,PS5,Xbox")
  private List<String> platforms;

  /**
   * 使用的游戏引擎（如 Unity, Unreal Engine）
   */
  @Schema(description = "游戏引擎", example = "Unreal Engine 5")
  private String gameEngine;

  /**
   * 游戏模式：SINGLE_PLAYER, MULTI_PLAYER, ONLINE
   */
  @Schema(description = "游戏模式", example = "ONLINE")
  private String gameMode;

  /**
   * 视角：FIRST_PERSON, THIRD_PERSON, TOP_DOWN
   */
  @Schema(description = "游戏视角", example = "THIRD_PERSON")
  private String perspective;

  /**
   * 安装包大小（MB）
   */
  @Schema(description = "安装包大小（MB）", example = "5000")
  private Long fileSizeMb;

  /**
   * 是否免费游玩
   */
  @Schema(description = "是否免费游玩", example = "false")
  private Boolean isFreeToPlay;

  /**
   * 内购说明
   */
  @Schema(description = "内购说明", example = "提供可选角色皮肤和道具")
  private String inAppPurchaseInfo;

  /**
   * DLC扩展包列表，存储为 JSON 字符串
   */
  @Schema(description = "DLC扩展包列表", example = "季票,皮肤包,剧情扩展")
  private List<String> dlcPackages;
}