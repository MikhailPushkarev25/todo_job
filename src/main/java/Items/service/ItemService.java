package Items.service;

import Items.model.Item;
import Items.persistence.ItemStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemStore itemStore;

    public ItemService(ItemStore itemStore) {
        this.itemStore = itemStore;
    }

    public void add(Item item, List<String> catId) {
        itemStore.add(item, catId);
    }

    public List all() {
        return itemStore.all();
    }

    public void update(Item item) {
        itemStore.update(item);
    }

    public Item findById(int id) {
       return itemStore.findById(id);
    }

    public void delete(Item item) {
        itemStore.delete(item);
    }
}
