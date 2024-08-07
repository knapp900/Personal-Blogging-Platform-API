-- Вставка пользователя-администратора в таблицу `users`
INSERT INTO users (username, firstname, lastname, password, email, date_of_creation, is_active)
VALUES ('admin', 'Admin', 'Admin', '{bcrypt}$2a$10$EwzEaPVx5pYckwpXbLOTKOTBLbusyyRXsuwIBzM3x1lypdXxPs/Nm', 'admin@example.com', CURRENT_DATE, true);

-- Получение ID созданного пользователя
-- (предполагается, что `id` является автоинкрементируемым первичным ключом)
WITH inserted_user AS (
   SELECT id FROM users WHERE username = 'admin'
)
-- Вставка ролей пользователя в таблицу `user_roles`
INSERT INTO user_roles (user_id, role)
SELECT id, 'ADMIN' FROM inserted_user;
