package com.powerhouse.interview;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.powerhouse.interview.entity.BusinessFault;
import com.powerhouse.interview.service.BusinessDelegate;

@Controller
@RequestMapping("/")
public class ConsumptionController{

	@Autowired
	BusinessDelegate businessService;

	@RequestMapping(value = "/profileUpload", method = RequestMethod.POST)
	public String handleProfileUpload(@RequestParam("datafile") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		
		if (isFileNameSuffixCsv(file.getOriginalFilename())) {
			try { 
				businessService.handleProfile(file);
				redirectAttributes.addFlashAttribute("profileMessage", businessService.getProfile("A") + "\n" + 
						businessService.getProfile("B"));
			} catch (IOException e) {
				redirectAttributes.addFlashAttribute("profileMessage", e.getMessage());
			} catch (BusinessFault e) {
				redirectAttributes.addFlashAttribute("profileMessage", e.getMessage());
			}

		} else {
			redirectAttributes.addFlashAttribute("profileMessage",
					"Excepted file type is csv. You choosed " + file.getOriginalFilename() + "!");
		}

		return "redirect:/";
	}
	
	public boolean isFileNameSuffixCsv(String fileName) {
		if(fileName != null) {
			String[] splittedFileName = fileName.split("\\.");
			if("csv".equals(splittedFileName[splittedFileName.length - 1])) {
				return true;
			}
		}
		return false;
	}

	@RequestMapping(value = "/meterUpload", method = RequestMethod.POST)
	public String handleMeterReadingsUpload(@RequestParam("datafile") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		
		if (isFileNameSuffixCsv(file.getOriginalFilename())) {
			try { 
				businessService.handleMeterReadings(file);
				redirectAttributes.addFlashAttribute("meterReadingMessage", businessService.getProfile("A") + "\n" + 
						businessService.getProfile("B"));
			} catch (IOException e) {
				redirectAttributes.addFlashAttribute("meterReadingMessage", e.getMessage());
			} catch (BusinessFault e) {
				redirectAttributes.addFlashAttribute("meterReadingMessage", e.getMessage());
			}

		} else {
			redirectAttributes.addFlashAttribute("meterReadingMessage",
					"Excepted file type is csv. You choosed " + file.getOriginalFilename() + "!");
		}

		return "redirect:/";
	}
	
}
