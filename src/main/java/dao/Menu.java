package dao;

import model.MenuRow;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Menu implements Dao<MenuRow> {
    String file;
    List<MenuRow> itemList;


    public Menu() {
        this.itemList = new ArrayList<>();
    }

    public Menu(List<MenuRow> itemList) {
        this.itemList = itemList;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void writeToCSV() {
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write("");
            for (MenuRow row: itemList) {
                writer.append(row.convertToCSV() + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
               writer.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void readFromCSV() {
        String line = "";
        BufferedReader reader = null;
        try {
           reader = new BufferedReader(new FileReader(file));
           while ((line = reader.readLine()) != null) {
               String[] row = line.split(";");
               MenuRow menuRow = new MenuRow();
               menuRow.setId(Long.parseLong(row[0]));
               menuRow.setName(row[1]);
               menuRow.setDescription(row[2]);
               menuRow.setPrice(Double.parseDouble(row[3]));
               menuRow.setAvailable(Boolean.parseBoolean(row[4]));
               this.save(menuRow);
           }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public Optional<MenuRow> get(long index) {
        return Optional.ofNullable(itemList.get((int) index));
    }

    @Override
    public List<MenuRow> getAll() {
        return this.itemList;
    }

    @Override
    public void save(MenuRow menuRow) {
        itemList.add(menuRow);
    }

    @Override
    public void update(MenuRow menuRow, String[] params) {

    }

    @Override
    public void delete(MenuRow menuRow) {
        itemList.remove(menuRow);
    }
}
