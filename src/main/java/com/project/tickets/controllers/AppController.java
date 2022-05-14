package com.project.tickets.controllers;

import java.util.List;
import java.util.Set;

import com.project.tickets.entities.Ticket;
import com.project.tickets.entities.User;
import com.project.tickets.services.AccountService;
import com.project.tickets.services.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	@Autowired
	private TicketService ticketService;

	@Autowired
	AccountService accountService;

	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Ticket> listTickets = ticketService.listAll();
		model.addAttribute("listTickets", listTickets);
		System.out.println("Home Page");
		return "index";
	}

	@RequestMapping("/new")
	public String showNewTicketForm(Model model) {
		Ticket Ticket = new Ticket();
		model.addAttribute("ticket", Ticket);
		System.out.println("New Ticket Form");
		return "new_ticket";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveTicket(@ModelAttribute("ticket") Ticket ticket) {
		ticket.setStatus("NA");
		ticketService.save(ticket);
		System.out.println("Ticket Saved Successfully");
		return "redirect:/";
	}

	Ticket ticket_user = new Ticket();

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateTicket(@ModelAttribute("ticket") Ticket ticket,
			@RequestParam(value = "nameDev", required = false) String nameDev) {
		Set<User> users = ticket.getUsers();
		users.addAll(ticket_user.getUsers());

		ticketService.update(ticket, users);
		System.out.println("Ticket Updated Successfully");
		return "redirect:/";
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView showEditTicketForm(@PathVariable(name = "id") Long id, Model model) {
		List<User> users_dev = accountService.getUsersByRole("DEVELOPER");
		model.addAttribute("list_devs", users_dev);

		ModelAndView mav = new ModelAndView("edit_ticket");
		Ticket ticket = ticketService.get(id);
		System.out.println("Ticket à éditer : " + ticket.getUsers().toString());
		mav.addObject("ticket", ticket);
		ticket_user = ticket;
		return mav;
	}

	@RequestMapping("/delete/{id}")
	public String deleteTicket(@PathVariable(name = "id") Long id) {
		ticketService.delete(id);
		System.out.println("Ticket supprimé : " + id);
		return "redirect:/";
	}
}