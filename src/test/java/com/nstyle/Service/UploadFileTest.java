package com.nstyle.Service;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.internal.stubbing.answers.CallsRealMethods;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.springframework.mock.web.MockMultipartFile;

import javax.persistence.EntityManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by mumarm45 on 18/10/2017.
 */

public class UploadFileTest {

    @Mock
    private FileService fileService;

    @Mock
    private EntityManager entityManager;
    @Mock
    private DealService dealService;

    @InjectMocks  @Spy
    private UploadFile uploadFile;




    private File file;
    List<String> data = new ArrayList<>();
    private Stream<String> lines;
    private String a[] = new String[]{"abc", "klm", "xyz", "pqr"};
    private UploadFile mockUp;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        file = new File("upload-dir/test.txt");
        lines = Arrays.stream(a);
       // mockUp = Mockito.spy(UploadFile.class);
      //  mockUp = Mockito.spy(uploadFileT);
    }

    @Test
    public void fileUploadSuccess() {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt",
                "text/plain", "12,COA,AOC,43".getBytes());
        when(fileService.store(multipartFile)).thenReturn(file);
        when(fileService.createBuffer(file)).thenReturn(lines);
        when(entityManager.unwrap(Session.class)).thenReturn(null);
        doNothing().when(fileService).deleteAll();
        doNothing().when(dealService).insertRecords("",0,"test.txt",null);


       Stream<String> lns =  uploadFile.fileUpload(multipartFile);

        verify(uploadFile, times(1)).fileUpload(multipartFile);
        assertThat(lines.equals(lns));

    }

    @Test(expected = RuntimeException.class)
    public void fileNotAvaiableUpload() {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt",
                "text/plain", "12,COA,AOC,43".getBytes());
        when(fileService.store(multipartFile)).thenThrow(new RuntimeException());
        when(fileService.createBuffer(file)).thenReturn(lines);
        when(entityManager.unwrap(Session.class)).thenReturn(null);
        doNothing().when(fileService).deleteAll();
        doNothing().when(dealService).insertRecords("",0,"test.txt",null);


        Stream<String> lns =  uploadFile.fileUpload(multipartFile);

        verify(uploadFile, times(1)).fileUpload(multipartFile);
        assertThat(lines.equals(lns));

    }
}
