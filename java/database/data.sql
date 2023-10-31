BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO movies (movie_title, movie_release_date, movie_type, movie_genre, number_discs, movie_location, movie_poster)
VALUES ('The Shawshank Redemption', '1994-09-23', 'DVD', 'Drama', 2, 'AAD', 'https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcTCo3hgDvRyVobABzofq_0A9F95e5q2zKoC2D81UarELmCRvkj2T4_K8MKRNAdkYIBH9vj3pdUFqYZgLB_9wlWhsTz4px0Jbw');

COMMIT TRANSACTION;
