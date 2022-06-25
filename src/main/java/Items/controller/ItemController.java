package Items.controller;

import Items.model.Item;
import Items.model.User;
import Items.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping("/items")
    public String items(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        model.addAttribute("user", user);
        model.addAttribute("items", service.all());
        return "items";
    }
    @GetMapping("/success")
    public String success(Model model) {
        model.addAttribute("items", service.all());
        return "success";
    }

    @GetMapping("/exercise")
    public String exercise(Model model) {
        model.addAttribute("items", service.all());
        return "exercise";
    }

    @GetMapping("/newItem")
    public String newItem(Model model) {
        model.addAttribute("items", service.all());
        return "newItem";
    }

    @GetMapping("/formAddItem")
    public String formAddItem() {
        return "addItem";
    }

    @GetMapping("/publicate")
    public String publicate(Model model) {
        return "publicate";
    }

    @GetMapping("/formUpdateItem/{itemId}")
    public String formUpdatePost(Model model, @PathVariable("itemId") int id) {
        Item item = service.findById(id);
        model.addAttribute("item", item);
        return "updateItem";
    }

    @GetMapping("/formDeleteItem/{itemId}")
    public String deleteTicket(Model model, @PathVariable("itemId") int id) {
        Item item = service.findById(id);

        model.addAttribute("item", item);
        return "deleteItem";
    }

    @PostMapping("/itemDelete")
    public String formDeleteTicket(@ModelAttribute Item item) {
        service.delete(item);
        return "redirect:/exercise";
    }

    @PostMapping("/updateItem")
    public String updatePost(@ModelAttribute Item item) {
        service.update(item);
        return "redirect:/exercise";
    }

    @PostMapping("/createItem")
    public String createItem(@ModelAttribute Item item) {
        service.save(item);
        return "redirect:/items";
    }


    @PostMapping("/publication")
    public String publication(@ModelAttribute Item item) {
        service.save(item);
        return "redirect:/items";
    }

}
