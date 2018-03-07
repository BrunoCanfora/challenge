package br.com.project.challenge.model;

public class Consultorio {
	
	private int numero;
	
	
	//----------------------------------------GETTERSANDSETTERS---------------------------------------------------

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numero;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consultorio other = (Consultorio) obj;
		if (numero != other.numero)
			return false;
		return true;
	}

}
