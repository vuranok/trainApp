package com.powerhouse.interview;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.powerhouse.interview.entity.BusinessFault;
import com.powerhouse.interview.entity.Profile;
import com.powerhouse.interview.util.Converter;
import com.powerhouse.interview.util.FileUtil;
import com.powerhouse.interview.util.Validate;

@Controller
@RequestMapping("/")
public class ProfileController {

	@Autowired
	FileUtil fileUtil;

	@Autowired
	Validate validate;

	@Autowired
	Converter converter;

	@RequestMapping(method = RequestMethod.GET)
	public String index(Map<String, List<String>> model) {

		ArrayList<String> profiles = new ArrayList<String>();
		profiles.add("JAN,A,0.2");
		profiles.add("JAN,B,0.18");
		profiles.add("FEB,A,0.1");

		model.put("profiles", profiles);

		return "index";
	}

	@RequestMapping(value = "/profileUpload", method = RequestMethod.POST)
	public String handleProfileUpload(@RequestParam("datafile") MultipartFile file,
			RedirectAttributes redirectAttributes) {

		if (validate.fileNameSuffix(file.getOriginalFilename())) {

			try {
				List<String> profiles = fileUtil.readLines(file);
				profiles = profiles.subList(1, profiles.size());
				Map<String, Profile> profileMap = converter.convertToProfileMap(profiles);
				for(Profile profile : profileMap.values()) {
					if(!validate.fractions(profile)) {
						throw new BusinessFault("Given fractions are not valid for profile " + profile.getName());
					}
				}
				redirectAttributes.addFlashAttribute("message", profiles);
			} catch (BusinessFault e) {
				redirectAttributes.addFlashAttribute("message", e.getMessage());
			} catch (IOException e) {
				redirectAttributes.addFlashAttribute("message", e.getMessage());
			}

		} else {
			redirectAttributes.addFlashAttribute("message",
					"Excepted file type is csv. You choosed " + file.getOriginalFilename() + "!");
		}

		return "redirect:/";
	}
}
