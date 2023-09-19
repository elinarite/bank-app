--liquibase formatted sql
--changeset elina:v.0.1.0-test-data

INSERT INTO bank_app.managers (id, first_name, last_name, email, is_active)
VALUES (1, 'John', 'Doe', 'test1@gmail.com', true),
       (2, 'Alice', 'Smith', 'test2@gmail.com', true),
       (3, 'Michael', 'Johnson', 'test3@gmail.com', true),
       (4, 'Emily', 'Brown', 'test4@gmail.com', true),
       (5, 'David', 'Davis', 'test5@gmail.com', true),
       (6, 'Sarah', 'Miller', 'test6@gmail.com', true),
       (7, 'James', 'Wilson', 'test7@gmail.com', false),
       (8, 'Olivia', 'Taylor', 'test8@gmail.com', false),
       (9, 'Daniel', 'Jones', 'test9@gmail.com', true),
       (10, 'Ava', 'White', 'test10@gmail.com', true);

INSERT INTO bank_app.clients (id, status, tax_code, first_name, last_name, email, address, phone)
VALUES (UUID_TO_BIN('2c26dd1e-498c-11ee-bbf9-10d0e2fa4154'), 1, '321-12345-321', 'John', 'Doe',
        'john.doe@example.com',
        'Alexanderplatz 7, 10178 Berlin', '+1234567890'),
       (UUID_TO_BIN('2c26e002-498c-11ee-bbf9-10d0e2fa4154'), 1, '123-12345-123', 'Alice', 'Johnson',
        'alice.johnson@example.com',
        'Kurfürstendamm 216, 10719 Berlin', '+1234567999'),
       (UUID_TO_BIN('2c26e124-498c-11ee-bbf9-10d0e2fa4154'), 2, '123-34234-123', 'Michael', 'Smith',
        'michael.smith@example.com',
        'Marienplatz 22, 80331 München', '+9876543210'),
       (UUID_TO_BIN('2c26e1c4-498c-11ee-bbf9-10d0e2fa4154'), 2, '123-12345-123', 'Emily', 'Brown',
        'emily.brown@example.com',
        'Unter den Linden 77, 10117 Berlin', '+4444444444'),
       (UUID_TO_BIN('2c26e246-498c-11ee-bbf9-10d0e2fa4154'), 1, '123-12345-123', 'David', 'Wilson',
        'david.wilson@example.com',
        'Heerstraße 12, 14052 Berlin', '+5555555555'),
       (UUID_TO_BIN('2c26e2d2-498c-11ee-bbf9-10d0e2fa4154'), 1, '123-12345-123', 'Sophia', 'Miller',
        'sophia.miller@example.com',
        'Schlossplatz 1, 70173 Stuttgart', '+6666666666'),
       (UUID_TO_BIN('2c26e354-498c-11ee-bbf9-10d0e2fa4154'), 3, '123-12345-123', 'James', 'Anderson',
        'james.anderson@example.com',
        'Friedrichstraße 96, 10117 Berlin', '+7777777777'),
       (UUID_TO_BIN('2c26e3d6-498c-11ee-bbf9-10d0e2fa4154'), 3, '123-12345-123', 'Olivia', 'Martinez',
        'olivia.martinez@example.com',
        'Am Zirkus 1, 10117 Berlin', '+8888888888'),
       (UUID_TO_BIN('2c26e462-498c-11ee-bbf9-10d0e2fa4154'), 1, '123-12345-123', 'Alexander', 'Lee',
        'alexander.lee@example.com',
        'Goethestraße 25, 60313 Frankfurt am Main', '+1234567890'),
       (UUID_TO_BIN('2c26e4ee-498c-11ee-bbf9-10d0e2fa4154'), 1, '123-12345-123', 'Ava', 'Garcia',
        'ava.garcia@example.com',
        'Goethestraße 20, 60313 Frankfurt am Main', '+1010101010');

INSERT INTO bank_app.accounts (id, client_id, manager_id, account_number, type, is_active, balance, currency_code)
VALUES (UUID_TO_BIN('38529bfe-498d-11ee-bbf9-10d0e2fa4154'), UUID_TO_BIN('2c26dd1e-498c-11ee-bbf9-10d0e2fa4154'), 1,
        '111122223333', 1, true, 1000.00, 'EUR'),
       (UUID_TO_BIN('3852a1a8-498d-11ee-bbf9-10d0e2fa4154'), UUID_TO_BIN('2c26e002-498c-11ee-bbf9-10d0e2fa4154'), 2,
        '111122224444', 2, true,
        2500.50, 'EUR'),
       (UUID_TO_BIN('38529f5a-498d-11ee-bbf9-10d0e2fa4154'), UUID_TO_BIN('2c26e124-498c-11ee-bbf9-10d0e2fa4154'), 2,
        '111122225555', 1, true,
        1500.25, 'EUR'),
       (UUID_TO_BIN('3852a220-498d-11ee-bbf9-10d0e2fa4154'), UUID_TO_BIN('2c26e1c4-498c-11ee-bbf9-10d0e2fa4154'), 1,
        '111122226666', 1, true,
        2000.00, 'EUR'),
       (UUID_TO_BIN('3852a02c-498d-11ee-bbf9-10d0e2fa4154'), UUID_TO_BIN('2c26e246-498c-11ee-bbf9-10d0e2fa4154'), 2,
        '777722223333', 2, true, 3000.75, 'EUR'),
       (UUID_TO_BIN('3852a298-498d-11ee-bbf9-10d0e2fa4154'), UUID_TO_BIN('2c26e2d2-498c-11ee-bbf9-10d0e2fa4154'), 4,
        '111122228888', 1, true, 500.00, 'EUR'),
       (UUID_TO_BIN('3852a0b8-498d-11ee-bbf9-10d0e2fa4154'), UUID_TO_BIN('2c26e354-498c-11ee-bbf9-10d0e2fa4154'), 3,
        '111144443333', 2, true, 700.00, 'EUR'),
       (UUID_TO_BIN('3852a310-498d-11ee-bbf9-10d0e2fa4154'), UUID_TO_BIN('2c26e3d6-498c-11ee-bbf9-10d0e2fa4154'), 4,
        '888822223333', 1, false, 1200.00, 'EUR'),
       (UUID_TO_BIN('3852a130-498d-11ee-bbf9-10d0e2fa4154'), UUID_TO_BIN('2c26e462-498c-11ee-bbf9-10d0e2fa4154'), 5,
        '999922223333', 2, false,
        800.25, 'USD'),
       (UUID_TO_BIN('3852a3e2-498d-11ee-bbf9-10d0e2fa4154'), UUID_TO_BIN('2c26e4ee-498c-11ee-bbf9-10d0e2fa4154'), 1,
        '333322223333', 1, false, 3500.00, 'USD');

INSERT INTO bank_app.transactions (id, debit_account_id, credit_account_id, type, amount, description)
VALUES (UUID_TO_BIN('d5ad9b48-4b3a-11ee-bbf9-10d0e2fa4154'), UUID_TO_BIN('38529bfe-498d-11ee-bbf9-10d0e2fa4154'),
        UUID_TO_BIN('3852a1a8-498d-11ee-bbf9-10d0e2fa4154'), 1, 50.00, 'Test1'),
       (UUID_TO_BIN('d5ad9d14-4b3a-11ee-bbf9-10d0e2fa4154'), UUID_TO_BIN('38529f5a-498d-11ee-bbf9-10d0e2fa4154'),
        UUID_TO_BIN('3852a220-498d-11ee-bbf9-10d0e2fa4154'), 2, 500.50, 'Test2'),
       (UUID_TO_BIN('d5ad9df0-4b3a-11ee-bbf9-10d0e2fa4154'), UUID_TO_BIN('3852a02c-498d-11ee-bbf9-10d0e2fa4154'),
        UUID_TO_BIN('3852a298-498d-11ee-bbf9-10d0e2fa4154'), 1, 20.25, 'Test3'),
       (UUID_TO_BIN('d5ad9e7c-4b3a-11ee-bbf9-10d0e2fa4154'), UUID_TO_BIN('3852a0b8-498d-11ee-bbf9-10d0e2fa4154'),
        UUID_TO_BIN('3852a310-498d-11ee-bbf9-10d0e2fa4154'), 2, 200.00, 'Test4'),
       (UUID_TO_BIN('d5ad9f08-4b3a-11ee-bbf9-10d0e2fa4154'), UUID_TO_BIN('3852a130-498d-11ee-bbf9-10d0e2fa4154'),
        UUID_TO_BIN('3852a3e2-498d-11ee-bbf9-10d0e2fa4154'), 3, 100.75, 'Test5');

INSERT INTO bank_app.products (id, product_typ, is_active, currency_code, interest_rate, product_limit)
VALUES (1, 'Auto Loan', true, 'EUR', 5.07, 50000),
       (2, 'Personal Loans', true, 'EUR', 4.5, 10000),
       (3, 'Investment Products', true, 'EUR', 1.2, 5000000),
       (4, 'Home Loan', true, 'USD', 2,  500000),
       (5, 'Overdraft', true, 'USD', 7.00, 500000);

INSERT INTO bank_app.agreements (id, account_id, product_id, interest_rate, is_active, sum)
VALUES (1, UUID_TO_BIN('38529bfe-498d-11ee-bbf9-10d0e2fa4154'), 1, 5.00, true, 5000),
       (2, UUID_TO_BIN('38529f5a-498d-11ee-bbf9-10d0e2fa4154'), 2, 3.00, true, 7000),
       (3, UUID_TO_BIN('3852a02c-498d-11ee-bbf9-10d0e2fa4154'), 3, 1.00, true, 25000),
       (4, UUID_TO_BIN('3852a0b8-498d-11ee-bbf9-10d0e2fa4154'), 1, 5.00, true, 5000),
       (5, UUID_TO_BIN('3852a130-498d-11ee-bbf9-10d0e2fa4154'), 5, 5.00, true, 500000);

