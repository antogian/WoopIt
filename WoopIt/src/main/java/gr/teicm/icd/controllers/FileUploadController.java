package gr.teicm.icd.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
	
    @RequestMapping(value = "/user/setavatar", method = RequestMethod.POST)
    public String uploadFileHandler(@RequestParam("file") MultipartFile file) {
 
        if (!file.isEmpty()) {
            try {
            	String format = null;
            	String fileName = null;
            	
                byte[] bytes = file.getBytes();
                
                // Get file extension
                fileName = file.getOriginalFilename();
                System.out.println(fileName);
                int index = fileName.lastIndexOf(".");
                if(index > 0){
                    format = fileName.substring(index+1);
                    format = format.toLowerCase();
                }
                
                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "avatars");
                if (!dir.exists())
                    dir.mkdirs();
 
                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + "USERNAME" + "." + format); //TODO: to test.png einai hardcoded, prepei na mpenei to username
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
 
                return "redirect:/user/setavatar?success=true";
            } catch (Exception e) {
                return "redirect:/user/setavatar?success=false&message=" + e.getMessage();
            }
        } else {
            return "redirect:/user/setavatar?success=false&message=File is empty";
        }
    }
}
