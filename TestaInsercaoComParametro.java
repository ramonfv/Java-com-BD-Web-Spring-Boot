import java.sql.*;

public class TestaInsercaoComParametro {
    public static void main(String[] args) throws SQLException, Exception {

        Conexao conexao = new Conexao();
        try (Connection connection = conexao.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement stm = connection.prepareStatement("INSERT INTO produto (nome, descricao) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);) {

                adicionarVariavel("SmartTV", "SANSUMG 45 polegadas", stm);
                adicionarVariavel("Monitor", "LG UltraWide 29'", stm);

                connection.commit();

                Conexao.getData(connection);

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ROLLBACK Executado");
                connection.rollback();
            }
        }
    }

    private static void adicionarVariavel (String nome, String descricao, PreparedStatement stm) throws SQLException {

        stm.setString(1, nome);
        stm.setString(2, descricao);

        stm.execute();

        try (ResultSet rst = stm.getGeneratedKeys()) {

            while (rst.next()) {
                Integer id = rst.getInt(1);
                System.out.println("O id criado foi: " + id);
            }
        }
    }

}