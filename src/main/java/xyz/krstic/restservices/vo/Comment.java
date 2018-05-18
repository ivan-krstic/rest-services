package xyz.krstic.restservices.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {

	private Integer postId;
	private Integer id;
	private String name;
	private String email;
	private String body;
	
}