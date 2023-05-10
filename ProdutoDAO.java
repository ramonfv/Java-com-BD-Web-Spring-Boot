import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private Conexao conexao;

    public ProdutoDAO(Conexao conexao) throws SQLException {
        this.conexao = conexao;

    }



    public void salvar(Produto produto) throws SQLException {

        Connection connection = conexao.getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO produto (NOME, DESCRICAO) VALUES (?,?)";
        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getDescricao());
            pstm.execute();

            try (ResultSet rst = pstm.getGeneratedKeys()) {

                while (rst.next()) {
                    produto.setId(rst.getInt(1));
                }
                connection.commit();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ROLLBACK Executado");
                connection.rollback();
            }
        }
    }

    public List<Produto> listar() throws SQLException {
        Connection connection = conexao.getConnection();
        List<Produto> produtos = new ArrayList<Produto>();
        String sql = "SELECT ID, NOME, DESCRICAO FROM produto";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();

            try (ResultSet rst = pstm.getResultSet()) {
                while (rst.next()) {
                    Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));
                    produtos.add(produto);
                }

            }
        }
        return produtos;
    }
}


