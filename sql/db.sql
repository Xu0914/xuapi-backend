use xuapi;

-- 接口信息
create table if not exists xuapi.`interface_info`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `name` varchar(256) not null comment '名称',
    `description` varchar(256) null comment '描述',
    `url` varchar(512) not null comment '接口地址',
    `requestParams` text not null comment '请求参数',
    `requestHeader` text null comment '请求头',
    `responseHeader` text null comment '响应头',
    `status` int default 0 not null comment '接口状态（0-关闭，1-开启）',
    `method` varchar(256) not null comment '请求类型',
    `userId` bigint not null comment '创建人',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDelete` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
    ) comment '接口信息';

insert into xuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('林志泽', '邓志泽', 'www.emely-lueilwitz.name', '龙瑾瑜', '姚俊驰', 0, '邓晋鹏', 63);
insert into xuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('龙鸿煊', '史烨霖', 'www.arlinda-cummerata.net', '魏浩轩', '侯明轩', 0, '钟思淼', 3269673);
insert into xuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('冯修杰', '张擎宇', 'www.rosario-mcglynn.name', '孙鹭洋', '孙熠彤', 0, '任荣轩', 971);
insert into xuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('叶思淼', '金航', 'www.noma-yundt.net', '余思源', '苏锦程', 0, '魏思源', 94967);
insert into xuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('周文', '邱鸿涛', 'www.rene-krajcik.info', '程思源', '孔昊强', 0, '潘振家', 5532);
insert into xuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('阎博涛', '吴乐驹', 'www.willard-greenholt.biz', '于天磊', '郑伟祺', 0, '叶子默', 45445313);
insert into xuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('刘鹭洋', '毛晓博', 'www.luvenia-lubowitz.org', '邵钰轩', '赖擎苍', 0, '钱航', 16041455);
insert into xuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('崔子涵', '陈聪健', 'www.lionel-nader.io', '曾明辉', '雷嘉熙', 0, '严鹭洋', 1942);
insert into xuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('杨钰轩', '刘煜城', 'www.roosevelt-wolf.org', '吕荣轩', '陈昊强', 0, '黎哲瀚', 8);
insert into xuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('冯天翊', '莫熠彤', 'www.reagan-schmidt.co', '钟修洁', '钟伟诚', 0, '陆语堂', 99);
insert into xuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('薛凯瑞', '段熠彤', 'www.andrew-oreilly.co', '韩金鑫', '张锦程', 0, '阎博涛', 13130);
insert into xuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('赖瑾瑜', '蒋果', 'www.wendy-beer.com', '郝峻熙', '钱晟睿', 0, '吴哲瀚', 0);
insert into xuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('唐聪健', '汪子默', 'www.jan-romaguera.io', '邱鑫鹏', '李乐驹', 0, '魏雪松', 132);
insert into xuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('方雨泽', '邹昊强', 'www.terry-berge.co', '赵立轩', '韩耀杰', 0, '袁俊驰', 145);
insert into xuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('韩伟泽', '唐明杰', 'www.james-rowe.net', '姚烨华', '贾绍齐', 0, '高浩宇', 582211);
insert into xuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('郑鹤轩', '郝博文', 'www.leon-batz.info', '吴楷瑞', '唐越泽', 0, '范笑愚', 633491);
insert into xuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('黄晓啸', '武晓啸', 'www.darrin-zulauf.biz', '贺修洁', '何立果', 0, '冯君浩', 92558);
insert into xuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('汪晋鹏', '邵昊焱', 'www.shannon-funk.com', '吴风华', '廖浩然', 0, '董立辉', 940);
insert into xuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('郝鹤轩', '石荣轩', 'www.morton-franecki.name', '蔡泽洋', '梁鹤轩', 0, '彭文', 8118821);
insert into xuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('孟文', '余明辉', 'www.collette-jast.info', '陆睿渊', '朱哲瀚', 0, '尹鹏飞', 50);


-- 用户调用接口关系表
create table if not exists xuapi.`user_interface_info`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `userId` bigint not null comment '调用用户 id',
    `interfaceInfoId` bigint not null comment '接口 id',
    `totalNum` int default 0 not null comment '总调用次数',
    `leftNum` int default 0 not null comment '剩余调用次数',
    `status` int default 0 not null comment '0-正常，1-禁用',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDelete` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '用户调用接口关系表';
