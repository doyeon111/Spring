package exam01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
	
	@Bean //xml�� ����ϴ� �ڵ�
	public BoardDAO dao() {
		return new BoardDAO();
	}
}
