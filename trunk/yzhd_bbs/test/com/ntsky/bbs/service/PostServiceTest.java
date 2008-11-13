package com.ntsky.bbs.service;

import java.util.Date;

import com.ntsky.bbs.domain.Post;

public class PostServiceTest extends NTskyServiceTestCase{

	private PostService postService;
	private Post post = new Post();
	
    protected void setUp() throws Exception {
    	//postService = (PostService)super.getBean("postService");
    }
	
	public void testCreatePost(){
		post.setTopicId(1);
		post.setForumId(1);
		post.setTitle("ntsky is very good!");
		post.setContent("I like ntsky and it's very good!");
		post.setUserId(1);
		post.setUsername("ntsky");
		post.setDateCreated(new Date());
		postService.createPost(post);
	}
	
	public void testEditPost(){
		postService.editPost(postService.getPost(1));
	}
	
	public void testGetPost(){
		//assertNull(postService.getPost(1));
	}
	
	public void testDeletePost(){
		postService.deletePost(1);
	}	
	
	public void setDown(){
		post = null;
		postService = null;
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(PostServiceTest.class);
	}		
	
}
