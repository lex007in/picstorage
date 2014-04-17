package picstorage.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import picstorage.domain.BytePicture;
import picstorage.domain.Picture;
import picstorage.services.PictureService;
import picstorage.services.UserInfoService;

import javax.servlet.http.HttpServletResponse;
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
    public void updatePictureInfo(@PathVariable long id) {
        pictureService.deletePicture(id);
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

    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Picture handleFileUpload(@RequestBody Picture picture,
                                                 @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {

                return pictureService.addPicture(
                        new Picture(userInfoService.getUserInfo("test"), new Date()),
                        new BytePicture(file.getOriginalFilename(), file.getBytes()));
            } catch (Exception e) {
                return picture;
            }
        } else {
            return picture;
        }
    }
}
