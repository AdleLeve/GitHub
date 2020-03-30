-- pokedex Procedures Stockées --
USE pokedex;
-- Annee --

DELIMITER |

CREATE PROCEDURE PROC_Insert_annee(IN p_num_annee CHAR(4))
BEGIN
	INSERT INTO Annee (numero_annee) VALUES (p_num_annee);
END|

CREATE PROCEDURE PROC_Select_Annee_ordreA ()
BEGIN
	Select numero_annee as num 
	From Annee
	Order By num;
END|

-- Generation --
CREATE PROCEDURE PROC_Insert_Generation (IN p_num_gen Int, IN p_lib Varchar(3), IN p_num_annee Char(4))
BEGIN
	SET FOREIGN_KEY_CHECKS=0;
	INSERT INTO Generation (numero_generation, libelle_generation, annee_generation)
	VALUES (p_num_gen, p_lib, p_num_annee);
	SET FOREIGN_KEY_CHECKS=1;
END|

CREATE PROCEDURE PROC_Select_Generation_ordreA ()
BEGIN
	Select numero_generation as num, libelle_generation as lib, annee_generation as annee
	From Generation
	Order By num;
END|

-- Type --
CREATE PROCEDURE PROC_Insert_Type (IN p_id Int, IN p_lib Varchar(15), IN p_gen Int)
BEGIN
	SET FOREIGN_KEY_CHECKS=0;
	INSERT INTO Type (code_type, libelle_type, gen_apparition_type) VALUES (p_id, p_lib, (SELECT numero_generation FROM generation Where numero_generation = p_gen));
	SET FOREIGN_KEY_CHECKS=1;
END|

CREATE PROCEDURE PROC_Select_Type_ordreACode ()
BEGIN
	Select code_Type as code, libelle_type as lib, gen_apparition_type as gen
	From Type
	Order By code;
END|

-- Image --
CREATE PROCEDURE PROC_Insert_Image (IN p_id Int, IN p_url Varchar(50), IN p_ext Varchar(5))
BEGIN
	INSERT INTO Images (id_img, url_img, extension_img) VALUES (p_id, p_url, p_ext);
END|

CREATE PROCEDURE PROC_Select_Image_ordreAUrl ()
BEGIN
	Select id_img as id, url_img as url, extension_img as ext
	From Images
	Order By id;
END|

CREATE PROCEDURE PROC_Select_cbx_pkm_img_B ()
BEGIN
	Select CONCAT(url_img, extension_img) as url, num_pkdx_monde as numPkdx, nom_pkm as nomPkm
	From Images
	Inner Join Pokemon
	On id_img = url_img_pkm
	Order By id_img;
END|

CREATE PROCEDURE PROC_Select_cbx_pkm_img ()
BEGIN
	Select url_img as url, extension_img as ext, num_pkdx_monde as numPkdx, nom_pkm as nomPkm
	From Images
	Inner Join Pokemon
	On id_img = url_img_pkm
	Order By id_img;
END|
/*  RIGHT(url_img, INSTR(REVERSE(url_img), "/")-1)  */
-- Pokemon --
CREATE PROCEDURE PROC_Insert_Pkm (IN p_num_pkdx char(3), IN p_nom Varchar(20), IN p_desc Varchar(400), IN p_url int,  IN p_legendaire tinyint(1), IN p_gen_appar int)
BEGIN
	INSERT INTO Pokemon (num_pkdx_monde, nom_pkm, desc_pkdx_pkm, url_img_pkm, part_legend_pkm, gen_apparition_pkm) 
	VALUES (p_num_pkdx, p_nom, p_desc, (SELECT id_img FROM images Where id_img = p_url), p_legendaire, (SELECT numero_generation FROM generation Where numero_generation = p_gen_appar));
END|

CREATE PROCEDURE PROC_Select_Pkm_OrdrePkdx ()
BEGIN 
	Select num_pkdx_monde as num, nom_pkm as nom, desc_pkdx_pkm as descr, url_img_pkm as url, part_legend_pkm as legendaire, gen_apparition_pkm as gen
	From Pokemon
	Order By num;
END|

-- Avoir_Type --
CREATE PROCEDURE PROC_Insert_Avoir_Type (IN p_num_pkdx Char(3), IN p_code_type Int)
BEGIN
	INSERT INTO Avoir_Type (num_pkdex_monde, code_type) VALUES ((
    SELECT num_pkdx_monde FROM Pokemon WHERE num_pkdx_monde = p_num_pkdx), (
    SELECT code_type FROM Type WHERE code_type = p_code_type));
END|

CREATE PROCEDURE PROC_Select_Avoir_Type_ordAPkm ()
BEGIN
	Select num_pkdx_monde as num, code_type as code
	From Avoir_Type
	Order By num;
END|

CREATE PROCEDURE PROC_Select_Count_Avoir_Type_Pkm (IN p_num_pkdx CHAR(3))
BEGIN
	Select COUNT(code_type) as numTypePkm
	From Avoir_Type
	WHERE num_pkdex_monde = p_num_pkdx;
END|

-- Type_Evolution --
CREATE PROCEDURE PROC_Insert_TypeEvol (IN p_id Int, IN p_lib Varchar(30))
BEGIN 
	INSERT INTO Type_Evolution (id_type_evol, libelle_type_evol) 
	VALUES (p_id, p_lib);
END|

CREATE PROCEDURE PROC_Select_TypeEvol_OrdreA ()
BEGIN 
	Select id_type_evol as id, libelle_type_evol as lib
	From Type_Evolution
	Order By lib;
END|

-- Evolution --
CREATE PROCEDURE PROC_Insert_Evolution(
    IN p_id INT,
    IN p_lib CHAR(7),
    IN p_sous_evol CHAR(3),
    IN p_sur_evol CHAR(3),
    IN p_type_evol INT)
BEGIN
    DECLARE sous CHAR(3); DECLARE sur CHAR(3); DECLARE tp INT;
    SET sous := (SELECT num_pkdx_monde FROM Pokemon WHERE num_pkdx_monde = p_sous_evol);
	SET sur := (SELECT num_pkdx_monde FROM Pokemon WHERE num_pkdx_monde = p_sur_evol);
	SET tp := (SELECT code_type FROM type WHERE code_type = p_type_evol);
	INSERT INTO evolution(id_evol, libelle_court_evol, num_pkdex_sous_evol, num_pkdex_sur_evol, type_evol)
	VALUES(p_id, p_lib, sous, sur, tp);
END |

CREATE PROCEDURE PROC_Select_Evolution_ordASousEvol ()
BEGIN
	Select libelle_court_evol as lib, num_pkdex_sous_evol as sous_evol, num_pkdex_sur_evol as sur_evol, type_evol as typeEvol
	From Evolution
	Order By sous_evol;
END|
