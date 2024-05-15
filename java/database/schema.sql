BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS movies;
DROP TABLE IF EXISTS user_movie_collections;

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
    movie_poster varchar(250),
    imdb_id varchar(25),
    CONSTRAINT PK_movie PRIMARY KEY (movie_id)
    CONSTRAINT unique_movie_title_release_date UNIQUE (movie_title, movie_release_date)
);

CREATE TABLE user_movie_collections (
    collection_id SERIAL,
    user_id INT NOT NULL,
    movie_id INT NOT NULL,
    CONSTRAINT PK_user_movie_collections PRIMARY KEY (collection_id),
    CONSTRAINT FK_user_movie_collections_user FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT FK_user_movie_collections_movie FOREIGN KEY (movie_id) REFERENCES movies(movie_id),
    CONSTRAINT unique_user_movie UNIQUE (user_id, movie_id)
);

COMMIT TRANSACTION;
