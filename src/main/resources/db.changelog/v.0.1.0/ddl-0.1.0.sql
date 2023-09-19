--liquibase formatted sql
--changeset elina:v.0.1.0-ddl



create table if not exists bank_app.managers
(
    id         bigint primary key auto_increment,
    first_name varchar(50) not null,
    last_name  varchar(50) not null,
    email      varchar(60) not null,
    is_active  bool        not null default true,
    created_at timestamp            DEFAULT CURRENT_TIMESTAMP,
    update_at  timestamp            DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    unique (email)
);

create table if not exists bank_app.clients
(
    id         binary(16) primary key,
    status     int(1),
    tax_code   varchar(20),
    first_name varchar(50) not null,
    last_name  varchar(50) not null,
    email      varchar(60) not null,
    address    varchar(80) not null,
    phone      varchar(16) not null,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    update_at  timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    unique (tax_code, email)
);

create table if not exists bank_app.accounts
(
    id             binary(16) primary key,
    client_id      binary(16)  not null,
    manager_id     bigint      not null,
    account_number varchar(20) not null,
    type           int(1)      not null,
    is_active      boolean     not null default true,
    balance        decimal(15, 2)       DEFAULT 0 not null,
    currency_code  varchar(3)  not null,
    created_at     timestamp            DEFAULT CURRENT_TIMESTAMP,
    update_at      timestamp            DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    foreign key (manager_id) references managers (id),
    foreign key (client_id) references clients (id),
    unique (account_number)
);

create table if not exists bank_app.transactions
(
    id                binary(16) primary key,
    debit_account_id  binary(16)     not null,
    credit_account_id binary(16)     not null,
    type              int(1)         not null,
    amount            decimal(12, 4) not null,
    description       varchar(255)   not null,
    created_at        timestamp DEFAULT CURRENT_TIMESTAMP,
    foreign key (debit_account_id) references accounts (id),
    foreign key (credit_account_id) references accounts (id)
);

create table if not exists bank_app.products
(
    id            bigint primary key auto_increment,
    product_typ   varchar(70)   not null,
    is_active     boolean       not null default true,
    currency_code varchar(3)    not null,
    interest_rate decimal(6, 4) not null,
    product_limit int           not null,
    created_at    timestamp              DEFAULT CURRENT_TIMESTAMP,
    update_at     timestamp              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    unique (product_typ)
);

create table if not exists bank_app.agreements
(
    id            bigint primary key auto_increment,
    account_id    binary(16)     not null,
    product_id    bigint         not null,
    interest_rate decimal(6, 4)  not null,
    is_active     boolean        not null,
    sum           decimal(15, 2) not null,
    created_at    timestamp DEFAULT CURRENT_TIMESTAMP,
    update_at     timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    foreign key (account_id) references accounts (id),
    foreign key (product_id) references products (id)
);
