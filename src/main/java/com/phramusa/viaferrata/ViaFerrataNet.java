/*
 * Copyright (C) 2018 phramusca ( https://github.com/phramusca/JaMuz/ )
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.phramusa.viaferrata;

import com.phramusa.viaferrata.utils.ProcessAbstract;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Sync process class
 * @author phramusca ( https://github.com/phramusca/JaMuz/ )
 */
public class ViaFerrataNet extends ProcessAbstract {
	
	private final ICallBack callback;
	private String urlViaToRead;
    
	/**
	 * Creates a new sync process instance  
	 * @param progressBar
	 */
	public ViaFerrataNet(ICallBack progressBar) {
        super("Thread.ViaFerrataNet");
		this.callback = progressBar;
	}
	
	/**
	 * Creates a new sync process instance  
	 * @param progressBar
	 * @param url
	 */
	public ViaFerrataNet(ICallBack progressBar, String url) {
        super("Thread.ViaFerrataNet");
		this.callback = progressBar;
		this.urlViaToRead = url;
	}
	
	/**
	 * Starts file synchronisation process in a new thread
	 * Called by MainGUI
	 */
    @Override
	public void run() {
		this.resetAbort();

        try {
			if(urlViaToRead!=null) {
				read(urlViaToRead);
			} else {
				read();
			}			
        } catch (InterruptedException ex) {
            callback.interrupted();
        }
        finally {
            callback.completed();
        }
	}
	
	private void read() throws InterruptedException {
		try {
			Document doc = Jsoup.connect(
					"https://www.viaferrata-fr.net/via-ferrata.html").get();
			checkAbort();
			Elements els = new Elements();
			els.addAll(doc.getElementsByClass("tr1"));
			els.addAll(doc.getElementsByClass("tr2"));
			callback.setup(els.size());
			for (Element el : els) { 
				checkAbort();
				Elements tds = el.select("td");
				
				String urlVia=tds.get(0).select("a").get(0).absUrl("href");
				String nom=tds.get(0).select("a").text();
				String ville=tds.get(1).text(); 
				String difficulté=tds.get(2).text(); 
				String année=tds.get(3).text();
				
				ViaFerrata via = read(
					urlVia,
					nom, 
					ville, 
					difficulté, 
					année
				);
				callback.read(via);
				checkAbort();
			}
		} catch (IOException ex) {
			callback.error(ex);
		} finally {
			callback.completed();
		}
	}
	
	private void read(String url) throws InterruptedException {
		callback.setup(1);
		ViaFerrata via = read(
			url,
			"EMPTY", 
			"EMPTY", 
			"EMPTY", 
			"EMPTY"
		);
		callback.read(via);
		callback.completed();
	}
	
	public ViaFerrata read(String url, String nom, String ville, 
			String difficulté, String année) throws InterruptedException {
		Elements divElements;
		Elements topoTitre;
		Elements topoText;
		checkAbort();
		try {
			Document doc = Jsoup.connect(url).get();

//			année = "TODO !!!!";
			
			Elements aElements = doc.select("h1 a");

			//Departement
			Element departement = aElements.get(2);
			String depUrl = departement.absUrl("href");
			String depName = departement.text();
			ViaDepartement département = new ViaDepartement(depUrl, depName);
			
			String nomComplet = aElements.get(3).text();
			String[] split = nomComplet.split(" » ");
			ville = split[0];
			nom = split[1];
			
			divElements = doc.select("td");
			String altituteDépart = divElements.get(4).text();
			difficulté = divElements.get(6).text();
			String altitudeArrivée = divElements.get(8).text();
			String longueur = divElements.get(10).text();
			String dénivelé = divElements.get(12).text();
			String prix = divElements.get(14).text();
			String passerelles = divElements.get(20).text();
			String ponts = divElements.get(21).text();
			String echelles = divElements.get(22).text();
			String tyroliennes = divElements.get(23).text();
				
			String latitude = doc.select("div[id=latitude]").get(0).text();
			String longitude = doc.select("div[id=longitude]").get(0).text();

			topoTitre = doc.select("div.topoTitre");
			topoText = doc.select("div.topoTexte");
			String description="null";
			String infos="null";
			String type="null";
			String horaires="null";
			String approche="null";
			int i = 0;
			for(Element titre : topoTitre) {
				checkAbort();
				String value = i>=topoText.size()?"weird":topoText.get(i).text();
				switch(titre.text()) {
					case "Où se trouve cette via ferrata ?": i--; break;
					case "Description": description=value; break;
					case "Type de Via Ferrata": type=value; break;
					case "Horaires": horaires=value; break;
					case "Approche / Accès routier": approche=value; break;
					case "Plus d'infos": infos=value; break;
				}
				i++;
			}
			
//			String description = topoText.size()<1?"null":topoText.get(0).text();
//			String infos = topoText.size()<2?"null":topoText.get(1).text();
//			String type = topoText.size()<4?"null":topoText.get(3).text();
//			String horaires = topoText.size()<5?"null":topoText.get(4).text();
//			String approche = topoText.size()<6?"null":topoText.get(5).text();
			
			return new ViaFerrata(url, nom, ville, difficulté, année, 
					département, altituteDépart, altitudeArrivée, 
					longueur, dénivelé, prix, passerelles, ponts, echelles, 
					tyroliennes, latitude, longitude, description, infos, 
					type, horaires, approche);	
		} catch (IndexOutOfBoundsException | IOException ex) {
			Logger.getLogger(ViaFerrataNet.class.getName()).log(Level.SEVERE, null, ex);
			return new ViaFerrata(url, nom, ville, difficulté, année);
		}
	}
	
}
