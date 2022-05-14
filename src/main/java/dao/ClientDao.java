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

    @Override
    public Optional<Client> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Client> getAll() {
        return null;
    }

    @Override
    public void save(Client client) {

    }

    @Override
    public void update(Client client, String[] params) {

    }

    @Override
    public void delete(Client client) {

    }
}
