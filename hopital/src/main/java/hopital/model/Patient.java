package hopital;

import java.util.Objects;

public class Patient {
	private Integer patientID;
	private String nomPatient;
	private String prenomPatient;

	public Patient(Integer patientID, String nomPatient, String prenomPatient) {
		super();
		this.patientID = patientID;
		this.nomPatient = nomPatient;
		this.prenomPatient = prenomPatient;
	}

	public Patient(String nomPatient, String prenomPatient) {
		super();
		this.nomPatient = nomPatient;
		this.prenomPatient = prenomPatient;
	}

	public Integer getPatientID() {
		return patientID;
	}

	public void setPatientID(Integer patientID) {
		this.patientID = patientID;
	}

	public String getNomPatient() {
		return nomPatient;
	}

	public void setNomPatient(String nomPatient) {
		this.nomPatient = nomPatient;
	}

	public String getPrenomPatient() {
		return prenomPatient;
	}

	public void setPrenomPatient(String prenomPatient) {
		this.prenomPatient = prenomPatient;
	}

	@Override
	public int hashCode() {
		return Objects.hash(patientID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		return Objects.equals(patientID, other.patientID);
	}

	
}
