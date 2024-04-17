SET @ROOT_ROLE_ID = 1;
SET @ADMIN_ROLE_ID = 2;
SET @USER_ROLE_ID = 3;

set @ROOT_USER_ID = 1;
set @ADMIN_USER_ID = 2;
set @REGULAR_USER_ID = 3;

set @READ_USER_PERMISSION_ID = 1;
set @CREATE_USER_PERMISSION_ID = 2;
set @UPDATE_USER_PERMISSION_ID = 3;
set @DELETE_USER_PERMISSION_ID = 4;


-- Permissions
INSERT INTO ldap_login_lib_permission (id, name, description, creation_date, modification_date) values (@READ_USER_PERMISSION_ID, 'read_user', 'Read users', now(), now());
INSERT INTO ldap_login_lib_permission (id, name, description, creation_date, modification_date) values (@CREATE_USER_PERMISSION_ID, 'create_user', 'Create users', now(), now());
INSERT INTO ldap_login_lib_permission (id, name, description, creation_date, modification_date) values (@UPDATE_USER_PERMISSION_ID, 'update_user', 'Update users', now(), now());
INSERT INTO ldap_login_lib_permission (id, name, description, creation_date, modification_date) values (@DELETE_USER_PERMISSION_ID, 'delete_user', 'Delete users', now(), now());

ALTER SEQUENCE ldap_login_lib_permission_seq RESTART WITH (select (count(id) + 1) from ldap_login_lib_permission);


-- Roles
INSERT INTO ldap_login_lib_role (id, name, description, creation_date, modification_date) values (@ROOT_ROLE_ID, 'ROOT', 'ROOT role', now(), now());
INSERT INTO ldap_login_lib_role (id, name, description, creation_date, modification_date) values (@ADMIN_ROLE_ID, 'ADMIN', 'ADMIN role', now(), now());
INSERT INTO ldap_login_lib_role (id, name, description, creation_date, modification_date) values (@USER_ROLE_ID, 'USER', 'USER role', now(), now());

ALTER SEQUENCE ldap_login_lib_role_seq RESTART WITH (select (count(id) + 1) from ldap_login_lib_role);


-- Roles <---> Permissions
-- Root role permissions
INSERT INTO ldap_login_lib_role_permission (permission_id, role_id) values (@READ_USER_PERMISSION_ID,@ROOT_ROLE_ID);
INSERT INTO ldap_login_lib_role_permission (permission_id, role_id) values (@CREATE_USER_PERMISSION_ID,@ROOT_ROLE_ID);
INSERT INTO ldap_login_lib_role_permission (permission_id, role_id) values (@UPDATE_USER_PERMISSION_ID,@ROOT_ROLE_ID);
INSERT INTO ldap_login_lib_role_permission (permission_id, role_id) values (@DELETE_USER_PERMISSION_ID,@ROOT_ROLE_ID);
-- Admin role permissions
INSERT INTO ldap_login_lib_role_permission (permission_id, role_id) values (@READ_USER_PERMISSION_ID,@ADMIN_ROLE_ID);
INSERT INTO ldap_login_lib_role_permission (permission_id, role_id) values (@CREATE_USER_PERMISSION_ID,@ADMIN_ROLE_ID);
INSERT INTO ldap_login_lib_role_permission (permission_id, role_id) values (@UPDATE_USER_PERMISSION_ID,@ADMIN_ROLE_ID);
-- User role permissions
INSERT INTO ldap_login_lib_role_permission (permission_id, role_id) values (@READ_USER_PERMISSION_ID,@USER_ROLE_ID);




-- Root user
-- Hashed password: 12345678
INSERT INTO ldap_login_lib_user (id, ldap_flag, email, name, password, surname, username, creation_date, modification_date) values (@ROOT_USER_ID, 0, '1234@eroski.es', 'root', '$2a$10$J2Jy1kv0qYJ66KtkP.PCg.Jirq5qC6zQzn1JLVEWD/5.t6Xgx/LCi', 'root', 'root', now(), now());

-- Admin user
-- Hashed password: 1234567
INSERT INTO ldap_login_lib_user (id, ldap_flag, email, name, password, surname, username, creation_date, modification_date) values (@ADMIN_USER_ID, 0, 'example_admin@eroski.es', 'admin', '$2a$10$qTnS0c8dnZfZdATzR9U1EuPt/V/E3N16LcReIEJNbihUiFito2lra', 'admin', 'admin', now(), now());

-- Regular user
-- Hashed password: 123456
INSERT INTO ldap_login_lib_user (id, ldap_flag, email, name, password, surname, username, creation_date, modification_date) values (@REGULAR_USER_ID, 0, 'example_user@eroski.es', 'user', '$2a$10$7vfRTB0fB6QResxLVb/xb.iHAh6fzGUHSjJVp5x35O9or6W57zHw.', 'user', 'user', now(), now());

ALTER SEQUENCE ldap_login_lib_user_seq RESTART WITH (select (count(id) + 1) from ldap_login_lib_user);


-- Setting roles of root user (root + admin + user)
INSERT INTO ldap_login_lib_user_role (user_id, role_id) values (@ROOT_USER_ID,@ROOT_ROLE_ID);
INSERT INTO ldap_login_lib_user_role (user_id, role_id) values (@ROOT_USER_ID,@ADMIN_ROLE_ID);
INSERT INTO ldap_login_lib_user_role (user_id, role_id) values (@ROOT_USER_ID,@USER_ROLE_ID);

-- Setting roles of admin user (admin + user)
INSERT INTO ldap_login_lib_user_role (user_id, role_id) values (@ADMIN_USER_ID,@ADMIN_ROLE_ID);
INSERT INTO ldap_login_lib_user_role (user_id, role_id) values (@ADMIN_USER_ID,@USER_ROLE_ID);

-- Setting roles of regular user (user)
INSERT INTO ldap_login_lib_user_role (user_id, role_id) values (@REGULAR_USER_ID,@USER_ROLE_ID);


-- Setting app tables
CREATE TABLE IF NOT EXISTS proyecto
(
    cod_proyecto   BIGINT PRIMARY KEY,
    nombre TEXT NULL,
    tipo TEXT NULL,
    fechaInicio DATE NULL,
    comunidad_autonoma INT NULL,
    provincia INT NULL,
    municipio INT NULL,
    descripcion TEXT NULL
);

CREATE TABLE IF NOT EXISTS escenario
(
    id BIGSERIAL PRIMARY KEY,
    cod_proyecto BIGINT NOT NULL REFERENCES proyecto,
    nombre TEXT NULL,
    municipio INT NULL,
    descripcion TEXT NULL,
    latitud NUMERIC NULL,
    longitud NUMERIC NULL,
    radio_incidencia NUMERIC NULL
);

CREATE TABLE IF NOT EXISTS centro
(
    id BIGSERIAL PRIMARY KEY,
    tipo TEXT NULL,
    nombre TEXT NULL,
    latitud NUMERIC NULL,
    longitud NUMERIC NULL,
    comunidad_autonoma INT NULL,
    provincia INT NULL,
    municipio INT NULL,
    direccion TEXT NULL,
    acceso_parking BOOLEAN NULL,
    mostrador_fresco BOOLEAN NULL
);

CREATE TABLE IF NOT EXISTS variacion
(
    id BIGSERIAL PRIMARY KEY,
    tipo TEXT NULL,
    id_escenario BIGINT NOT NULL REFERENCES escenario
);

CREATE TABLE IF NOT EXISTS variacion_centro
(
    id_variacion BIGINT REFERENCES variacion,
    id_centro BIGINT REFERENCES centro,
    PRIMARY KEY (id_variacion, id_centro)
);