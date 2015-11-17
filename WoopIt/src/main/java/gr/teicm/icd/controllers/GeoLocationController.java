package gr.teicm.icd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gr.teicm.icd.data.entities.User;
import gr.teicm.icd.data.services.GeolocationService;
import gr.teicm.icd.data.services.UserService;

@Controller
public class GeoLocationController {
	
	@Autowired
	private GeolocationService geoLocationService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/trackme")
	public String goTrackMe()
	{
		return "trackme";
	}
	
	@RequestMapping(value="/trackme", method=RequestMethod.POST)
	public String setUserLocation(@ModelAttribute User user)
	{
		String userName = userService.getLoggedInUsername();
        // Update user location
        user.setUserName(userName);
		geoLocationService.insertGeoLocation(user);
		return "redirect:home";
	}
}
