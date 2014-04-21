package picstorage.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import picstorage.domain.BytePicture;
import picstorage.domain.Comment;
import picstorage.domain.Picture;
import picstorage.services.PictureService;
import picstorage.services.UserInfoService;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * User: ivannik
 * Date: 15.04.2014
 */
@Controller
@RequestMapping(value="/rest/pictures")
public class PicturesController {

    @Autowired
    private PictureService pictureService;

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(method= RequestMethod.GET)
    @ResponseBody
    public List<Picture> getAllPictures() {
        return pictureService.getAllPictures();
    }

    @RequestMapping(value = "/user/{login}", method= RequestMethod.GET)
    @ResponseBody
    public List<Picture> getUserPictures(@PathVariable String login) {
        return pictureService.getUserPictures(login);
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    @ResponseBody
    public Picture getPictureInfo(@PathVariable long id) {
        return pictureService.getPicture(id);
    }

    @RequestMapping(value="/{id}", method= RequestMethod.POST)
    @ResponseBody
    public Picture updatePictureInfo(@PathVariable long id, @RequestBody Picture picture) {
        if (picture.getDescription() != null) {
            pictureService.setDescription(id, picture.getDescription());
        }
        if (picture.getTitle() != null) {
            pictureService.setTitle(id, picture.getTitle());
        }
        return pictureService.getPicture(id);
    }

    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePicture(@PathVariable long id) {
        Picture pic = pictureService.getPicture(id);
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (pic.getCreator().getLogin().equals(userName)) {
            pictureService.deletePicture(id);
        }
    }

    @RequestMapping(value="/{id}/pic", method= RequestMethod.GET)
    public void handleFileDownload(@PathVariable long id, HttpServletResponse response) {
        BytePicture bytePicture = pictureService.getBytePicture(id);
        try {
            FileCopyUtils.copy(bytePicture.getPicture(), response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value="/{id}/thumb", method= RequestMethod.GET)
    public void handleThumbDownload(@PathVariable long id, HttpServletResponse response) {
        BytePicture bytePicture = pictureService.getBytePicture(id);
        try {
            FileCopyUtils.copy(bytePicture.getThumbnail(), response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Picture handleFileUpload(@RequestParam("title") String title,
                                    @RequestParam("description") String description,
                                    @RequestParam("file") MultipartFile file){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Picture picture = new Picture(userInfoService.getUserInfo(userName), new Date());
        picture.setTitle(title);
        picture.setDescription(description);
        if (!file.isEmpty()) {
            try {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                BufferedImage img = new BufferedImage(75, 75, BufferedImage.TYPE_INT_RGB);
                img.createGraphics().drawImage(ImageIO.read(file.getInputStream())
                        .getScaledInstance(75, 75, Image.SCALE_SMOOTH),0,0,null);
                ImageIO.write(img, "jpg", os);
                return pictureService.addPicture(picture,
                        new BytePicture(file.getOriginalFilename(), file.getBytes(), os.toByteArray()));
            } catch (Exception e) {
                return picture;
            }
        } else {
            return picture;
        }
    }

    @ResponseBody
    @RequestMapping(value="/{id}/addcomment", method= RequestMethod.POST)
    public Picture addComment(@PathVariable long id, @RequestParam("text") String text) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return pictureService.addComment(id, new Comment(
                userInfoService.getUserInfo(userName),
                new Date(),
                text));
    }
}
