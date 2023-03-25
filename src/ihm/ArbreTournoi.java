package ihm;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modele.Arbitre;
import modele.Ecurie;
import modele.Ecurie.Statut;
import modele.Equipe;
import modele.Jeu;
import modele.Rencontre;
import modele.Tournoi;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.handler.mxVertexHandler;
import com.mxgraph.view.mxGraph;

import connexion.ConnexionBDD;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;

public class ArbreTournoi extends JDialog {
	Connection connx = ConnexionBDD.connecting();
	private final JPanel contentPanel = new JPanel();
	private static  final long serialVersionUID = -8123406571694511514L;


	public ArbreTournoi(Tournoi t) throws SQLException {
		setBounds(200, 0, 1000, 1500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		List<Rencontre> rencontres = t.getRencontres();

		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();
		//incrémente le compteur voir endUpdate
		graph.getModel().beginUpdate();
		/*
		 * Vertex => rectangle 
		 * Paramètres : (parent, null, Objet,1,2,3,4);
		 * parent : vertex sont reliés à la racine
		 * null : String identifiant le vertex (optionnel)
		 * Objet contenu par le vertex
		 * 4 chiffres : x,y,largeur,hauteur du rectangle
		 */
		try {

			Object v1  = graph.insertVertex(parent, null, rencontres.get(0).getEquipe1().getNom() ,20, 10,  80,30);
			Object v2  = graph.insertVertex(parent, null, rencontres.get(0).getEquipe2().getNom(), 20, 60,  80,30);
			Object v3  = graph.insertVertex(parent, null, rencontres.get(1).getEquipe1().getNom(), 20, 110, 80,30);
			Object v4  = graph.insertVertex(parent, null, rencontres.get(1).getEquipe2().getNom(), 20, 160, 80,30);
			Object v5  = graph.insertVertex(parent, null, rencontres.get(2).getEquipe1().getNom(), 20, 210, 80,30);
			Object v6  = graph.insertVertex(parent, null, rencontres.get(2).getEquipe2().getNom(), 20, 260, 80,30);
			Object v7  = graph.insertVertex(parent, null, rencontres.get(3).getEquipe1().getNom(), 20, 310, 80,30);
			Object v8  = graph.insertVertex(parent, null, rencontres.get(3).getEquipe2().getNom(), 20, 360, 80,30);
			Object v9  = graph.insertVertex(parent, null, rencontres.get(4).getEquipe1().getNom(), 20, 410, 80,30);
			Object v10 = graph.insertVertex(parent, null, rencontres.get(4).getEquipe2().getNom(), 20, 460, 80,30);
			Object v11 = graph.insertVertex(parent, null, rencontres.get(5).getEquipe1().getNom(), 20, 510, 80,30);
			Object v12 = graph.insertVertex(parent, null, rencontres.get(5).getEquipe2().getNom(), 20, 560, 80,30);
			Object v13 = graph.insertVertex(parent, null, rencontres.get(6).getEquipe1().getNom(), 20, 610, 80,30);
			Object v14 = graph.insertVertex(parent, null, rencontres.get(6).getEquipe2().getNom(), 20, 660, 80,30);
			Object v15 = graph.insertVertex(parent, null, rencontres.get(7).getEquipe1().getNom(), 20, 710, 80,30);
			Object v16 = graph.insertVertex(parent, null, rencontres.get(7).getEquipe2().getNom(), 20, 760, 80,30);
			
			Object v17  = graph.insertVertex(parent, null, rencontres.get(0).getEquipe1().getNom() , 150, 35, 80,30);			
			Object v19  = graph.insertVertex(parent, null, rencontres.get(0).getEquipe2().getNom() , 150, 135, 80,30);		
			Object v21  = graph.insertVertex(parent, null,rencontres.get(1).getEquipe1().getNom() , 150, 235, 80,30);		
			Object v23  = graph.insertVertex(parent, null, rencontres.get(1).getEquipe2().getNom() , 150, 335, 80,30);			
			Object v25  = graph.insertVertex(parent, null, rencontres.get(2).getEquipe1().getNom() , 150, 435, 80,30);			
			Object v27  = graph.insertVertex(parent, null,rencontres.get(2).getEquipe2().getNom() , 150, 535, 80,30);			
			Object v29  = graph.insertVertex(parent, null,rencontres.get(3).getEquipe1().getNom() , 150, 635, 80,30);			
			Object v31  = graph.insertVertex(parent, null,rencontres.get(3).getEquipe2().getNom() , 150, 735, 80,30);

			/*
			 *Edge -> lien etre deux vertex
			 * Paramètres : (parent, null, "Edge",v1,v2);
			 * parent : à quoi est relié le vertex
			 * null : String identifiant le lien (optionnel)
			 * v1 : le vertex source du edge
			 * v2 : le vertex destination du edge
			 */

			graph.insertEdge(null, null, "", rencontres.get(0).getEquipeGagnante().getNom() , v17);
			graph.insertEdge(null, null, "", rencontres.get(1).getEquipeGagnante().getNom(), v19);
			graph.insertEdge(null, null, "", rencontres.get(2).getEquipeGagnante().getNom(), v21);
			graph.insertEdge(null, null, "", rencontres.get(3).getEquipeGagnante().getNom(), v23);
			graph.insertEdge(null, null, "", rencontres.get(4).getEquipeGagnante().getNom(), v25);
			graph.insertEdge(null, null, "", rencontres.get(5).getEquipeGagnante().getNom(), v27);
			graph.insertEdge(null, null, "", rencontres.get(6).getEquipeGagnante().getNom(), v29);
			graph.insertEdge(null, null, "", rencontres.get(7).getEquipeGagnante().getNom(), v31);
			
			Object v33  = graph.insertVertex(parent, null, t.getRencontres().get(0).getEquipe1().getNom()  , 290, 85, 80,30);		
			Object v35  = graph.insertVertex(parent, null, t.getRencontres().get(0).getEquipe2().getNom() , 290, 285, 80,30);		
			Object v37  = graph.insertVertex(parent, null, t.getRencontres().get(1).getEquipe1().getNom() , 290, 485, 80,30);		
			Object v39  = graph.insertVertex(parent, null, t.getRencontres().get(1).getEquipe2().getNom() , 290, 685, 80,30);
			graph.insertEdge(null, null, "", t.getRencontres().get(0).getEquipeGagnante().getNom(), v33);
			graph.insertEdge(null, null, "", t.getRencontres().get(1).getEquipeGagnante().getNom(), v35);
			graph.insertEdge(null, null, "", t.getRencontres().get(2).getEquipeGagnante().getNom(), v37);
			graph.insertEdge(null, null, "", t.getRencontres().get(3).getEquipeGagnante().getNom(), v39);
			
			Object v41  = graph.insertVertex(parent, null, t.getRencontres().get(0).getEquipe1().getNom() , 420, 185, 80,30);			
			Object v43  = graph.insertVertex(parent, null, t.getRencontres().get(0).getEquipe2().getNom() , 420, 585, 80,30);
			graph.insertEdge(null, null, "", t.getRencontres().get(0).getEquipeGagnante().getNom(), v41);
			graph.insertEdge(null, null, "", t.getRencontres().get(1).getEquipeGagnante().getNom(), v43);
		}catch (Exception e) {
			// TODO: handle exception
		}
		finally 
		{
			graph.getModel().endUpdate();
		}
		graph.setCellsMovable(false);

	}

	public static void main(String[] args) throws Exception {
		Connection connx = ConnexionBDD.connecting();
		Tournoi tournoi = ConnexionBDD.getTournoiFromId(1);
		List<Equipe> listeEquipes = ConnexionBDD.getEquipesFromTournoi(tournoi);


		ArbreTournoi at = new ArbreTournoi(tournoi);
		at.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		at.setSize(1000,1500);
		at.setVisible(true);

	}
}
