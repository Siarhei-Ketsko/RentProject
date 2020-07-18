create table m_tools
(
  id bigserial not null
    constraint m_tools_pk
      primary key,
  brand varchar(200) not null,
  model varchar(100) not null,
  personal_number varchar not null,
  price double precision not null,
  availability boolean default true not null
);

alter table m_tools owner to toolsadmin;

create unique index m_tools_id_uindex
  on m_tools (id);

create index m_tools_model_index
  on m_tools (model);

create index m_tools_availability_index
  on m_tools (availability);

create index m_tools_brand_index
  on m_tools (brand);

create table m_users
(
  id bigserial not null
    constraint m_users_pk
      primary key,
  username varchar(200) not null,
  surname varchar(200) not null,
  patronymic varchar(200) not null,
  phone varchar(20) not null,
  series_passport varchar(2) not null,
  number_passport integer not null,
  address varchar(300) not null,
  login varchar(200) not null,
  password varchar(1000) not null,
  email varchar(100) not null,
  verified boolean default false not null
);

alter table m_users owner to toolsadmin;

create unique index m_users_id_uindex
  on m_users (id);

create index m_users_phone_index
  on m_users (phone);

create index m_users_surname_index
  on m_users (surname);

create index m_users_username_index
  on m_users (username);

create unique index m_users_email_uindex
  on m_users (email);

create unique index m_users_login_uindex
  on m_users (login);

create table m_contract
(
  id bigserial not null
    constraint m_contract_pk
      primary key,
  users_id bigint not null
    constraint m_contract_m_users_id_fk
      references m_users,
  contract_date date not null,
  issue_date date not null,
  return_date date not null,
  rent_price double precision
);

alter table m_contract owner to toolsadmin;

create unique index m_contract_id_uindex
  on m_contract (id);

create index m_contract_issue_date_return_date_index
  on m_contract (issue_date, return_date);

create table m_tools_condition
(
  id bigserial not null
    constraint m_tools_condition_pk
      primary key,
  id_m_contract bigint not null
    constraint m_tools_condition_m_contract_id_fk
      references m_contract,
  id_m_tools bigint not null,
  defect_description text,
  check_date date not null
);

alter table m_tools_condition owner to toolsadmin;

create unique index m_tools_condition_id_uindex
  on m_tools_condition (id);

create table m_roles
(
  id bigserial not null
    constraint m_roles_pk
      primary key,
  role_name varchar(20) not null,
  users_id bigint not null
    constraint m_roles_m_users_id_fk
      references m_users
);

alter table m_roles owner to toolsadmin;

create unique index m_roles_id_uindex
  on m_roles (id);

create unique index m_roles_id_m_users_uindex
  on m_roles (users_id);

create table m_service
(
  id bigserial not null
    constraint service_pk
      primary key,
  service_name varchar(100) not null,
  service_address varchar(200) not null,
  is_deleted boolean default false not null
);

alter table m_service owner to toolsadmin;

create unique index service_id_uindex
  on m_service (id);

create index m_service_service_name_index
  on m_service (service_name);

create table m_contract_repair
(
  id bigserial not null
    constraint m_contract_repair_pk
      primary key,
  tools_id bigint not null
    constraint m_contract_repair_m_tools_id_fk
      references m_tools,
  service_id bigint not null
    constraint m_contract_repair_m_service_id_fk
      references m_service,
  total_cost_repair double precision not null
);

alter table m_contract_repair owner to toolsadmin;

create unique index m_contract_repair_id_uindex
  on m_contract_repair (id);

create index m_contract_repair_tools_id_index
  on m_contract_repair (tools_id);

create table l_contract_tools
(
  id bigserial not null
    constraint l_contract_tools_pk
      primary key,
  tools_id bigint not null
    constraint l_contract_tools_m_tools_id_fk
      references m_tools,
  contract_id bigint not null
    constraint l_contract_tools_m_contract_id_fk
      references m_contract
);

alter table l_contract_tools owner to toolsadmin;

create unique index l_contract_tools_id_uindex
  on l_contract_tools (id);