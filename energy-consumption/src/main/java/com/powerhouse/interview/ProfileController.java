package com.powerhouse.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class ProfileController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(Map<String, List<String>> model) {
		
		ArrayList<String> profiles = new ArrayList<String>();
		profiles.add("JAN,A,0.2");
		profiles.add("JAN,B,0.18");
		profiles.add("FEB,A,0.1");

		model.put("profiles", profiles);
		
		return "index";
	}
	
//	@RequestMapping(method=RequestMethod.POST)
//	public String submit(CommonsMultipartFile file) {
//
//		String filename=file.getOriginalFilename();  
//        
//        System.out.println(filename);  
//		
//		return "redirect:/";
//	}
	
	@RequestMapping(method=RequestMethod.POST)
    public String handleFileUpload(@RequestParam("datafile") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {


        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        
        return "redirect:/";
    }
}
