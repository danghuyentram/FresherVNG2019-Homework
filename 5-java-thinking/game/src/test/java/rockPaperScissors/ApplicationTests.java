//package rockPaperScissors;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//public class ApplicationTests {
//
//	@Autowired
//	private MockMvc mvc;
//
//	@Test
//	public void contextLoads() {
//	}
//
//	private String oldToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyLzMiLCJ1c2VybmFtZSI6IjUxZWY2ZTI3ODVkYSIsInVzZXJpZCI6MywiZXhwIjoxNTY3NTY3MjI5fQ.sheRGRSNfyvxIIheqZccwU1gZUqeIp0ixfkFzwxMXbk";
//	private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkYW5naHV5ZW50cmFtIiwiZXhwIjoxNTY1ODc0Mjk1fQ.Pkzgoe8sWOCwsQSM8bCTn7mKZEWyART5fP06TWP9PKwB1XQ7EHxcp4cHs7ZMEJnSBeXUtTnZ63UMPnfoIsOtdw";
//	private String fakeToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ4eXoiLCJleHAiOjE1NjU4NzQ4NDJ9.zZvn4RWYA6izad9tFS2o6RhvmDQ_AoA7n0zObtCgmJNIpcT_RjIekevQrb-USr5-eRorvk3nfyFF7X8mn07ysw";
//
//
//	@Test
//	public void signup() throws Exception {
//
//		mvc.perform(post("/gamerps/signup")
//				.contentType("application/x-www-form-urlencoded")
//				.param("username", "")
//				.param("password", "abc"))
//				.andExpect(status().isBadRequest());
//
//
//		mvc.perform(post("/gamerps/signup")
//				.contentType("application/x-www-form-urlencoded")
//				.param("username", "abc")
//				.param("password", ""))
//				.andExpect(status().isBadRequest());
//
//		mvc.perform(post("/gamerps/signup")
//				.contentType("application/x-www-form-urlencoded")
//				.param("username", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
//				.param("password", "abc"))
//				.andExpect(status().isBadRequest());
//
//		mvc.perform(post("/gamerps/signup")
//				.contentType("application/x-www-form-urlencoded")
//				.param("username", "abc")
//				.param("password", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"))
//				.andExpect(status().isBadRequest());
//
//		mvc.perform(post("/gamerps/signup")
//				.contentType("application/x-www-form-urlencoded")
//				.param("username", "")
//				.param("password", ""))
//				.andExpect(status().isBadRequest());
//
//
//		mvc.perform(post("/gamerps/signup")
//				.contentType("application/x-www-form-urlencoded")
//				.param("username", "quiz02")
//				.param("password", "quiz02"))
//				.andExpect(status().isCreated());
//
//		mvc.perform(post("/gamerps/signup")
//				.contentType("application/x-www-form-urlencoded")
//				.param("username", "danghuyentram")
//				.param("password", "quiz01"))
//				.andExpect(status().isConflict());
//	}
//
//
//
//
//	@Test
//	public void createNewGame() throws Exception {
//		mvc.perform(get("/gamerps/newgame")
//				.contentType("application/x-www-form-urlencoded")
//				.header("authorization","Bearer "+fakeToken))
//				.andExpect(status().isForbidden());
//
//		mvc.perform(get("/gamerps/newgame")
//				.contentType("application/x-www-form-urlencoded")
//				.header("authorization","Bearer "+token))
//				.andExpect(status().isCreated());
//
//
//	}
//
//	@Test
//	public void createNewTurn() throws Exception {
//		mvc.perform(get("/gamerps/play?idgame=1&userstep=1")
//				.contentType("application/x-www-form-urlencoded")
//				.header("authorization","Bearer "+fakeToken))
//				.andExpect(status().isForbidden());
//
//		mvc.perform(get("/gamerps/play?idgame=5&userstep=1")
//				.contentType("application/x-www-form-urlencoded")
//				.header("authorization","Bearer "+token))
//				.andExpect(status().isBadRequest());
//
//
//
//		mvc.perform(get("/gamerps/play?idgame=1&userstep=1")
//				.contentType("application/x-www-form-urlencoded")
//				.header("authorization","Bearer "+token))
//				.andExpect(status().isBadRequest());
//
//		mvc.perform(get("/gamerps/play?idgame=1&userstep=1")
//				.contentType("application/x-www-form-urlencoded")
//				.header("authorization","Bearer "+token))
//				.andExpect(status().isBadRequest());
//
//
//		mvc.perform(get("/gamerps/play?idgame=1&userstep=3")
//				.contentType("application/x-www-form-urlencoded")
//				.header("authorization","Bearer "+token))
//				.andExpect(status().isBadRequest());
//
//
//
//	}
//
//
//	@Test
//	public void getHistory() throws Exception {
//		mvc.perform(get("/gamerps/history")
//				.contentType("application/x-www-form-urlencoded")
//				.header("authorization","Bearer "+fakeToken))
//				.andExpect(status().isForbidden());
//
//		mvc.perform(get("/gamerps/history")
//				.contentType("application/x-www-form-urlencoded")
//				.header("authorization","Bearer "+token))
//				.andExpect(status().isOk());
//	}
//
//	@Test
//	public void getRanking() throws Exception {
//		mvc.perform(get("/gamerps/top100")
//				.contentType("application/x-www-form-urlencoded")
//				.header("authorization","Bearer "+fakeToken))
//				.andExpect(status().isForbidden());
//
//		mvc.perform(get("/gamerps/top100")
//				.contentType("application/x-www-form-urlencoded")
//				.header("authorization","Bearer "+token))
//				.andExpect(status().isOk());
//	}
//
//
//}
