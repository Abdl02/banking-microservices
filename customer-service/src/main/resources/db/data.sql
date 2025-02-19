MERGE INTO customers (id, name, legal_id, type, address)
VALUES
    (1234567, 'John Doe', 'A1B2C3D4E5', 'RETAIL', '123 Main Street, NY'),
    (1234568, 'Jane Smith', 'F6G7H8I9J0', 'CORPORATE', '456 Elm Street, CA'),
    (1234569, 'Alice Johnson', 'K1L2M3N4O5', 'INVESTMENT', '789 Oak Avenue, TX');
