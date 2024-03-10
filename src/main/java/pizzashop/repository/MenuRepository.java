package pizzashop.repository;

import pizzashop.exceptions.OrderException;
import pizzashop.model.PizzaItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MenuRepository {
    private static String filename = "data/menu.txt";
    private List<PizzaItem> listMenu;

    private void readMenu() throws OrderException {
        File file = new File(filename);
        this.listMenu= new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = null;
            while((line=br.readLine())!=null){
                PizzaItem menuItem=getMenuItem(line);
                listMenu.add(menuItem);
            }
        } catch (IOException e) {
            throw new OrderException();
        }
    }

    private PizzaItem getMenuItem(String line){
        PizzaItem item=null;
        if (line==null|| line.equals("")) return null;
        StringTokenizer st=new StringTokenizer(line, ",");
        String name= st.nextToken();
        double price = Double.parseDouble(st.nextToken());
        item = new PizzaItem(name, 0, price);
        return item;
    }

    public List<PizzaItem> getMenu() throws OrderException {
        readMenu();//create a new menu for each table, on request
        return listMenu;
    }
}
