package com.nstyle.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static org.mockito.Mockito.when;

/**
 * Created by mumarm45 on 18/10/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileServiceTest {

    @InjectMocks
    private FileService fileService;

    @Test
    public void savingFileOnUpload() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt",
                "text/plain", "12,COA,AOC,43".getBytes());
        File file = fileService.store(multipartFile);


        assert file.exists();
    }
}
