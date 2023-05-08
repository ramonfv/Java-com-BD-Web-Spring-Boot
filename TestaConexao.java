import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexao {
    public static void main(String[] args) throws SQLException {

        Conexao conexao = new Conexao();
        Connection connection = conexao.getConnection();
        Conexao.getData(connection);
        Conexao.closeConnection(connection);

    }
}