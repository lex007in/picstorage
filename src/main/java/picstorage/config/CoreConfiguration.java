package picstorage.config;

import org.springframework.boot.context.embedded.MultiPartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CharacterEncodingFilter;
import picstorage.repository.BytePictureRepository;
import picstorage.repository.PictureRepository;
import picstorage.repository.UserInfoRepository;
import picstorage.services.PictureService;
import picstorage.services.PictureServiceImpl;
import picstorage.services.UserInfoService;
import picstorage.services.UsesrInfoServiceImpl;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;

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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultiPartConfigFactory factory = new MultiPartConfigFactory();
        factory.setMaxFileSize("128MB");
        factory.setMaxRequestSize("128MB");
        return factory.createMultipartConfig();
    }

    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }
}
