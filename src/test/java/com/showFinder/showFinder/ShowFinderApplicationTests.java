package com.showFinder.showFinder;

import com.showFinder.showFinder.entity.dao.ShowServicePrimaryDao;
import com.showFinder.showFinder.entity.service.ShowServiceImpl;
import com.showFinder.showFinder.model.data.ShowBasicInfo;
import com.showFinder.showFinder.model.data.ShowInfo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyString;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ShowFinderApplicationTests {

	@MockBean
	private ShowServicePrimaryDao showServicePrimaryDao;

	@MockBean
	private ShowServiceImpl showService;


	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	@BeforeAll
	public void  setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		ShowInfo show = new ShowInfo(1,"A Quiet place",7.5,2018,false,false,false,false,false,false,false,false,false,false,false,false,true,true,false,false,null);
		ShowInfo show2 = new ShowInfo(2,"A Quiet place 2",7.3,2020,false,false,false,false,false,false,false,false,false,false,false,false,true,true,false,false,null);

		Mockito.when(showServicePrimaryDao.findByShowId(1))
				.thenReturn(Arrays.asList(show));
		ShowBasicInfo showBasicInfo = new ShowBasicInfo(1,"A Quiet place", 2018);
		ShowBasicInfo showBasicInfo2 = new ShowBasicInfo(2,"A Quiet place 2", 2020);

		Mockito.when(showServicePrimaryDao.findPopularShows(PageRequest.of(0, 15)))
				.thenReturn(Arrays.asList(showBasicInfo,showBasicInfo2));
	}

	@Test
	void contextLoads() {
	}

	@Test
	public  void showInfo() throws Exception {
		mockMvc.perform(get("/showInfo/1")
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(content().json("{\n" +
						"    \"errorCode\": null,\n" +
						"    \"errorMessage\": null,\n" +
						"    \"showNames\": [\n" +
						"        {\n" +
						"            \"showId\": 1,\n" +
						"            \"showName\": \"A Quiet place\",\n" +
						"            \"rating\": 7.5,\n" +
						"            \"releaseyear\": 2018,\n" +
						"            \"actionFlag\": false,\n" +
						"            \"comedyFlag\": false,\n" +
						"            \"documentaryFlag\": false,\n" +
						"            \"fantasyFlag\": false,\n" +
						"            \"horrorFlag\": false,\n" +
						"            \"musicalFlag\": false,\n" +
						"            \"romanceFlag\": false,\n" +
						"            \"sportFlag\": false,\n" +
						"            \"animationFlag\": false,\n" +
						"            \"crimeFlag\": false,\n" +
						"            \"historyFlag\": false,\n" +
						"            \"kidsFlag\": false,\n" +
						"            \"thrillerFlag\": true,\n" +
						"            \"scifiFlag\": true,\n" +
						"            \"warFlag\": false,\n" +
						"            \"realityFlag\": false,\n" +
						"            \"image\": \"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\"\n" +
						"        }\n" +
						"    ]\n" +
						"}"));
	}

	@Test
	public  void popularShows() throws Exception {
		mockMvc.perform(get("/showInfo/popular")
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk());

	}
}
