

package com.agilemaster.partbase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class ApiDocController {
	@RequestMapping(value = "/api/doc/{docPage}")
	public String docPage(@PathVariable String docPage,Model model) {
		model.addAttribute("docPage",docPage);
		return "apiDoc/"+docPage;
	}

}

