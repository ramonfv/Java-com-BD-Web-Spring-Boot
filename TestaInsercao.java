import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class TestaInsercao {
    public static void main(String[] args) throws SQLException {

        Conexao conexao = new Conexao();
        Connection connection = conexao.getConnection();
        Statement stm = connection.createStatement();

        stm.execute("INSERT INTO produto (nome, descricao) VALUES ('Mouse', 'Mouse sem fio')",
                Statement.RETURN_GENERATED_KEYS);

        ResultSet rst = stm.getGeneratedKeys();
        while(rst.next()){
            Integer id = rst.getInt(1);
            System.out.println("O id criado foi: " + id);
        }



    }
}