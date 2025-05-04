create table book (
       id bigint not null,
       availability varchar(255),
       cid varchar(255),
       title varchar(255),
       publisher varchar(255),
       author varchar(255),
       genre varchar(255),
       isbn varchar(255),
       primary key (id)
);

insert into BOOK (ID, CID, TITLE, PUBLISHER, AUTHOR, ISBN, GENRE, AVAILABILITY) values (1,'LM-000001','A Game of Thrones','HarperCollins Publishers','George R. R. Martin','9780006479888','Fantasy','Available');
insert into BOOK (ID, CID, TITLE, PUBLISHER, AUTHOR, ISBN, GENRE, AVAILABILITY) values (2,'LM-000002','The Hobbit','Random House Inc','J.R.R. Tolkien','9780345538376','Fantasy','Available');
insert into BOOK (ID, CID, TITLE, PUBLISHER, AUTHOR, ISBN, GENRE, AVAILABILITY) values (3,'LM-000003','The Girl with the Dragon Tattoo','Quercus Publishing Plc','Stieg Larsson','9781849162883','Crime','Available');
insert into BOOK (ID, CID, TITLE, PUBLISHER, AUTHOR, ISBN, GENRE, AVAILABILITY) values (4,'LM-000004','I, Robot','Random House Inc','Isaac Asimov','9780553382563','Scifi','Available');
