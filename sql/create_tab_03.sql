CREATE TABLE IF NOT EXISTS t_lib_member (
	id int8 NOT NULL,
	card_id int8 NULL,
	cod_fiscale varchar(255) NULL,
	cognome varchar(255) NULL,
	data_card timestamp(6) NULL,
	email varchar(255) NULL,
	scadenza_member timestamp(6) NULL,
	indirizzo varchar(255) NULL,
	nome varchar(255) NULL,
	"password" varchar(255) NULL,
	userid varchar(255) NULL,
	tot_book_borrowed int8 NULL,
	CONSTRAINT t_lib_member_pkey PRIMARY KEY (id)
);