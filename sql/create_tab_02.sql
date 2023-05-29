CREATE TABLE IF NOT EXISTS lending_details (
	id int8 NOT NULL,
	date_lending timestamp(6) NULL,
	id_book int8 NULL,
	id_member int8 NULL,
	return_date timestamp(6) NULL,
	due_return_date timestamp(6) NULL,
	CONSTRAINT lending_details_pkey PRIMARY KEY (id)
);