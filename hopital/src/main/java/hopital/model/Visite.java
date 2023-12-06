package hopital.model;

import java.time.LocalDate;
import java.util.Objects;

public class Visite {
	private Integer numeroVisite;
	private Patient patient;
	private Medecin medecin;
	private static double coutVisite = 20;
	private int salle;
	private LocalDate dateVisite;
	
	public Visite(Integer numeroVisite, Patient patient, Medecin medecin, int salle,
			LocalDate dateVisite) {
		super();
		this.numeroVisite = numeroVisite;
		this.patient = patient;
		this.medecin = medecin;
		this.salle = salle;
		this.dateVisite = dateVisite;
	}

	public Visite(Patient patient, Medecin medecin, int salle, LocalDate dateVisite) {
		super();
		this.patient = patient;
		this.medecin = medecin;
		this.salle = salle;
		this.dateVisite = dateVisite;
	}
	
	

	public Visite(Integer numeroVisite, int salle, LocalDate dateVisite) {
		super();
		this.numeroVisite = numeroVisite;
		this.salle = salle;
		this.dateVisite = dateVisite;
	}

	public Integer getNumeroVisite() {
		return numeroVisite;
	}

	public void setNumeroVisite(Integer numeroVisite) {
		this.numeroVisite = numeroVisite;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Compte getMedecin() {
		return medecin;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}

	public double getCoutVisite() {
		return coutVisite;
	}

	public void setCoutVisite(double coutVisite) {
		this.coutVisite = coutVisite;
	}

	public int getSalle() {
		return salle;
	}

	public void setSalle(int salle) {
		this.salle = salle;
	}

	public LocalDate getDateVisite() {
		return dateVisite;
	}

	public void setDateVisite(LocalDate dateVisite) {
		this.dateVisite = dateVisite;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numeroVisite);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Visite other = (Visite) obj;
		return Objects.equals(numeroVisite, other.numeroVisite);
	}
	
	
	
}
