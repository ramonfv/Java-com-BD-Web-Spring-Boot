import java.sql.Connection;
import java.util.Scanner;

public class TestaConexao {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do usuário");
        String usuario = scanner.nextLine();
        System.out.println("Digite a senha: ");
        String senha = scanner.nextLine();

        Connection conexao = Conexao.getConnection(usuario, senha);
        Conexao.getData(conexao);

    }
}
