package org.mikufans.entity;

import lombok.Data;

/**
 * 人物与作品的出演/参与关系表实体类
 * 支持跨类型作品关联
 */
@Data
public class CharacterWork {
    /**
     * 人物ID
     */
    private Long characterId;
    
    /**
     * 作品类型：ANIMATION, COMIC, GAME
     */
    private String workType;
    
    /**
     * 作品ID（对应 animation.id / comic.id / game.id）
     */
    private Long workId;
    
    /**
     * 角色类型（如 Main Character, Voice Actor, Director）
     */
    private String roleType;
    
    /**
     * 角色描述或职责说明
     */
    private String description;
}