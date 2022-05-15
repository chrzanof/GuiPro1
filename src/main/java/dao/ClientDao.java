package dao;

import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDao implements Dao<Client> {
    private List<Client> clients;

    public ClientDao() {
        this.clients = new ArrayList<>();
    }

    public ClientDao(List<Client> clients) {
        this.clients = clients;
    }

    private long getIndexOfId(long id) {
        long index = -1;
        for (int i = 0; i < clients.size(); i++) {
            if(clients.get(i).getId() == id){
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public Optional<Client> get(long id) {
        return Optional.ofNullable(clients.get((int)getIndexOfId(id)));
    }

    @Override
    public List<Client> getAll() {
        return clients;
    }

    @Override
    public void save(Client client) {
        clients.add(client);
    }

    @Override
    public void update(Client client, String[] params) {

    }

    @Override
    public void delete(Client client) {
        clients.remove(client);
    }
}
