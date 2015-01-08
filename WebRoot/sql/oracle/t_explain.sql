-- Create table
create table T_EXPLAIN
(
  id        VARCHAR2(50) not null,
  plan_id   VARCHAR2(50),
  excu_time DATE
)

-- Add comments to the table 
comment on table T_EXPLAIN
  is '������¼����';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_EXPLAIN
  add constraint PK_T_EXPLAIN primary key (ID)
  using index;