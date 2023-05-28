package com.codemos.springthymeleafitemservice.web.form;

import com.codemos.springthymeleafitemservice.domain.item.DeliveryCode;
import com.codemos.springthymeleafitemservice.domain.item.Item;
import com.codemos.springthymeleafitemservice.domain.item.ItemRepository;
import com.codemos.springthymeleafitemservice.domain.item.ItemType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/form/items")
@RequiredArgsConstructor
public class FormItemController {
	private final ItemRepository itemRepository;

	@ModelAttribute("regions")
	public Map<String, String> regions() {
		Map<String, String> regions = new LinkedHashMap<>();
		regions.put("SEOUL", "Seoul");
		regions.put("BUSAN", "Busan");
		regions.put("JEJU", "Jeju");

		return regions;
	}

	@ModelAttribute("itemTypes")
	public ItemType[] itemTypes() {
		return ItemType.values();
	}

	@ModelAttribute("deliveryCodes")
	public List<DeliveryCode> deliveryCodes() {
		List<DeliveryCode> deliveryCodes = new ArrayList<>();
		deliveryCodes.add(new DeliveryCode("FAST", "Fast Shipping"));
		deliveryCodes.add(new DeliveryCode("NORMAL", "Normal Shipping"));
		deliveryCodes.add(new DeliveryCode("SLOW", "Slow Shipping"));

		return deliveryCodes;
	}

	@GetMapping
	public String items(Model model) {
		List<Item> items = itemRepository.findAll();
		model.addAttribute("items", items);

		return "form/items";
	}

	@GetMapping("/{itemId}")
	public String item(@PathVariable long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);

		return "form/item";
	}

	@GetMapping("/add")
	public String addForm(Model model) {
		model.addAttribute("item", new Item());

		return "form/add-form";
	}

	@PostMapping("/add")
	public String addItem(Item item, RedirectAttributes redirectAttributes) {
		log.info("item.open={}", item.getOpen());
		log.info("item.regions={}", item.getRegions());
		log.info("item.itemType={}", item.getItemType());

		Item savedItem = itemRepository.save(item);
		redirectAttributes.addAttribute("itemId", savedItem.getId());
		redirectAttributes.addAttribute("status", true);
		return "redirect:/form/items/{itemId}";
	}

	@GetMapping("/{itemId}/edit")
	public String editForm(@PathVariable Long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);

		return "form/edit-form";
	}

	@PostMapping("/{itemId}/edit")
	public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
		itemRepository.update(itemId, item);

		return "redirect:/form/items/{itemId}";
	}

	@GetMapping("/{itemId}/delete")
	public String deleteForm(@PathVariable Long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);

		return "form/delete-form";
	}

	@PostMapping("/{itemId}/delete")
	public String delete(@PathVariable Long itemId) {
		itemRepository.delete(itemId);
		return "redirect:/form/items";
	}

	/**
	 * Test Data
	 */
	@PostConstruct
	public void init() {
		itemRepository.save(new Item("itemA", 10000, 10));
		itemRepository.save(new Item("itemB", 20000, 20));
	}
}
