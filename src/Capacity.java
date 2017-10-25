public enum Capacity {
	ECHANGE_MATIERE_PREMIERE_DROITE,
	ECHANGE_MATIERE_PREMIERE_GAUCHE,
	ECHANGE_MATIERE_PREMIERE_DROITE_GAUCHE,
	ECHANGE_PRODUIT_MANUFACTURE_DROITE_GAUCHE,
	CHOISIR_1_MATIERE_PREMIERE,
	CHOISIR_1_PRODUIT_MANUFACTURE,
	RAPPORTE_1_PIECE_MARRON_JOUEUR_ET_VOISINS,
	RAPPORTE_2_PIECES_GRIS_JOUEUR_ET_VOISINS,
	RAPPORTE_1_PIECE_1_VICTOIRE_MARRON,
	RAPPORTE_1_PIECE_1_VICTOIRE_JAUNE,
	RAPPORTE_2_PIECE_2_VICTOIRE_GRIS,
	RAPPORTE_1_VICTOIRE_MARRON_VOISINS,
	RAPPORTE_1_VICTOIRE_JAUNE_VOISINS,
	RAPPORTE_1_VICTOIRE_BLEU_VOISINS,
	RAPPORTE_1_VICTOIRE_VERT_VOISINS,
	RAPPORTE_1_VICTOIRE_ROUGE_VOISINS,
	RAPPORTE_2_VICTOIRE_GRIS_VOISINS,
	RAPPORTE_3_PIECE_1_VICTOIRE_ETAPE,
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
			result = "Achat d'une ressource de base du joueur de droite à 1 pièce";
			break;
		case ECHANGE_MATIERE_PREMIERE_GAUCHE:
			result = "Achat d'une ressource de base du joueur de gauche à 1 pièce";
			break;
		case ECHANGE_MATIERE_PREMIERE_DROITE_GAUCHE:
			result = "Achat d'une ressource de base du joueur de gauche ou de droite à 1 pièce";
			break;
		case ECHANGE_PRODUIT_MANUFACTURE_DROITE_GAUCHE:
			result = "Achat d'une ressource élaborée du joueur de gauche ou de droite à 1 pièce";
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
		default :
			result = "La description de cette capacité n'est pas encore disponible";
		}
		
		
		
		return result;
	}
}
