package codigo;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class Processamento {
	
	public String processo(String notaVenda) throws SQLException, IOException {
		
	ResultSet dado = Conexao.selectSql("select id, status from dav where codigo = '" + notaVenda + "';");
	
	long idDav = 0;
	int status = 0;
	while(dado.next()) {
		idDav = dado.getLong("id");
		status = dado.getInt("status");
	}
	
	if(status != 1) {
		return "PEDIDO JA FINALIZADO OU CANCELADO";
	}
	
	
	Itens produto = new Itens();
	ArrayList<Itens> item = new ArrayList<>();
	
	ResultSet resul = produto.buscaProdutoSaida(notaVenda);
			
	while (resul.next()){
	
       Itens novoItem = new Itens();
       novoItem.setIdproduto(resul.getLong("idproduto"));
       novoItem.setQuantidade(resul.getDouble("quantidade"));
       item.add(novoItem);
        
   }
	
	int controle = 0;
	while(controle < item.size() ){
			
			Conexao.executaSql("update saldoestoque set quantidade = quantidade - '" + item.get(controle).getQuantidade() + "' where idproduto = '" + item.get(controle).getIdproduto() + "';");
			Conexao.executaSql("insert into movimentoestoque (idfilial, data, datahora, idproduto,variacao, tipodocumento, quantidadeentrada, quantidadesaida,valortotal, cancelado, observacao) values "
					+ "(1, (SELECT CURRENT_DATE), (SELECT CURRENT_TIMESTAMP(0)), " + item.get(controle).getIdproduto() + ", 0, -4, 0.000000, " + item.get(controle).getQuantidade() + ", 0.00, 0, 'P" + idDav + "')");
			controle++;
	}
	
	Conexao.executaSql("update dav set status = '4' where codigo = '" + notaVenda + "';");
	
	return "PEDIDO BAIXADO";


  }
	
	public String cancelar(String notaVenda) throws SQLException, IOException {
		
	ResultSet dado = Conexao.selectSql("select id, status from dav where codigo = '" + notaVenda + "';");
	
	long idDav = 0;
	int status = 0;
	
	while(dado.next()) {
		idDav = dado.getLong("id");
		status = dado.getInt("status");
	}
	
	if (status != 4)
	{
		return "PEDIDO JA CANCELADO OU EM ABERTO" ;
	}
	
	Itens produto = new Itens();
	ArrayList<Itens> item = new ArrayList<>();
	
	ResultSet resul = produto.buscaProdutoCancelamento(notaVenda);
			
	while (resul.next()){
	
       Itens novoItem = new Itens();
       novoItem.setIdproduto(resul.getLong("idproduto"));
       novoItem.setQuantidade(resul.getDouble("quantidade"));
       item.add(novoItem);
        
   }
	
	int controle = 0;
	while(controle < item.size() ){
			
			Conexao.executaSql("update saldoestoque set quantidade = quantidade + '" + item.get(controle).getQuantidade() + "' where idproduto = '" + item.get(controle).getIdproduto() + "';");
		controle++;
	}
	
	Conexao.executaSql("update movimentoestoque set cancelado = '1' where observacao = 'P" + idDav + "';");
	Conexao.executaSql("update dav set status = '3' where codigo = '" + notaVenda + "';");
	
	return "PEDIDO CANCELADO";

  }
	
}//fim classe
