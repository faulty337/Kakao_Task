CREATE TABLE Menu
(
    id       BIGINT PRIMARY KEY AUTO_INCREMENT,
    name     VARCHAR(20) NOT NULL,
    price    INT         NOT NULL,
    status   TINYINT(1) DEFAULT 1,
    category VARCHAR(20)
);


CREATE TABLE Users
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    total_point BIGINT DEFAULT 0
);

CREATE TABLE Point
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    amount     BIGINT DEFAULT 0 NOT NULL,
    created_at DATETIME         NOT NULL,
    users_id   BIGINT           NOT NULL,
    FOREIGN KEY (users_id) REFERENCES Users (id)
);

CREATE TABLE Orders
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    price      BIGINT   DEFAULT 0,
    created_at DATETIME NOT NULL,
    users_id   BIGINT NOT NULL,
    menu_id    BIGINT NOT NULL,
    FOREIGN KEY (users_id) REFERENCES Users (id),
    FOREIGN KEY (menu_id) REFERENCES Menu (id)
);

CREATE TABLE Rank
(
    id       BIGINT PRIMARY KEY AUTO_INCREMENT,
    price    BIGINT     DEFAULT 0,
    name     VARCHAR(2) DEFAULT NOW(),
    category BIGINT NOT NULL
);
