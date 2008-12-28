
/*==============================================================*/
/* Table: ARTI_ARTICLE                                          */
/*==============================================================*/
create table ARTI_ARTICLE
(
   ARTICLE_ID           bigint not null auto_increment,
   WEBSITE_ID           bigint not null,
   ARTICTG_ID           bigint not null,
   NEXT_ID              bigint comment '��һƪ',
   PRE_ID               bigint comment '��һƪ',
   GROUP_ID             bigint comment '���������Ա��',
   CHANNEL_ID           bigint not null comment '��ĿID',
   MEMBER_ID            bigint comment '¼���Ա',
   ADMIN_CHECK          bigint comment '��˹���Ա',
   ADMIN_DISABLE        bigint comment '���ù���Ա',
   ADMIN_INPUT          bigint comment '¼�����Ա',
   TITLE                varchar(255) comment '���±���',
   SHORT_TITLE          varchar(255) comment '��̱��⣨������Ŀ����ҳ������',
   TITLE_COLOR          varchar(10) comment '������ɫ',
   TITLE_IMG            varchar(100) comment '���±�������ͼ',
   DESCRIPTION          varchar(255) comment '��������',
   TAGS                 varchar(255) comment '��ǣ����ƹؼ���',
   AUTHOR               varchar(100) comment '����',
   ORIGIN               varchar(100) comment '��Դ',
   RELEASE_DATE         datetime comment '�������ڣ�����Ϊ���ã�',
   RELEASE_SYS_DATE     datetime comment '�������ڣ�ϵͳ���ڣ�',
   CHECK_TIME           datetime comment '����ͨ��ʱ��',
   DISABLE_TIME         datetime comment '����ʱ��',
   VISIT_TOTAL          bigint comment '�ܹ����ʴ���',
   VISIT_TODAY          bigint comment '������ʴ���',
   STAT_DATE            date comment 'ͳ��ʱ��',
   OUTER_URL            varchar(255) comment '�ⲿ����',
   CONTENT_RES_PATH     varchar(200) comment '�������ݵ���Դ·��',
   PAGE_COUNT           int comment '������ҳ��',
   TPL_CONTENT          varchar(100) comment 'ָ��ģ��',
   CHECK_STEP           int comment '��˼���',
   CHECK_OPINION        varchar(255) comment '������',
   HAS_TITLEIMG         bool not null comment '�Ƿ��б���ͼƬ',
   IS_BOLD              bool not null comment '�Ƿ�Ӵ�',
   IS_DRAFT             bool not null comment '�Ƿ�ݸ�',
   IS_RECOMMEND         bool not null comment '�Ƿ��Ƽ�',
   IS_CHECK             bool not null comment '�Ƿ����',
   IS_DISABLED          bool not null comment '�Ƿ����',
   IS_REJECT            bool not null comment '�Ƿ񲵻�',
   PARAM1               varchar(255) comment '�����������1',
   PARAM2               varchar(255) comment '�����������2',
   PARAM3               varchar(255) comment '�����������3',
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
   CHECK_STEP           int comment '��˼���',
   CHECK_OPINION        varchar(255) comment '������',
   CHECK_TIME           datetime comment '���ʱ��',
   IP                   varchar(20) comment '����ԱIP',
   IS_PASS              bool not null comment 'ͨ���򲵻�',
   IS_FINAL             bool not null,
   primary key (ACLOG_ID)
)
comment = "�����־";

/*==============================================================*/
/* Table: ARTI_CTG                                              */
/*==============================================================*/
create table ARTI_CTG
(
   ARTICTG_ID           bigint not null auto_increment,
   WEBSITE_ID           bigint not null,
   LABEL                varchar(20) not null comment '��ʶID',
   NAME                 varchar(50) comment '����',
   IMG_WIDTH            int default 139 comment '����ͼƬ���',
   IMG_HEIGHT           int default 139 comment '����ͼƬ�߶�',
   HAS_TITLEIMG         bool not null default 1 comment '�Ƿ��б���ͼƬ',
   IS_DISABLED          bool not null default 0 comment '�Ƿ����ʹ��',
   primary key (ARTICTG_ID)
)
comment = "���µ�����";

/*==============================================================*/
/* Table: AUXI_CONFIG                                           */
/*==============================================================*/
create table AUXI_CONFIG
(
   CONFIG_ID            bigint not null,
   MSG_NEED_CHECK       bool default 0 comment '���԰��Ƿ���Ҫ���',
   MSG_IS_OPEN          bool default 1 comment '���԰��Ƿ񿪷�',
   MSG_ANONYMITY        varchar(20) default '��������' comment '���԰�������������',
   MSG_MAX_SIZE         int default 16384 comment '���԰����Դ�С(�ֽ�)',
   primary key (CONFIG_ID)
)
comment = "����ϵͳ���ñ�";

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
   TITLE                varchar(200) comment '���Ա���',
   CONTENT_MEMBER       longtext comment '��������',
   CONTENT_ADMIN        longtext comment '�ظ�����',
   EMAIL                varchar(100) comment '��������',
   PHONE                varchar(100) comment '��ϵ�绰',
   QQ                   varchar(50) comment 'QQ',
   IP                   varchar(20) comment '����IP',
   CREATE_TIME          datetime comment '����ʱ��',
   REPLY_TIME           datetime comment '�ظ�ʱ��',
   IS_CHECK             bool not null comment '�Ƿ����',
   IS_RECOMMEND         bool not null comment '�Ƿ��Ƽ�',
   IS_DISABLED          bool not null comment '�Ƿ����',
   primary key (MSG_ID)
)
comment = "���԰�����";

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
comment = "�������";

/*==============================================================*/
/* Table: CMS_ADMIN                                             */
/*==============================================================*/
create table CMS_ADMIN
(
   ADMIN_ID             bigint not null,
   WEBSITE_ID           bigint not null,
   CHECK_RIGHT          int default 0 comment '��˼��𣨵ڼ���',
   primary key (ADMIN_ID)
)
comment = "jeecms����Ա";

/*==============================================================*/
/* Table: CMS_CHANNEL                                           */
/*==============================================================*/
create table CMS_CHANNEL
(
   CHANNEL_ID           bigint not null auto_increment,
   WEBSITE_ID           bigint not null,
   PARENT               bigint comment '����Ŀ',
   MODEL_ID             bigint not null,
   SYS_TYPE             varchar(20) not null comment 'ϵͳ����',
   PATH                 varchar(100) comment '��Ŀ·��',
   NAME                 varchar(100) comment '��Ŀ����',
   TITLE_IMG            varchar(100) comment '��ĿͼƬ',
   TPL_INDEX            varchar(100) comment '��Ŀҳģ��·��',
   TPL_CONTENT          varchar(100) comment '����ҳģ��·��',
   TITLE                varchar(255) comment 'TITLE',
   KEYWORDS             varchar(255) comment 'KEYWORDS',
   DESCRIPTION          varchar(255) comment 'DESCRIPTION',
   VISIT_TOTAL          bigint comment '�ܹ����ʴ���',
   VISIT_TODAY          bigint comment '������ʴ���',
   STAT_DATE            date comment 'ͳ������',
   OUTER_URL            varchar(255) comment '�ⲿ����',
   CONTRIBUTE_LEVEL     int comment '������һ�����ԱͶ��',
   PRIORITY             int not null comment '��Ŀ����˳��',
   HAS_CHILD            bool not null comment '�Ƿ�������ӽڵ�',
   IS_DISPLAY           bool not null comment '�Ƿ���ʾ',
   PARAM1               varchar(255) comment '��Ŀ�������1',
   PARAM2               varchar(255) comment '��Ŀ�������2',
   PARAM3               varchar(255) comment '��Ŀ�������3',
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
   SYS_TYPE             varchar(20) not null comment 'ϵͳ����',
   NAME                 varchar(100) comment 'ģ������',
   SHORT_NAME           varchar(20) comment '���',
   TPL_PREFIX_CHANNEL   varchar(20) comment '��Ŀģ��ǰ׺',
   TPL_PREFIX_CONTENT   varchar(20) comment '����ģ��ǰ׺',
   HAS_CHILD            bool not null default 1 comment '�Ƿ����������Ŀ',
   primary key (MODEL_ID)
)
comment = "��Ŀģ��";

/*==============================================================*/
/* Table: CMS_CHNL_MODEL_ITEM                                   */
/*==============================================================*/
create table CMS_CHNL_MODEL_ITEM
(
   MITEM_ID             bigint not null auto_increment,
   MODEL_ID             bigint not null,
   NAME                 varchar(100) not null comment '������',
   LABEL                varchar(100) comment 'LABEL����',
   HELP                 varchar(255) comment '������Ϣ',
   INPUT_TYPE           varchar(20) comment '������',
   PRIORITY             int not null comment '����˳��',
   IS_CHECKED           bool not null comment '�Ƿ�ѡ��',
   primary key (MITEM_ID)
)
comment = "��Ŀģ�Ͷ���";

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
   TITLE                varchar(200) comment '����',
   CONTENT_GUEST        longtext comment '�û���������',
   CONTENT_ADMIN        longtext comment '����Ա�ظ�����',
   CREATE_TIME          datetime comment '����ʱ��',
   REPLAY_TIME          datetime comment '�ظ�ʱ��',
   IP                   varchar(50) comment '������IP',
   IS_CHECK             bool not null comment '�Ƿ����',
   IS_RECOMMEND         bool not null comment '�Ƿ��Ƽ�',
   IS_DISABLED          bool not null comment '�Ƿ����',
   primary key (COMMENT_ID)
)
comment = "jeecms���۱�";

/*==============================================================*/
/* Table: CMS_CONFIG                                            */
/*==============================================================*/
create table CMS_CONFIG
(
   CONFIG_ID            bigint not null,
   COMMENT_NEED_CHECK   bool default 0 comment '�����Ƿ���Ҫ���',
   CHECK_COUNT          int default 0 comment '������Ҫ��˵Ĵ���',
   DEFAULT_SYSTEM       varchar(20) default 'article' comment 'Ĭ��ϵͳ',
   primary key (CONFIG_ID)
)
comment = "jeecms���ñ�";

/*==============================================================*/
/* Table: CMS_MEMBER                                            */
/*==============================================================*/
create table CMS_MEMBER
(
   MEMBER_ID            bigint not null,
   WEBSITE_ID           bigint not null,
   GROUP_ID             bigint not null,
   SCORE                int default 0 comment '����',
   COUPON               int default 0 comment '�ㄻ',
   IS_DISABLED          bool not null default 0 comment '�Ƿ����',
   primary key (MEMBER_ID)
)
comment = "jeecms��Ա";

/*==============================================================*/
/* Table: CMS_MEMBER_GROUP                                      */
/*==============================================================*/
create table CMS_MEMBER_GROUP
(
   GROUP_ID             bigint not null auto_increment,
   WEBSITE_ID           bigint not null,
   NAME                 varchar(100) comment '����',
   GROUP_LEVEL          int default 10 comment '��ȼ���ԽСԽ�ߣ�',
   DESCRIPTION          varchar(255) comment '����',
   UPLOAD_SIZE          int default 0 comment '�ϴ��ļ��Ĵ�С����λKB',
   primary key (GROUP_ID)
)
comment = "jeecms�û���";

/*==============================================================*/
/* Table: CMS_RECOMMEND_GROUP                                   */
/*==============================================================*/
create table CMS_RECOMMEND_GROUP
(
   RGROUP_ID            bigint not null auto_increment,
   CHANNEL_ID           bigint not null,
   WEBSITE_ID           bigint not null,
   SYS_TYPE             char(4) not null comment 'ϵͳ����',
   NAME                 varchar(50) comment '�Ƽ�����',
   DESCRIPTION          varchar(250) comment '����',
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
   SYS_TYPE             char(4) not null comment 'ϵͳ����',
   TITLE                varchar(250) comment '�Ƽ��ı���',
   DESCRIPTION          varchar(255) comment '�Ƽ�������',
   PIC_PATH             varchar(250) comment 'ͼƬ��ַ������url',
   URL                  varchar(250) comment '�Ƽ����ӣ�����url',
   COLOR                varchar(20) comment '�������ɫ',
   PRIORITY             int not null comment '���ȼ�����ʾ���Ⱥ�˳��',
   IS_CHECK             bool not null comment '�Ƿ����',
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
   CREATE_TIME          datetime comment '����ʱ��',
   IS_DISABLED          bool not null default 0 comment '�Ƿ����',
   primary key (ADMIN_ID)
)
comment = "���Ĺ���Ա";

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
   PARENT               bigint comment '������',
   NAME                 varchar(100),
   URL                  varchar(200),
   FUNCS                longtext comment '�����б�����@�ָ�',
   DESCRIPTION          varchar(250),
   PRIORITY             int not null comment '���ܲ˵�����˳��',
   IS_MENU              bool not null comment '�Ƿ�Ϊ�˵�',
   primary key (FUNCTION_ID)
)
comment = "���ܱ�";

/*==============================================================*/
/* Table: CORE_MEMBER                                           */
/*==============================================================*/
create table CORE_MEMBER
(
   MEMBER_ID            bigint not null auto_increment,
   USER_ID              bigint not null,
   WEBSITE_ID           bigint not null,
   NICK_NAME            varchar(50) comment '�ǳ�',
   CREATE_TIME          datetime comment '����ʱ��',
   QQ                   varchar(100) comment 'QQ',
   MSN                  varchar(100) comment 'MSN',
   IS_DISABLED          bool not null default 0 comment '�Ƿ����',
   primary key (MEMBER_ID)
)
comment = "��Ա��";

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
comment = "��ɫ��";

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
comment = "ģ�巽����";

/*==============================================================*/
/* Table: CORE_USER                                             */
/*==============================================================*/
create table CORE_USER
(
   USER_ID              bigint not null auto_increment,
   LOGIN_NAME           varchar(50) not null comment '��¼��',
   REAL_NAME            varchar(50) comment '��ʵ����',
   PASSWORD             char(32) comment '����',
   EMAIL                varchar(100) comment '�����ʼ�',
   FAX                  varchar(250) comment '����',
   TEL                  varchar(250) comment '�绰',
   MOBILE               varchar(250) comment '�ֻ�',
   ZIP                  varchar(20) comment '�ʱ�',
   ADDRESS              varchar(250) comment '��ϵ��ַ',
   GENDER               bool comment '�Ա�',
   BIRTHDAY             date comment '��������',
   CREATE_TIME          datetime comment '����ʱ��',
   LAST_LOGIN_TIME      datetime comment '����¼ʱ��',
   LAST_LOGIN_IP        varchar(50) comment '����¼IP',
   CURRENT_LOGIN_TIME   datetime comment '��ǰ��¼ʱ��',
   CURRENT_LOGIN_IP     varchar(50) comment '��ǰ��¼IP',
   LOGIN_COUNT          bigint default 0 comment '�ܹ���¼����',
   IS_DISABLED          bool not null default 0 comment '�Ƿ����',
   primary key (USER_ID),
   key AK_LOGIN_NAME (LOGIN_NAME)
)
comment = "ͳһ�û���";

/*==============================================================*/
/* Table: CORE_WEBSITE                                          */
/*==============================================================*/
create table CORE_WEBSITE
(
   WEBSITE_ID           bigint not null auto_increment,
   PARENT               bigint comment '��վ��',
   UNITED               bigint comment '��Ա����վ��',
   DOMAIN               varchar(50) not null comment '����',
   RES_PATH             varchar(20) not null comment '��Դ·��',
   RES_DOMAIN           varchar(50) comment '��Դ��ַ',
   BASE_DOMAIN          varchar(50) comment '������',
   DOMAIN_ALIAS         varchar(250) comment '�ɴ�������������;�ָ�',
   PORT                 int comment '����Ķ˿ں�',
   CONTEXT_PATH         varchar(20) comment 'Ӧ�ò����·��',
   NAME                 varchar(100) comment 'վ������',
   SHORT_NAME           varchar(20) comment '���',
   SUFFIX               varchar(20) default 'htm' comment '���ʺ�׺��������htm��asp��php��',
   CURRENT_SYSTEM       varchar(20) default 'jeecms' comment '��ǰϵͳ��jeecms,jeeshop,jeeforum��',
   COOKIE_KEY           varchar(20) default '_jeecms' comment 'ϵͳcookieʶ����',
   OWNER_NAME           varchar(100) default '' comment '��վӵ��������',
   OWNER_IDENTITY       varchar(100) default '' comment '��վӵ�������֤��',
   COMPANY              varchar(200) default '' comment '��˾����',
   COPYRIGHT            varchar(255) default '' comment '��Ȩ��Ϣ',
   RECORD_CODE          varchar(255) default '' comment '������',
   EMAIL                varchar(100) default '' comment '��վӵ���ߵ�������',
   PHONE_CODE           varchar(200) default '' comment '�绰����',
   MOBILE_CODE          varchar(200) default '' comment '�ֻ�����',
   CREATE_TIME          datetime comment 'վ�㴴��ʱ��',
   EMAIL_CHARSET        varchar(20) default 'gbk' comment '�ʼ����ͱ���',
   EMAIL_HOSTNAME       varchar(50) default '' comment '�ʼ����ͷ�����',
   EMAIL_ACCOUNT        varchar(100) default '' comment '��վ�����˺�',
   EMAIL_USER_NAME      varchar(50) default '' comment '��վ��������',
   EMAIL_USER_ID        varchar(100) default '' comment '��վ�����¼��',
   EMAIL_USER_PWD       varchar(50) default '' comment '��վ��������',
   EMAIL_SUBJECT        varchar(255) default '' comment '�ʼ����⣨���⣩',
   EMAIL_CONTENT        longtext comment '�ʼ�����',
   CLOSE_REASON         varchar(255) default '��վ��ʱ�ر�' comment '�ر�ԭ��',
   IS_CLOSE             bool not null default 0 comment '�Ƿ�ر���վ����̨�Կɷ��ʣ�',
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
   TITLE                varchar(255) comment '����',
   DESCRIPTION          varchar(255) comment '����',
   VOTE_COUNT           bigint comment 'ͶƱ����',
   PRIORITY             int not null comment '��ʾ���ȼ�',
   primary key (VOTEITEM_ID)
)
comment = "ͶƱ��";

/*==============================================================*/
/* Table: VOTE_RECORD                                           */
/*==============================================================*/
create table VOTE_RECORD
(
   VRECORD_ID           bigint not null auto_increment,
   VTOPIC_ID            bigint not null,
   MEMBER_ID            bigint,
   VOTE_TIME            datetime comment 'ͶƱʱ��',
   VOTE_IP              varchar(50) comment 'ͶƱIP',
   VOTE_COOKIE          char(32) comment 'ͶƱcookie',
   primary key (VRECORD_ID)
)
comment = "ͶƱ��¼";

/*==============================================================*/
/* Table: VOTE_TOPIC                                            */
/*==============================================================*/
create table VOTE_TOPIC
(
   VTOPIC_ID            bigint not null auto_increment,
   WEBSITE_ID           bigint not null,
   TITLE                varchar(255) comment '����',
   DESCRIPTION          varchar(255) comment '����',
   TOTAL_COUNT          bigint comment 'ͶƱ����',
   START_TIME           datetime comment '��ʼʱ��',
   END_TIME             datetime comment '����ʱ��',
   REPEATE_HOUR         int comment '�ظ�ͶƱ����ʱ��(��λСʱ)',
   MULTI_SELECT         int comment '������ѡ����',
   IS_RESTRICT_MEMBER   bool not null comment '�Ƿ����ƻ�ԱID',
   IS_RESTRICT_IP       bool not null comment '�Ƿ�����IP',
   IS_RESTRICT_COOKIE   bool not null comment '�Ƿ�����cookie',
   IS_CURRENT           bool not null comment '�Ƿ�Ϊ����ͶƱ',
   IS_DISABLED          bool not null comment '�Ƿ��ֹͶƱ',
   primary key (VTOPIC_ID)
)
comment = "ͶƱ����";

alter table VOTE_ITEM add constraint FK_VITEM_TOPIC foreign key (VTOPIC_ID)
      references VOTE_TOPIC (VTOPIC_ID) on delete restrict on update restrict;

alter table VOTE_RECORD add constraint FK_VRECORD_GUEST foreign key (MEMBER_ID)
      references CORE_MEMBER (MEMBER_ID) on delete restrict on update restrict;

alter table VOTE_RECORD add constraint FK_VRECORD_TOPIC foreign key (VTOPIC_ID)
      references VOTE_TOPIC (VTOPIC_ID) on delete restrict on update restrict;

alter table VOTE_TOPIC add constraint FK_VTOPIC_WEBSITE foreign key (WEBSITE_ID)
      references CORE_WEBSITE (WEBSITE_ID) on delete restrict on update restrict;