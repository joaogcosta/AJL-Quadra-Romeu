
package ws;

import entidade.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import rn.ClienteRN;


/**
 *
 * @author 631620135
 */
@Path("cliente")
public class ClienteWS {
    
    ClienteRN clienteRN;

    @Context
    private UriInfo context;

    public ClienteWS() {
        clienteRN = new ClienteRN();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> getClientes() {
        return (clienteRN.listar());

    }
    
    @GET
    @Path("/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente getClientePorCPF(@PathParam("cpf") String cpf) {
        return clienteRN.buscarPorCPF(cpf);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente insereCliente(Cliente cliente, 
            @Context HttpServletResponse response){
        cliente = clienteRN.inserir(cliente);
        
        
        response.setStatus(HttpServletResponse.SC_CREATED); 
        try {
            response.flushBuffer();
        } catch (IOException ex) {
            throw new InternalServerErrorException();
        }
        return(cliente);
    }
    
    @PUT
    @Path("/{cpf}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente atualizaCliente(@PathParam("cpf") String cpf, Cliente cliente){
        cliente.setCpf(cpf);
        return clienteRN.atualizar(cliente);
    }
    
    @DELETE
    @Path("/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente removeMotor(@PathParam("cpf") String cpf){
        Cliente cliente = clienteRN.deletar(cpf);
        return cliente;
    }
    
}//FINAL CLASS
