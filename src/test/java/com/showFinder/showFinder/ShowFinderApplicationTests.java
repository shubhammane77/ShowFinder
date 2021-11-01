package com.showFinder.showFinder;

import com.showFinder.showFinder.entity.dao.ShowServicePrimaryDao;
import com.showFinder.showFinder.entity.service.ShowServiceImpl;
import com.showFinder.showFinder.model.data.ShowBasicInfo;
import com.showFinder.showFinder.model.data.ShowInfo;
import com.showFinder.showFinder.model.facade.GetFilteredShowsResp;
import com.showFinder.showFinder.model.facade.GetPopularShowResp;
import com.showFinder.showFinder.model.facade.GetShowInfoResp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ShowFinderApplicationTests {

	@MockBean
	private ShowServiceImpl showService;

	ShowBasicInfo showBasicInfo = new ShowBasicInfo(1, "A Quiet place", 2018);
	ShowBasicInfo showBasicInfo2 = new ShowBasicInfo(2, "A Quiet place 2", 2020);

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	@BeforeAll
	public void setUp(){
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public  void showInfo() throws Exception {
		ShowInfo show = new ShowInfo(1, "A Quiet place", 7.5, 2018, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, null);
		GetShowInfoResp getShowInfoResp= new GetShowInfoResp();
		getShowInfoResp.setShowNames(Arrays.asList(show));
		when(showService.findByShowId(any())).thenReturn(getShowInfoResp);
		mockMvc.perform(get("/showInfo/1")
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(content().json("{\"errorCode\":null,\"errorMessage\":null,\"showNames\":[{\"showId\":1,\"showName\":\"A Quiet place\",\"rating\":7.5,\"releaseyear\":2018,\"actionFlag\":false,\"comedyFlag\":false,\"documentaryFlag\":false,\"fantasyFlag\":false,\"horrorFlag\":false,\"musicalFlag\":false,\"romanceFlag\":false,\"sportFlag\":false,\"animationFlag\":false,\"crimeFlag\":false,\"historyFlag\":false,\"kidsFlag\":false,\"thrillerFlag\":true,\"scifiFlag\":true,\"warFlag\":false,\"realityFlag\":false,\"image\":null}]}"));
	}

	@Test
	public  void popularShows() throws Exception {
		GetPopularShowResp getPopularShowResp= new GetPopularShowResp();
		getPopularShowResp.setShowNames(Arrays.asList(showBasicInfo,showBasicInfo2));

		when(showService.findPopularShows()).thenReturn(getPopularShowResp); 
		mockMvc.perform(get("/showInfo/popular")
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andExpect(content().json("{\"errorCode\":null,\"errorMessage\":null,\"showNames\":[{\"showId\":1,\"showName\":\"A Quiet place\",\"releaseyear\":2018,\"image\":null},{\"showId\":2,\"showName\":\"A Quiet place 2\",\"releaseyear\":2020,\"image\":null}],\"pageCount\":null}"));

	}
	@Test
	public  void popularShowsByPageNo() throws Exception {
		GetPopularShowResp getPopularShowResp= new GetPopularShowResp();
		getPopularShowResp.setShowNames(Arrays.asList(showBasicInfo,showBasicInfo2));

		when(showService.findPopularShowsByPage(anyInt())).thenReturn(getPopularShowResp); 
		mockMvc.perform(get("/showInfo/popular/1")
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andExpect(content().json("{\"errorCode\":null,\"errorMessage\":null,\"showNames\":[{\"showId\":1,\"showName\":\"A Quiet place\",\"releaseyear\":2018,\"image\":null},{\"showId\":2,\"showName\":\"A Quiet place 2\",\"releaseyear\":2020,\"image\":null}],\"pageCount\":null}"));

	}

	@Test
	public  void filterShows() throws Exception {
		GetFilteredShowsResp getFilteredShowsResp= new GetFilteredShowsResp();
		getFilteredShowsResp.setShowInfoList(Arrays.asList(showBasicInfo,showBasicInfo2));

		when(showService.getFilteredShows(any())).thenReturn(getFilteredShowsResp);
		mockMvc.perform(get("/showInfo/filter?releaseyear=2011-2021&rating=1.0&genre=thriller,scifi&pageno=1")
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andExpect(content().json("{\"errorCode\":null,\"errorMessage\":null,\"showInfoList\":[{\"showId\":1,\"showName\":\"A Quiet place\",\"releaseyear\":2018,\"image\":null},{\"showId\":2,\"showName\":\"A Quiet place 2\",\"releaseyear\":2020,\"image\":null}],\"pageCount\":0}"));

	}


	@Test
	public  void searchShows() throws Exception {
		GetFilteredShowsResp getFilteredShowsResp= new GetFilteredShowsResp();
		getFilteredShowsResp.setShowInfoList(Arrays.asList(showBasicInfo,showBasicInfo2));

		when(showService.searchShows(any())).thenReturn(getFilteredShowsResp); 
		mockMvc.perform(get("/showInfo/search?search=a quiet")
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andExpect(content().json("{\"errorCode\":null,\"errorMessage\":null,\"showInfoList\":[{\"showId\":1,\"showName\":\"A Quiet place\",\"releaseyear\":2018,\"image\":null},{\"showId\":2,\"showName\":\"A Quiet place 2\",\"releaseyear\":2020,\"image\":null}],\"pageCount\":0}"));

	}

}
