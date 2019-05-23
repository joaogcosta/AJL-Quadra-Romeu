
package rn;

import entidade.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JPAUtil;

/**
 *
 * @author 631620135
 */
public class ClienteRN {
   
    public ClienteRN() {

    }
    public Cliente inserir(Cliente cliente) {
            EntityManager manager = JPAUtil.createManager();

            manager.getTransaction().begin();
            manager.persist(cliente);
            manager.getTransaction().commit();

            manager.close();

            return (cliente);

        }
    public Cliente buscarPorCPF(String cpf) {
        EntityManager manager = JPAUtil.createManager();
        Cliente cliente = manager.find(Cliente.class, cpf);
        manager.close();
        return cliente;
    }
    
    public Cliente atualizar(Cliente cliente) {
        EntityManager manager = JPAUtil.createManager();

        manager.getTransaction().begin();
        cliente = manager.merge(cliente);
        manager.getTransaction().commit();

        manager.close();

        return (cliente);
    }
    
    public Cliente deletar(String cpf) {

        EntityManager manager = JPAUtil.createManager();
        Cliente cliente = manager.find(Cliente.class, cpf);

        manager.getTransaction().begin();
        manager.remove(cliente.getCpf());
        manager.getTransaction().commit();

        manager.close();

        return (cliente);

    }
    
    public List<Cliente> listar() {
        EntityManager manager = JPAUtil.createManager();
        
        Query query = manager.createQuery("select m from Cliente m");
        List<Cliente> listaClientes = query.getResultList();
        return listaClientes;
    }



}//FINAL CLASS
