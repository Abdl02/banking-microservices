-- accounts table
CREATE TABLE IF NOT EXISTS accounts (
                                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                        account_number VARCHAR(10) NOT NULL UNIQUE,
    balance DECIMAL(15,2) NOT NULL,
    status VARCHAR(10) CHECK (status IN ('ACTIVE', 'INACTIVE', 'CLOSED')),
    type VARCHAR(15) CHECK (type IN ('SAVING', 'SALARY', 'INVESTMENT')),
    customer_id BIGINT NOT NULL
    );
