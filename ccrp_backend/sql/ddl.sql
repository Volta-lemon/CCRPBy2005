SET NAMES utf8;
CREATE TABLE artifact (
                          id INT NOT NULL COMMENT '文物唯一标识符',
                          artifactName VARCHAR(255) NOT NULL COMMENT '文物名称',
                          author VARCHAR(255) NOT NULL COMMENT '文物作者',
                          relicTime VARCHAR(255) NOT NULL COMMENT '文物所在时间',
                          description TEXT NOT NULL COMMENT '文物描述',
                          imageUrl VARCHAR(255) NOT NULL COMMENT '文物主图URL',
                          moreUrl VARCHAR(255) NOT NULL COMMENT '文物原地址',
                          createTime DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
                          updateTime DATETIME DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                          isDelete INT DEFAULT 0 NOT NULL COMMENT '是否删除',
                          PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文物基本信息表';

CREATE TABLE user (
                      id INT NOT NULL COMMENT '用户唯一标识符',
                      username VARCHAR(255) NOT NULL COMMENT '用户昵称',
                      userAccount VARCHAR(255) NOT NULL COMMENT '用户账号',
                      password VARCHAR(255) NOT NULL COMMENT '密码',
                      avatarUrl VARCHAR(255) NOT NULL COMMENT '头像URL',
                      email VARCHAR(255) NOT NULL COMMENT '电子邮件地址',
                      phone VARCHAR(255) NOT NULL COMMENT '手机号码',
                      createTime DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
                      updateTime DATETIME DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                      isDelete INT DEFAULT 0 NOT NULL COMMENT '是否删除',
                      PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户基本信息表';

CREATE TABLE history (
                         id INT NOT NULL COMMENT '历史记录唯一标识符',
                         userId INT NOT NULL COMMENT '用户ID',
                         artifactId INT NOT NULL COMMENT '文物ID',
                         historyTime DATETIME NOT NULL COMMENT '浏览时间',
                         createTime DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
                         updateTime DATETIME DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                         isDelete INT DEFAULT 0 NOT NULL COMMENT '是否删除',
                         PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户浏览历史记录表';

CREATE TABLE favorite (
                          id INT NOT NULL COMMENT '收藏记录唯一标识符',
                          userId INT NOT NULL COMMENT '用户ID',
                          artifactId INT NOT NULL COMMENT '文物ID',
                          favoriteTime DATETIME NOT NULL COMMENT '收藏时间',
                          createTime DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
                          updateTime DATETIME DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                          isDelete INT DEFAULT 0 NOT NULL COMMENT '是否删除',
                          PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户收藏记录表';

CREATE TABLE comment (
                         id INT NOT NULL COMMENT '评论记录唯一标识符',
                         userId INT NOT NULL COMMENT '用户ID',
                         artifactId INT NOT NULL COMMENT '文物ID',
                         content TEXT NOT NULL COMMENT '评论内容',
                         time DATETIME NOT NULL COMMENT '评论时间',
                         createTime DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
                         updateTime DATETIME DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                         isDelete INT DEFAULT 0 NOT NULL COMMENT '是否删除',
                         PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户评论记录表';

CREATE TABLE `like` (
                        id INT NOT NULL COMMENT '点赞记录唯一标识符',
                        userId INT NOT NULL COMMENT '用户ID',
                        artifactId INT NOT NULL COMMENT '文物ID',
                        time DATETIME NOT NULL COMMENT '点赞时间',
                        createTime DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
                        updateTime DATETIME DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        isDelete INT DEFAULT 0 NOT NULL COMMENT '是否删除',
                        PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户点赞记录表';

CREATE TABLE message_audit (
                               id INT NOT NULL COMMENT '审核记录唯一标识符',
                               type INT NOT NULL COMMENT '审核记录类型，1为留言审核记录，2为图片上传审核记录',
                               userId INT NOT NULL COMMENT '用户ID',
                               messageId INT NOT NULL COMMENT '留言/图片ID',
                               auditTime DATETIME NOT NULL COMMENT '审核时间',
                               status INT NOT NULL COMMENT '审核状态，0为未审核，1为审核通过，2为审核不通过',
                               createTime DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
                               updateTime DATETIME DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               isDelete INT DEFAULT 0 NOT NULL COMMENT '是否删除',
                               PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='留言和图片审核记录表';





