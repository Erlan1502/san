create table patients (id integer not null auto_increment, adress varchar(255), arterial_pressure varchar(255), date_of_arrival date not null,
                       date_of_birth date not null, days_of_stay integer not null, departure_day date not null, diagnosis varchar(255),
                       full_name varchar(50), phone_number varchar(255), procedures varchar(255), room integer not null, sex varchar(255),
                       primary key (id));

create table role (id integer not null auto_increment, name varchar(255), primary key (id));

create table user (id integer not null auto_increment, activation_code varchar(255), active bit not null, email varchar(50) not null,
                   password varchar(255), username varchar(50) not null, primary key (id));

create table user_roles (user_id integer not null, role_id integer not null);

alter table user_roles add constraint role_fk foreign key (role_id) references role (id);

alter table user_roles add constraint user_fk foreign key (user_id) references user (id);