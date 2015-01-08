-- Create table
create table T_PLAN
(
  id      VARCHAR2(50) not null,
  name    VARCHAR2(100),
  content CLOB
)
-- Add comments to the table 
comment on table T_PLAN
  is '计划数据';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_PLAN
  add constraint PK_T_PLAN primary key (ID)
  using index;