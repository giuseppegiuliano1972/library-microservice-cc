CREATE TABLE IF NOT EXISTS t_book (
	id int4 NOT NULL,
	titolo varchar(100) NULL,
	autore varchar(100) NULL,
	nota varchar(500) NULL,
	anno_pubblicazione int4 NULL,
	editore varchar(255) NULL,
	anno varchar(255) NULL,
	descrizione varchar(255) NULL,
	available int8 NULL
);