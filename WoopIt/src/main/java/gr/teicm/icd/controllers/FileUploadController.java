package gr.teicm.icd.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import gr.teicm.icd.data.entities.User;
import gr.teicm.icd.data.services.UserService;

@Controller
public class FileUploadController {
	@Autowired
	private UserService userService;
	
    @RequestMapping(value="/user/setavatar", method=RequestMethod.GET) 
    public String setAvatarView(HttpServletRequest request, HttpServletResponse response){ 
    	String photoPath = userService.getPhotoPath(userService.getLoggedInUsername());
    	request.setAttribute("profileAvatar", photoPath);
    	return "setavatar"; 
    }
    
    @RequestMapping(value="/user/removeavatar", method=RequestMethod.GET) 
    public String removeAvatarView(){ 
    	User user = new User();
    	user.setUserName(userService.getLoggedInUsername());
    	user.setUserPhotoPath(null);
    	userService.removePhoto(user);
    	return "setavatar"; 
    }
    
    @RequestMapping(value = "/user/setavatar", method = RequestMethod.POST)
    public String uploadFileHandler(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
    	
        if (!file.isEmpty()) {
            try {
            	String format = null;
            	String fileName = null;
            	String userName = userService.getLoggedInUsername();
            	
                byte[] bytes = file.getBytes();
                
                // Get file extension
                fileName = file.getOriginalFilename();
                int index = fileName.lastIndexOf(".");
                if(index > 0){
                    format = fileName.substring(index+1);
                    format = format.toLowerCase();
                }
                
                // Creating the directory to store file
                String uploadPath = "C:/WoopIt/upload/avatars/";
                
                File dir = new File(uploadPath);
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                String filePath = userName+"."+format;
                File serverFile = new File(uploadPath+filePath);
                
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                
                // Update photo path in db
                User user = new User();
                user.setUserName(userName);
                user.setUserPhotoPath(filePath);
                userService.insertPhotoPath(user);
                
                return "redirect:/user/setavatar?success=true";
            } catch (Exception e) {
                return "redirect:/user/setavatar?success=false&message=" + e.getMessage();
            }
        } else {
            return "redirect:/user/setavatar?success=false&message=File is empty";
        }
    }
}
