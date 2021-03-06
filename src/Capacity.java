public enum Capacity {
	ECHANGE_MATIERE_PREMIERE_DROITE, // Codee
	ECHANGE_MATIERE_PREMIERE_GAUCHE, // Codee
	ECHANGE_MATIERE_PREMIERE_DROITE_GAUCHE, // Codee
	ECHANGE_PRODUIT_MANUFACTURE_DROITE_GAUCHE, // Codee
	CHOISIR_1_MATIERE_PREMIERE, //Codee
	CHOISIR_1_PRODUIT_MANUFACTURE, // Codee
	GAGNER_2_BOUCLIERS,
	RAPPORTE_1_PIECE_MARRON_JOUEUR_ET_VOISINS, // Codée
	RAPPORTE_2_PIECES_GRIS_JOUEUR_ET_VOISINS, // Codée
	RAPPORTE_1_PIECE_1_VICTOIRE_MARRON, // Codée pour les pieces
	RAPPORTE_1_PIECE_1_VICTOIRE_JAUNE, // Codée pour les pieces
	RAPPORTE_2_PIECE_2_VICTOIRE_GRIS, // Codée pour les pieces
	RAPPORTE_1_VICTOIRE_MARRON_VOISINS,
	RAPPORTE_1_VICTOIRE_JAUNE_VOISINS,
	RAPPORTE_1_VICTOIRE_BLEU_VOISINS,
	RAPPORTE_1_VICTOIRE_VERT_VOISINS,
	RAPPORTE_1_VICTOIRE_ROUGE_VOISINS,
	RAPPORTE_2_VICTOIRE_GRIS_VOISINS,
	RAPPORTE_3_PIECE_1_VICTOIRE_ETAPE, // Codée pour les pieces
	RAPPORTE_1_VICTOIRE_ETAPE_JOUEUR_ET_VOISINS,
	CONSTRUCTION_GRATUITE_1_PAR_AGE,
	JOUER_DERNIERE_CARTE_AGE,
	CONSTRUIRE_GRATUITEMENT_1_CARTE_DEFAUSSEE,
	COPIER_GUILDE_VOISINS,
	RAPPORTE_1_VICTOIRE_VIOLETTE_MARRON_GRISE,
	RAPPORTE_1_VICTOIRE_JETON_DEFAITE_VOISINS,
	RAPPORTE_1_SYMBOLE_SCIENTIFIQUE_AU_CHOIX;
	
	public String toString(){
		String result = "";
		switch(this){
		case ECHANGE_MATIERE_PREMIERE_DROITE:
			result = "Achat d'une matière première du joueur de droite à 1 pièce";
			break;
		case ECHANGE_MATIERE_PREMIERE_GAUCHE:
			result = "Achat d'une matière première du joueur de gauche à 1 pièce";
			break;
		case ECHANGE_MATIERE_PREMIERE_DROITE_GAUCHE:
			result = "Achat d'une matière première d'un de vos voisins à 1 pièce";
			break;
		case ECHANGE_PRODUIT_MANUFACTURE_DROITE_GAUCHE:
			result = "Achat d'un produit manufacturé d'un de vos voisins à 1 pièce";
			break;
		case CONSTRUCTION_GRATUITE_1_PAR_AGE:
			result = "Construction gratuite d'un batiment par âge";
			break;
		case JOUER_DERNIERE_CARTE_AGE:
			result = "Mise en jeu de la dernière carte de chaque âge au lieu de la défausser";
			break;
		case CONSTRUIRE_GRATUITEMENT_1_CARTE_DEFAUSSEE:
			result = "Mise en jeu d'une carte de la défausse gratuitement";
			break;
		case CHOISIR_1_MATIERE_PREMIERE:
			result = "Production d'une unité de l'une des matières premières de votre choix par tour";
			break;
		case GAGNER_2_BOUCLIERS:
			result = "Gain de deux boucliers";
			break;
		default :
			result = "La description de cette capacité n'est pas encore disponible";
		}
		
		
		
		return result;
	}
}
