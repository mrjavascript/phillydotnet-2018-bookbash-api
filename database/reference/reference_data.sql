
--
-- security role
insert into book_bash.security_role (role_name) values ('Administrator');

--
-- application_user
insert into book_bash.application_user (username, password_hash, date_disabled) VALUES ('administrator', NULL, NOW());

--
-- application_user_security
insert into book_bash.application_user_security (role_id, user_id) values (1, 1);

--
--  backlog_status
insert into book_bash.backlog_status (type_name) values ('Queued');
insert into book_bash.backlog_status (type_name) values ('Reading');
insert into book_bash.backlog_status (type_name) values ('Completed');