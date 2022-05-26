package exam01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
	
	@Bean //xml을 대신하는 코드
	public BoardDAO dao() {
		return new BoardDAO();
	}
}
