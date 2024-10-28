import Cadastro.Cliente;
import dao.ClienteMapDAO;
import dao.IClienteDAO;

import javax.swing.*;

public class App {
    private static IClienteDAO iClienteDAO;

    public static void main(String[] args) {
        iClienteDAO = new ClienteMapDAO();
        String opcao = JOptionPane.showInputDialog(null,
                "Digite 1 para cadastrar, 2 para consultar, 3 para exclusão, 4 para alteração ou 5 para sair",
                "Green Dinner", JOptionPane.INFORMATION_MESSAGE);

        while (!isOpcaoValido(opcao)) {
            if ("".equals(opcao)) {
                sair();
            }
            opcao = JOptionPane.showInputDialog(null,
                    "Opção inválida. Digite 1 para cadastrar, 2 para consultar, 3 para exclusão, 4 para alteração ou 5 para sair",
                    "Green Dinner", JOptionPane.INFORMATION_MESSAGE);
        }

        while (isOpcaoValido(opcao)) {
            if (isOpcaoSair(opcao)) {
                sair();
            } else if (isCadastro(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite os dados do cliente separados por vírgula, conforme exemplo: Nome, CPF, Telefone, Endereço, Estado, Número",
                        "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                cadastrar(dados);
            } else if (isConsulta(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite o CPF do cliente",
                        "Consulta Cliente", JOptionPane.INFORMATION_MESSAGE);
                consultar(dados);
            } else if (isExclusao(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite o CPF do cliente",
                        "Exclusão Cliente", JOptionPane.INFORMATION_MESSAGE);
                excluir(dados);
            } else {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite os dados do cliente separados por vírgula, conforme exemplo: Nome, CPF, Telefone, Endereço, Estado, Número",
                        "Green Dinner", JOptionPane.INFORMATION_MESSAGE);
                atualizar(dados);
            }
        }
    }

    private static void atualizar(String dados) {
        String[] dadosSeparados = dados.split(",");
        Cliente cliente = new Cliente(dadosSeparados[0], dadosSeparados[1], dadosSeparados[2], dadosSeparados[3], dadosSeparados[4], dadosSeparados[5], dadosSeparados[6]);
        iClienteDAO.alterar(cliente);
    }

    private static void excluir(String dados) {
        iClienteDAO.excluir(Long.parseLong(dados));
        JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!",
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void consultar(String dados) {
        Cliente cliente = iClienteDAO.consultar(Long.parseLong(dados));
        if (cliente != null) {
            JOptionPane.showMessageDialog(null, "Cliente encontrado: " + cliente.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void cadastrar(String dados) {
        String[] dadosSeparados = dados.split(",");
        Cliente cliente = new Cliente(dadosSeparados[0], dadosSeparados[1], dadosSeparados[2], dadosSeparados[3], dadosSeparados[4], dadosSeparados[5], dadosSeparados[6]);
        Boolean isCadastrado = iClienteDAO.cadastrar(cliente);
        if (isCadastrado) {
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Sucesso no cadastro", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente já se encontra cadastrado", "Erro no cadastro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void sair() {
        JOptionPane.showMessageDialog(null, "Até logo!", "Sair", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private static boolean isOpcaoValido(String opcao) {
        return "1".equals(opcao) || "2".equals(opcao) || "3".equals(opcao) || "4".equals(opcao) || "5".equals(opcao);
    }

    private static boolean isCadastro(String opcao) {
        return "1".equals(opcao);
    }

    private static boolean isConsulta(String opcao) {
        return "2".equals(opcao);
    }

    private static boolean isExclusao(String opcao) {
        return "3".equals(opcao);
    }

    private static boolean isOpcaoSair(String opcao) {
        return "5".equals(opcao);
    }
}
