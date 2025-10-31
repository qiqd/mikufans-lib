CREATE TABLE animation (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键，自增ID',

    -- 基本信息
    title VARCHAR(255) NOT NULL COMMENT '作品名称（中文名）',
    original_title VARCHAR(255) COMMENT '原名（如日文名）',
    english_title VARCHAR(255) COMMENT '英文名称',
    description TEXT COMMENT '作品简介',
    release_date DATE COMMENT '发布日期（首播日期）',
    country VARCHAR(100) COMMENT '制作国家/地区',
    language VARCHAR(50) COMMENT '原始语言',
    status VARCHAR(20) DEFAULT 'ONGOING' COMMENT '状态：ONGOING, COMPLETED, CANCELLED, HIATUS',
    cover_image_url VARCHAR(500) COMMENT '封面图URL',
    official_website VARCHAR(500) COMMENT '官方网站链接',

    -- 评分与热度
    average_rating DECIMAL(3,2) COMMENT '平均评分（0.00~10.00）',
    total_views BIGINT DEFAULT 0 COMMENT '总观看/点击次数',

    -- 创作者
    studio_or_author VARCHAR(255) COMMENT '制作公司或原作者（通用字段）',

    -- 年龄评级
    age_rating_system VARCHAR(50) COMMENT '评级系统（如 CERO、ESRB、MPAA）',
    age_rating_code VARCHAR(10) COMMENT '评级代码（如 A/B/C 或 E/T/M）',
    age_rating_min_age INT COMMENT '建议最小年龄（如 3, 6, 12, 16, 18）',
    age_rating_description TEXT COMMENT '评级说明（包含暴力、语言、性暗示等内容）',

    -- 动画特有字段
    episode_count INT COMMENT '总集数',
    duration_per_episode INT COMMENT '单集时长（分钟）',
    broadcast_platform VARCHAR(100) COMMENT '播放平台（如 Bilibili、Crunchyroll）',
    start_date DATE COMMENT '开播日期',
    end_date DATE COMMENT '完结日期',
    director VARCHAR(255) COMMENT '导演',
    script_writer VARCHAR(255) COMMENT '编剧',
    music_composer VARCHAR(255) COMMENT '音乐制作',
    animation_studio VARCHAR(255) COMMENT '动画制作公司',
    main_voice_actors TEXT COMMENT '主要声优列表，存储为 JSON 字符串，格式：["花泽香菜", "小野贤章"]',
    source_material VARCHAR(255) COMMENT '改编来源（如“漫画改编”、“轻小说改编”）',

    -- 时间戳
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',

    INDEX idx_title (title),
    INDEX idx_release_date (release_date),
    INDEX idx_status (status),
    INDEX idx_average_rating (average_rating),
    INDEX idx_director (director),
    INDEX idx_studio (animation_studio)

) COMMENT='动画作品表，包含所有动画的完整信息';

CREATE TABLE comic (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键，自增ID',

    -- 基本信息
    title VARCHAR(255) NOT NULL COMMENT '作品名称（中文名）',
    original_title VARCHAR(255) COMMENT '原名（如日文名）',
    english_title VARCHAR(255) COMMENT '英文名称',
    description TEXT COMMENT '作品简介',
    release_date DATE COMMENT '发布日期（首次连载日期）',
    country VARCHAR(100) COMMENT '制作国家/地区',
    language VARCHAR(50) COMMENT '原始语言',
    status VARCHAR(20) DEFAULT 'ONGOING' COMMENT '状态：ONGOING, COMPLETED, CANCELLED, HIATUS',
    cover_image_url VARCHAR(500) COMMENT '封面图URL',
    official_website VARCHAR(500) COMMENT '官方网站链接',

    -- 评分与热度
    average_rating DECIMAL(3,2) COMMENT '平均评分（0.00~10.00）',
    total_views BIGINT DEFAULT 0 COMMENT '总阅读/点击次数',

    -- 创作者
    studio_or_author VARCHAR(255) COMMENT '作者或原作（通用字段）',

    -- 年龄评级
    age_rating_system VARCHAR(50) COMMENT '评级系统（如 CERO、ESRB）',
    age_rating_code VARCHAR(10) COMMENT '评级代码',
    age_rating_min_age INT COMMENT '建议最小年龄',
    age_rating_description TEXT COMMENT '评级说明',

    -- 漫画特有字段
    chapter_count INT COMMENT '总章节数',
    volume_count INT COMMENT '总卷数',
    author VARCHAR(255) COMMENT '作者',
    artist VARCHAR(255) COMMENT '画师（若与作者不同）',
    publisher VARCHAR(255) COMMENT '出版社',
    serialization_magazine VARCHAR(100) COMMENT '连载杂志（如周刊少年Jump）',
    first_published_date DATE COMMENT '首次发表日期',
    last_published_date DATE COMMENT '最后发表日期',
    demographic VARCHAR(20) DEFAULT 'SHONEN' COMMENT '目标受众：SHONEN, SHOJO, SEINEN, JOSEI, KIDS',
    is_completed BOOLEAN DEFAULT FALSE COMMENT '是否已完结（冗余字段，便于快速查询）',

    -- 时间戳
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',

    INDEX idx_title (title),
    INDEX idx_release_date (release_date),
    INDEX idx_author (author),
    INDEX idx_magazine (serialization_magazine),
    INDEX idx_publisher (publisher),
    INDEX idx_status (status)

) COMMENT='漫画作品表，包含所有漫画的完整信息';

CREATE TABLE game (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键，自增ID',

    -- 基本信息
    title VARCHAR(255) NOT NULL COMMENT '游戏名称（中文名）',
    original_title VARCHAR(255) COMMENT '原名（如日文/英文名）',
    english_title VARCHAR(255) COMMENT '英文名称',
    description TEXT COMMENT '游戏简介',
    release_date DATE COMMENT '发布日期（发售日）',
    country VARCHAR(100) COMMENT '开发国家/地区',
    language VARCHAR(50) COMMENT '原始语言',
    status VARCHAR(20) DEFAULT 'ONGOING' COMMENT '状态：ONGOING, COMPLETED, CANCELLED, HIATUS',
    cover_image_url VARCHAR(500) COMMENT '封面图URL',
    official_website VARCHAR(500) COMMENT '官网链接',

    -- 评分与热度
    average_rating DECIMAL(3,2) COMMENT '平均评分（0.00~10.00）',
    total_views BIGINT DEFAULT 0 COMMENT '总浏览/下载次数',

    -- 创作者
    studio_or_author VARCHAR(255) COMMENT '开发商或制作人',

    -- 年龄评级
    age_rating_system VARCHAR(50) COMMENT '评级系统（CERO, ESRB, PEGI）',
    age_rating_code VARCHAR(10) COMMENT '评级代码（A/B/C 或 E/T/M）',
    age_rating_min_age INT COMMENT '建议最小年龄',
    age_rating_description TEXT COMMENT '评级内容说明',

    -- 游戏特有字段
    developer VARCHAR(255) COMMENT '开发公司',
    publisher VARCHAR(255) COMMENT '发行公司',
    platforms TEXT COMMENT '支持平台，存储为 JSON 字符串，格式：["PC", "PS5", "Switch", "Mobile"]',
    game_engine VARCHAR(100) COMMENT '使用的游戏引擎（如 Unity, Unreal Engine）',
    game_mode VARCHAR(20) DEFAULT 'SINGLE_PLAYER' COMMENT '游戏模式：SINGLE_PLAYER, MULTI_PLAYER, ONLINE',
    perspective VARCHAR(20) COMMENT '视角：FIRST_PERSON, THIRD_PERSON, TOP_DOWN',
    file_size_mb BIGINT COMMENT '安装包大小（MB）',
    is_free_to_play BOOLEAN DEFAULT FALSE COMMENT '是否免费游玩',
    in_app_purchase_info TEXT COMMENT '内购说明',
    dlc_packages TEXT COMMENT 'DLC扩展包列表，存储为 JSON 字符串，格式：["DLC1: 海外篇", "DLC2: 未来篇"]',

    -- 时间戳
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',

    INDEX idx_title (title),
    INDEX idx_release_date (release_date),
    INDEX idx_developer (developer),
    INDEX idx_publisher (publisher),
    INDEX idx_game_mode (game_mode)

) COMMENT='游戏作品表，包含所有游戏的完整信息';


CREATE TABLE acg_character (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键，自增ID',
    
    name VARCHAR(255) NOT NULL COMMENT '角色或人物名称',
    type VARCHAR(20) NOT NULL COMMENT '类型：FICTIONAL（虚构角色）或 REAL（现实人物）',
    image_url VARCHAR(500) COMMENT '头像或照片URL',
    description TEXT COMMENT '简介',
    gender VARCHAR(20) DEFAULT 'Unknown' COMMENT '性别（M/F/Other/Unknown）',
    tags TEXT COMMENT '标签列表，存储为 JSON 字符串，如 ["傲娇", "声线磁性"]',

    -- 剧中人特有
    fictional_species VARCHAR(100) COMMENT '种族（如人类、机器人、恶魔）',
    fictional_age INT COMMENT '设定年龄',
    fictional_birthday VARCHAR(10) COMMENT '设定生日（MM-DD格式）',
    fictional_personality VARCHAR(200) COMMENT '性格特征（如热血、冷静、腹黑）',

    -- 非剧中人特有
    real_birth_date DATE COMMENT '真实出生日期',
    real_nationality VARCHAR(100) COMMENT '国籍',
    real_agency VARCHAR(200) COMMENT '所属事务所或公司',
    real_skills TEXT COMMENT '技能列表，存储为 JSON 字符串，如 ["配音", "唱歌", "作曲"]',
    social_accounts TEXT COMMENT '社交账号，存储为 JSON 字符串，格式：{"twitter": "@xxx", "bilibili": "12345"}',

    -- 关联
    voice_actor_id BIGINT COMMENT '声优ID（仅对剧中人有效，指向另一个 acg_character）',

    -- 时间戳
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',

    FOREIGN KEY (voice_actor_id) REFERENCES acg_character(id) ON DELETE SET NULL,
    INDEX idx_name (name),
    INDEX idx_type (type),
    INDEX idx_voice_actor (voice_actor_id)

) COMMENT='ACG人物统一表，包含剧中角色与现实人物（声优、作者等）';

CREATE TABLE character_work (
    character_id BIGINT NOT NULL COMMENT '人物ID',
    work_type ENUM('ANIMATION', 'COMIC', 'GAME') NOT NULL COMMENT '作品类型',
    work_id BIGINT NOT NULL COMMENT '作品ID（对应 animation.id / comic.id / game.id）',
    role_type VARCHAR(50) COMMENT '角色类型（如 Main Character, Voice Actor, Director）',
    description TEXT COMMENT '角色描述或职责说明',
    PRIMARY KEY (character_id, work_type, work_id),
    
    FOREIGN KEY (character_id) REFERENCES acg_character(id) ON DELETE CASCADE,
    
    INDEX idx_work_id (work_id) COMMENT '按作品ID查询',
    INDEX idx_work_type (work_type) COMMENT '按作品类型过滤',
    INDEX idx_role_type (role_type) COMMENT '按角色类型检索'

) COMMENT='人物与作品的出演/参与关系表，支持跨类型作品关联';

CREATE TABLE genre (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键，自增ID',
    name VARCHAR(50) NOT NULL UNIQUE COMMENT '类型名称（如 Action, Comedy）',
    description TEXT COMMENT '类型描述',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT='作品类型表（Genre），用于分类作品主题';

CREATE TABLE tag (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键，自增ID',
    name VARCHAR(50) NOT NULL UNIQUE COMMENT '标签名称（如“热血”、“治愈”）',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT='用户生成标签表（Tag），支持社区打标';

-- 动画-类型
CREATE TABLE animation_to_genre (
 id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键，自增ID',
    animation_id BIGINT NOT NULL COMMENT '动画ID',
    genre_id BIGINT NOT NULL COMMENT '类型ID'
) COMMENT='动画与类型多对多关联表';

-- 漫画-类型
CREATE TABLE comic_to_genre (
 id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键，自增ID',
    comic_id BIGINT NOT NULL COMMENT '漫画ID',
    genre_id BIGINT NOT NULL COMMENT '类型ID'
) COMMENT='漫画与类型多对多关联表';

-- 游戏-类型
CREATE TABLE game_to_genre (
 id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键，自增ID',
    game_id BIGINT NOT NULL COMMENT '游戏ID',
    genre_id BIGINT NOT NULL COMMENT '类型ID'

) COMMENT='游戏与类型多对多关联表';