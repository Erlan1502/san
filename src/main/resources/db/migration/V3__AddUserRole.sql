insert into user_roles(user_id, role_id)
values ((SELECT user_id FROM user WHERE id = 1), (SELECT role_id FROM role WHERE id = 1));