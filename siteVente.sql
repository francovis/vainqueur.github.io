CREATE TABLE CATEGORY (
    categoryID INT(8) NOT NULL auto_increment,
    CONSTRAINT categoryID_pk PRIMARY KEY (categoryID)
)engine = InnoDB default charset = utf8; 
CREATE TABLE LANGUAGES (
    languageID INT(8) NOT NULL auto_increment,
	languageName VARCHAR(200) NOT NULL,
    CONSTRAINT languageID_pk PRIMARY KEY (languageID)
)engine = InnoDB default charset = utf8;
CREATE TABLE TRANSLATIONCATEGORY (
	translateID INT(8) NOT NULL auto_increment,
    idCategory INT(8) NOT NULL,
    idLanguage INT(8) NOT NULL,
    categoryName VARCHAR(50) NOT NULL,
    CONSTRAINT translateCategoryID_pk PRIMARY KEY (translateID),
    CONSTRAINT translateCategory_fk FOREIGN KEY (idCategory)
        REFERENCES CATEGORY (categoryID),
    CONSTRAINT translateCategoryLanguage_fk FOREIGN KEY (idLanguage)
        REFERENCES LANGUAGES (languageID)
)engine = InnoDB default charset = utf8;
CREATE TABLE PRODUCT (
    productID INT(8) NOT NULL auto_increment,
    productPrice DECIMAL(5,2) NOT NULL,
    idCategory INT(8) NOT NULL,
    productImage VARCHAR(200),    
	CHECK (productPrice > 0),
    CONSTRAINT productID_pk PRIMARY KEY (productID),
    CONSTRAINT category_fk FOREIGN KEY (idCategory)
        REFERENCES CATEGORY (categoryID)
)engine = InnoDB default charset = utf8;
CREATE TABLE TRANSLATIONPRODUCT (
	translateID INT(8) NOT NULL auto_increment,
    idProduct INT(8) NOT NULL,
    idLanguage INT(8) NOT NULL,
    productName VARCHAR(30) NOT NULL,
    productDescription VARCHAR(200) NOT NULL,
    CONSTRAINT translateProductID_pk PRIMARY KEY (translateID),
    CONSTRAINT translateProduct_fk FOREIGN KEY (idProduct)
        REFERENCES PRODUCT (productID),
    CONSTRAINT translateProductLanguage_fk FOREIGN KEY (idLanguage)
        REFERENCES LANGUAGES (languageID)
)engine = InnoDB default charset = utf8;
CREATE TABLE LOCALITY (
    localityName VARCHAR(60) NOT NULL,
    POcode INT(8) NOT NULL,
    CONSTRAINT localityName_pk PRIMARY KEY (localityName)
)engine = InnoDB default charset = utf8;
CREATE TABLE USERS (
    userName VARCHAR(30) NOT NULL,
    userLocality VARCHAR(30) NOT NULL,
    userFirstName VARCHAR(30) NOT NULL,
    userLastName VARCHAR(30) NOT NULL,
    userMail VARCHAR(100) NOT NULL,
    userPassword VARCHAR(64) NOT NULL,
    userPhoneNumber VARCHAR(12),
    userDeliveryAddress VARCHAR(50) NOT NULL,
    userBillingAddress VARCHAR(50),
    isRefundable bit not null default 0,
    CONSTRAINT userName_pk PRIMARY KEY (userName),
    CONSTRAINT userLocality_fk FOREIGN KEY (userLocality)
        REFERENCES LOCALITY (localityName)
)engine = InnoDB default charset = utf8;
CREATE TABLE ORDERS (
    orderID INT(8) NOT NULL auto_increment,
    orderUser VARCHAR(30) NOT NULL,
    CONSTRAINT orderID_pk PRIMARY KEY (orderID),
    CONSTRAINT orderUser_fk FOREIGN KEY (orderUser)
        REFERENCES USERS (userName)
)engine = InnoDB default charset = utf8;
CREATE TABLE ORDERDETAILS (
    orderDetailsID INT(8) NOT NULL auto_increment,
    idOrder INT(8) NOT NULL,
    idProduct INT(8) NOT NULL,
    quantity INT(2) NOT NULL,
    unitprice DECIMAL(6,2) NOT NULL,
	CHECK (quantity>0),
    CONSTRAINT orderDetailsID_pk PRIMARY KEY (orderDetailsID),
    CONSTRAINT orderDetailsParent_fk FOREIGN KEY (idOrder)
        REFERENCES ORDERS (orderID),
    CONSTRAINT orderDetailsProduct_fk FOREIGN KEY (idProduct)
        REFERENCES PRODUCT (productID)
)engine = InnoDB default charset = utf8;
insert into locality (localityName,POcode) values ('Namur',5000),('Gembloux',5030),('Anvers',2000),('Arlon',6700),('Bouilllon',6830),('Bruxelles',1000),('Charleroi',6000),('Mons',7000),('Liège',4000),('Leuven',3000),('Wavre',1300),('Ottignies/LLN',1340),('Dinant',5500),('Ostende',8400),('Bruges',8000),('Gand',9000),('Hasselt',3500),('Huy',5200),('Knokke',8300),('Tournai',7500),('Waterloo',1410),('Neufchâteau',6620),('Ypres',8900),('Chimay',6460),('Rochefort',5430),('Rixensart',1330);
insert into category (categoryid) values (1),(2),(3);
insert into languages (languagename) values ('Français'),('English');
insert into TRANSLATIONCATEGORY (idCategory, idLanguage,categoryName) values (1,1,'Masque'), (2,1,'Costume'),(3,1,'Accessoire');
insert into TRANSLATIONCATEGORY (idCategory, idLanguage,categoryName) values (1,2,'Mask'), (2,2,'Suit'),(3,2,'Accessory');
insert into PRODUCT (productprice,idcategory,productimage)
	 values (14.99,1,'/images/articles/masque/masque-canard.jpg'),
			(13.99,1,'/images/articles/masque/masque-cheval.jpg'),
            (16.99,1,'/images/articles/masque/masque-chien.jpg'),
            (15.99,1,'/images/articles/masque/masque-clown.jpg'),
            (19.99,1,'/images/articles/masque/masque-trump.jpg'); 
insert into PRODUCT (productprice,idcategory,productimage) 
	 values (24.99,2,'/images/articles/costume/déguisement-biere.jpg'),
			(23.99,2,'/images/articles/costume/déguisement-cafard.jpg'),
            (26.99,2,'/images/articles/costume/déguisement-minecraft.jpg'),
            (25.99,2,'/images/articles/costume/déguisement-sumo.jpg'),
            (29.99,2,'/images/articles/costume/déguisement-wc.jpg');
insert into PRODUCT (productprice,idcategory,productimage) 
	 values (4.99,3,'/images/articles/accessoires/boite-a-meuh.jpg'),
			(3.99,3,'/images/articles/accessoires/bonbon-brulant.jpg'),
            (6.99,3,'/images/articles/accessoires/boule-puante.jpg'),
            (5.99,3,'/images/articles/accessoires/chewinggum-electric.jpg'),
            (9.99,3,'/images/articles/accessoires/coussin-peteur.jpg'),
            (7.99,3,'/images/articles/accessoires/fausse-jambe.jpg'),
            (8.99,3,'/images/articles/accessoires/faux-vomi.jpg'),
            (3.99,3,'/images/articles/accessoires/pate-a-prout.jpg'),
            (4.99,3,'/images/articles/accessoires/sifflet-peteur.jpg'),
            (5.99,3,'/images/articles/accessoires/verre-baveur.jpg');
insert into TRANSLATIONPRODUCT (idProduct,idLanguage,productName, productDescription)
		values (1,1,'Masque de canard','Masque en latex à forme de tête de canard. Coin coin.'),
			   (2,1,'Masque de cheval','Masque en latex à forme de tête de cheval'),
			   (3,1,'Masque de chien','Masque en latex à forme de tête de chien. Chien qui aboie ne mord pas.'),
			   (4,1,'Masque de clown','Masque en latex à forme de tête de clown teffifiant. Nous nous délestons de toutes responsabilités en cas d\'arrestation.'),
			   (5,1,'Masque de Donald Trump','Masque en latex à forme de tête de Donald Trump. Make america great again !'),
			   (6,1,'Costume de biere','Costume en forme de biere (taille unique, ne se boit pas)'),
			   (7,1,'Costume de cafard','Costume en forme de cafard (taille unique). On n\'écrase pas unc cafard!'),
			   (8,1,'Costume minecraft','Costume en forme de minecraft (taille unique). Pret à miner ?'),
			   (9,1,'Costume de sumo','Costume en forme de sumo (taille unique). Respect garanti.'),
			   (10,1,'Costume de wc','Costume en forme de wc (taille unique). CE NE SONT PAS DE VRAIS WC.'),
			   (11,1,'Boite à meuh','Boite qui fait le même bruit qu\'une vache, obligatoire en cours.'),
			   (12,1,'Bonbon brulant','Attention ça brule !'),
			   (13,1,'Boule puante','Très prisées dans les soirées mondaines.'),
			   (14,1,'Chewing gum électrique','Histoire que le courant passe ...'),
			   (15,1,'Coussin péteur','Qui à pété !?'),
			   (16,1,'Fausse jambe','Se faire couper une jambe sans rien sentir, si ça c\'est pas génial.'),
			   (17,1,'Faux vomi','Pratique en plein examen.'),
			   (18,1,'Pâte à prout','Même effet que son cousin le coussin. NE SE MANGE PAS.'),
			   (19,1,'Sifflet peteur','Le moins discret de la game péteur mais il laisse plus d\'imagination au niveau du pet engendré.'),
			   (20,1,'Verre baveur','Verre à trou, l\'effet s\'enclenche quand on tente de le boire."Quand on ne sait pas boire, on ne boit pas."');
insert into TRANSLATIONPRODUCT (idProduct,idLanguage,productName, productDescription)
		values (1,2,'Duck mask','Latex mask representing a duck head. Coin coin.'),
			   (2,2,'Horse mask','Latex mask representing a horse head.'),
			   (3,2,'Dog mask','Latex mask representing a dog head. Barking dogs seldom bite.'),
			   (4,2,'Clown mask','Latex mask representing a teffifying clown head. We relieve ourselves of all responsibilities in case of arrest.'),
			   (5,2,'Donald Trump mask','Latex mask representing the Donald Trump\'s head. Make america great again !'),
			   (6,2,'Beer suit','Unique size, don\'t try to drink it.'),
			   (7,2,'Bug suit','Uinque size, don\'t try to crush it'),
			   (8,2,'Minecraft suit','Unique size, ready to mine ?'),
			   (9,2,'Sumo suit','Uinque size, everybody will respect you.'),
			   (10,2,'Wc suit','Unique size, THAT\'S NOT REAL WC.'),
			   (11,2,'Moo box','Box that makes same sound than a cow, mandatory at course.'),
			   (12,2,'Burning candy','Warning, it burn'),
			   (13,2,'Stink ball','Very popular un society'),
			   (14,2,'Electric chewing gum','Any chewing gum ?'),
			   (15,2,'Whoopee cushion','Who have farted !?'),
			   (16,2,'Fake leg','To have a leg cut without feeling anything, isn\'t it fantastic.'),
			   (17,2,'Fake vomit','Useful at exam.'),
			   (18,2,'Fart pasta','Same effect as whoopee cushion. DON\'T EAT IT.'),
			   (19,2,'Farting whistle','The least discreet of the farting game but it let you more freedom about the fart wanted.'),
			   (20,2,'Dribble glass','Glass with holes, try to drink it to see effects.');