import java.sql.*;
import java.util.Scanner;

public class Conexao {
    private String url = "jdbc:postgresql://localhost:5432/loja";
    private String usuario = "postgres";
    private String senha;

    public Conexao() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a senha: ");
        this.senha = scanner.nextLine();
    }

    public Connection getConnection() throws SQLException {

        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
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
            PreparedStatement stm = conexao.prepareStatement("SELECT id, nome, descricao FROM produto");
            stm.execute();
            ResultSet rs = stm.getResultSet();
            while (rs.next()) {
                System.out.println("ID: " +  rs.getInt("id") + ", Nome: " + rs.getString("nome") +
                        ", Descrição: " + rs.getString("descricao"));
            }
            rs.close();
            stm.close();
        }catch (SQLException e){
            System.out.println("Erro ao executar a consulta SQL");
            e.printStackTrace();

        }
    }
    public static void closeConnection(Connection conexao){
        try{
            conexao.close();
            System.out.println("\nConexão fechada com sucesso!");
        }catch (SQLException e){
            System.out.println("\nErro ao fechar conexão");
            e.printStackTrace();
        }
    }
}