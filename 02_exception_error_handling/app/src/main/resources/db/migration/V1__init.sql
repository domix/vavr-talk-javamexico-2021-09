CREATE TABLE people
(
    id           uuid           NOT NULL,
    date_created TIMESTAMP      NOT NULL,
    username     VARCHAR UNIQUE NOT NULL,
    password     VARCHAR        NOT NULL,
    PRIMARY KEY (id)
);
