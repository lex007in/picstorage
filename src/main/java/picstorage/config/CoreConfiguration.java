package picstorage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import picstorage.repository.BytePictureRepository;
import picstorage.repository.PictureRepository;
import picstorage.repository.UserInfoRepository;
import picstorage.services.PictureService;
import picstorage.services.PictureServiceImpl;
import picstorage.services.UserInfoService;
import picstorage.services.UsesrInfoServiceImpl;

/**
 * User: ivannik
 * Date: 13.04.2014
 */
@Configuration
public class CoreConfiguration {

    @Bean
    public UserInfoService userInfoService(UserInfoRepository userInfoRepository) {
        return new UsesrInfoServiceImpl(userInfoRepository);
    }

    @Bean
    public PictureService pictureService(PictureRepository pictureRepository,
                                         BytePictureRepository bytePictureRepository) {
        return new PictureServiceImpl(pictureRepository, bytePictureRepository);
    }
}
