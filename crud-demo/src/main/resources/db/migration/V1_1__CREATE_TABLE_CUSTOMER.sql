DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
  customer_number int(11) NOT NULL,
  contact_last_name varchar(50) NOT NULL,
  contact_first_name varchar(50) NOT NULL,
  phone varchar(50) NOT NULL,
  address_line1 varchar(50) NOT NULL,
  address_line2 varchar(50) DEFAULT NULL,
  city varchar(50) NOT NULL,
  state varchar(50) DEFAULT NULL,
  postal_code varchar(15) DEFAULT NULL,
  country varchar(50) NOT NULL,
  PRIMARY KEY (customer_number)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
