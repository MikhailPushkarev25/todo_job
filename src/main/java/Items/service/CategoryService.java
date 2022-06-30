package Items.service;

import Items.model.Category;
import Items.persistence.CategoryStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryStore store;

    public CategoryService(CategoryStore store) {
        this.store = store;
    }

    public List<Category> findAllCategory() {
        return store.findAllCategory();
    }
}
