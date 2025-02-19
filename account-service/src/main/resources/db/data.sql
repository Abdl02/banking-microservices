MERGE INTO accounts (id, account_number, balance, status, type, customer_id)
VALUES
    (1, '1000000001', 5000.00, 'ACTIVE', 'SAVING', 1234567),
    (2, '1000000002', 12000.50, 'ACTIVE', 'SALARY', 1234568),
    (3, '1000000003', 7500.75, 'INACTIVE', 'INVESTMENT', 1234569);