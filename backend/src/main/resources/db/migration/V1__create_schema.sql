CREATE TABLE short_url(
    id BIGSERIAL PRIMARY KEY,
    request_url TEXT NOT NULL,
    code_url VARCHAR(10) NOT NULL UNIQUE
); 