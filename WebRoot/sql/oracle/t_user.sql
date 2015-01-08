-- Create table
create table T_USER
(
  id       VARCHAR2(50) not null,
  username VARCHAR2(50) not null,
  password VARCHAR2(50)
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_USER
  add constraint PK_T_USER primary key (ID)
  using index;