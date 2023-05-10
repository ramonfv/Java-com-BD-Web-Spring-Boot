import java.sql.*;
import java.util.List;

public class TestaInsercaoComProduto {
    public static void main(String[] args) throws SQLException {

        Produto comoda = new Produto("Comoda", "Comoda vertical com 6 gavetas");


        try {
            Conexao conexao = new Conexao();
            ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
            produtoDAO.salvar(comoda);
            List<Produto> listaDeProdutos = produtoDAO.listar();
            listaDeProdutos.stream().forEach(lp -> System.out.println(lp));

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}