package domain.core.lugares;

import static java.util.Objects.hash;

public class TipoDeLugar {
	private String desig;
	private String descricao;
	private double preco;
	
	public TipoDeLugar(String d, String desc, double preco) {
		this.desig = d;
		this.descricao = desc;
		this.preco = preco;
	}

	public String getDesig() {
		return desig;
	}

	public void setDesig(String desig) {
		this.desig = desig;
	}

	public String getDescricao() {
		return descricao;
	}

	public double getPreco() {
		return preco;
	}
	
	@Override
	public String toString() {
		return desig;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null || getClass() != obj.getClass())
	        return false;
	    TipoDeLugar other = (TipoDeLugar) obj;
	    return Double.compare(preco, other.preco) == 0 &&
	           desig.equals(other.desig) &&
	           descricao.equals(other.descricao);
	}

	@Override
	public int hashCode() {
	    return hash(desig, descricao, preco);
	}
	
}
