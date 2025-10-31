package org.mikufans.entity;

import lombok.Data;
import java.util.List;

/**
 * 游戏作品表实体类
 * 包含所有游戏的完整信息
 */
@Data
public class Game extends BaseWork {
    /**
     * 开发公司
     */
    private String developer;
    
    /**
     * 发行公司
     */
    private String publisher;
    
    /**
     * 支持平台，存储为 JSON 字符串
     */
    private List<String> platforms;
    
    /**
     * 使用的游戏引擎（如 Unity, Unreal Engine）
     */
    private String gameEngine;
    
    /**
     * 游戏模式：SINGLE_PLAYER, MULTI_PLAYER, ONLINE
     */
    private String gameMode;
    
    /**
     * 视角：FIRST_PERSON, THIRD_PERSON, TOP_DOWN
     */
    private String perspective;
    
    /**
     * 安装包大小（MB）
     */
    private Long fileSizeMb;
    
    /**
     * 是否免费游玩
     */
    private Boolean isFreeToPlay;
    
    /**
     * 内购说明
     */
    private String inAppPurchaseInfo;
    
    /**
     * DLC扩展包列表，存储为 JSON 字符串
     */
    private List<String> dlcPackages;
}