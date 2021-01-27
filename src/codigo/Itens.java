package codigo;

import java.io.IOException;
import java.sql.ResultSet;

public class Itens {

	private long idproduto;
	private double quantidade;

	public long getIdproduto() {
		return idproduto;
	}

	public void setIdproduto(long idproduto) {
		this.idproduto = idproduto;
	}


	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}
	

	public ResultSet buscaProdutoSaida(String notaVenda) throws IOException {
	ResultSet dados = Conexao.selectSql("select idproduto, quantidade from davitem where codigodav = '" + notaVenda + "';");
		return dados;
	}
	
	public ResultSet buscaProdutoCancelamento(String notaVenda) throws IOException {
	ResultSet dados = Conexao.selectSql("select idproduto, quantidade from davitem where codigodav = '" + notaVenda + "';");
		return dados;
	}
	
}

