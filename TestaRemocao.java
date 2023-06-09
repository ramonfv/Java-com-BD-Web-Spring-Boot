import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaRemocao {
    public static void main(String[] args) throws SQLException {
        Conexao conexao = new Conexao();
        Connection connection = conexao.getConnection();

        PreparedStatement stm = connection.prepareStatement("DELETE FROM produto WHERE id > ?");
        stm.setInt(1, 2);
        stm.execute();

        Integer linhasModificadas = stm.getUpdateCount();
        System.out.println("Quantidade de linhas modificadas: " + linhasModificadas);
        Conexao.closeConnection(connection);

    }
}
