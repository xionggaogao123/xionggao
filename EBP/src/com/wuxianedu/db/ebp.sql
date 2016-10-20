create database ebp;

use ebp;

create table category(
	id int primary key not null auto_increment,
	name varchar(100) not null unique,
	parent_id int default null
);

insert into category(id, name, parent_id) values(1, '小说成功励志', null);
insert into category(id, name, parent_id) values(11, '言情', 1);
insert into category(id, name, parent_id) values(12, '嫌疑推理', 1);
insert into category(id, name, parent_id) values(13, '科幻', 1);
insert into category(id, name, parent_id) values(14, '世界名著', 1);
insert into category(id, name, parent_id) values(15, '中国名著', 1);
insert into category(id, name, parent_id) values(16, '近代史', 1);
insert into category(id, name, parent_id) values(17, '职场', 1);
insert into category(id, name, parent_id) values(18, '为人处世', 1);
insert into category(id, name, parent_id) values(19, '个人修养', 1);

insert into category(id, name, parent_id) values(2, '文学  原创文学', null);
insert into category(id, name, parent_id) values(21, '文学推理', 2);
insert into category(id, name, parent_id) values(22, '影视文学', 2);
insert into category(id, name, parent_id) values(23, '文学评论与鉴赏', 2);
insert into category(id, name, parent_id) values(24, '明间文学', 2);
insert into category(id, name, parent_id) values(25, '戏剧与曲艺', 2);
insert into category(id, name, parent_id) values(26, '散文随笔', 2);
insert into category(id, name, parent_id) values(27, '女性小说', 2);
insert into category(id, name, parent_id) values(28, '古代言情', 2);
insert into category(id, name, parent_id) values(29, '游戏竞技', 2);

insert into category(id, name, parent_id) values(3, '人文社科  自然科学', null);
insert into category(id, name, parent_id) values(31, '哲学与宗教', 3);
insert into category(id, name, parent_id) values(32, '社会学', 3);
insert into category(id, name, parent_id) values(33, '国学', 3);
insert into category(id, name, parent_id) values(34, '语言文学', 3);
insert into category(id, name, parent_id) values(35, '文化', 3);
insert into category(id, name, parent_id) values(36, '教育', 3);
insert into category(id, name, parent_id) values(37, '心理学', 3);
insert into category(id, name, parent_id) values(38, '随笔', 3);
insert into category(id, name, parent_id) values(39, '图书馆学', 3);

insert into category(id, name, parent_id) values(4, '生活  杂志', null);
insert into category(id, name, parent_id) values(41, '美食', 4);
insert into category(id, name, parent_id) values(42, '养生保健', 4);
insert into category(id, name, parent_id) values(43, '亲子教育', 4);
insert into category(id, name, parent_id) values(44, '家居休闲', 4);
insert into category(id, name, parent_id) values(45, '新闻人物', 4);
insert into category(id, name, parent_id) values(46, '商业财经', 4);

insert into category(id, name, parent_id) values(5, '动漫绘本', null);
insert into category(id, name, parent_id) values(51, '幽默', 5);
insert into category(id, name, parent_id) values(52, '轻小说', 5);
insert into category(id, name, parent_id) values(53, '卡通图', 5);
insert into category(id, name, parent_id) values(54, '插图本', 5);
insert into category(id, name, parent_id) values(55, '设计', 5);
insert into category(id, name, parent_id) values(56, '书法', 5);
insert into category(id, name, parent_id) values(57, '摄影', 5);
insert into category(id, name, parent_id) values(58, '建筑', 5);
insert into category(id, name, parent_id) values(59, '舞蹈', 5);

insert into category(id, name, parent_id) values(6, '政治军事', null);
insert into category(id, name, parent_id) values(61, '政治军事', 6);
insert into category(id, name, parent_id) values(62, '政治理论',6);
insert into category(id, name, parent_id) values(63, '中国共产党', 6);
insert into category(id, name, parent_id) values(64, '外交国际关系马克思主义理论', 6);
insert into category(id, name, parent_id) values(65, '中国政治', 6);

insert into category(id, name, parent_id) values(7, '小说  成功励志', null);
insert into category(id, name, parent_id) values(71, '言情', 7);
insert into category(id, name, parent_id) values(72, '嫌疑推理', 7);
insert into category(id, name, parent_id) values(73, '科幻', 7);
insert into category(id, name, parent_id) values(74, '世界名著', 7);
insert into category(id, name, parent_id) values(75, '中国名著', 7);
insert into category(id, name, parent_id) values(76, '近代史', 7);
insert into category(id, name, parent_id) values(77, '职场', 7);
insert into category(id, name, parent_id) values(78, '为人处世', 7);
insert into category(id, name, parent_id) values(79, '个人修养', 7);

insert into category(id, name, parent_id) values(8, '中国总史', null);
insert into category(id, name, parent_id) values(81, '太洋州史', 8);
insert into category(id, name, parent_id) values(82, '地方志', 8);
insert into category(id, name, parent_id) values(83, '风俗习惯', 8);
insert into category(id, name, parent_id) values(84, '文物考古', 8);
insert into category(id, name, parent_id) values(85, '史学理论', 8);

create table book(
	id int primary key not null auto_increment,
	name varchar(40) not null,
	author varchar(20) not null,
	descration varchar(200) not null,
	oldprice double(5, 2) not null,
	newprice double(5, 2) not null,
	filename varchar(100) not null,
	amount int(11) not null,
	category_id int not null,
	constraint category_id_fk foreign key(category_id) references category(id)
);

create table adminuser(
	id int primary key not null,
	username varchar(20) not null unique,
	password varchar(50) not null
);

create table user(
	id int primary key not null auto_increment,
	name varchar(20),
	username varchar(20) not null unique,
	password varchar(50) not null,
	phone varchar(16) unique,
	gender varchar(2),
	registerTime datetime not null,
	balance double(10, 2) default 0
);

create table orders(
	id varchar(40) primary key not null,
	orderNum varchar(40) not null unique,
	createTime datetime not null,
	price double(10, 2) not null,
	isDelete boolean not null default false,
	user_id int not null,
	constraint user_id_fk foreign key(user_id) references user(id)
);

create table item(
	id int primary key not null auto_increment,
	price double(5, 2) not null,
	amount int not null,
	order_id varchar(40) not null,
	book_id int not null,
	constraint order_id_fk foreign key(order_id) references orders(id),
	constraint book_id_fk foreign key(book_id) references book(id)
);




