drop table if exists users;
drop table if exists roles;
drop table if exists permissions;
drop table if exists users_roles;
drop table if exists roles_permissions;

create table users (
  id bigint auto_increment,
  username varchar(100),
  password varchar(100),
  salt varchar(100),
  locked bool default false,
  constraint pk_users primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_users_username on users(username);

create table roles (
  id bigint auto_increment,
  role varchar(100),
  description varchar(100),
  available bool default false,
  constraint pk_roles primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_roles_role on roles(role);

create table permissions (
  id bigint auto_increment,
  permission varchar(100),
  description varchar(100),
  available bool default false,
  constraint pk_permissions primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_permissions_permission on permissions(permission);

create table users_roles (
  user_id bigint,
  role_id bigint,
  constraint pk_users_roles primary key(user_id, role_id)
) charset=utf8 ENGINE=InnoDB;

create table roles_permissions (
  role_id bigint,
  permission_id bigint,
  constraint pk_roles_permissions primary key(role_id, permission_id)
) charset=utf8 ENGINE=InnoDB;