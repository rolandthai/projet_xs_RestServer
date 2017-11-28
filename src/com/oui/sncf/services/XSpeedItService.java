package com.oui.sncf.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/XSpeedIt")
public class XSpeedItService {

	    @GET
	    @Path("/{chaineArticle}")
	    @Produces(MediaType.TEXT_PLAIN)
	    public String getUser( @PathParam("chaineArticle") String chaineArticle ) {
	        String chaineArticleTri = "";
	        
	        boolean isValid = isCorrect(chaineArticle);
	        if(!isValid)
	        {
	        	return "Erreur de saisie de la chaîne d\'articles";
	        }
	        
	    	chaineArticleTri = traitementDesArticles(chaineArticle);
	    	return chaineArticleTri;
	    }
	    
		/**
		 * Permet de voir si la valeur est bien numerique
		 * @param chaineArticles
		 * @return
		 */
		public boolean isCorrect(String chaineArticles)
		{
			try
			{
			     Long.decode(chaineArticles);
			     return true;  
			}
			catch(NumberFormatException  e)
			{
			    return false;
			}
		}

	    
	    /**
	     * Traitement des articles
	     * @param chaineArticle
	     * @return
	     */
	    public String traitementDesArticles(String chaineArticle) {
			
			//liste de résultat
			List<String> listLotCartons = new ArrayList<String>();
			
			//liste temporaire utilisée pour le traitement
			List<Integer> listArticlesTmpA = new ArrayList<Integer>();

			//initialisation de la liste temporaire de la taille des articles
			for(int i=0;i<chaineArticle.length();i++)
			{
				String str = chaineArticle.substring(i, i+1);
				listArticlesTmpA.add(Integer.valueOf(str));
			}

			//Tri decroissant de la liste temporaire de la taille des articles
			Collections.sort(listArticlesTmpA);
			Collections.reverse(listArticlesTmpA);
			
			//copie de la liste temporaire des articles
			List<Integer> listArticlesTmpB = new ArrayList<>();
			listArticlesTmpB = listArticlesTmpA;
			
			System.out.println(listArticlesTmpA.toString());
			
			//Algorithmique pour le traitement des cartons
			for(int i=0;i<listArticlesTmpA.size();i++)
			{
				//carton contenant les articles
				String strCarton = "";
				
				//recuperation d'un article de la liste tempraire
				int tailleArticle1 = listArticlesTmpA.get(i);
				
				//ajout du paquet dans le lot
				strCarton = String.valueOf(tailleArticle1);
				
				//boucle pour optimiser l'espace du Carton
				for(int j=i+1;j<listArticlesTmpB.size();j++)
				{
					//recupération de l'article suivant
					int tailleArticleB = listArticlesTmpB.get(j);
					//verification que les paquets rentre dans le lot
					if((tailleArticle1 + tailleArticleB)<=10)
					{
						//si il rentre on l'ajoute
						strCarton += tailleArticleB;
						tailleArticle1 = tailleArticle1 + tailleArticleB;
						//On retire l'article ajouté
						listArticlesTmpB.remove(j);
						j--;
					}
				}
				//on enregistre le carton dans la liste du lot de carton
				listLotCartons.add(strCarton);
			}
			
			String strResult = listLotCartons.toString().replaceAll(", ", "/");
			
			return strResult.substring(1, strResult.length()-1);
		}
	}