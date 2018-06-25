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

/**
 *
 * @author phramusca ( https://github.com/phramusca/JaMuz/ )
 */
public class ViaFerrata {
	public String url;
	public String nom;
	public String ville;
	public String difficulté;
	public String année;
	public ViaDepartement département = new ViaDepartement("null", "null");
	public String altituteDépart="null";
	public String altitudeArrivée="null";
	public String longueur="null";
	public String dénivelé="null";
	public String prix="null";
	public String passerelles="null";
	public String ponts="null";
	public String echelles="null";
	public String tyroliennes="null";
	public String latitude="null";
	public String longitude="null";
	public String description="null";
	public String infos="null";
	public String type="null";
	public String horaires="null";
	public String approche="null";

	public ViaFerrata(String url, String name, String town, String difficulty, String year) {
		this.url = url;
		this.nom = name;
		this.ville = town;
		this.difficulté = difficulty;
		this.année = year;
	}

	public ViaFerrata(String url, String nom, String ville, String difficulté, 
			String année, ViaDepartement département, String altituteDépart, 
			String altitudeArrivée, String longueur, 
			String dénivelé, String prix, String passerelles, String ponts, 
			String echelles, String tyroliennes, String latitude, 
			String longitude, String description, String infos, String type, 
			String horaires, String approche) {
		this.url = url;
		this.nom = nom;
		this.ville = ville;
		this.difficulté = difficulté;
		this.année = année;
		this.département = département;
		this.altituteDépart = altituteDépart;
		this.altitudeArrivée = altitudeArrivée;
		this.longueur = longueur;
		this.dénivelé = dénivelé;
		this.prix = prix;
		this.passerelles = passerelles;
		this.ponts = ponts;
		this.echelles = echelles;
		this.tyroliennes = tyroliennes;
		this.latitude = latitude;
		this.longitude = longitude;
		this.description = description;
		this.infos = infos;
		this.type = type;
		this.horaires = horaires;
		this.approche = approche;
	}

	@Override
	public String toString() {
		String sep = ":\t\t";
		String end = "\n";
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Nom").append(sep).append(nom).append(end);
		stringBuilder.append("URL").append(sep).append(url).append(end);
		stringBuilder.append("Ville").append(sep).append(ville).append(end);
		stringBuilder.append("Difficulté").append(sep).append(difficulté).append(end);
		stringBuilder.append("Année").append(sep).append(année).append(end);
		stringBuilder.append("Département").append(sep).append(département.name).append(end);
		stringBuilder.append("Altitute Départ").append(":\t").append(altituteDépart).append(end);
		stringBuilder.append("Altitude Arrivée").append(":\t").append(altitudeArrivée).append(end);
		stringBuilder.append("Longueur").append(sep).append(longueur).append(end);
		stringBuilder.append("Dénivelé").append(sep).append(dénivelé).append(end);
		stringBuilder.append("Prix").append(sep).append(prix).append(end);
		stringBuilder.append("Passerelle").append(sep).append(passerelles).append(end);
		stringBuilder.append("Pont de singe & Népalais").append(":\t").append(ponts).append(end);
		stringBuilder.append("Echelle & Filet").append(":\t").append(echelles).append(end);
		stringBuilder.append("Tyrolienne").append(sep).append(tyroliennes).append(end);
		stringBuilder.append("Latitude").append(sep).append(latitude).append(end);
		stringBuilder.append("Longitude").append(sep).append(longitude).append(end);
		stringBuilder.append("Description").append(sep).append(description).append(end);
		stringBuilder.append("Infos").append(sep).append(infos).append(end);
		stringBuilder.append("Type").append(sep).append(type).append(end);
		stringBuilder.append("Horaires").append(sep).append(horaires).append(end);
		stringBuilder.append("Approche").append(sep).append(approche).append(end);
		return stringBuilder.toString();
	}
	
}
