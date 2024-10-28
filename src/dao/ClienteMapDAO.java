package dao;

import Cadastro.Cliente;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ClienteMapDAO implements IClienteDAO {

    private Map<Long, Cliente> clientes = new HashMap<>();



    @Override
    public Boolean cadastrar(Cliente cliente) {
        if (clientes.containsKey(cliente.getCpf())) {
            return false;
        }
        clientes.put(cliente.getCpf(), cliente);
        return true;
    }

    @Override
    public void excluir(Long cpf) {
        Cliente clienteCadastrado = clientes.get(cpf);

        if (clienteCadastrado == null) {
            clientes.remove(clienteCadastrado.getCpf(), clienteCadastrado);
        }

    }

    @Override
    public void alterar(Cliente cliente) {
        Cliente clienteCadastrado = clientes.get(cliente.getCpf());
        if (clienteCadastrado != null) {
            clienteCadastrado = clientes.get(cliente.getCpf());
            clienteCadastrado.setNome(cliente.getNome());
            clienteCadastrado.setTel(cliente.getTel());
            clienteCadastrado.setNumero(cliente.getNumero());
            clienteCadastrado.setEnd(cliente.getEnd());
            clienteCadastrado.setCidade(cliente.getCidade());
            clienteCadastrado.setEstado(cliente.getEstado());
        }

    }

    @Override
    public Cliente consultar(Long cpf) {
        return clientes.get(cpf);
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        return clientes.values();
    }
}
