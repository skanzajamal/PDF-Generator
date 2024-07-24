CREATE TABLE staff(
  id            INTEGER       NOT NULL,
  full_name     VARCHAR(64)   NOT NULL,
  job_title     VARCHAR(64)   NOT NULL,
  department    VARCHAR(64)   NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE task(
  id            INTEGER       NOT NULL,
  description   VARCHAR(64)   NOT NULL,
  status        VARCHAR(64)   NOT NULL,
  CONSTRAINT    fk_id         FOREIGN KEY (id) REFERENCES staff(id),
  PRIMARY KEY (id)
);