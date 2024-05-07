CREATE TABLE `ability_flow`
(
    id             bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
    scene_id       bigint(20)          NOT NULL DEFAULT 0 COMMENT '场景id',
    flow_name      varchar(255)        NOT NULL DEFAULT '' COMMENT '业务流名称',
    flow_desc      varchar(255)        NOT NULL DEFAULT '' COMMENT '业务流描述',
    flow_assignees varchar(255)        NOT NULL DEFAULT '' COMMENT '业务流负责人',
    flow_enabled   tinyint(1)          NOT NULL DEFAULT 0 COMMENT '是否启用',
    flow_status    varchar(255)        NOT NULL DEFAULT 'DESIGNING' COMMENT '流转状态 设计中（DESIGNING） 已部署（DEPLOYED）',
    deleted        tinyint             NOT NULL DEFAULT 0 COMMENT '是否删除 0 未删除 1 删除 默认是0',
    create_user_id varchar(32)         NOT NULL DEFAULT '' COMMENT '创建人id',
    create_time    timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间，默认当前时间',
    update_user_id varchar(32)         NOT NULL DEFAULT '' COMMENT '更新人id',
    update_time    timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    tenant_id      varchar(32)         NOT NULL DEFAULT '' COMMENT '租户id',
    primary key (id),
    index idx_scene_id (scene_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci comment '业务流表';

CREATE TABLE `ability_def_flow`
(
    id                  bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
    flow_id             bigint(20)          NOT NULL DEFAULT 0 COMMENT '业务流id',
    def_name            varchar(255)        NOT NULL DEFAULT '' COMMENT '流程定义名称',
    def_key             varchar(255)        NOT NULL DEFAULT '' COMMENT '流程定义key',
    def_flow_version    integer(10)         NOT NULL DEFAULT 0 COMMENT '流程定义版本',
    def_cf_flow_version integer(10)         NOT NULL DEFAULT 0 COMMENT '跟camunda关联的最新版本号',
    def_bpmn            longtext            NOT NULL DEFAULT '' COMMENT '流程定义文件',
    flow_type           varchar(20)         NOT NULL DEFAULT '' COMMENT '流程定义类型 默认类型 预定义类型（任务中心） 预定义类型（等等）',
    test_success        tinyint(1)          NOT NULL DEFAULT 0 COMMENT '测试是否成功 0 未成功 1 成功',
    deleted             tinyint             NOT NULL DEFAULT 0 COMMENT '是否删除 0 未删除 1 删除 默认是0',
    create_user_id      varchar(32)         NOT NULL DEFAULT '' COMMENT '创建人id',
    create_time         timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间，默认当前时间',
    update_user_id      varchar(32)         NOT NULL DEFAULT '' COMMENT '更新人id',
    update_time         timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    tenant_id           varchar(32)         NOT NULL DEFAULT '' COMMENT '租户id',
    primary key (id),
    index idx_flow_id (flow_id),
    index idx_def_key_version (def_key, def_flow_version),
    index idx_flow_type (flow_type)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci comment '流程定义表';

CREATE TABLE `ability_def_context`
(
    id            bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
    def_flow_id   bigint(20)          NOT NULL DEFAULT 0 COMMENT '流程定义id',
    var_key       varchar(255)        NOT NULL DEFAULT '' COMMENT '变量key',
    var_type      varchar(20)         NOT NULL DEFAULT '' COMMENT '变量类型',
    var_name      varchar(255)        NOT NULL DEFAULT '' COMMENT '变量名称',
    from_act_id   varchar(255)        NOT NULL DEFAULT '' COMMENT '来源节点id',
    from_act_name varchar(255)        NOT NULL DEFAULT '' COMMENT '来源节点名称',
    deleted       tinyint             NOT NULL DEFAULT 0 COMMENT '是否删除 0 未删除 1 删除 默认是0',
    create_time   timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间，默认当前时间',
    update_time   timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    primary key (id),
    index idx_flow_id (def_flow_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci comment '流程定义上下文表';

CREATE TABLE `ability_def_act`
(
    id          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
    def_flow_id bigint(20)          NOT NULL DEFAULT 0 COMMENT '流程定义id',
    act_id      varchar(255)        NOT NULL DEFAULT '' COMMENT '节点id',
    act_name    varchar(255)        NOT NULL DEFAULT '' COMMENT '节点名称',
    act_type    varchar(20)         NOT NULL DEFAULT '' COMMENT '节点类型 任务节点 消息节点',
    deleted     tinyint             NOT NULL DEFAULT 0 COMMENT '是否删除 0 未删除 1 删除 默认是0',
    create_time timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间，默认当前时间',
    update_time timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    primary key (id),
    index idx_flow_id (def_flow_id),
    index idx_act_id (act_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci comment '流程节点表';

CREATE TABLE `ability_def_var_mapping`
(
    id              bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
    def_flow_id     bigint(20)          NOT NULL DEFAULT 0 COMMENT '流程定义id',
    def_key         varchar(255)        NOT NULL DEFAULT '' COMMENT '流程定义key',
    act_id          varchar(255)        NOT NULL DEFAULT '' COMMENT '节点id',
    mapping_type    varchar(20)         NOT NULL DEFAULT '' COMMENT '映射类型 input output',
    source_var_key  varchar(255)        NOT NULL DEFAULT '' COMMENT '源变量key',
    source_var_name varchar(255)        NOT NULL DEFAULT '' COMMENT '源变量名称',
    source_var_type varchar(20)         NOT NULL DEFAULT '' COMMENT '源变量类型',
    target_var_key  varchar(255)        NOT NULL DEFAULT '' COMMENT '目标变量key',
    target_var_name varchar(255)        NOT NULL DEFAULT '' COMMENT '目标变量名称',
    target_var_type varchar(20)         NOT NULL DEFAULT '' COMMENT '目标变量类型',
    deleted         tinyint             NOT NULL DEFAULT 0 COMMENT '是否删除 0 未删除 1 删除 默认是0',
    create_time     timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间，默认当前时间',
    update_time     timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    primary key (id),
    index idx_flow_id (def_flow_id),
    index idx_act_id (act_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci comment '流程变量映射表';

CREATE TABLE `ability_def_external_topic`
(
    id                 bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
    external_task_name varchar(255)        NOT NULL DEFAULT '' COMMENT '外部任务名称',
    topic              varchar(255)        NOT NULL DEFAULT '' COMMENT 'topic',
    external_task_type varchar(255)        NOT NULL DEFAULT '' COMMENT '外部任务类型 任务节点 消息节点 等等',
    deleted            tinyint             NOT NULL DEFAULT 0 COMMENT '是否删除 0 未删除 1 删除 默认是0',
    create_time        timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间，默认当前时间',
    update_time        timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    tenant_id          varchar(32)         NOT NULL DEFAULT '' COMMENT '租户id',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci comment '组件能力topic枚举表';

