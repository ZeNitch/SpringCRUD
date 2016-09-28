package com.ivan.controller;

import static org.mockito.Matchers.anyInt;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ivan.domain.User;
import com.ivan.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController 
{
	@Autowired
	private UserRepository repository;
	
	@RequestMapping(value="", method=RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("users", repository.findAll());
        return "users/list";
    }
	
	@RequestMapping(value="/new", method = RequestMethod.GET)
    public String newUser() 
	{
        return "users/new";
    }
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@RequestParam("firstName") String firstName, 
    						   @RequestParam("lastName") String lastName,
    						   @RequestParam("dateOfBirth") String dateOfBirth,
    						   @RequestParam("phoneNumber") String phoneNumber,
    						   @RequestParam("email") String email) 
	{
        repository.save(new User(firstName, lastName, dateOfBirth, phoneNumber, email));
        return new ModelAndView("redirect:/users");
    }
	
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable long id) 
	{
        repository.delete(id);
        return new ModelAndView("redirect:/users");
    }
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable long id, Model model) 
	{
        User user = repository.findOne(id);
        model.addAttribute("user", user);
        return "users/edit";
    }
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@RequestParam("id") long id,
				    		   @RequestParam("firstName") String firstName, 
							   @RequestParam("lastName") String lastName,
							   @RequestParam("dateOfBirth") String dateOfBirth,
							   @RequestParam("phoneNumber") String phoneNumber,
							   @RequestParam("email") String email) 
	{
        User user = repository.findOne(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDateOfBirth(dateOfBirth);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        repository.save(user);
        return new ModelAndView("redirect:/users");
    }
	
	@RequestMapping(value="/sortLastName", method=RequestMethod.GET)
    public String sortUsersByLastName(Model model) {
		List<User> users = (List<User>) repository.findAll();
		Collections.sort(users, new UserComparator("name"));
        model.addAttribute("users", users);
        return "users/list";
    }
	
	@RequestMapping(value="/sortDateOfBirth", method=RequestMethod.GET)
    public String sortUsersByDateOfBirth(Model model) {
		List<User> users = (List<User>) repository.findAll();
		Collections.sort(users, new UserComparator("doesntmatter"));
        model.addAttribute("users", users);
        return "users/list";
    }
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String search(@RequestParam("par")Long id, Model model){
		if(id!=null){
			User user = repository.findOne(id);
	        if(user != null)
	        {
		        model.addAttribute("user", user);
		        return "users/searchResult";
	        }
	        else
	        {
	        	return "users/list";
	        }
		}
		return "users/list";
	}
	class UserComparator implements Comparator<User> 
	{
		String id;
		public UserComparator(String id)
		{
			this.id = id;
		}
		@Override
		public int compare(User a, User b) 
		{
			if(this.id.equals("name"))
			{
				return a.getLastName().compareTo(b.getLastName());
			}
			else
			{
				return a.getDateOfBirth().compareTo(b.getDateOfBirth());
			}
		}
	}
	
	
}
