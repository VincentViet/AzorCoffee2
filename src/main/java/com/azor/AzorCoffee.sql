-- Account
CREATE TABLE IF NOT EXISTS Account(
                                      id  		    INT 			AUTO_INCREMENT PRIMARY KEY,
                                      username 	    NVARCHAR(30) 	UNIQUE,
                                      email 		    NVARCHAR(50) 	UNIQUE,
                                      password      NVARCHAR(50)    NOT NULL,
                                      fullname 	    NVARCHAR(30) 	NOT NULL,
                                      address		    NVARCHAR(100) 	NOT NULL,
                                      telphone	    NVARCHAR(11) 	UNIQUE,
                                      type		    INT				NOT NULL
);

-- Table
CREATE TABLE IF NOT EXISTS CoffeeTable(
                                          id			    INT 			AUTO_INCREMENT PRIMARY KEY,
                                          isBlank		    BOOLEAN         DEFAULT FALSE
);

-- Categories
CREATE TABLE IF NOT EXISTS Categories(
                                         id              INT             AUTO_INCREMENT PRIMARY KEY,
                                         name            NVARCHAR(30)    NOT NULL
);

-- Drink
CREATE TABLE IF NOT EXISTS FoodAndDrink(
                                           id 			    INT 			AUTO_INCREMENT  PRIMARY KEY,
                                           name		    NVARCHAR(30)	NOT NULL,
                                           price		    DECIMAL(9, 0)	NOT NULL,
                                           categoryID      INT             NOT NULL        REFERENCES Categories(id)
);

-- Bill
CREATE TABLE IF NOT EXISTS Bill(
                                   id              INT             AUTO_INCREMENT  PRIMARY KEY,
                                   creationTime    TIMESTAMP       NOT NULL        DEFAULT NOW(),
                                   paymentTime     TIMESTAMP       NOT NULL
);

-- Bill Information
CREATE TABLE IF NOT EXISTS BillInfo(
                                       name            NVARCHAR(30)    NOT NULL        REFERENCES FoodAndDrink(name),
                                       price           DECIMAL(9, 0)   NOT NULL        REFERENCES FoodAndDrink(price),
                                       count           INT             NOT NULL        DEFAULT 1,
                                       billID          INT             NOT NULL        REFERENCES Bill(id)
);

SELECT * FROM Account;
SELECT * FROM CoffeeTable;
SELECT * FROM FoodAndDrink;
SELECT * FROM Categories;
SELECT * FROM Bill;
SELECT * FROM BillInfo;