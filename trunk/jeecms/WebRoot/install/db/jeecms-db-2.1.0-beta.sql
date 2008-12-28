
/*==============================================================*/
/* Table: ARTI_ARTICLE                                          */
/*==============================================================*/
create table ARTI_ARTICLE
(
   ARTICLE_ID           bigint not null auto_increment,
   WEBSITE_ID           bigint not null,
   ARTICTG_ID           bigint not null,
   NEXT_ID              bigint comment '下一篇',
   PRE_ID               bigint comment '上一篇',
   GROUP_ID             bigint comment '允许浏览会员组',
   CHANNEL_ID           bigint not null comment '栏目ID',
   MEMBER_ID            bigint comment '录入会员',
   ADMIN_CHECK          bigint comment '审核管理员',
   ADMIN_DISABLE        bigint comment '禁用管理员',
   ADMIN_INPUT          bigint comment '录入管理员',
   TITLE                varchar(255) comment '文章标题',
   SHORT_TITLE          varchar(255) comment '简短标题（用于栏目或首页索引）',
   TITLE_COLOR          varchar(10) comment '标题颜色',
   TITLE_IMG            varchar(100) comment '文章标题缩略图',
   DESCRIPTION          varchar(255) comment '文章描述',
   TAGS                 varchar(255) comment '标记，类似关键字',
   AUTHOR               varchar(100) comment '作者',
   ORIGIN               varchar(100) comment '来源',
   RELEASE_DATE         datetime comment '发表日期（可人为设置）',
   RELEASE_SYS_DATE     datetime comment '发布日期（系统日期）',
   CHECK_TIME           datetime comment '终审通过时间',
   DISABLE_TIME         datetime comment '禁用时间',
   VISIT_TOTAL          bigint comment '总共访问次数',
   VISIT_TODAY          bigint comment '当天访问次数',
   STAT_DATE            date comment '统计时间',
   OUTER_URL            varchar(255) comment '外部链接',
   CONTENT_RES_PATH     varchar(200) comment '文章内容的资源路径',
   PAGE_COUNT           int comment '文章总页数',
   TPL_CONTENT          varchar(100) comment '指定模板',
   CHECK_STEP           int comment '审核级数',
   CHECK_OPINION        varchar(255) comment '审核意见',
   HAS_TITLEIMG         bool not null comment '是否有标题图片',
   IS_BOLD              bool not null comment '是否加粗',
   IS_DRAFT             bool not null comment '是否草稿',
   IS_RECOMMEND         bool not null comment '是否推荐',
   IS_CHECK             bool not null comment '是否审核',
   IS_DISABLED          bool not null comment '是否禁用',
   IS_REJECT            bool not null comment '是否驳回',
   PARAM1               varchar(255) comment '文章相关数据1',
   PARAM2               varchar(255) comment '文章相关数据2',
   PARAM3               varchar(255) comment '文章相关数据3',
   primary key (ARTICLE_ID)
);

/*==============================================================*/
/* Table: ARTI_CHECK_LOG                                        */
/*==============================================================*/
create table ARTI_CHECK_LOG
(
   ACLOG_ID             bigint not null auto_increment,
   ARTICLE_ID           bigint not null,
   ADMIN_ID             bigint,
   CHECK_STEP           int comment '审核级数',
   CHECK_OPINION        varchar(255) comment '审核意见',
   CHECK_TIME           datetime comment '审核时间',
   IP                   varchar(20) comment '操作员IP',
   IS_PASS              bool not null comment '通过或驳回',
   IS_FINAL             bool not null,
   primary key (ACLOG_ID)
)
comment = "审核日志";

/*==============================================================*/
/* Table: ARTI_CTG                                              */
/*==============================================================*/
create table ARTI_CTG
(
   ARTICTG_ID           bigint not null auto_increment,
   WEBSITE_ID           bigint not null,
   LABEL                varchar(20) not null comment '标识ID',
   NAME                 varchar(50) comment '名称',
   IMG_WIDTH            int default 139 comment '标题图片宽度',
   IMG_HEIGHT           int default 139 comment '标题图片高度',
   HAS_TITLEIMG         bool not null default 1 comment '是否有标题图片',
   IS_DISABLED          bool not null default 0 comment '是否继续使用',
   primary key (ARTICTG_ID)
)
comment = "文章的属性";

/*==============================================================*/
/* Table: AUXI_CONFIG                                           */
/*==============================================================*/
create table AUXI_CONFIG
(
   CONFIG_ID            bigint not null,
   MSG_NEED_CHECK       bool default 0 comment '留言板是否需要审核',
   MSG_IS_OPEN          bool default 1 comment '留言板是否开放',
   MSG_ANONYMITY        varchar(20) default '匿名网友' comment '留言板匿名网友名称',
   MSG_MAX_SIZE         int default 16384 comment '留言板留言大小(字节)',
   primary key (CONFIG_ID)
)
comment = "辅助系统配置表";

/*==============================================================*/
/* Table: AUXI_MSG                                              */
/*==============================================================*/
create table AUXI_MSG
(
   MSG_ID               bigint not null auto_increment,
   ADMIN_ID             bigint,
   MSGCTG_ID            bigint not null,
   WEBSITE_ID           bigint not null,
   MEMBER_ID            bigint,
   CONFIG_ID            bigint not null,
   TITLE                varchar(200) comment '留言标题',
   CONTENT_MEMBER       longtext comment '留言内容',
   CONTENT_ADMIN        longtext comment '回复内容',
   EMAIL                varchar(100) comment '电子邮箱',
   PHONE                varchar(100) comment '联系电话',
   QQ                   varchar(50) comment 'QQ',
   IP                   varchar(20) comment '留言IP',
   CREATE_TIME          datetime comment '留言时间',
   REPLY_TIME           datetime comment '回复时间',
   IS_CHECK             bool not null comment '是否审核',
   IS_RECOMMEND         bool not null comment '是否推荐',
   IS_DISABLED          bool not null comment '是否禁用',
   primary key (MSG_ID)
)
comment = "留言板留言";

/*==============================================================*/
/* Table: AUXI_MSG_CTG                                          */
/*==============================================================*/
create table AUXI_MSG_CTG
(
   MSGCTG_ID            bigint not null auto_increment,
   WEBSITE_ID           bigint not null,
   NAME                 varchar(50),
   DESCRIPTION          varchar(250),
   primary key (MSGCTG_ID)
)
comment = "留言类别";

/*==============================================================*/
/* Table: CMS_ADMIN                                             */
/*==============================================================*/
create table CMS_ADMIN
(
   ADMIN_ID             bigint not null,
   WEBSITE_ID           bigint not null,
   CHECK_RIGHT          int default 0 comment '审核级别（第几审）',
   primary key (ADMIN_ID)
)
comment = "jeecms管理员";

/*==============================================================*/
/* Table: CMS_CHANNEL                                           */
/*==============================================================*/
create table CMS_CHANNEL
(
   CHANNEL_ID           bigint not null auto_increment,
   WEBSITE_ID           bigint not null,
   PARENT               bigint comment '父栏目',
   MODEL_ID             bigint not null,
   SYS_TYPE             varchar(20) not null comment '系统类型',
   PATH                 varchar(100) comment '栏目路径',
   NAME                 varchar(100) comment '栏目名称',
   TITLE_IMG            varchar(100) comment '栏目图片',
   TPL_INDEX            varchar(100) comment '栏目页模板路径',
   TPL_CONTENT          varchar(100) comment '内容页模板路径',
   TITLE                varchar(255) comment 'TITLE',
   KEYWORDS             varchar(255) comment 'KEYWORDS',
   DESCRIPTION          varchar(255) comment 'DESCRIPTION',
   VISIT_TOTAL          bigint comment '总共访问次数',
   VISIT_TODAY          bigint comment '当天访问次数',
   STAT_DATE            date comment '统计日期',
   OUTER_URL            varchar(255) comment '外部链接',
   CONTRIBUTE_LEVEL     int comment '允许哪一级别会员投稿',
   PRIORITY             int not null comment '栏目排列顺序',
   HAS_CHILD            bool not null comment '是否可以有子节点',
   IS_DISPLAY           bool not null comment '是否显示',
   PARAM1               varchar(255) comment '栏目相关数据1',
   PARAM2               varchar(255) comment '栏目相关数据2',
   PARAM3               varchar(255) comment '栏目相关数据3',
   primary key (CHANNEL_ID)
);

/*==============================================================*/
/* Table: CMS_CHNL_MODEL                                        */
/*==============================================================*/
create table CMS_CHNL_MODEL
(
   MODEL_ID             bigint not null auto_increment,
   WEBSITE_ID           bigint not null,
   CONFIG_ID            bigint not null,
   SYS_TYPE             varchar(20) not null comment '系统类型',
   NAME                 varchar(100) comment '模型名称',
   SHORT_NAME           varchar(20) comment '简称',
   TPL_PREFIX_CHANNEL   varchar(20) comment '栏目模板前缀',
   TPL_PREFIX_CONTENT   varchar(20) comment '内容模板前缀',
   HAS_CHILD            bool not null default 1 comment '是否可以有子栏目',
   primary key (MODEL_ID)
)
comment = "栏目模型";

/*==============================================================*/
/* Table: CMS_CHNL_MODEL_ITEM                                   */
/*==============================================================*/
create table CMS_CHNL_MODEL_ITEM
(
   MITEM_ID             bigint not null auto_increment,
   MODEL_ID             bigint not null,
   NAME                 varchar(100) not null comment '表单名称',
   LABEL                varchar(100) comment 'LABEL名称',
   HELP                 varchar(255) comment '帮助信息',
   INPUT_TYPE           varchar(20) comment '表单类型',
   PRIORITY             int not null comment '排列顺序',
   IS_CHECKED           bool not null comment '是否选中',
   primary key (MITEM_ID)
)
comment = "栏目模型定义";

/*==============================================================*/
/* Table: CMS_COMMENT                                           */
/*==============================================================*/
create table CMS_COMMENT
(
   COMMENT_ID           bigint not null auto_increment,
   ARTICLE_ID           bigint,
   WEBSITE_ID           bigint not null,
   MEMBER_ID            bigint,
   ADMIN_ID             bigint,
   TITLE                varchar(200) comment '标题',
   CONTENT_GUEST        longtext comment '用户评论内容',
   CONTENT_ADMIN        longtext comment '管理员回复内容',
   CREATE_TIME          datetime comment '创建时间',
   REPLAY_TIME          datetime comment '回复时间',
   IP                   varchar(50) comment '评论者IP',
   IS_CHECK             bool not null comment '是否审核',
   IS_RECOMMEND         bool not null comment '是否推荐',
   IS_DISABLED          bool not null comment '是否禁用',
   primary key (COMMENT_ID)
)
comment = "jeecms评论表";

/*==============================================================*/
/* Table: CMS_CONFIG                                            */
/*==============================================================*/
create table CMS_CONFIG
(
   CONFIG_ID            bigint not null,
   COMMENT_NEED_CHECK   bool default 0 comment '评论是否需要审核',
   CHECK_COUNT          int default 0 comment '内容需要审核的次数',
   DEFAULT_SYSTEM       varchar(20) default 'article' comment '默认系统',
   primary key (CONFIG_ID)
)
comment = "jeecms配置表";

/*==============================================================*/
/* Table: CMS_MEMBER                                            */
/*==============================================================*/
create table CMS_MEMBER
(
   MEMBER_ID            bigint not null,
   WEBSITE_ID           bigint not null,
   GROUP_ID             bigint not null,
   SCORE                int default 0 comment '积分',
   COUPON               int default 0 comment '点劵',
   IS_DISABLED          bool not null default 0 comment '是否禁用',
   primary key (MEMBER_ID)
)
comment = "jeecms会员";

/*==============================================================*/
/* Table: CMS_MEMBER_GROUP                                      */
/*==============================================================*/
create table CMS_MEMBER_GROUP
(
   GROUP_ID             bigint not null auto_increment,
   WEBSITE_ID           bigint not null,
   NAME                 varchar(100) comment '组名',
   GROUP_LEVEL          int default 10 comment '组等级（越小越高）',
   DESCRIPTION          varchar(255) comment '描述',
   UPLOAD_SIZE          int default 0 comment '上传文件的大小，单位KB',
   primary key (GROUP_ID)
)
comment = "jeecms用户组";

/*==============================================================*/
/* Table: CMS_RECOMMEND_GROUP                                   */
/*==============================================================*/
create table CMS_RECOMMEND_GROUP
(
   RGROUP_ID            bigint not null auto_increment,
   CHANNEL_ID           bigint not null,
   WEBSITE_ID           bigint not null,
   SYS_TYPE             char(4) not null comment '系统类型',
   NAME                 varchar(50) comment '推荐组名',
   DESCRIPTION          varchar(250) comment '描述',
   primary key (RGROUP_ID)
);

/*==============================================================*/
/* Table: CMS_RECOMMEND_ITEM                                    */
/*==============================================================*/
create table CMS_RECOMMEND_ITEM
(
   RITEM_ID             bigint not null auto_increment,
   WEBSITE_ID           bigint not null,
   RGROUP_ID            bigint not null,
   SYS_TYPE             char(4) not null comment '系统类型',
   TITLE                varchar(250) comment '推荐的标题',
   DESCRIPTION          varchar(255) comment '推荐的描述',
   PIC_PATH             varchar(250) comment '图片地址，完整url',
   URL                  varchar(250) comment '推荐链接，完整url',
   COLOR                varchar(20) comment '标题的颜色',
   PRIORITY             int not null comment '优先级（显示的先后顺序）',
   IS_CHECK             bool not null comment '是否审核',
   primary key (RITEM_ID)
);

/*==============================================================*/
/* Table: CORE_ADMIN                                            */
/*==============================================================*/
create table CORE_ADMIN
(
   ADMIN_ID             bigint not null auto_increment,
   WEBSITE_ID           bigint not null,
   USER_ID              bigint not null,
   CREATE_TIME          datetime comment '创建时间',
   IS_DISABLED          bool not null default 0 comment '是否禁用',
   primary key (ADMIN_ID)
)
comment = "核心管理员";

/*==============================================================*/
/* Table: CORE_ADMIN_FUNCTION                                   */
/*==============================================================*/
create table CORE_ADMIN_FUNCTION
(
   ADMIN_ID             bigint not null,
   FUNCTION_ID          bigint not null,
   primary key (ADMIN_ID, FUNCTION_ID)
);

/*==============================================================*/
/* Table: CORE_ADMIN_ROLE                                       */
/*==============================================================*/
create table CORE_ADMIN_ROLE
(
   ROLE_ID              bigint not null,
   ADMIN_ID             bigint not null,
   primary key (ROLE_ID, ADMIN_ID)
);

/*==============================================================*/
/* Table: CORE_FUNCTION                                         */
/*==============================================================*/
create table CORE_FUNCTION
(
   FUNCTION_ID          bigint not null auto_increment,
   PARENT               bigint comment '父功能',
   NAME                 varchar(100),
   URL                  varchar(200),
   FUNCS                longtext comment '功能列表集，用@分割',
   DESCRIPTION          varchar(250),
   PRIORITY             int not null comment '功能菜单排列顺序',
   IS_MENU              bool not null comment '是否为菜单',
   primary key (FUNCTION_ID)
)
comment = "功能表";

/*==============================================================*/
/* Table: CORE_MEMBER                                           */
/*==============================================================*/
create table CORE_MEMBER
(
   MEMBER_ID            bigint not null auto_increment,
   USER_ID              bigint not null,
   WEBSITE_ID           bigint not null,
   NICK_NAME            varchar(50) comment '昵称',
   CREATE_TIME          datetime comment '创建时间',
   QQ                   varchar(100) comment 'QQ',
   MSN                  varchar(100) comment 'MSN',
   IS_DISABLED          bool not null default 0 comment '是否禁用',
   primary key (MEMBER_ID)
)
comment = "会员表";

/*==============================================================*/
/* Table: CORE_ROLE                                             */
/*==============================================================*/
create table CORE_ROLE
(
   ROLE_ID              bigint not null auto_increment,
   NAME                 varchar(50),
   DESCRIPTION          varchar(250),
   primary key (ROLE_ID)
)
comment = "角色表";

/*==============================================================*/
/* Table: CORE_ROLE_FUNCTION                                    */
/*==============================================================*/
create table CORE_ROLE_FUNCTION
(
   ROLE_ID              bigint not null,
   FUNCTION_ID          bigint not null,
   primary key (ROLE_ID, FUNCTION_ID)
);

/*==============================================================*/
/* Table: CORE_TPL_SOLUTION                                     */
/*==============================================================*/
create table CORE_TPL_SOLUTION
(
   WEBSITE_ID           bigint not null,
   SYSTEM_NAME          varchar(20) not null,
   SOLUTION_NAME        varchar(20)
)
comment = "模板方案表";

/*==============================================================*/
/* Table: CORE_USER                                             */
/*==============================================================*/
create table CORE_USER
(
   USER_ID              bigint not null auto_increment,
   LOGIN_NAME           varchar(50) not null comment '登录名',
   REAL_NAME            varchar(50) comment '真实姓名',
   PASSWORD             char(32) comment '密码',
   EMAIL                varchar(100) comment '电子邮件',
   FAX                  varchar(250) comment '传真',
   TEL                  varchar(250) comment '电话',
   MOBILE               varchar(250) comment '手机',
   ZIP                  varchar(20) comment '邮编',
   ADDRESS              varchar(250) comment '联系地址',
   GENDER               bool comment '性别',
   BIRTHDAY             date comment '出生年月',
   CREATE_TIME          datetime comment '创建时间',
   LAST_LOGIN_TIME      datetime comment '最后登录时间',
   LAST_LOGIN_IP        varchar(50) comment '最后登录IP',
   CURRENT_LOGIN_TIME   datetime comment '当前登录时间',
   CURRENT_LOGIN_IP     varchar(50) comment '当前登录IP',
   LOGIN_COUNT          bigint default 0 comment '总共登录次数',
   IS_DISABLED          bool not null default 0 comment '是否禁用',
   primary key (USER_ID),
   key AK_LOGIN_NAME (LOGIN_NAME)
)
comment = "统一用户表。";

/*==============================================================*/
/* Table: CORE_WEBSITE                                          */
/*==============================================================*/
create table CORE_WEBSITE
(
   WEBSITE_ID           bigint not null auto_increment,
   PARENT               bigint comment '父站点',
   UNITED               bigint comment '会员联合站点',
   DOMAIN               varchar(50) not null comment '域名',
   RES_PATH             varchar(20) not null comment '资源路径',
   RES_DOMAIN           varchar(50) comment '资源地址',
   BASE_DOMAIN          varchar(50) comment '根域名',
   DOMAIN_ALIAS         varchar(250) comment '可储存多个别名，用;分割',
   PORT                 int comment '服务的端口号',
   CONTEXT_PATH         varchar(20) comment '应用部署的路径',
   NAME                 varchar(100) comment '站点名称',
   SHORT_NAME           varchar(20) comment '简称',
   SUFFIX               varchar(20) default 'htm' comment '访问后缀，可以是htm、asp、php等',
   CURRENT_SYSTEM       varchar(20) default 'jeecms' comment '当前系统（jeecms,jeeshop,jeeforum）',
   COOKIE_KEY           varchar(20) default '_jeecms' comment '系统cookie识别码',
   OWNER_NAME           varchar(100) default '' comment '网站拥有者姓名',
   OWNER_IDENTITY       varchar(100) default '' comment '网站拥有者身份证号',
   COMPANY              varchar(200) default '' comment '公司名称',
   COPYRIGHT            varchar(255) default '' comment '版权信息',
   RECORD_CODE          varchar(255) default '' comment '备案号',
   EMAIL                varchar(100) default '' comment '网站拥有者电子邮箱',
   PHONE_CODE           varchar(200) default '' comment '电话号码',
   MOBILE_CODE          varchar(200) default '' comment '手机号码',
   CREATE_TIME          datetime comment '站点创建时间',
   EMAIL_CHARSET        varchar(20) default 'gbk' comment '邮件发送编码',
   EMAIL_HOSTNAME       varchar(50) default '' comment '邮件发送服务器',
   EMAIL_ACCOUNT        varchar(100) default '' comment '网站邮箱账号',
   EMAIL_USER_NAME      varchar(50) default '' comment '网站邮箱名称',
   EMAIL_USER_ID        varchar(100) default '' comment '网站邮箱登录名',
   EMAIL_USER_PWD       varchar(50) default '' comment '网站邮箱密码',
   EMAIL_SUBJECT        varchar(255) default '' comment '邮件主题（标题）',
   EMAIL_CONTENT        longtext comment '邮件内容',
   CLOSE_REASON         varchar(255) default '网站暂时关闭' comment '关闭原因',
   IS_CLOSE             bool not null default 0 comment '是否关闭网站（后台仍可访问）',
   primary key (WEBSITE_ID),
   key AK_DOMAIN (DOMAIN),
   key AK_RES_PATH (RES_PATH)
);

alter table ARTI_ARTICLE add constraint FK_ARTICLE_ARTICTG foreign key (ARTICTG_ID)
      references ARTI_CTG (ARTICTG_ID) on delete restrict on update restrict;

alter table ARTI_ARTICLE add constraint FK_ARTICLE_CHANNEL foreign key (CHANNEL_ID)
      references CMS_CHANNEL (CHANNEL_ID) on delete restrict on update restrict;

alter table ARTI_ARTICLE add constraint FK_ARTICLE_CHECK_CADMIN foreign key (ADMIN_CHECK)
      references CMS_ADMIN (ADMIN_ID) on delete restrict on update restrict;

alter table ARTI_ARTICLE add constraint FK_ARTICLE_CMEMBER foreign key (MEMBER_ID)
      references CMS_MEMBER (MEMBER_ID) on delete restrict on update restrict;

alter table ARTI_ARTICLE add constraint FK_ARTICLE_DISABLE_CADMIN foreign key (ADMIN_DISABLE)
      references CMS_ADMIN (ADMIN_ID) on delete restrict on update restrict;

alter table ARTI_ARTICLE add constraint FK_ARTICLE_INPUT_CADMIN foreign key (ADMIN_INPUT)
      references CMS_ADMIN (ADMIN_ID) on delete restrict on update restrict;

alter table ARTI_ARTICLE add constraint FK_ARTICLE_MGROUP foreign key (GROUP_ID)
      references CMS_MEMBER_GROUP (GROUP_ID) on delete restrict on update restrict;

alter table ARTI_ARTICLE add constraint FK_ARTICLE_WEBSITE foreign key (WEBSITE_ID)
      references CORE_WEBSITE (WEBSITE_ID) on delete restrict on update restrict;

alter table ARTI_CHECK_LOG add constraint FK_ACLOG_ADMIN foreign key (ADMIN_ID)
      references CMS_ADMIN (ADMIN_ID) on delete restrict on update restrict;

alter table ARTI_CHECK_LOG add constraint FK_ACLOG_ARTICLE foreign key (ARTICLE_ID)
      references ARTI_ARTICLE (ARTICLE_ID) on delete restrict on update restrict;

alter table ARTI_CTG add constraint FK_ARTICTG_WEBSITE foreign key (WEBSITE_ID)
      references CORE_WEBSITE (WEBSITE_ID) on delete restrict on update restrict;

alter table AUXI_CONFIG add constraint FK_AUXICONFIG_WEBSITE foreign key (CONFIG_ID)
      references CORE_WEBSITE (WEBSITE_ID) on delete restrict on update restrict;

alter table AUXI_MSG add constraint FK_AMSG_ADMIN foreign key (ADMIN_ID)
      references CORE_ADMIN (ADMIN_ID) on delete restrict on update restrict;

alter table AUXI_MSG add constraint FK_AMSG_MEMBER foreign key (MEMBER_ID)
      references CORE_MEMBER (MEMBER_ID) on delete restrict on update restrict;

alter table AUXI_MSG add constraint FK_AMSG_MSGCTG foreign key (MSGCTG_ID)
      references AUXI_MSG_CTG (MSGCTG_ID) on delete restrict on update restrict;

alter table AUXI_MSG add constraint FK_AMSG_WEBSITE foreign key (WEBSITE_ID)
      references CORE_WEBSITE (WEBSITE_ID) on delete restrict on update restrict;

alter table AUXI_MSG add constraint FK_AUXIMSG_CONFIG foreign key (CONFIG_ID)
      references AUXI_CONFIG (CONFIG_ID) on delete restrict on update restrict;

alter table AUXI_MSG_CTG add constraint FK_AMSGCTG_WEBSITE foreign key (WEBSITE_ID)
      references CORE_WEBSITE (WEBSITE_ID) on delete restrict on update restrict;

alter table CMS_ADMIN add constraint FK_CADMIN_ADMIN foreign key (ADMIN_ID)
      references CORE_ADMIN (ADMIN_ID) on delete restrict on update restrict;

alter table CMS_ADMIN add constraint FK_CADMIN_WEBSITE foreign key (WEBSITE_ID)
      references CORE_WEBSITE (WEBSITE_ID) on delete restrict on update restrict;

alter table CMS_CHANNEL add constraint FK_CCHANNEL_CHNLMODEL foreign key (MODEL_ID)
      references CMS_CHNL_MODEL (MODEL_ID) on delete restrict on update restrict;

alter table CMS_CHANNEL add constraint FK_CHANNEL_WEBSITE foreign key (WEBSITE_ID)
      references CORE_WEBSITE (WEBSITE_ID) on delete restrict on update restrict;

alter table CMS_CHANNEL add constraint FK_CHANNL_PARENT foreign key (PARENT)
      references CMS_CHANNEL (CHANNEL_ID) on delete restrict on update restrict;

alter table CMS_CHNL_MODEL add constraint FK_CHNLMODEL_CONFIG foreign key (CONFIG_ID)
      references CMS_CONFIG (CONFIG_ID) on delete restrict on update restrict;

alter table CMS_CHNL_MODEL add constraint FK_CHNLMODEL_WEBSITE foreign key (WEBSITE_ID)
      references CORE_WEBSITE (WEBSITE_ID) on delete restrict on update restrict;

alter table CMS_CHNL_MODEL_ITEM add constraint FK_CHNLMODELITEM_MODEL foreign key (MODEL_ID)
      references CMS_CHNL_MODEL (MODEL_ID) on delete restrict on update restrict;

alter table CMS_COMMENT add constraint FK_CCOMMENT_ADMIN foreign key (ADMIN_ID)
      references CMS_ADMIN (ADMIN_ID) on delete restrict on update restrict;

alter table CMS_COMMENT add constraint FK_CCOMMENT_ARTICLE foreign key (ARTICLE_ID)
      references ARTI_ARTICLE (ARTICLE_ID) on delete restrict on update restrict;

alter table CMS_COMMENT add constraint FK_CCOMMENT_MEMBER foreign key (MEMBER_ID)
      references CORE_MEMBER (MEMBER_ID) on delete restrict on update restrict;

alter table CMS_COMMENT add constraint FK_CCOMMENT_WEBSITE foreign key (WEBSITE_ID)
      references CORE_WEBSITE (WEBSITE_ID) on delete restrict on update restrict;

alter table CMS_CONFIG add constraint FK_CMSCONFIG_WEBSITE foreign key (CONFIG_ID)
      references CORE_WEBSITE (WEBSITE_ID) on delete restrict on update restrict;

alter table CMS_MEMBER add constraint FK_CMEMBER_MEMBER foreign key (MEMBER_ID)
      references CORE_MEMBER (MEMBER_ID) on delete restrict on update restrict;

alter table CMS_MEMBER add constraint FK_CMEMBER_MGROUP foreign key (GROUP_ID)
      references CMS_MEMBER_GROUP (GROUP_ID) on delete restrict on update restrict;

alter table CMS_MEMBER add constraint FK_CMEMBER_WEBSITE foreign key (WEBSITE_ID)
      references CORE_WEBSITE (WEBSITE_ID) on delete restrict on update restrict;

alter table CMS_MEMBER_GROUP add constraint FK_CMGROUP_WEBSITE foreign key (WEBSITE_ID)
      references CORE_WEBSITE (WEBSITE_ID) on delete restrict on update restrict;

alter table CMS_RECOMMEND_GROUP add constraint FK_RGROUP_CHANNEL foreign key (CHANNEL_ID)
      references CMS_CHANNEL (CHANNEL_ID) on delete restrict on update restrict;

alter table CMS_RECOMMEND_GROUP add constraint FK_RGROUP_WEBSITE foreign key (WEBSITE_ID)
      references CORE_WEBSITE (WEBSITE_ID) on delete restrict on update restrict;

alter table CMS_RECOMMEND_ITEM add constraint FK_RITEM_RGOUP foreign key (RGROUP_ID)
      references CMS_RECOMMEND_GROUP (RGROUP_ID) on delete restrict on update restrict;

alter table CMS_RECOMMEND_ITEM add constraint FK_RITEM_WEBSITE foreign key (WEBSITE_ID)
      references CORE_WEBSITE (WEBSITE_ID) on delete restrict on update restrict;

alter table CORE_ADMIN add constraint FK_ADMIN_USER foreign key (USER_ID)
      references CORE_USER (USER_ID) on delete restrict on update restrict;

alter table CORE_ADMIN add constraint FK_ADMIN_WEBSITE foreign key (WEBSITE_ID)
      references CORE_WEBSITE (WEBSITE_ID) on delete restrict on update restrict;

alter table CORE_ADMIN_FUNCTION add constraint FK_CORE_ADMIN_FUNCTION foreign key (FUNCTION_ID)
      references CORE_FUNCTION (FUNCTION_ID) on delete restrict on update restrict;

alter table CORE_ADMIN_FUNCTION add constraint FK_CORE_FUNCTION_ADMIN foreign key (ADMIN_ID)
      references CORE_ADMIN (ADMIN_ID) on delete restrict on update restrict;

alter table CORE_ADMIN_ROLE add constraint FK_CORE_ADMIN_ROLE foreign key (ROLE_ID)
      references CORE_ROLE (ROLE_ID) on delete restrict on update restrict;

alter table CORE_ADMIN_ROLE add constraint FK_CORE_ROLE_ADMIN foreign key (ADMIN_ID)
      references CORE_ADMIN (ADMIN_ID) on delete restrict on update restrict;

alter table CORE_FUNCTION add constraint FK_PARENT foreign key (PARENT)
      references CORE_FUNCTION (FUNCTION_ID) on delete restrict on update restrict;

alter table CORE_MEMBER add constraint FK_MEMBER_USER foreign key (USER_ID)
      references CORE_USER (USER_ID) on delete restrict on update restrict;

alter table CORE_MEMBER add constraint FK_MEMBER_WEBSITE foreign key (WEBSITE_ID)
      references CORE_WEBSITE (WEBSITE_ID) on delete restrict on update restrict;

alter table CORE_ROLE_FUNCTION add constraint FK_CORE_FUNCTION_ROLE foreign key (ROLE_ID)
      references CORE_ROLE (ROLE_ID) on delete restrict on update restrict;

alter table CORE_ROLE_FUNCTION add constraint FK_CORE_ROLE_FUNCTION foreign key (FUNCTION_ID)
      references CORE_FUNCTION (FUNCTION_ID) on delete restrict on update restrict;

alter table CORE_TPL_SOLUTION add constraint FK_TPLSOLUTION_WEBSITE foreign key (WEBSITE_ID)
      references CORE_WEBSITE (WEBSITE_ID) on delete restrict on update restrict;

alter table CORE_WEBSITE add constraint FK_PARENT_WEBSITE foreign key (PARENT)
      references CORE_WEBSITE (WEBSITE_ID) on delete restrict on update restrict;

alter table CORE_WEBSITE add constraint FK_UNITED_MEMBER_WEBISTE foreign key (UNITED)
      references CORE_WEBSITE (WEBSITE_ID) on delete restrict on update restrict;


/*==============================================================*/
/* Table: VOTE_ITEM                                             */
/*==============================================================*/
create table VOTE_ITEM
(
   VOTEITEM_ID          bigint not null auto_increment,
   VTOPIC_ID            bigint not null,
   TITLE                varchar(255) comment '标题',
   DESCRIPTION          varchar(255) comment '描述',
   VOTE_COUNT           bigint comment '投票数量',
   PRIORITY             int not null comment '显示优先级',
   primary key (VOTEITEM_ID)
)
comment = "投票项";

/*==============================================================*/
/* Table: VOTE_RECORD                                           */
/*==============================================================*/
create table VOTE_RECORD
(
   VRECORD_ID           bigint not null auto_increment,
   VTOPIC_ID            bigint not null,
   MEMBER_ID            bigint,
   VOTE_TIME            datetime comment '投票时间',
   VOTE_IP              varchar(50) comment '投票IP',
   VOTE_COOKIE          char(32) comment '投票cookie',
   primary key (VRECORD_ID)
)
comment = "投票记录";

/*==============================================================*/
/* Table: VOTE_TOPIC                                            */
/*==============================================================*/
create table VOTE_TOPIC
(
   VTOPIC_ID            bigint not null auto_increment,
   WEBSITE_ID           bigint not null,
   TITLE                varchar(255) comment '标题',
   DESCRIPTION          varchar(255) comment '描述',
   TOTAL_COUNT          bigint comment '投票数量',
   START_TIME           datetime comment '开始时间',
   END_TIME             datetime comment '结束时间',
   REPEATE_HOUR         int comment '重复投票限制时间(单位小时)',
   MULTI_SELECT         int comment '最多可以选择几项',
   IS_RESTRICT_MEMBER   bool not null comment '是否限制会员ID',
   IS_RESTRICT_IP       bool not null comment '是否限制IP',
   IS_RESTRICT_COOKIE   bool not null comment '是否限制cookie',
   IS_CURRENT           bool not null comment '是否为最新投票',
   IS_DISABLED          bool not null comment '是否禁止投票',
   primary key (VTOPIC_ID)
)
comment = "投票主题";

alter table VOTE_ITEM add constraint FK_VITEM_TOPIC foreign key (VTOPIC_ID)
      references VOTE_TOPIC (VTOPIC_ID) on delete restrict on update restrict;

alter table VOTE_RECORD add constraint FK_VRECORD_GUEST foreign key (MEMBER_ID)
      references CORE_MEMBER (MEMBER_ID) on delete restrict on update restrict;

alter table VOTE_RECORD add constraint FK_VRECORD_TOPIC foreign key (VTOPIC_ID)
      references VOTE_TOPIC (VTOPIC_ID) on delete restrict on update restrict;

alter table VOTE_TOPIC add constraint FK_VTOPIC_WEBSITE foreign key (WEBSITE_ID)
      references CORE_WEBSITE (WEBSITE_ID) on delete restrict on update restrict;