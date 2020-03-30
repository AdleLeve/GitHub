-- Create Database pokedex;
DROP DATABASE IF EXISTS pokedex;
CREATE DATABASE pokedex CHARACTER SET 'utf8';

USE pokedex;

-- Table Annee --
Create Table Annee (
numero_annee Char(4),
Constraint PK_num_annee PRIMARY KEY (numero_annee)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Table Generation --
Create Table Generation (
numero_generation Int,
libelle_generation Varchar(3) NOT NULL,
annee_generation Char(4), 
Constraint PK_numero_generation PRIMARY KEY (numero_generation),
Constraint FK_annee_generation Foreign Key (annee_generation) References Annee (numero_annee)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Table Type --
Create Table Type (
code_type Int,
libelle_type Varchar(15) NOT NULL,
gen_apparition_type Int NOT NULL, 
Constraint PK_code_type PRIMARY KEY (code_type),
Constraint FK_gen_apparition_type Foreign Key (gen_apparition_type) References Generation (numero_generation)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Table Image -
Create Table Images (
id_img Int,
url_img Varchar(50),
extension_img Varchar(5),
Constraint PK_id_img PRIMARY KEY (id_img)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Table Pokemon --
Create Table Pokemon (
num_pkdx_monde CHAR(3),
nom_pkm Varchar(20) NOT NULL,
desc_pkdx_pkm Varchar(400) NOT NULL,
url_img_pkm int,
part_legend_pkm tinyint(1) NOT NULL,
gen_apparition_pkm Int NOT NULL,
Constraint PK_num_pokedex_monde PRIMARY KEY (num_pkdx_monde),
Constraint FK_gen_apparition_pkm Foreign Key (gen_apparition_pkm) References Generation (numero_generation),
Constraint FK_url_images_pkm Foreign Key (url_img_pkm) References Images (id_img)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Table Avoir_Type --
Create Table Avoir_Type (
num_pkdex_monde Char(3),
code_type Int,
Constraint PK_avoir_type PRIMARY KEY (num_pkdex_monde, code_type),
Constraint FK_num_pkdex_monde Foreign Key (num_pkdex_monde) References Pokemon (num_pkdx_monde),
Constraint FK_code_type Foreign Key (code_type) References Type (code_type)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Table Type_Evolution --
Create Table Type_Evolution (
id_type_evol Int,
libelle_type_evol  Varchar(30),
Constraint PK_type_evol Primary Key (id_type_evol)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Table Evolution --
Create Table Evolution (
id_evol Int,
libelle_court_evol Char(7) NOT NULL,
num_pkdex_sous_evol Char(3) NOT NULL,
num_pkdex_sur_evol Char(3) NOT NULL,
type_evol Int NOT NULL,
Constraint PK_id_evol PRIMARY KEY (id_evol),
Constraint FK_type_evol_evol Foreign Key (type_evol) References Type_Evolution (id_type_evol),
Constraint FK_num_pkdex_sous_evol Foreign Key (num_pkdex_sous_evol) References Pokemon (num_pkdx_monde),
Constraint FK_num_pkdex_sur_evol Foreign Key (num_pkdex_sur_evol) References Pokemon (num_pkdx_monde)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;