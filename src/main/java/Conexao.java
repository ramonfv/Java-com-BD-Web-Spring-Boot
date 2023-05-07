import java.sql.*;

public class Conexao {
    public static Connection getConnection(String usuario, String senha) {
        String url = "jdbc:postgresql://localhost:5432/loja";
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (SQLException e){
            System.out.println("Não foi possível estabelecer a conexão.");
            e.printStackTrace();
        }
        return conexao;
    }

    public static void getData(Connection conexao){
        String SQL = "SELECT id, nome, descricao FROM produto";
        try{
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                System.out.println("ID: " +  rs.getInt("id") + ", Nome: " + rs.getString("nome") +
                                    ", Descrição: " + rs.getString("descricao"));
            }
            rs.close();
            stmt.close();
            conexao.close();
        }catch (SQLException e){
            System.out.println("Erro ao executar a consulta SQL");
            e.printStackTrace();

        }
    }
}

