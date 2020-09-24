package com.trungtamjava.springbootdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.trungtamjava.springbootdemo.dao.BillDao;
import com.trungtamjava.springbootdemo.dao.BillProductDao;
import com.trungtamjava.springbootdemo.entity.BillProduct;

@Controller
public class AdminBillController {
	@Autowired
	private BillDao billDao;
	@Autowired
	private BillProductDao billProductDao;
	long billId;
	@GetMapping("/admin/bills")
	public String getAllBill(Model model) {
		model.addAttribute("bills",billDao.findAll());
		return "admin/bills";
	}
	@GetMapping("/admin/bill/{id}")
	public String billDetail(@PathVariable Long id,Model model) {
		this.billId=id;
		List<BillProduct> list = billProductDao.searchbyBill(id);
		model.addAttribute("billProducts", list);
		return "admin/bill";
	}
	@GetMapping("/admin/delete-bills/{id}")
	public String deleteBills(@PathVariable Long id,Model model) {
		billDao.deleteById(id);
		return "redirect:/admin/bills";
	}
	@GetMapping("/admin/delete-bill/{id}")
	public String deleteBill(@PathVariable Long id,Model model) {
		billProductDao.deleteById(id);
		return "redirect:/admin/bill/"+this.billId;
	}
}
