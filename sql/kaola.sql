drop database if exists kaola;
create database kaola;
use kaola

drop table if exists seller;
create table seller (
  id bigint NOT NULL AUTO_INCREMENT,
  username varchar(100) NOT NULL ,
  password varchar(100) NOT NULL ,
  telephone varchar(100) NOT NULL ,
  CONSTRAINT pk_user PRIMARY KEY(id)
) ENGINE=InnoDB charset=utf8;
create unique index idx_seller_username on seller(username);
create unique index idx_seller_telephone on seller(telephone);

insert into seller values(NULL ,'seller','relles','18220226374');

drop table if exists product;
create table product(
  id bigint not null auto_increment,
  title varchar(100) not null,
  summary varchar(100) not null,
  detail text not null,
  price double not null,
  img_data mediumblob not null,
  status tinyint,
  constraint pk_product primary key(id)
) ENGINE=InnoDB charset=utf8;

