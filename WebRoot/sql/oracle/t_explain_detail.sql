-- Create table
create table T_EXPLAIN_DETAIL
(
  id                VARCHAR2(50) not null,
  explain_id        VARCHAR2(50),
  explain_idx       NUMBER(5),
  explain_operation VARCHAR2(100),
  explain_name      VARCHAR2(100),
  explain_rows      VARCHAR2(50),
  explain_bytes     VARCHAR2(50),
  explain_cost      VARCHAR2(50),
  explain_time      VARCHAR2(50)
)
-- Add comments to the table 
comment on table T_EXPLAIN_DETAIL
  is '∑÷ŒˆœÍ«È';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_EXPLAIN_DETAIL
  add constraint PK_T_EXPLAIN_DETAIL primary key (ID)
  using index;