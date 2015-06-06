/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kazakov.webbeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author aka_Kup3a
 */
@ManagedBean
@ViewScoped
public class Bean {
    private List<Item> items = new ArrayList<Item>();

    public void add() {
        items.add(new Item());
    }

    public void submit() {
        System.out.println("items: " + items);
    }

    public List<Item> getItems() {
        return items;
    }
}