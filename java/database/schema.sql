BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS movies;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE movies (
    movie_id SERIAL,
    movie_title varchar(200) NOT NULL,
    movie_release_date DATE,
    movie_type varchar (25),
    movie_genre varchar(100),
    number_discs INT,
    movie_location varchar(25) NOT NULL,
    CONSTRAINT PK_movie PRIMARY KEY (movie_id)
);

COMMIT TRANSACTION;
